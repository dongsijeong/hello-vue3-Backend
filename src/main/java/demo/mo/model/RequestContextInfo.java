package demo.mo.model;

import lombok.Data;

@Data
public class RequestContextInfo {
    // 内部認証token
    private String authToken;

    // ユーザ情報
    private UserInfo userInfo;

    /**
     * 初期化メソッド
     * 
     * @param pAuthToken
     */
    public RequestContextInfo(final String pAuthToken) {
        this.authToken = pAuthToken;
    }

    /**
     * 初期化メソッド
     * 
     * @param pAuthToken
     * @param pUserInfo
     */
    public RequestContextInfo(final String pAuthToken, final UserInfo pUserInfo) {
        this.authToken = pAuthToken;
        this.userInfo = pUserInfo;
    }

}
