package dao.provider;
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
                + "FROM OXX15X.DT_ON_LINE_CONTROL "
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
}
