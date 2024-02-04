package util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import jp.co.lawson.mo.constants.Constant.MESSAGECODE;
import jp.co.lawson.mo.model.MessageModel;

/**
 * 各種マスター管理のプレイヤー制限処理関数<br>
 *
 * @author Lv.Jieqiang
 */
public final class LmvGenManager {
    private LmvGenManager() {
    }
    /**
     * 有効開始日
     */
    static final boolean KAISI = true;

    /**
     * 有効終了日
     */
    static final boolean SYURYO = false;

    /**
     * 翌日の日付を取得する
     * @param strToday
     * @return 翌日の日付
     */
    public static String getNextDay(String strToday) {
        Calendar cal = Calendar.getInstance();
        cal.set(getYear(strToday), getMonth(strToday), getDay(strToday));
        cal.add(Calendar.DATE, 1);

        String strYear = String.valueOf(cal.get(Calendar.YEAR));
        String strMonth = fillZero(String.valueOf(cal.get(Calendar.MONTH) + 1), 2);
        String strDate = fillZero(String.valueOf(cal.get(Calendar.DATE)), 2);
        return strYear + strMonth + strDate;
    }

    private static int getYear(String date) {
        if (date == null || date.equals("")) {
            return 0;
        }
        return Integer.parseInt(date.substring(0, 4));
    }

    private static int getMonth(String date) {
        if (date == null || date.equals("")) {
            return 0;
        }
        return Integer.parseInt(date.substring(4, 6)) - 1;
    }

    private static int getDay(String date) {
        if (date == null || date.equals("")) {
            return 0;
        }
        return Integer.parseInt(date.substring(6, 8));
    }

    private static String fillZero(String str, int length) {
        // 短い文字列を任意の文字長になるように、文字列の左に'0'を埋める
        for (int i = 0; i < (length - str.length()); i++) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * 日付を比較する
     * @param    strDateA    日付1
     * @param    strDateB    日付2
     * @return    dateA < dateB の場合    -n
     *             dateA = dateB の場合は    0
     *             dateA > dateB の場合は    n
     *             (n は2つの日付の差)
     */
    public static long dateComp(String strDateA, String strDateB) {
        GregorianCalendar gc1 = new GregorianCalendar(
            getYear(strDateA),
            getMonth(strDateA),
            getDay(strDateA));
        GregorianCalendar gc2 = new GregorianCalendar(
            getYear(strDateB),
            getMonth(strDateB),
            getDay(strDateB));
        // 日付が同じ場合は 0 を返す
        if (gc1.after(gc2)) {
            return 1;
        } else if (gc1.before(gc2)) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 先日の日付を取得する
     * @param strToday
     * @return 先日の日付
     */
    public static String getPrevDay(String strToday) {
        Calendar cal = Calendar.getInstance();
        cal.set(getYear(strToday), getMonth(strToday), getDay(strToday));
        cal.add(Calendar.DATE, -1);

        String strYear = String.valueOf(cal.get(Calendar.YEAR));
        String strMonth = fillZero(String.valueOf(cal.get(Calendar.MONTH) + 1), 2);
        String strDate = fillZero(String.valueOf(cal.get(Calendar.DATE)), 2);
        return strYear + strMonth + strDate;
    }

    /**
     * システム時間 + offset の日付を取得する
     * @param    offset    システム時間からの差異
     * @return    システム時間 + offset の日付
     */
    public static String getSystemOffset(int offset) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, offset);

        String strYear = String.valueOf(cal.get(Calendar.YEAR));
        String strMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (strMonth.length() == 1) {
            strMonth = "0" + strMonth;
        }
        String strDate = String.valueOf(cal.get(Calendar.DATE));
        if (strDate.length() == 1) {
            strDate = "0" + strDate;
        }
        return  strYear + strMonth + strDate;
    }

