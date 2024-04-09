package demo.mo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

/**
 * 数字文字列操作クラス<BR>
 */
public final class DecimalString {

    private static String strError = "";
    private static final String FORMAT_NUMBER = "^-?(0|[1-9][0-9]*)($|\\.[0-9]+$)";
    private static final String FORMAT_NUMBER_ZERO_ERR = "^(-0|-0.0+)$";

    // コンストラクタ（非公開）
    private DecimalString() {

    }

    /**
     * 数字の書式チェック関数
     *
     * @param iIntegerDifits  整数部の桁数
     * @param iFractionDigits 小数部の桁数
     * @param strData         判定対象文字列
     * @return 書式判定結果を返す<BR>
     *         指定された書式に一致する場合、true<BR>
     *         （空文字（""）の場合はtrueを返す）<BR>
     *         指定された書式に一致しない場合、false<BR>
     */
    public static boolean checkFormat(int iIntegerDifits, int iFractionDigits, String strData) {
        boolean bResult = false;
        String strCntInteger = null;
        String strCntFraction = null;
        String strFormat = null;

        if (null == strData) {
            return true;
        }
        if (strData.equals("")) {
            return true;
        }

        //
        if (iIntegerDifits > 2) {
            strCntInteger = "{1," + Integer.toString(iIntegerDifits - 1) + "}";
        } else if (iIntegerDifits == 2) {
            strCntInteger = "";
        } else if (iIntegerDifits < 1) {
            strCntInteger = "";
        }

        if (iFractionDigits >= 2) {
            strCntFraction = "{1," + Integer.toString(iFractionDigits) + "}";
        } else if (iFractionDigits <= 1) {
            strCntFraction = "";
        }

        if (iIntegerDifits > 1 && iFractionDigits > 0) {
            strFormat = "^-0\\.0" + strCntFraction + "$";
            bResult = StringCheck.checkFormat(strFormat, strData);
            if (bResult) {
                return false;
            }
            strFormat = "^(0|-?[1-9]|-?[0-9]\\.[0-9]" + strCntFraction + "|-?[1-9][0-9]" + strCntInteger
                    + "|-?[1-9][0-9]" + strCntInteger + "\\.[0-9]" + strCntFraction + ")$";
        } else if (iIntegerDifits > 1 && iFractionDigits == 0) {
            strFormat = "^(0|-?[1-9]|-?[1-9][0-9]" + strCntInteger + ")$";
        } else if (iIntegerDifits == 1 && iFractionDigits > 0) {
            strFormat = "^-0\\.0" + strCntFraction + "$";
            bResult = StringCheck.checkFormat(strFormat, strData);
            if (bResult) {
                return false;
            }
            strFormat = "^(0|-?[1-9]|-?[0-9]\\.[0-9]" + strCntFraction + ")$";
        } else if (iIntegerDifits == 1 && iFractionDigits == 0) {
            strFormat = "^(0|-?[1-9])$";
        } else {
            return false;
        }
        bResult = StringCheck.checkFormat(strFormat, strData);
        // Debug用のコード
        // System.out.print( "Input = " + strData + " Check = " );
        // System.out.print( bResult );
        // System.out.println( " Format = " + strFormat );
        return bResult;

    }

    /**
     * カンマ削除関数<BR>
     *
     * @param strData 対象文字列
     * @return カンマを取り除いた文字列
     *         <TABLE border=1 cellspacing=0 cellpadding=2>
     *         <TR bgcolor="#CCCCFF">
     *         <TD align=center>入力データ</TD>
     *         <TD align=center>削除結果</TD>
     *         </TR>
     *         <TR>
     *         <TD align=right>123,456</TD>
     *         <TD align=right>123456</TD>
     *         </TR>
     *         <TR>
     *         <TD align=right>12,345,678</TD>
     *         <TD align=right>12345678</TD>
     *         </TR>
     *         <TR>
     *         <TD align=right>12</TD>
     *         <TD align=right>12</TD>
     *         </TR>
     *         </TABLE>
     *         <BR>
     *         <BR>
     */
    public static String removeComma(String strData) {
        int iLength = 0;
        int iCnt = 0;
        char chrC = 0;
        String newStrData = "";

        try {
            if (null != strData) {
                // 文字の長さを取得する
                iLength = strData.length();
                // 文字があるまで1文字づつ取得する
                for (iCnt = 0; iCnt < iLength; iCnt++) {
                    chrC = strData.charAt(iCnt);
                    // 取得した文字がカンマ以外の場合
                    if (chrC != ',') {
                        // 文字を連結させる
                        newStrData = newStrData + chrC;
                    }
                }
                // 連結した文字を返す
                strData = newStrData;
                return strData;
            } else {
                return strError;
            }
        } catch (Exception e) {
            // 例外処理
            return strError;
        }
    }

