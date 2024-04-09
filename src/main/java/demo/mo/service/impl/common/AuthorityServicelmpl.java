package demo.mo.service.impl.common;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import demo.mo.config.DataAccessConfig;
import demo.mo.constants.Constant.LoginErrCode;
import demo.mo.dao.CommonDao;
import demo.mo.exception.SystemException;
import demo.mo.model.AuthorityModel;
import demo.mo.model.DtPlayerKinoHyojiModel;
import demo.mo.model.DtPlayerTabHyojiModel;
import demo.mo.model.UserInfo;
import demo.mo.param.LoginParam;
import demo.mo.service.common.AuthorityService;
import demo.mo.util.LogUtil;
import demo.mo.util.UserInfoManager;

@Service
@Transactional(readOnly = false, isolation = Isolation.DEFAULT,
rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class AuthorityServicelmpl implements AuthorityService {

    private final int laswons = 0; // 社内

    @SuppressWarnings("unused")
    private final int others = 1; // 社外

    private String[] strLeftButtonHeader = {"LMV", "LMR", "LMC", "LMP", "LRO", "LRR", "LRS", "LRU", "LPL", "LSP",
            "LGN", "LSM", "LMT" };

     // 各コード桁数
      // 氏名コード
      private  final int sizeUserId            = 7;
      // 組織コード
      private  final int sizeOrganizationCode  = 8;
      // 職位コード
      private  final int sizePostCode          = 8;
      // 利用者コード
      private  final int sizeUserCd            = 7;
      // 権限コード
      private  final int sizePrivCd            = 3;
      // 取引先コード
      private  final int sizeCustomerCode      = 6;

    // DB ユーザーID省略値
      private  final String defSimeiid        = "-------";
      // DB 組織コード省略値
      @SuppressWarnings("unused")
    private  final String defSosikicode     = "--------";
      // DB 職位コード省略値
      private  final String defSyokuicode     = "--------";
      // DB 利用者コード省略値
      @SuppressWarnings("unused")
    private  final String defRiyousyacode   = "-------";
      // DB 権限コード省略値
      private  final String defSeigencode     = "---";

    @Autowired
    private CommonDao commonDao;

    private boolean isLgnExist(List<String> gamenIdList) {
        boolean result = false;
        for (String id : gamenIdList) {
            if (id.toLowerCase().startsWith("lgn")) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * タブIDリストを取得
     * @param gamenId
     * @param playerCode
     * @param tabHyojiModelList
     *
     * @return タブIDリスト
     */
    private List<String> getTabIdList(String gamenId, String playerCode,
            List<DtPlayerTabHyojiModel> tabHyojiModelList) {
        List<String> tabIdList = new ArrayList<>();
        for (DtPlayerTabHyojiModel model : tabHyojiModelList) {
            if (model.getGamenId().equalsIgnoreCase(gamenId) && model.getPlayerCd().equalsIgnoreCase(playerCode)) {
                tabIdList.add(model.getTabId());
            }
        }
        return tabIdList;
    }
    /**
     * 権限リストを取得する
     *
     * @return 権限情報
     */
    @Override
    public List<AuthorityModel> getAuthorityInfo() {
        String playerCode = UserInfoManager.getDiscernmentCode();
        List<DtPlayerKinoHyojiModel> dtPlayerKinoHyojiList = commonDao.getDtPlayerKinoHyojiListByCd(playerCode);
        List<String> gamenIdList = dtPlayerKinoHyojiList.stream().map(item -> {
            return item.getGamenId();
        }).collect(Collectors.toList());
        List<DtPlayerTabHyojiModel> tabIdList = commonDao.getDtPlayerTabHyojiListByCd(playerCode);
        List<AuthorityModel> authorityInfoList = new ArrayList<>();
        AuthorityModel model = null;
        for (String gamenId : gamenIdList) {
            String mainMenuCode = "";
            if (isLgnExist(gamenIdList) || gamenId.toLowerCase().equals("lcu0107e")) {
                mainMenuCode = "LGN";
            }
            for (int i = 0; i < strLeftButtonHeader.length; i++) {
                if (gamenId.toLowerCase().startsWith(strLeftButtonHeader[i].toLowerCase())) {
                    mainMenuCode = strLeftButtonHeader[i];
                    break;
                }
            }
            List<String> tabIdLidt = getTabIdList(gamenId, playerCode, tabIdList);
            if (tabIdLidt.isEmpty()) {
                model = new AuthorityModel();
                model.setMainMenuCode(mainMenuCode);
                model.setSubMenuCode(gamenId);
                model.setControlCode("");
                authorityInfoList.add(model);
            } else {
                for (String tabId : tabIdLidt) {
                    model = new AuthorityModel();
                    model.setMainMenuCode(mainMenuCode);
                    model.setSubMenuCode(gamenId);
                    model.setControlCode(tabId.trim());
                    authorityInfoList.add(model);
                }
            }
        }
        return authorityInfoList;
    }

    /**
     *
     * @param userInfo
     * @return コードを返す
     */
    public String getPrivilegeCode(UserInfo userInfo) {
        int kind = userInfo.getKind();
        String playerCode = null;
        if (kind == laswons) {
            String userID = userInfo.getUserID();
            // ユーザー名
            String userName = userInfo.getUserName();
            // ユーザーの所属する組織コード
            String orgCode = userInfo.getOrgCode();
            // ユーザーの所属する組織名
            String orgName = userInfo.getOrgName();
            // 職位コード
            String postCode = userInfo.getPostCode();
            // 職位名
            String postName = userInfo.getPostName();

            if ((null == userID)   || (userID.equals(""))
                    || (null == userName) || (userName.equals(""))
                    || (null == orgCode)  || (orgCode.equals(""))
                    || (null == orgName)  || (orgName.equals(""))
                    || (null == postCode) || (postCode.equals(""))
                    || (null == postName) || (postName.equals(""))) {

                throw new SystemException(DataAccessConfig.getMessage("E_CM22001.22001"));
            }
            // 規定の桁数に足りないコードは先頭をゼロ埋めする
            // ユーザーID ･･･ 7桁
            userID = appendZero(userID, sizeUserId);
            // 組織コード ･･･ 8桁
            orgCode = appendZero(orgCode, sizeOrganizationCode);
            // 職位コード ･･･ 8桁
            postCode = appendZero(postCode, sizePostCode);
            userInfo.setUserID(userID);
            userInfo.setOrgCode(orgCode);
            userInfo.setPostCode(postCode);
            playerCode = commonDao.getDtPlayerSecuritynByuserId(userID.toUpperCase());
            if (!StringUtils.hasText(playerCode)) {
                playerCode = commonDao.getDtPlayerSecurityn(defSimeiid, orgCode.toUpperCase(), postCode.toUpperCase());
                if (!StringUtils.hasText(playerCode)) {
                    playerCode = commonDao.getDtPlayerSecurityn(defSimeiid, orgCode.toUpperCase(), defSyokuicode);
                }
                if (!StringUtils.hasText(playerCode)) {
                    throw new SystemException(DataAccessConfig.getMessage("E_CM22002.22002"));
                }
            }

        } else {
             //  利用者コード
              String externalUserCode = userInfo.getExternalUserCode();
              //  取引先コード
              String customerCode = userInfo.getDealingsCode();
              //  権限コード
              String privilegeCode = userInfo.getPrivCode();
              if ((null == externalUserCode) || (externalUserCode.equals(""))
                        || (null == customerCode)     || (customerCode.equals(""))
                        || (null == privilegeCode)    || (privilegeCode.equals(""))) {
                  throw new SystemException(DataAccessConfig.getMessage("E_CM22001.22001"));

              }
              // 利用者コード ･･･ 7桁
              externalUserCode = appendZero(externalUserCode, sizeUserCd);
              // 取引先コード ･･･ 6桁
              customerCode = appendZero(customerCode, sizeCustomerCode);
              // 権限コード ･･･ 3桁
              privilegeCode = appendZero(privilegeCode, sizePrivCd);
              userInfo.setExternalUserCode(externalUserCode);
              userInfo.setDealingsCode(customerCode);
              userInfo.setPrivCode(privilegeCode);
              playerCode = commonDao.getDtPlayerSecurityG(customerCode.toUpperCase(), privilegeCode.toUpperCase());
              if (!StringUtils.hasText(playerCode)) {
                  playerCode = commonDao.getDtPlayerSecurityG(customerCode, defSeigencode);
                  if (!StringUtils.hasText(playerCode)) {
                      String strPreTorihikisakiCD = customerCode.substring(0, 2);
                      playerCode = commonDao.getDtPlayerSecurityG2(
                        strPreTorihikisakiCD.toUpperCase(), privilegeCode.toUpperCase());
                  }
                  if (!StringUtils.hasText(playerCode)) {
                      throw new SystemException(DataAccessConfig.getMessage("E_CM22002.22002"));
                  }
              }
        }
        return playerCode;
    }

    @SuppressWarnings("unused")
    private String getPrivilegeCode() {
        int kind = UserInfoManager.getKind();
        String playerCode = null;
        if (kind == laswons) {
            String userID = UserInfoManager.getKousinID();
            // ユーザー名
            String userName = UserInfoManager.getUserName();
            // ユーザーの所属する組織コード
            String orgCode = UserInfoManager.getOrgCode();
            // ユーザーの所属する組織名
            String orgName = UserInfoManager.getOrgName();
            // 職位コード
            String postCode = UserInfoManager.getPostCode();
            // 職位名
            String postName = UserInfoManager.getPostName();

            if ((null == userID)   || (userID.equals(""))
                    || (null == userName) || (userName.equals(""))
                    || (null == orgCode)  || (orgCode.equals(""))
                    || (null == orgName)  || (orgName.equals(""))
                    || (null == postCode) || (postCode.equals(""))
                    || (null == postName) || (postName.equals(""))) {

                throw new SystemException(DataAccessConfig.getMessage("E_CM22001.22001"));
            }
            // 規定の桁数に足りないコードは先頭をゼロ埋めする
            // ユーザーID ･･･ 7桁
            userID = appendZero(userID, sizeUserId);
            // 組織コード ･･･ 8桁
            orgCode = appendZero(orgCode, sizeOrganizationCode);
            // 職位コード ･･･ 8桁
            postCode = appendZero(postCode, sizePostCode);
            playerCode = commonDao.getDtPlayerSecuritynByuserId(userID.toUpperCase());
            if (!StringUtils.hasText(playerCode)) {
                playerCode = commonDao.getDtPlayerSecurityn(defSimeiid, orgCode.toUpperCase(), postCode.toUpperCase());
                if (!StringUtils.hasText(playerCode)) {
                    playerCode = commonDao.getDtPlayerSecurityn(defSimeiid, orgCode, defSyokuicode);
                }
                if (!StringUtils.hasText(playerCode)) {
                    throw new SystemException(DataAccessConfig.getMessage("E_CM22002.22002"));
                }
            }

        } else {
             //  利用者コード
              String externalUserCode = UserInfoManager.getExternalUserCode();
              //  取引先コード
              String customerCode = UserInfoManager.getDealingsCode();
              //  権限コード
              String privilegeCode = UserInfoManager.getPrivCode();
              if ((null == externalUserCode) || (externalUserCode.equals(""))
                        || (null == customerCode)     || (customerCode.equals(""))
                        || (null == privilegeCode)    || (privilegeCode.equals(""))) {
                  throw new SystemException(DataAccessConfig.getMessage("E_CM22001.22001"));

              }
              // 利用者コード ･･･ 7桁
              externalUserCode = appendZero(externalUserCode, sizeUserCd);
              // 取引先コード ･･･ 6桁
              customerCode = appendZero(customerCode, sizeCustomerCode);
              // 権限コード ･･･ 3桁
              privilegeCode = appendZero(privilegeCode, sizePrivCd);
              playerCode = commonDao.getDtPlayerSecurityG(customerCode.toUpperCase(), privilegeCode.toUpperCase());
              if (!StringUtils.hasText(playerCode)) {
                  playerCode = commonDao.getDtPlayerSecurityG(customerCode, defSeigencode);
                  if (!StringUtils.hasText(playerCode)) {
                      String strPreTorihikisakiCD = customerCode.substring(0, 2);
                      playerCode = commonDao.getDtPlayerSecurityG(
                        strPreTorihikisakiCD.toUpperCase(), privilegeCode.toUpperCase());
                  }
                  if (!StringUtils.hasText(playerCode)) {
                      throw new SystemException(DataAccessConfig.getMessage("E_CM22002.22002"));
                  }
              }
        }
        return playerCode;
    }

    /**
     * 【文字列ユーティリティメソッド（先頭ゼロ埋め）】<BR>
     * 指定された文字列を、指定された桁数にサイズ整形する。 文字列の先頭のゼロ埋めを行う。<BR>
     *
     * @param strCode サイズ整形を行う文字列
     * @param iSize   サイズ
     * @return サイズ整形を行った文字列
     * @exception @since 1.00
     */
    private static String appendZero(String strCode, int iSize) {

        String code = strCode;

        // 0埋め
        if (strCode.length() < iSize) {

            StringBuffer zero = new StringBuffer("");

            for (int i = 0; i < (iSize - strCode.length()); i++) {
                zero.append("0");
            }

            code = zero + strCode;
        }

        return code;

    }

    /**
     * 氏名
     * @param param
     */
    public void callGetUserName(LoginParam param) {
//        commonDao.callGetUserName(param);
        if (!"00".equals(param.getStatus())) {
            LogUtil.outApplog("\t\t" + "----氏名-ステータス--" +  param.getStatus() + "\t\t");
            throw new SystemException(LoginErrCode.GET_USER_ERR + "_" + param.getStatus());
        }
    }

    /**
     * 組織名
     * @param param
     */
    public void callGetOrganizationName(LoginParam param) {
//        commonDao.callGetOrganizationName(param);
        if (!"00".equals(param.getStatus())) {
            LogUtil.outApplog("\t\t" + "----組織名-ステータス--" +  param.getStatus() + "\t\t");
            throw new SystemException(LoginErrCode.GET_SOSIK_ERR + "_" + param.getStatus());
        }
    }

    /**
     * 職位名
     * @param param
     */
    public void callGetPostName(LoginParam param) {
//        commonDao.callGetPostName(param);
        if (!"00".equals(param.getStatus())) {
            LogUtil.outApplog("\t\t" + "----職位名-ステータス--" +  param.getStatus() + "\t\t");
            throw new SystemException(LoginErrCode.GET_SYOKUI_ERR + "_" + param.getStatus());
        }
    }
}
