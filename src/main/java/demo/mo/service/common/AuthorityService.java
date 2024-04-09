package demo.mo.service.common;

import java.util.List;

import demo.mo.model.AuthorityModel;
import demo.mo.model.UserInfo;
import demo.mo.param.LoginParam;

/**
 * 権限管理サービス
 * @author zchang4
 *
 */
public interface AuthorityService {

    /**
     * 権限リストを取得する
     * @return 権限情報
     */
    List<AuthorityModel> getAuthorityInfo();

    /**
     * プレイヤーコード取得
     * @param userInfo ユーザー情報
     * @return プレイヤーコード
     */
    String getPrivilegeCode(UserInfo userInfo);

    /**
     * 氏名
     * @param param
     */
    void callGetUserName(LoginParam param);

    /**
     * 組織名
     * @param param
     */
    void callGetOrganizationName(LoginParam param);

    /**
     * 職位名
     * @param param
     */
    void callGetPostName(LoginParam param);
}
