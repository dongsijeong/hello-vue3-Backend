package demo.co.interceptor;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo.co.config.DataAccessConfig;
import demo.co.constants.Constant.MESSAGECODE;
import demo.co.constants.Constant.TOKEN;
import demo.co.model.ActionContextInfo;
import demo.co.model.ApiResponseModel;
import demo.co.model.RequestContextInfo;
import demo.co.model.StatusContextInfo;
import demo.co.model.UserInfo;
import demo.co.service.common.CommonService;
import demo.co.service.common.JWTProviderService;
import demo.co.util.ActionContextUtil;
import demo.co.util.ActionInfoManger;
import demo.co.util.CommonUtil;
import demo.co.util.LogUtil;
import demo.co.util.RequestContextUtil;
import demo.co.util.RestApiAuthCheckManager;
import demo.co.util.StatusContextUtil;
import demo.co.util.StringUtil;
import io.jsonwebtoken.ExpiredJwtException;


public class JwtAuthinInterceptor implements HandlerInterceptor {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JWTProviderService jwtProviderService;

    @Autowired
    private CommonService commonService;

    /**
     * リクエストの事前処理
     * @param request
     * @param response
     * @param handler
     * @return 処理するかどうか結果
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String emgMsg = CommonUtil.getEmgMessage();
        if (emgMsg != null) {
            setInfoResponse(response, emgMsg);
            return false;
        } else {
            if (!commonService.checkOnline()) {
                setInfoResponseNoHeader(response, commonService.getOnlinInfo());
                return false;
            }
        }

        String url =  request.getRequestURI();
        String actionId = StringUtil.converNulltoEmpty(request.getHeader("ActionId"));
        if (actionId.toUpperCase().equals("UNDEFINED") || actionId.toUpperCase().equals("NULL")) {
            actionId = "";
        }
        String gamenName = ActionInfoManger.getGamenInfo(actionId.toLowerCase());
        String actionName = StringUtil.converNulltoEmpty(request.getHeader("ActionName"));
        LogUtil.outApplog(actionId + "\t" + gamenName  + "\t" + url + "\t" + "開始" + "\t");
        String authToken = request.getHeader(TOKEN.AUTH_TOKEN_HEADER);
        if (!StringUtils.hasText(authToken)) {
            setResponse(response, "トークン認証エラー:資格情報ありません。");
            LogUtil.outAppErrlog(actionId + "\t" + gamenName  + "\t" + url + "\t" + "" + "\t" + "トークン認証エラー:資格情報ありません。");
            return false;

        }
        try {
            UserInfo userInfo = jwtProviderService.getUserInfo(authToken);
            String userId = StringUtil.converNulltoEmpty(userInfo.getUserID());
//            if (userInfo.getKind() == 0) {
//                userId = userInfo.getUserID();
//            } else {
//                userId = userInfo.getDealingsCode();
//            }
            String actionInfo = ActionInfoManger.getActionInfo(actionName);
            LogUtil.outAccesslog(url + "\t" + userId + "\t" + userInfo.getDiscernmentCode() + "\t" + actionInfo);
            RequestContextUtil.setContext(new RequestContextInfo(authToken, userInfo));
            String cheecurl = "";
            int index = url.indexOf("/v1");
            cheecurl = url.substring(index);
            if (!RestApiAuthCheckManager.checkApiAuth(cheecurl)) {
                setResponse(response, "restAPI不正アクセス");
                LogUtil.outAppErrlog(actionId + "\t" + gamenName  + "\t" + url + "\t" + "" + "\t" + "restAPI不正アクセス。");
                return false;
            }
            ActionContextUtil.setContext(new ActionContextInfo(url, actionName));
        } catch (ExpiredJwtException  eje) {
            setResponse(response, DataAccessConfig.getMessage(MESSAGECODE.E_CM8015_8015));
            LogUtil.outAppErrlog(actionId + "\t" + gamenName  + "\t" + url + "\t" + ""
                    + "\t" + DataAccessConfig.getMessage(MESSAGECODE.E_CM8015_8015));
            return false;
        } catch (Exception e) {
            setResponse(response, "トークン解析エラー");
            LogUtil.outAppErrlog(actionId + "\t" + gamenName  + "\t" + url + "\t" + "" + "\t" + "トークン解析エラー。");
            return false;
        }

        return true;
    }

    /**
     * リスポンスの設定
     * @param response
     * @param message
     * @throws JsonProcessingException
     * @throws IOException
     */
    private void setInfoResponse(HttpServletResponse response, String message)
            throws JsonProcessingException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        ApiResponseModel<String> responseModel = new ApiResponseModel<String>();
        responseModel.code(HttpStatus.CONFLICT.value());
        responseModel.message(message);
        responseModel.data(Collections.emptyList());
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(objectMapper.writeValueAsString(responseModel));
    }

    /**
     * リスポンスの設定
     * @param response
     * @param message
     * @throws JsonProcessingException
     * @throws IOException
     */
    private void setInfoResponseNoHeader(HttpServletResponse response, String message)
            throws JsonProcessingException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        ApiResponseModel<String> responseModel = new ApiResponseModel<String>();
        responseModel.code(HttpStatus.GONE.value());
        responseModel.message(message);
        responseModel.data(Collections.emptyList());
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(objectMapper.writeValueAsString(responseModel));
    }

    /**
     * リスポンスの設定
     * @param response
     * @param message
     * @throws JsonProcessingException
     * @throws IOException
     */
    private void setResponse(HttpServletResponse response, String message)
            throws JsonProcessingException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        ApiResponseModel<String> responseModel = new ApiResponseModel<String>();
        responseModel.code(HttpStatus.UNAUTHORIZED.value());
        responseModel.message(message);
        responseModel.data(Collections.emptyList());
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(objectMapper.writeValueAsString(responseModel));
    }

    /**
     * 処理後の処理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {
        String status = String.valueOf(response.getStatus());
        String actionId = StringUtil.converNulltoEmpty(request.getHeader("ActionId"));
        if (actionId.toUpperCase().equals("UNDEFINED") || actionId.toUpperCase().equals("NULL")) {
            actionId = "";
        }
        String gamenName = ActionInfoManger.getGamenInfo(actionId.toLowerCase());
        String uri = request.getRequestURI();
        StatusContextInfo info = StatusContextUtil.getContext();
        if (info == null) {
            LogUtil.outApplog(actionId + "\t" + gamenName + "\t" + uri +  "\t" + "正常," +  status + "\t");
        } else {
            LogUtil.outApplog(actionId + "\t" + gamenName + "\t" + uri +  "\t" + info.getResult() + ","
        +  info.getStatus() + "\t");
        }
        RequestContextUtil.removeContext();
        ActionContextUtil.removeContext();
        StatusContextUtil.removeContext();
    }

}
