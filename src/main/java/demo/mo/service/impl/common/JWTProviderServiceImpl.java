package demo.mo.service.impl.common;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import demo.mo.constants.Constant.LoginErrCode;
import demo.mo.exception.SystemException;
import demo.mo.model.UserInfo;
import demo.mo.service.common.AuthorityService;
import demo.mo.service.common.JWTProviderService;
import demo.mo.util.JacksonUtil;
import demo.mo.util.RequestContextUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTProviderServiceImpl implements JWTProviderService {

    // Signatureのエンコードに使うシークレットキー
    @Value("${jwt.token.secret-key}")
    private String secret;

    @Value("${jwt.token.claim.iss}")
    private String issUser;

    // expiration time
    @Value("${jwt.token.claim.exp:1800}")
    private long expiration;

    @Autowired
    private AuthorityService authorityService;

    /**
     * トークンの生成
     * @param userInfo
     * @return 生成したトークン
     */
    public String createToken(UserInfo userInfo) {
        try {
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            //String encryId = Base64Util.encode(userInfo.getStrUserID());
            HashMap<String, Object> map = new HashMap<>();
//            userInfo.setDiscernmentCode(authorityService.getPrivilegeCode(userInfo));
            userInfo.setDiscernmentCode("0078662");
            map.put("userInfo", JacksonUtil.bean2Json(userInfo));
            JwtBuilder jwtBuilder = Jwts.builder().setClaims(map);
            jwtBuilder.setIssuer(issUser).setSubject(userInfo.getUserID());
            if (expiration >= 0) {
                long expMillis = nowMillis + expiration * 1000;
                Date exp = new Date(expMillis);
                jwtBuilder.setIssuedAt(new Date());
                jwtBuilder.setExpiration(exp); //トークン有効期限
                jwtBuilder.setNotBefore(now); //トークンが有効になる時間
            }
            return jwtBuilder.signWith(SignatureAlgorithm.HS512, secret).compact();
        } catch (Exception e) {
            throw new SystemException(LoginErrCode.INPUT_OTHER_ERR);
        }
    }

    /**
     * ユーザ情報を取得する
     * @param token
     * @return ユーザ情報
     */
    public UserInfo getUserInfo(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String jwtIssuer = claims.getIssuer();
        if (!issUser.equals(jwtIssuer)) {
            throw new SystemException("Issuer検証エラー");
        }
        UserInfo userInfo = JacksonUtil.json2Bean(claims.get("userInfo").toString(), UserInfo.class);
        return userInfo;
    }

    /**
     * トークン刷新
     * @return トークン
     */
    public String getNewToken() {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //String encryId = Base64Util.encode(userInfo.getStrUserID());
        HashMap<String, Object> map = new HashMap<>();
        UserInfo userInfo = RequestContextUtil.getContext().getUserInfo();
        map.put("userInfo", JacksonUtil.bean2Json(userInfo));
        JwtBuilder jwtBuilder = Jwts.builder().setClaims(map);
        jwtBuilder.setIssuer(issUser).setSubject(userInfo.getUserID());
        if (expiration >= 0) {
            long expMillis = nowMillis + expiration * 1000;
            Date exp = new Date(expMillis);
            jwtBuilder.setIssuedAt(new Date());
            jwtBuilder.setExpiration(exp); //トークン有効期限
            jwtBuilder.setNotBefore(now); //トークンが有効になる時間
        }
        return jwtBuilder.signWith(SignatureAlgorithm.HS512, secret).compact();
    }

}
