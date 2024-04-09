package demo.mo.dao.provider;
/**
 * 共通機能のDAO
 *
 **/
public class CommonDaoProvider {

    /**
     * オンライン時間コントロールデータ取得
     * @return オンライン時間コントロールデータ
     */
    public String getDtOnLineControl() {
        String sql = "SELECT GAMEN_ID, "
                + "KAISI_TM, "
                + "SYURYO_TM, "
                + "KYOTU_MOTO_GAMEN_ID "
                + "FROM DT_ON_LINE_CONTROL "
                + "ORDER BY GAMEN_ID, KAISI_TM, SYURYO_TM";
        return sql;
    }

    /**
     * 開始時間と終了時間を取得
     * @return 開始時間と終了時間
     */
    public String getOnLineTime() {
        String today = new java.sql.Timestamp(System.currentTimeMillis()).toString();
        String todayStr = today.substring(0, 4) + today.substring(5, 7) + today.substring(8, 10);
        String sql = "SELECT APP_SYUBETU, "
                + "YUKOKAISI_DT, "
                + "YUKOSYURYO_DT, "
                + "KAISI_TM, "
                + "SYURYO_TM "
                + "FROM DT_ONLINE_TIME "
                + "WHERE YUKOKAISI_DT<='" + todayStr
                + "' AND '" + todayStr + "'<=YUKOSYURYO_DT"
                + " AND APP_SYUBETU = 'xx'";
          return sql;
    }

    /**
     * プレイヤー毎機能表示制限データ取得
     * @param playerCd プレイヤーコード
     * @return プレイヤー毎機能表示制限データ
     */
    public String getDtPlayerKinoHyojiListByCd(String playerCd) {
        String sql = "SELECT GAMEN_ID, "
                + "PLAYER_CD "
                + "FROM DT_PLAYER_KINO_HYOJI "
                + "WHERE PLAYER_CD = #{playerCd} "
                + "ORDER BY PLAYER_CD, GAMEN_ID";
        return sql;
    }

    /**
     * プレイヤー毎タブ表示制限データ取得
     * @param playerCd プレイヤーコード
     * @return プレイヤー毎タブ表示制限データ
     */
    public String getDtPlayerTabHyojiListByCd(String playerCd) {
        String sql = "SELECT GAMEN_ID, "
                + "trim(TAB_ID) TAB_ID, "
                + "PLAYER_CD "
                + "FROM DT_PLAYER_TAB_HYOJI "
                + "WHERE PLAYER_CD = #{playerCd} "
                + "ORDER BY PLAYER_CD, GAMEN_ID, TAB_ID";
        return sql;
    }

    /**
     * プレイヤーセキュリティデータ（社内）情報を取得
     * @param userId ユーザーID
     * @return プレイヤーセキュリティデータ（社内）検索SQL
     */
    public String getDtPlayerSecuritynByuserId(String userId) {
        String sql = "SELECT PLAYER_CD "
                + "FROM DT_PLAYER_SECURITY_N "
                + "WHERE UPPER(SIMEI_CD) = #{userId} limit 1";
        return sql;
    }

    /**
     * プレイヤーセキュリティデータ（社内）情報を取得
     * @param userId
     * @param sosikiCD
     * @param syokuiCD
     * @return プレイヤーセキュリティデータ（社内）検索SQL
     */
    public String getDtPlayerSecurityn(String userId, String sosikiCD, String syokuiCD) {
        String sql = "SELECT PLAYER_CD "
                + "FROM DT_PLAYER_SECURITY_N "
                + "WHERE SIMEI_CD = #{userId} "
                + "AND UPPER(SYOKUI_CD) = #{syokuiCD} "
                + "AND UPPER(SOSIKI_CD) = #{sosikiCD} limit 1";
        return sql;
    }

    /**
     * プレイヤーセキュリティデータ（社外）情報を取得
     * @param torihikisakiCd
     * @param riyosyaCd
     * @return プレイヤーセキュリティデータ（社外）情報を取得
     */
    public String getDtPlayerSecurityG(String torihikisakiCd, String riyosyaCd) {
        String sql = "SELECT PLAYER_CD "
                + "FROM DT_PLAYER_SECURITY_G "
                + "WHERE UPPER(TORIHIKISAKI_CD) = #{torihikisakiCd} "
                + "AND UPPER(SEIGEN_CD) = #{riyosyaCd} limit 1 ";
        return sql;
    }

    /**
     * プレイヤーセキュリティデータ（社外）情報を取得
     * @param torihikisakiCd
     * @param riyosyaCd
     * @return プレイヤーセキュリティデータ（社外）情報を取得
     */
    public String getDtPlayerSecurityG2(String torihikisakiCd, String riyosyaCd) {
        String sql = "SELECT PLAYER_CD "
                + "FROM OXX15X.DT_PLAYER_SECURITY_G "
                + "WHERE TORIHIKISAKI_CD LIKE concat(#{torihikisakiCd},'%') "
                + "AND UPPER(SEIGEN_CD) = #{riyosyaCd} limit 1 ";
        return sql;
    }
}
