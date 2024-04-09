package demo.mo.service.common;

import demo.mo.model.UserInfo;

/**
 * jWT トークン生成のサービス
 * @author zchang4
 * @since: 2021-05-31
 */
public interface JWTProviderService {

    /**
     * トークンの生成
     * @param userInfo
     * @return 生成したトークン
     */
    String createToken(UserInfo userInfo);

    /**
     * ユーザ情報を取得する
     * @param token
     * @return ユーザ情報
     */
    UserInfo getUserInfo(String token);

    /**
     * トークン刷新
     * @return トークン
     */
    String getNewToken();
}
