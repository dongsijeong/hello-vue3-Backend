package demo.mo.util;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.text.ParseException;

/**
*日付文字列操作クラス<BR>
*/
public final class DateString {
    /**
    *年月情報（日付情報の種別）
    */
    public static final int DATE_YYMM      = 1;
    /**
    *年月日情報（日付情報の種別）
    */
    public static final int DATE_YYMMDD    = 2;
    /**
    *年月週情報（日付情報の種別）
    */
    public static final int DATE_YYMM_WEEK = 3;

    // 書式チェック用の正規表現
    private static final String DATE_FORMAT_YYMM      = "^(20\\d{2}/\\d{2}|9999/12)$";
    private static final String DATE_FORMAT_YYMMDD    = "^(20\\d{2}/\\d{2}/\\d{2}|9999/12/31)$";
    private static final String DATE_FORMAT_YYMM_WEEK = "^(20\\d{2}/\\d{2}/\\d{2}|9999/12/31)週$";

    // クラスのインスタンスを生成しない為に記述
    private DateString() {

    }

    /**
    *日付の正当性チェック関数
    *@param iDateType 日付情報の種別<BR>
    *                 <TABLE border=1 cellspacing=0 cellpadding=2>
    *                 <TR bgcolor="#CCCCFF"><TD align=center>引数</TD><TD align=center>日付情報の種別</TD></TR>
    *                 <TR><TD>DATE_YYMM</TD><TD>年月</TD></TR>
    *                 <TR><TD>DATE_YYMMDD</TD><TD>年月日</TD></TR>
    *                 <TR><TD>DATE_YYMM_WEEK</TD><TD>年月週</TD></TR>
    *                 </TABLE><BR>
    *@param strDate   日付の正当性チェック対象の文字列
    *<BR>
    *@return 判定結果を返す<BR>
    *        true:正常&nbsp;&nbsp;false:異常<BR>
    *        （空文字（""）の場合はtrueを返す）<BR>
    *@since 1.04
    */
    public static boolean checkDate(int iDateType, String strDate) {
        // #### 入力文字列の解析 ####
        DateFormat    dateFormat = null;
        @SuppressWarnings("unused")
        Date        dateValue  = null;
        String        strFormat = null;

        if (null == strDate) {
            return false;
        }
        if (strDate.equals("")) {
            return true;
        }

        try {
            // 形式を判定する
            switch (iDateType) {
                // 年月形式の場合
                case DATE_YYMM :
                    dateFormat = new SimpleDateFormat("yyyy/MM");
                    strFormat = DATE_FORMAT_YYMM;
                    break;
                // 年月日形式の場合
                case DATE_YYMMDD :
                    dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    strFormat = DATE_FORMAT_YYMMDD;
                    break;
                // 年月週形式の場合
                case DATE_YYMM_WEEK :
                    dateFormat = new SimpleDateFormat("yyyy/MM/dd週");
                    strFormat = DATE_FORMAT_YYMM_WEEK;
                    break;
                // その他の場合、falseを返す
                default :
                    return false;
            }

            boolean bResult = StringCheck.checkFormat(strFormat, strDate);
            if (!bResult) {
                return false;
            }

            // チェックを厳密に行う様に設定する
            dateFormat.setLenient(false);
            // 日付文字列の解析
            dateValue = dateFormat.parse(strDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
    * システム日付の文字列の取得関数
    *@param iDateType 日付情報の種別<BR>
    *<TABLE border=1 cellspacing=0 cellpadding=2>
    *<TR bgcolor="#CCCCFF"><TD align=center>引数</TD><TD align=center>日付情報の種別</TD><TD>システム日付の作成例</TD></TR>
    *<TR><TD>DATE_YYMM</TD><TD>年月</TD><TD>2002/07</TD></TR>
    *<TR><TD>DATE_YYMMDD</TD><TD>年月日</TD><TD>2002/07/29</TD></TR>
    *<TR><TD>DATE_YYMM_WEEK</TD><TD>年月週</TD><TD>2002/07/29週</TD></TR>
    *</TABLE><BR>
    *@return 日付の文字列を返す
    *@since 1.04
    */
    public static String getSystemDate(int iDateType) {
        Date         sysDate = null;
        String         strDate = null;
        DateFormat    dateFormat = null;
        @SuppressWarnings("unused")
        int            iWeek = 0;

        Calendar cal = Calendar.getInstance(Locale.JAPAN);
        // システム日付を取得する
        sysDate = cal.getTime();
        // 形式を判定する
        switch (iDateType) {
            // 年月形式の場合
            case DATE_YYMM :
                dateFormat = new SimpleDateFormat("yyyy/MM");
                break;
            // 年月日形式の場合
            case DATE_YYMMDD :
                dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                break;
            // 年月週形式の場合
            case DATE_YYMM_WEEK :
                dateFormat = new SimpleDateFormat("yyyy/MM/dd週");
                // 月曜日までループする
                while (Calendar.MONDAY != cal.get(Calendar.DAY_OF_WEEK)) {
                    cal.add(Calendar.DATE, -1);
                }
                sysDate = cal.getTime();
                break;
            // その他の場合は空白文字列を返す
            default :
                strDate = new String("");
                return strDate;
        }
        // フォーマット変換して文字列を作成
        strDate = dateFormat.format(sysDate);
        // 文字列を返す
        return strDate;
    }

    /**
    * 日付の区切り文字列の削除
    *@param strDate 日付区切り文字の削除対象文字列
    *@return 日付の区切り文字列を削除した文字列
    *<TABLE border=1 cellspacing=0 cellpadding=2>
    *<TR bgcolor="#CCCCFF"><TD align=center>入力データ</TD><TD align=center>削除結果</TD></TR>
    *<TR><TD>2002/07</TD><TD>200207</TD></TR>
    *<TR><TD>2002/07/29</TD><TD>20020729</TD></TR>
    *<TR><TD>2002/07/29週</TD><TD>20020729</TD></TR>
    *</TABLE><BR>
    *@since 1.04
    */
    public static String removeSeparator(String strDate) {
        int     iLength = 0;
        int     iCnt = 0;
        char     chrC = 0;
        String     newStrDate = "";

        try {
            if (null != strDate) {
                //文字の長さを取得する
                iLength = strDate.length();
                //文字があるまで1文字づつ取得する
                for (iCnt = 0; iCnt < iLength; iCnt++) {
                    chrC = strDate.charAt(iCnt);
                    // 数字のみを抜き出す
                    if ((chrC >= '0') && (chrC <= '9')) {
                        //文字を連結させる
                        newStrDate = newStrDate + chrC;
                    }
                }
            } else {
                return newStrDate;
            }
        } catch (Exception e) {
            //例外処理
            return newStrDate;
        }
        return newStrDate;
    }

    /**
    * 日付文字列の比較
    *@param strDate1 比較対象文字列１
    *@param strDate2 比較対象文字列２
    *@return 日付文字列を辞書式に比較した結果を返す。<BR>
    *        strDate1とstrDate2が辞書式で等しい場合は０。<BR>
    *        strDate1がstrDate2より辞書式で小さい場合は０より小さい値。<BR>
    *        strDate1がstrDate2より辞書式で大きい場合は０より大きい値。<BR>
    *<TABLE border=1 cellspacing=0 cellpadding=2>
    *<TR bgcolor="#CCCCFF"><TD align=center>strDate1</TD><TD align=center>strDate2</TD>
    *<TD align=center>比較結果</TD></TR>
    *<TR><TD align=center>2000/02/29</TD><TD align=center>2002/03/21</TD><TD align=center>-2</TD></TR>
    *<TR><TD align=center>2004/12/29</TD><TD align=center>2002/03/21</TD><TD align=center>2</TD></TR>
    *<TR><TD align=center>2002/07/29</TD><TD align=center>2002/07/29</TD><TD align=center>0</TD></TR>
    *</TABLE>
    *<BR>
    *@since 1.04
    */
    public static int compareDate(String strDate1, String strDate2) {
        String strCompDate1 = null;
        String strCompDate2 = null;

        strCompDate1 = removeSeparator(strDate1);
        strCompDate2 = removeSeparator(strDate2);

        return strCompDate1.compareTo(strCompDate2);
    }

    /**
    * 日付文字列の作成
    *@param strDate 日付文字列の作成する元となる文字列
    *               （８桁の半角数字であること）
    *@return 作成した日付文字列を返す<BR>
    *        引数で指定された文字列が８桁の半角英数字でない場合、空白文字列を返す<BR>
    *<TABLE border=1 cellspacing=0 cellpadding=2>
    *<TR bgcolor="#CCCCFF"><TD align=center>入力データ</TD><TD align=center>作成結果</TD></TR>
    *<TR><TD>20020729</TD><TD>2002/07/29</TD></TR>
    *<TR><TD>99991231</TD><TD>9999/12/31</TD></TR>
    *<TR><TD>1234567</TD><TD>(空白文字)</TD></TR>
    *<TR><TD>abcdefgh</TD><TD>(空白文字)</TD></TR>
    *</TABLE><BR>
    *<BR>
    *@since 1.04
    */
    public static String getDateString(String strDate) {
        if (null == strDate) {
            return "";
        }

        boolean bResult = StringCheck.checkFormat("^[\\d]{8}$", strDate);
        if (!bResult) {
            return "";
        }
        String strNewDate = strDate.substring(0, 4) + "/"
                 + strDate.substring(4, 6) + "/" + strDate.substring(6);
        return strNewDate;
    }

    /**
     * システム日付と引数の日数の差分日数
     *@param nDate 日付
     *@return 引数の日付－システム日付
     */
    public static long getDateDiff(String nDate) {
        String yukouKaisibi = removeSeparator(nDate);

        long long1 = Calendar.getInstance().getTime().getTime();

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Integer.parseInt(yukouKaisibi.substring(0, 4)),
                Integer.parseInt(yukouKaisibi.substring(4, 6)) - 1,
                Integer.parseInt(yukouKaisibi.substring(6, 8)));
        long long2 = cal2.getTime().getTime();

        return (long2 - long1) / (long) 86400000;
    }

    /**
     * システム日付＋引数の日数
     * @param nDate
     * @return 日数
     */
    public static String getSystemDateAdd(int nDate) {

        Calendar cal2 = Calendar.getInstance(Locale.JAPAN);
        cal2.add(Calendar.DATE, nDate);
        return getDateString(String.valueOf(cal2.get(Calendar.YEAR) * 10000
                + (cal2.get(Calendar.MONTH) + 1) * 100 + cal2.get(Calendar.DATE)));
    }

/**
 * 日付・時刻を文字列にして返す。
 *
 * @param date   日付をDate形式で指定
 * @param format 日付文字列のフォーマットを指定
 * @return 日付を文字列に変換した物を返す
 */
    public static String getDateTimeString(Date date, String format) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      return sdf.format(date);
    }

    /**
     * システム日付・時刻を文字列にして返す。
     *
     * @param format 日付文字列のフォーマットを指定
     * @return 日付を文字列に変換した物を返す
     */
    public static String getDateTimeString(String format) {
      return getDateTimeString(new Date(), format);
    }

    /**
     * システム日付・時刻を文字列にして返す(yyyy-MM-dd HH:mm:ss)。
     *
     * @return 日付を文字列に変換した物を返す
     */
    public static String getDateTimeString() {
      return getDateTimeString(new Date(), "yyyy-MM-dd HH:mm:ss");
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
        String strMonth = fillZero(String.valueOf(cal.get(Calendar.MONTH) + 1), 2);
        String strDate = fillZero(String.valueOf(cal.get(Calendar.DATE)), 2);
        return  strYear + strMonth + strDate;
    }

    //  短い文字列を任意の文字長になるように、文字列の左に'0'を埋める
    private static String fillZero(String str, int length) {
        for (int i = 0; i < (length - str.length()); i++) {
            str = "0" + str;
        }
        return str;
    }
}