    /**
     * カンマ削除関数
     * @param strData  数値
     * @return カンマ削除済文字列(入力文字は、空白の場合、NULLで返す)
     */
    public static String removeCommaWithNull(String strData) {
        String removedCommStr = DecimalString.removeComma(strData);
        return "".equals(removedCommStr) ? null : removedCommStr;
    }

    /**
     * カンマの付加関数<BR>
     *
     * @param dData  数値
     * @param ibeams 小数部の有効桁数 （0〜10を有効範囲とする）<BR>
     * @return ３桁毎にカンマを付けた文字列を返す 小数部の指定が不正な場合は空白文字列を返す
     *         <TABLE border=1 cellspacing=0 cellpadding=2>
     *         <TR bgcolor="#CCCCFF">
     *         <TD align=center>入力データ</TD>
     *         <TD align=center>小数部の桁数</TD>
     *         <TD align=center>付加結果</TD>
     *         </TR>
     *         <TR>
     *         <TD align=right>123456.78</TD>
     *         <TD align=right>3</TD>
     *         <TD align=right>123,456.780</TD>
     *         </TR>
     *         <TR>
     *         <TD align=right>456</TD>
     *         <TD align=right>2</TD>
     *         <TD align=right>456.00</TD>
     *         </TR>
     *         <TR>
     *         <TD align=right>1234.567</TD>
     *         <TD align=right>2</TD>
     *         <TD align=right>1,234.6</TD>
     *         </TR>
     *         <TR>
     *         <TD align=right>1234.567</TD>
     *         <TD align=right>11</TD>
     *         <TD align=center>(空白文字)</TD>
     *         </TR>
     *         <TR>
     *         <TD align=right>1234.567</TD>
     *         <TD align=right>-1</TD>
     *         <TD align=center>(空白文字)</TD>
     *         </TR>
     *         </TABLE>
     *         <BR>
     *         <BR>
     */
    public static String addComma(double dData, int ibeams) {
        int iCnt;
        @SuppressWarnings("unused")
        String format = "";
        String strData = null;
        try {
            // 特別な非数（NaN）の場合、空白を返す
            if (Double.isNaN(dData)) {
                return "";
            }

            // 指定された小数点有効桁数が0以上10以下の場合、有効桁数を設定する
            if ((0 <= ibeams) && (ibeams <= 10)) {
                String strFugou = "";
                String strSei = "";
                String strShou = "";
                int iSei = 0;
                int iShou = 0;
                int iLength = 0;
                if (dData < 0.0D) {
                    strFugou = "-";
                    dData *= -1;
                } else {
                    strFugou = "";
                }
                strData = "";

                dData *= Math.pow(10.0D, ibeams);
                dData += 0.5D;

                // 無限大の場合、空白を返す
                if (Double.isInfinite(dData)) {
                    return "";
                }

                // int型で扱える値の範囲内の場合
                if (dData <= Math.pow(2.0, 31) - 1) {
                    iSei = (int) (dData / Math.pow(10.0D, ibeams));
                    iShou = (int) (dData % Math.pow(10.0D, ibeams));
                    DecimalFormat df = new DecimalFormat(",##0");
                    strSei = df.format(iSei);
                    strShou = Integer.toString(iShou);
                    strData = strSei;
                    if (0 != ibeams) {
                        iLength = strShou.length();
                        for (iCnt = 0; iCnt < (ibeams - iLength); iCnt++) {
                            strShou = "0" + strShou;
                        }
                        strData = strData + "." + strShou;
                    }
                    // ０.０以外の場合、符号を付ける
                    if (0 != iSei || 0 != iShou) {
                        strData = strFugou + strData;
                    }
                } else {
                    // int型で扱える値の範囲外の場合
                    BigDecimal bigDecimal1 = new BigDecimal(dData);
                    BigDecimal bigDecimal2 = new BigDecimal(Math.pow(10.0D, ibeams));
                    bigDecimal1 = bigDecimal1.divide(bigDecimal2, ibeams, RoundingMode.DOWN);
                    String strFormat = ",##0";
                    if (0 < ibeams) {
                        strFormat += ".";
                    }
                    for (iCnt = 0; iCnt < ibeams; iCnt++) {
                        strFormat += "0";
                    }
                    DecimalFormat df = new DecimalFormat(strFormat);
                    strData = df.format(bigDecimal1.doubleValue());
                    strData = strFugou + strData;
                }
            } else {
                // 指定された小数点有効桁数が0以上10以下以外の場合空白を返す
                return strError;
            }
        } catch (Exception e) {
            return strError;
        }
        return strData;
    }

