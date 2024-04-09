package demo.mo.util;

import java.util.List;

import demo.mo.config.DataAccessConfig;
import demo.mo.config.RestApiAuthInfoManager;
import demo.mo.model.AccesInfo;
import demo.mo.model.ApiDetailInfo;

/**
 * API認可情報チェック機能
 * @author zchang4
 *
 */
public final class RestApiAuthCheckManager {

    private RestApiAuthCheckManager() {

    }

    /**
     * API認可情報チェック
     * @param apiPath APIパス
     * @return チェック結果
     */
    public static boolean checkApiAuth(String apiPath) {
        if (!DataAccessConfig.getApicheck()) {
            return true;
        }
        ApiDetailInfo apiInfo = RestApiAuthInfoManager.getApiDetailInfo(apiPath);
        if (apiInfo == null) {
            return false;
        }
        if (apiInfo.isAllAccess()) {
            return true;
        }
        String playCode = UserInfoManager.getDiscernmentCode();
        for (String gamenId : apiInfo.getAccessMap().keySet()) {
            AccesInfo accesInfo = apiInfo.getAccessMap().get(gamenId);
//            if (accesInfo.isNoCheckCtl()) {
//                if (checkGamenId(gamenId, playCode)) {
//                    return true;
//                }
//            }
//            if (accesInfo.isAllAccesCtl()) {
//                if (checkAllAccesCtl(gamenId, playCode)) {
//                    return true;
//                }
//            } else {
//                if (checkAccesCtl(gamenId, playCode, accesInfo)) {
//                    return true;
//                }
//            }
        }
        return false;
    }

}
