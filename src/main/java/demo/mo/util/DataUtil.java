package demo.mo.util;

import java.util.Calendar;

/**
 * DBデータ対応のユーティリティクラス。<br>
 *
 */
public class DataUtil {

/**
 * 終売日が20年以上前なら、発売日と終売日をプラス(＋)20年した値の文字列を戻す
 * @param hatubaiDt    発売日(YYYYMMDD)
 * @param syubaiDt     終売日(YYYYMMDD)
 * @return  strDate     strDate[0]=発売日(YYYYMMDD),strDate[1]=終売日(YYYYMMDD),
 */
public static String[] add20years(String hatubaiDt, String syubaiDt) {

    String[] strDate = new String[2];
    DataUtil tu = new DataUtil();

    // ①終売日がnullもしくは""でない場合対応する
    if (syubaiDt != null && !syubaiDt.equals("")) {

        // システム日付の取得(YYYYMMDD)
        Calendar calendar = Calendar.getInstance();
        // 20年前の日付の取得(YYYYMMDD)
        calendar.add(Calendar.YEAR, -20);

        // 終売日
        Calendar syubaiCalendar = tu.getCalendar(syubaiDt);

        if (calendar.after(syubaiCalendar) || calendar.equals(syubaiCalendar)) {

            // 発売日がnullもしくは""でない場合→発売日に＋20年
            if (hatubaiDt != null && !hatubaiDt.equals("")) {
                Calendar hatubaiCalendar = tu.getCalendar(hatubaiDt);
                hatubaiCalendar.add(Calendar.YEAR, 20);
                strDate[0] = tu.getStringDate(hatubaiCalendar);
            } else {
                // 発売日がnullもしくは""の場合→""をセット
                strDate[0] = "";
            }
            // 終売日に＋20年
            syubaiCalendar.add(Calendar.YEAR, 20);
            strDate[1] = tu.getStringDate(syubaiCalendar);
        } else {
        // 発売日がnullもしくは""の場合→""をセット
            if (hatubaiDt != null && !hatubaiDt.equals("")) {
                strDate[0] = hatubaiDt;
            } else {
            // 発売日がnullもしくは""の場合→""をセット
                strDate[0] = "";
            }
            // 終売日はそのまま
            strDate[1] = syubaiDt;
        }
    } else {
    // ①'終売日がnullもしくは""の場合
        // 発売日がnullもしくは""でない場合→そのままの値をセット
        if (hatubaiDt != null && !hatubaiDt.equals("")) {
            strDate[0] = hatubaiDt;
        } else {
        // 発売日がnullもしくは""の場合→""をセット
            strDate[0] = "";
        }
        // 終売日には""をセット
        strDate[1] = "";
    }

    return strDate;
}
  //YYYYMMDDからカレンダークラスを作るメソッド
  private Calendar getCalendar(String strDate) {

    Calendar syubaiCalendar = Calendar.getInstance();

    int year = Integer.parseInt(strDate.substring(0, 4));
    int month = Integer.parseInt(strDate.substring(4, 6)) - 1;
    int day = Integer.parseInt(strDate.substring(6, 8));
    syubaiCalendar.set(year, month, day);

    return syubaiCalendar;
  }
  //カレンダークラスからYYYYMMDDを作るメソッド
  private String getStringDate(Calendar calendar) {
    String monthStr = "";
    String dayStr = "";
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DATE);

    if (10 > month) {
      monthStr = "0" + Integer.toString(month);
    } else {
      monthStr = Integer.toString(month);
    }
    if (10 > day) {
      dayStr = "0" + Integer.toString(day);
    } else {
      dayStr = Integer.toString(day);
    }
    String strCalendar =  calendar.get(Calendar.YEAR) + monthStr + dayStr;
    return strCalendar;
  }
}