    /**
     * システム時刻とのオフセットをチェックする
     * @param    strYukokaisi    有効開始日
     * @param    offset    システム時間とのオフセット
     * @return    システム時間 + offset > 有効開始日 なら true<br>システム時間 + offset < 有効開始日 なら false
     */
    public static boolean checkOffset(String strYukokaisi, int offset) {
        if (dateComp(strYukokaisi, getSystemOffset(offset)) < 0) {
            return false;
        }
        return true;
    }

    /**
     * 入力された日付のチェックをする。
     *
     * @param dateStr
     * @param param
     * @param isNeed
     * @param msgList
     * @return
     */
    public static void checkDateInput(String dateStr, String param, boolean isNeed, List<MessageModel> msgList) {
        MessageModel msg = new MessageModel();
        String errMes = "";
        if (param.equals("YUKOKAISI_DT") || param.equals("S_YUKOKAISI_DT")) {
            errMes = "有効開始日";
        }
        if (param.equals("YUKOSYURYO_DT")) {
            errMes = "有効終了日";
        }
        if (param.equals("KOJO_KADO_KAISI_DT")) {
            errMes = "工場稼動開始日";
        }
        if (param.equals("KOJO_KADO_SYURYO_DT")) {
            errMes = "工場稼動終了日";
        }
        final String haisoFukaStr = "HAISO_FUKA_DT";
        int len1 = param.length();
        int len2 = haisoFukaStr.length();
        if (len1 > len2) {
            String str = param.substring(0, len2);
            if (str.equals(haisoFukaStr)) {
                errMes = "配送不可日";
            }
        }

        // 必須入力チェック
        if (dateStr == null || dateStr.equals("")) {
            if (isNeed) {
                msg.setMessageId(MESSAGECODE.E_CM_0001_1001);
                msg.setParams(new String[] {errMes});
                msgList.add(msg);
                return;
            } else {
                return;
            }
        }

        // 日付形式をチェック
        int index1 = dateStr.indexOf("/");
        int index2 = dateStr.lastIndexOf("/");
        if (index1 != 4 || index2 != 7) {
            msg.setMessageId(MESSAGECODE.TN_CM0000_5020);
            msg.setParams(new String[] {errMes});
            msgList.add(msg);
            return;
        }

        // スラッシュ取る
        dateStr = StringUtil.dateTOStr(dateStr);

        // 入力禁止文字チェック
        if (StringCheck.checkProhibitionChar(dateStr)) {
            msg.setMessageId(MESSAGECODE.E_CM_0002_1002);
            msg.setParams(new String[] {errMes});
            msgList.add(msg);
            return;
        }

        // 半角数字のみで構成されているかチェック
        if (!StringCheck.checkNumeric(dateStr)) {
            msg.setMessageId(MESSAGECODE.E_CM_0002_1002);
            msg.setParams(new String[] {errMes});
            msgList.add(msg);
            return;
        }

        // 入力サイズチェック
        if (!StringCheck.checkLength(dateStr, 8)) {
            msg.setMessageId(MESSAGECODE.E_CM_0003_1003);
            msg.setParams(new String[] {errMes});
            msgList.add(msg);
            return;
        }
    }

    /**
     * 有効開始日が有効終了日よりも前の日付であるかどうかのチェック
     *
     * @param strStart 有効開始日
     * @param strEnd 有効終了日
     * @param msgList メッセージリスト
     * @return
     */
    public static void checkDateRange(String strStart, String strEnd, List<MessageModel> msgList) {
        MessageModel msg = new MessageModel();
        if ((null == strStart) || strStart.equals("")) {
            return;
        }
        if ((null == strEnd) || strEnd.equals("")) {
            return;
        }

        // スラッシュを取る
        strStart = StringUtil.dateTOStr(strStart);
        strEnd = StringUtil.dateTOStr(strEnd);

        if (LmvGenManager.dateComp(strEnd, strStart) < 0) {
            msg.setMessageId(MESSAGECODE.E_CM_0007_1007);
            msg.setFleldName("yukokaisiDt");
            msgList.add(msg);
            return;
        }
    }
}
