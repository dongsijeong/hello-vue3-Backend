package demo.co.util;

import java.util.List;

import demo.co.config.DataAccessConfig;
import demo.co.config.RestApiAuthInfoManager;
import demo.co.model.AccesInfo;
import demo.co.model.ApiDetailInfo;

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
