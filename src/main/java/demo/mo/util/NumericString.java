/*********************************************************************
 * システム名称     : 生産加工管理システム STEP2開発
 * サブシステム名   :
 * 機能名           :
 * クラス名         : jp.co.lawson.mo.util.NumericString
 * 作成日           : 2021/07/06
 * 作成者           : Meng,Ling-kang
**********************************************************************/
package demo.mo.util;

/**
 * 金額を扱うクラス。<br>
 */
public final class NumericString {
    private NumericString() {
    }
    /**
     * 金額入力項目のパラメータを取得し、カンマや小数の桁数を調整するメソッド
     *
     * @param param       パラメータ
     * @param decimalSize 小数部の有効桁数
     * @return 取得し、調整したパラメータ
     */
    public static String getMoneyParameter(String param, int decimalSize) {
        // パラメータが取得できなければ空文字を返す
        if (null == param) {
            return "";
        }

        // カンマと小数桁の調整
        String adjustedParam = DecimalString.addComma(DecimalString.removeComma(param), decimalSize);

        // ちゃんと調整されていればそれを返す
        if (!adjustedParam.equals("")) {
            return adjustedParam;
        } else {
            // 調整中にエラーが出れば元の値を返す
            return param;
        }
    }

    /**
     * 小数入力項目のパラメータを取得し、小数の桁数を調整するメソッド
     *
     * @param param      パラメータ
     * @param decimalMax 小数部の有効桁数
     * @return 取得し、調整したパラメータ
     */
    public static String getDecimalParameter(String param, int decimalMax) {
        // パラメータが取得できなければ空文字を返す
        if (null == param || param.equals("")) {
            return "";
        }

        if (!DecimalString.checkNumber(param)) {
            return param;
        }

        StringBuffer tempBuf = new StringBuffer(50);
        tempBuf.append(param);

        int decimalSize = 0;
        int index = param.indexOf(".");
        if (index == -1) {
            tempBuf.append(".");
        } else {
            String decimal = param.substring(index + 1);
            decimalSize = decimal.length();
        }

        // カンマと小数桁の調整
        for (int i = decimalSize; i < decimalMax; i++) {
            tempBuf.append("0");
        }

        return tempBuf.toString();
    }

    /**
     * 数字の入った文字列にカンマを付与する。既に入っている場合は一度カンマを削除してから付与し直す。
     *
     * @param orgNumber 元になる数字の入ったString
     * @return カンマを調整文字列
     */
    public static String addComma(String orgNumber) {
        if (null == orgNumber || 0 >= orgNumber.length()) {
            return orgNumber;
        }

        StringBuffer tempBuffer = new StringBuffer(orgNumber);
        String tempStr;
        int index;
        @SuppressWarnings("unused")
        int length;
        int endIndex;

        // 元からあるカンマを削除
        tempStr = orgNumber;
        index = tempStr.indexOf(",");
        while (-1 != index) {
            tempBuffer = tempBuffer.deleteCharAt(index);
            tempStr = tempBuffer.toString();
            index = tempStr.indexOf(",");
        }

        tempStr = tempBuffer.toString();
        if (!DecimalString.checkNumber(tempStr)) {
            return tempStr;
        }

        if (0 >= tempStr.length()) {
            return "";
        }

        // 小数部がある場合は整数部が開始位置
        index = tempStr.indexOf(".");
        // 小数部がない場合は文字列の一番最後が開始位置
        if (-1 == index) {
            index = tempBuffer.length();
        }
        // 負の数字ならエンドが1
        if ('-' == tempBuffer.charAt(0)) {
            endIndex = 1;
        } else {
            // 正の数字ならエンドが0
            endIndex = 0;
        }
        // カンマを付与
        length = index;
        index -= 3;
        while (endIndex < index) {
            tempBuffer = tempBuffer.insert(index, ",");
            index -= 3;
        }
        return tempBuffer.toString();
    }
}
