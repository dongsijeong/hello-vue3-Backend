package demo.mo.util;

import java.util.Arrays;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

public final class StringUtil {
    private StringUtil() {
    }
    /**
     * yyyy/mm/dd という文字列を yyyymmdd に変換する。
     *
     * @param date 日付
     * @return 変換した8桁の数字
     */
    public static String dateTOStr(String date) {
        String strTemp = new String();
        String strYear = new String();
        String strMonth = new String();
        String strDay = new String();
        String strRet = new String();

        if (null == date) {
            return null;
        }

        // / がある
        if (date.indexOf("/") != -1) {
            strYear = date.substring(0, date.indexOf("/"));
            strTemp = date.substring(date.indexOf("/") + 1);

            // もう一つ / がある
            if (strTemp.indexOf("/") != -1) {
                strMonth = strTemp.substring(0, strTemp.indexOf("/"));
                strDay = strTemp.substring(strTemp.indexOf("/") + 1);
            }
        } else {
            // 半角数字のみで構成されているかチェック
//            if(StringCheck.checkNumeric(date) != true){
            return date;
//            }
        }

        // 日付の生成
        strRet = strYear + strMonth + strDay;

        // もしまだ / があるなら文字列は不正
        if (strRet.indexOf("/") != -1) {
            return date;
        }

        return strRet;
    }

    /**
     * yyyymmdd という文字列を yyyy/mm/dd に変換する。
     *
     * @param date 日付の8桁の数字
     * @return 変換した/で区切られたの日付
     */
    public static String strTODate(String date) {
        if (null == date) {
            return null;
        }
        if (date.length() != 8) {
            return date;
        }
        return date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6, 8);
    }

    /**
     * 短い文字列を任意の文字長になるように、文字列の左に'0'を埋める。
     *
     * @param str    操作対象の文字列
     * @param length 桁
     * @return 0で埋められた文字列
     */
    public static String fillZero(String str, int length) {
        for (int i = 0; i < (length - str.length()); i++) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * DB の char 型のカラムから取得したデータから ' ' を取り去る。
     *
     * @param str 処理対象文字列
     * @return str から ' ' を取り去った文字列
     */
    public static String removeSpace(String str) {
        int i;
        if (null != str) {
            for (i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; i--) {
                str = str.substring(0, i + 1);
            }
        }
        return str;
    }

    /**
     * char 型のカラムから取得した一行の文字列から後半部分の ' ' を取り去る。
     * @param str     処理対象文字列
     * @return  str から ' ' を取り去った文字列
     */
    public static String removeSpaceT(String str) {
      int i;
      if (null != str) {
        for (i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; i--) {
            str = str.substring(0, i + 1);
        }
      }
      return str;
    }

    /**
     * 文字列配列の詰めなおしをする
     *
     * @param str [] 処理対象文字列配列
     *
     * @return 空要素が詰めなおされた文字列配列
     */
    public static String[] fillString(String[] str) {
        int len = str.length;
        String[] strRet = new String[len];

        // 初期化
        for (int i = 0; i < len; i++) {
            strRet[i] = new String();
        }

        // 詰めなおし
        int cnt = 0;
        for (int i = 0; i < len; i++) {
            if (null != str[i] && !str[i].equals("")) {
                strRet[cnt++] = str[i];
            }
        }

        return strRet;
    }


    /**
     * 文字列ＮＵＬＬの場合、空に変換する
     *
     * @param str 処理対象文字列
     * @return 変換された文字列
     */
    public static String converNulltoEmpty(String str) {
        if (str == null) {
            return "";
        }

        return str;
    }

    /**
     * 文字列が空白の場合、NULLに変換する
     *
     * @param str 処理対象文字列
     * @return 変換された文字列
     */
    public static String convertEmpty2NULL(String str) {

        if ("".equals(str)) {
            return null;
        }

        return str;
    }

    /**
     * システム日付からyyyymmddhhmmssの文字列を戻す。
     * @return  str から ' ' を取り去った文字列
     */
    public static String getCalender() {
      //システム日付の取得
      Calendar calendar = Calendar.getInstance();
      String monthStr = "";
      String dayStr = "";
      String hourStr = "";
      String minuteStr = "";
      String secondStr = "";
      int month = calendar.get(Calendar.MONTH) + 1;
      int day = calendar.get(Calendar.DATE);
      int hour = calendar.get(Calendar.HOUR_OF_DAY);
      int minute = calendar.get(Calendar.MINUTE);
      int second = calendar.get(Calendar.SECOND);
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
      if (10 > hour) {
        hourStr = "0" + Integer.toString(hour);
      } else {
        hourStr = Integer.toString(hour);
      }
      if (10 > minute) {
        minuteStr = "0" + Integer.toString(minute);
      } else {
        minuteStr = Integer.toString(minute);
      }
      if (10 > second) {
        secondStr = "0" + Integer.toString(second);
      } else {
        secondStr = Integer.toString(second);
      }

      String strCalender =  calendar.get(Calendar.YEAR) + monthStr + dayStr
                  + hourStr + minuteStr + secondStr;

      return strCalender;
    }

    /**
     * char 型のカラムから取得した一行の文字列から '\r\n'(改行) を取り去る。
     * @param str     処理対象文字列
     * @return  str から ' ' を取り去った文字列
     */
    public static String removeKaigyo(String str) {
      int i = 0;
      if (str != null && !str.equals("")) {

        //全ての改行を削除
        for (i = 0; i < str.length(); i++) {
          if (str.charAt(i) == '\n' || str.charAt(i) == '\r') {
            str = str.substring(0, i) + str.substring(i + 1, str.length());
            i--;
          }
        }
      }
      return str;
    }

    /**
     *入力文字は、全角スペースと半角スペースでスプリットする。
     * @param inputStr
     * @return スプリットした配列
     */
    public static String[] splitBySapce(String inputStr) {
        if (StringUtils.isEmpty(StringCheck.trimSpace(inputStr))) {
            return new String[] {};
        } else {
            //トレーリングとヘディングの全角スペースと半角スペースをトリムする
            inputStr = StringCheck.trimSpace(inputStr).replaceAll("　", " ");
            //全角スペースを半角スペースで取り替え
            inputStr = inputStr.replaceAll("　", " ");
            //重複なキーワードを除く
            String[] keyword = inputStr.split("\\s+");
            return Arrays.asList(keyword).stream().distinct().toArray(String[]::new);
        }
    }
}
