package util;

import java.util.List;

import jp.co.lawson.mo.config.AuthorityConfig;
import jp.co.lawson.mo.config.DataAccessConfig;
import jp.co.lawson.mo.config.RestApiAuthInfoManager;
import jp.co.lawson.mo.exception.SystemException;
import jp.co.lawson.mo.model.AccesInfo;
import jp.co.lawson.mo.model.ApiDetailInfo;

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
            if (accesInfo.isNoCheckCtl()) {
                if (checkGamenId(gamenId, playCode)) {
                    return true;
                }
            }
            if (accesInfo.isAllAccesCtl()) {
                if (checkAllAccesCtl(gamenId, playCode)) {
                    return true;
                }
            } else {
                if (checkAccesCtl(gamenId, playCode, accesInfo)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkGamenId(String gamenId, String playCode) {
//        List<String> gamenIdList = AuthorityConfig.getGamenIdLidt(playCode).
//                stream().map(id ->
//                     id.toLowerCase()
//                    ).collect(Collectors.toList());
        List<String> gamenIdList = AuthorityConfig.getGamenIdLidt(playCode);
        if (gamenIdList.contains(gamenId.toLowerCase())) {
            return true;
        }
        return false;
    }

    private static boolean checkAllAccesCtl(String gamenId, String playCode) {
        List<String> tabIdList = AuthorityConfig.getTabIdList(gamenId, playCode);
        if (tabIdList.isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean checkAccesCtl(String gamenId, String playCode, AccesInfo accesInfo) {
//        List<String> tabIdList = AuthorityConfig.getTabIdList(gamenId, playCode)
//                .stream().map( id -> id.toLowerCase()).collect(Collectors.toList());
        List<String> tabIdList = AuthorityConfig.getTabIdList(gamenId, playCode);
        List<String> ctlIdList = accesInfo.getControlidList();
        for (String ctlId : ctlIdList) {
            if (tabIdList.contains(ctlId.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 指定編集権限あるかチェックする。
     * @param gamenID
     * @param tabId
     */
    public static void checkEditType(String gamenID, String tabId) {
        String playCode = UserInfoManager.getDiscernmentCode();
        List<String> tabIdList = AuthorityConfig.getTabIdList(gamenID, playCode);
        if (!tabIdList.contains(tabId.toLowerCase())) {
            throw new SystemException(DataAccessConfig.getMessage("DE_CM0006.10006"));
        }
    }
}
