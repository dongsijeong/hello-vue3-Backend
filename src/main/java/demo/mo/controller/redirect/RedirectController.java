package demo.mo.controller.redirect;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.mo.config.DataAccessConfig;
import demo.mo.constants.Constant.LoginErrCode;
import demo.mo.exception.SystemException;
import demo.mo.model.UserInfo;
import demo.mo.param.LoginParam;
import demo.mo.service.common.AuthorityService;
import demo.mo.service.common.JWTProviderService;
import demo.mo.util.LogUtil;
import demo.mo.util.StringCheck;
import demo.mo.util.StringUtil;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * リダイレクトコントロール
 * @author zchang4
 *
 */

@Controller
@RequestMapping("/v1/Redirect")
@Tag(name = "RedirectController", description = "リダイレクト機能")
public class RedirectController {

    @Autowired
    private JWTProviderService jwtProviderService;

    @Autowired
    private AuthorityService authorityService;
 
    /**
     * メニューにリダイレクトする。
     * @param req リクエスト
     * @param res
     * @throws IOException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String url = req.getRequestURI();
        try {
            LogUtil.outApplog("\t\t" + url + "\t\t");
            // ログイン区分
            String inoutCls = req.getParameter("inout_cls");
            UserInfo userInfo = new UserInfo();
            if ("1".equals(inoutCls)) {
                // ログイン種別
                userInfo.setKind(0);
            } else {
                // ログイン種別
                userInfo.setKind(1);

            }
            setLoginInfo(req, res, userInfo);
            String token = jwtProviderService.createToken(userInfo);
            String userId = null;
            if (userInfo.getKind() == 0) {
                userId = StringUtil.converNulltoEmpty(userInfo.getUserID());
            } else {
                userId = StringUtil.converNulltoEmpty(userInfo.getExternalUserCode());
            }
            res.sendRedirect(DataAccessConfig.getRedirecturl() + "?token=" + token);
            LogUtil.outApplog("\t\t" + url + "\t" + "正常," + String.valueOf(res.getStatus()) + "\t");
            LogUtil.outAccesslog(url + "\t" + userId + "\t" + userInfo.getDiscernmentCode() + "\t" + "ログイン：リダイレクト");
        } catch (SystemException ex) {
            String msg = ex.getMessage();
            String[] msgCode = msg.split("_");
            String errmode = msgCode[0];
            String mesCode = "";
            if (msgCode.length > 1) {
                mesCode = msgCode[1];
            }
            LogUtil.outAppErrlog("\t" + ""  + "\t" + url + "\t" + "" + "\t" + LogUtil.getStackTrace(ex));
            LogUtil.outAppErrlog("\t" + ""  + "\t" + url + "\t" + "" + "\t" + "認証エラー");
            LogUtil.outAccessErrlog(url + "\t"
                + StringUtil.converNulltoEmpty(req.getParameter("namecd")) + "\t" + "" + "\t" + "認証エラー");
            res.sendRedirect(DataAccessConfig.getRedirecturl()
                    + "#/authorityError?errmode=" + errmode + "&mescode=" + mesCode);
        } catch (Exception ex) {
            LogUtil.outAppErrlog("\t" + ""  + "\t" + url + "\t" + "" + "\t" + LogUtil.getStackTrace(ex));
            LogUtil.outAppErrlog("\t" + ""  + "\t" + url + "\t" + "" + "\t" + "認証エラー");
            LogUtil.outAccessErrlog(url + "\t"
                + StringUtil.converNulltoEmpty(req.getParameter("namecd")) + "\t" + "" + "\t" + "認証エラー");
            res.sendRedirect(DataAccessConfig.getRedirecturl() + "#/systemError");
        }
    }

    private void setLoginInfo(HttpServletRequest req, HttpServletResponse res, UserInfo userInfo) {
        // 運営会社コード（ＩＤ）
        String uneiCoCd = req.getParameter("id_unei_co_cd");
        // 氏名コード
        String namecd = req.getParameter("namecd");
        // 運営会社コード（所属）
        String syozokuUneiCoCd = req.getParameter("syozoku_unei_co_cd");
        // 組織コード
        String sosiki = req.getParameter("sosiki");
        // 組織グループコード
        String sosikiGrp = req.getParameter("sosiki_grp");
        // 職位コード
        String syokui = req.getParameter("syokui");
        // 職位セキュリティグループ区分
        String syokuiSec = req.getParameter("syokui_sec");
        //職位グループコード
        String syokuiGrp = req.getParameter("syokui_grp");
        //画面処理日
        String syoribi = req.getParameter("syoribi");
        //旧所属メニューフラグ
        String kyumenu = req.getParameter("kyumenu");
        //（新）氏名コード
        String newNamecd = req.getParameter("new_namecd");
        // メニューＩＤ
        String menuId = req.getParameter("menu_id");
        // PRiSM社コード
        String prismCoCd = req.getParameter("prism_co_cd");
        // 取引相手先コード
        String cstPrtCd = req.getParameter("cst_prt_cd");
        LoginParam param = null;
        if (userInfo.getKind() == 0) {
            //ログイン種別
            userInfo.setKind(0);
            // 運営会社コード（ＩＤ）
            userInfo.setUneiCoCd(uneiCoCd);
            // 運営会社コード（ＩＤ）
            userInfo.setSyozokuUneiCoCd(syozokuUneiCoCd);
            //ユーザーID
            if (!StringUtils.hasText(namecd)) {
//                throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.E_CM20001_20001,
//                        "氏名コード"));
                throw new SystemException(LoginErrCode.INPUT_USER_ERR);
            }
            userInfo.setUserID(namecd);
            param = new LoginParam();
            param.setNamecd(namecd);
            param.setEncryptKey(DataAccessConfig.getInEncryptKey());
//            authorityService.callGetUserName(param);
            //ユーザー名
//            userInfo.setUserName(param.getUserName());
            userInfo.setUserName("demo");
//            userInfo.setUserName("todo");
            // 組織コード
            userInfo.setSosiki(sosiki);
            // 組織セキュリティグループ区分
            userInfo.setSosikiSec("001");
            //ユーザーの組織グループコード
//            userInfo.setOrgCode(sosikiGrp);
            userInfo.setOrgCode("999999");
            if (!StringUtils.hasText(sosikiGrp)) {
//                throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.E_CM20001_20001,
//                        "組織グループコード"));
                throw new SystemException(LoginErrCode.INPUT_SYGREP_ERR);
            }
            param = new LoginParam();
            param.setSosikiSec("0001");
            param.setSosikiGrp(sosikiGrp);
//            authorityService.callGetOrganizationName(param);
//            //  ユーザーの所属する組織名
//            userInfo.setOrgName(param.getOrganizationName());
            userInfo.setOrgName("test");
//            userInfo.setOrgName("todo");
            // 職位コード
            userInfo.setSyokui(syokui);
            // 職位セキュリティグループ区分
            userInfo.setSyokuiSec("0001");
            //  職位グループコード
            userInfo.setPostCode(syokuiGrp);
            if (!StringUtils.hasText(syokuiGrp)) {
//                throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.E_CM20001_20001,
//                        "職位グループコード"));
                throw new SystemException(LoginErrCode.INPUT_SYOKUIGEP_ERR);
            }
            param = new LoginParam();
            param.setSyokuiSec("0001");
            param.setSyokuiGrp(syokuiGrp);
//            authorityService.callGetPostName(param);
            //  職位名
//            userInfo.setPostName(param.getPostName());
            userInfo.setPostName("test");
//            userInfo.setPostName("todo");
            // 画面処理日
            userInfo.setSyoribi(syoribi);
            // 旧所属メニューフラグ
            userInfo.setKyumenu(kyumenu);
            // （新）氏名コード
            userInfo.setNewNamecd(newNamecd);
            // メニューＩＤ
            userInfo.setMenuId(menuId);
            // PRiSM社コード
            userInfo.setPrismCoCd(prismCoCd);
            // 取引相手先コード
            userInfo.setCstPrtCd(cstPrtCd);
            // 利用者コード
            userInfo.setExternalUserCode("");
            // 取引者コード
            userInfo.setDealingsCode("");
            // 権限コード
            userInfo.setPrivCode("");
        } else {
            // 運営会社コード（ＩＤ）
            userInfo.setUneiCoCd(uneiCoCd);
            // 運営会社コード（ＩＤ）
            userInfo.setSyozokuUneiCoCd(syozokuUneiCoCd);
            //ユーザーID
//            userInfo.setUserID(namecd);
            // 利用者コード
            userInfo.setExternalUserCode(namecd);
            if (!StringUtils.hasText(namecd)) {
//                throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.E_CM20001_20001,
//                        "氏名コード"));
                throw new SystemException(LoginErrCode.INPUT_USER_ERR);
            }
            if (!StringCheck.checkLength(namecd, 7)) {
                throw new SystemException(LoginErrCode.INPUT_USER_LENGTH_ERR);
            }
            // 職位コード
            userInfo.setSyokui(syokui);
            // 権限コード
            userInfo.setPrivCode(syokui);
            if (!StringUtils.hasText(syokui)) {
//                throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.E_CM20001_20001,
//                        "職位コード"));
                throw new SystemException(LoginErrCode.INPUT_SYOKUI_ERR);
            }
            // 職位セキュリティグループ区分
            userInfo.setSyokuiSec(syokuiSec);
            //  職位グループコード
            userInfo.setPostCode(syokuiGrp);
            // 画面処理日
            userInfo.setSyoribi(syoribi);
            // 旧所属メニューフラグ
            userInfo.setKyumenu(kyumenu);
            // （新）氏名コード
            userInfo.setNewNamecd(newNamecd);
            // メニューＩＤ
            userInfo.setMenuId(menuId);
            // PRiSM社コード
            userInfo.setPrismCoCd(prismCoCd);
            // 取引相手先コード
            if (!StringUtils.hasText(cstPrtCd)) {
//                throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.E_CM20001_20001,
//                        "取引先コード"));
                throw new SystemException(LoginErrCode.INPUT_TORIHIK_ERR);
            }
            userInfo.setUserID("");
            userInfo.setUserName("");
            userInfo.setOrgCode("");
            userInfo.setOrgName("");
            userInfo.setPostCode("");
            userInfo.setPostName("");
            userInfo.setCstPrtCd(cstPrtCd);
            // 取引者コード
            userInfo.setDealingsCode(cstPrtCd);

        }
    }

}