    /**
     * カンマの付加関数<BR>
     *
     * @param strData 文字列
     * @param ibeams  小数部の有効桁数 （0〜10を有効範囲とする）<BR>
     * @return ３桁毎にカンマを付けた文字列を返す 小数部の指定が不正な場合は空白文字列を返す
     *         <TABLE border=1 cellspacing=0 cellpadding=2>
     *         <TR bgcolor="#CCCCFF">
     *         <TD align=center>入力データ</TD>
     *         <TD align=center>小数部の桁数</TD>
     *         <TD align=center>付加結果</TD>
     *         </TR>
     *         <TR>
     *         <TD align=right>123456.78</TD>
     *         <TD align=right>3</TD>
     *         <TD align=right>123,456.780</TD>
     *         </TR>
     *         <TR>
     *         <TD align=right>456</TD>
     *         <TD align=right>2</TD>
     *         <TD align=right>456.00</TD>
     *         </TR>
     *         <TR>
     *         <TD align=right>1234.567</TD>
     *         <TD align=right>2</TD>
     *         <TD align=right>1,234.6</TD>
     *         </TR>
     *         <TR>
     *         <TD align=right>1234.567</TD>
     *         <TD align=right>11</TD>
     *         <TD align=center>(空白文字)</TD>
     *         </TR>
     *         <TR>
     *         <TD align=right>1234.567</TD>
     *         <TD align=right>-1</TD>
     *         <TD align=center>(空白文字)</TD>
     *         </TR>
     *         </TABLE>
     *         <BR>
     *         <BR>
     */
    public static String addComma(String strData, int ibeams) {
        try {
            // 文字列がnull以外の場合
            if (null != strData) {
                // 文字をdouble型に変換する
                double dData = 0.0D;
                dData = Double.parseDouble(strData);
                // addComma(double dData,int ibeams)を呼び出す
                strData = addComma(dData, ibeams);
                // カンマを付けた文字を返す
                return strData;
            } else {
                return strError;
            }
        } catch (Exception e) {
            // 例外処理
            return strError;
        }
    }

    /**
     * 数値の文字列チェック関数
     *
     * @param strData 判定対象文字列
     * @return 数値を表す文字のみで構成し、かつ、数値として認識できる文字列であることを判定する<BR>
     *         上記の条件を満たす場合、true。<BR>
     *         （空文字（""）の場合はtrueを返す）<BR>
     *         上記の条件を満たさない場合、false。<BR>
     */
    public static boolean checkNumber(String strData) {
        if (null == strData) {
            return true;
        }

        if (strData.equals("")) {
            return true;
        }
        boolean bResult = false;

        bResult = StringCheck.checkFormat(FORMAT_NUMBER_ZERO_ERR, strData);
        if (bResult) {
            return false;
        }
        return StringCheck.checkFormat(FORMAT_NUMBER, strData);
    }

    /**
     * 上に小数切り捨て
     * @param strDecimalValue
     * @param newScale
     * @return 切り捨て小数
     */
    public static String formatDecimalStringUp(String strDecimalValue, int newScale) {
        return formatDecimalString(strDecimalValue, newScale, RoundingMode.HALF_UP);
    }

    /**
     * 下に小数切り捨て
     * @param strDecimalValue
     * @param newScale
     * @return 切り捨て小数
     */
    public static String formatDecimalStringDown(String strDecimalValue, int newScale) {
        return formatDecimalString(strDecimalValue, newScale, RoundingMode.HALF_DOWN);
    }

    /**
     * 小数第newScale位以下切り捨て。
     *
     * @param strDecimalValue 小数値
     * @param newScale 位
     * @param roundingMode 四捨五入する/しない RoundingMode.HALF_UP RoundingMode.HALF_DOWN
     * @return 切り捨て小数
     */
    public static String formatDecimalString(String strDecimalValue, int newScale, RoundingMode roundingMode) {
        BigDecimal decVal = new BigDecimal(strDecimalValue);
        decVal = decVal.setScale(newScale, roundingMode);
        return DecimalString.addComma(decVal.toString(), newScale);
    }

    /**
     * カンマが含まれる入力値はそのまま、変換しないで、入力値を返す。
     * @param inputStr 入力値
     * @param precisons 小数部の有効桁数 （0〜10を有効範囲とする）
     * @return カンマが含まれる入力値
     */
    public static String getValueWithComma(String inputStr, int precisons) {
        if (!StringUtils.isEmpty(inputStr)) {
            //カンマが含まれる入力値はそのまま、変換不要
            if (inputStr.contains(",")) {
                return inputStr;
            } else {
                String convertedStr = DecimalString.addComma(inputStr, precisons);
                if (StringUtils.isEmpty(convertedStr)) {
                    return inputStr;
                } else {
                    return convertedStr;
                }
            }
        }
        return inputStr;
    }
}
