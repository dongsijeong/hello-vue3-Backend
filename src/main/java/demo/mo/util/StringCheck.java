package demo.mo.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import demo.mo.config.DataAccessConfig;



/**
 * 【StringCheck基本クラス】<BR>
 * 文字列チェッククラス<BR>
 * 正規表現を用いて文字列の判定処理を行う<BR>
 *
 */
public final class StringCheck {

    /**
     * 全角かな（ひらがな・カタカナ）判定の正規表現
     */
    // *<TABLE>
    // *<TR><TD>メモ</TD><TD>unicodeの文字コードは次の通り</TD></TR>
    // *<TR><TD></TD><TD>全角ひらがなは、0x3041-0x3094 0x3099-0x309E</TD></TR>
    // *<TR><TD></TD><TD>全角カタカナは、0x30A1-0x30FE</TD></TR>
    // *<TR><TD></TD><TD>全角スペースは、0x3000の範囲</TD></TR>
    // *<TR><TD></TD><TD>ただし、厳密には0x3099-0x309E,30FCは、</TD></TR>
    // *<TR><TD></TD><TD>ひらがなとカタカナの共通文字なので注意すること。</TD></TR>
    // *</TABLE><BR>
    public static final String FORMAT_WIDE_KANA =
      "^[\\u3041-\\u3094\\u3099-\\u309E\\u30a1-\\u30fe\\u3000\\u3099-\\u309C\\u30FC]+$";
    /**
     * 全角カタカナ判定の正規表現
     */
    // 全角カタカナ判定コードの仕様変更。許容する文字を追加する。
    // 全角カタカナは0x3000-0x30FE,0x3099-0x309E,30FC。
    // 追加する文字は全角英字（大文字・小文字）、全角数字、記号、全角空白
    // 全角空白 :0x3000
    // 全角カタカナ :0x30a1-0x30FE,0x3099-0x309E,0x30fc
    // 全角英字（大文字・小文字）Ａ−Ｚ、ａ−ｚ:0xff21-0xff3a、0xff41-0xff5a
    // 全角数字 ０１２３４５６７８９:0xff10-0xff19
    // 記号 ！:0xff01 ＃:0xff03 ＆:0xff04 ％:0xff05 ＆:0xff06 （:0xff08 ）:0xff09
    // ＊:0xff0a ＋:0xff0b ，:0xff0c −:0xff0d ．:0xff0e ／:0xff0f ：:0xff1a
    // ；:0xff1b ＜:0xff1c ＝:0xff1d ＞:0xff1e ？:0xff1f ＠:0xff20 ＿:0xff3f
    // ・:0x30fb
    // public static final String FORMAT_WIDE_KATAKANA =
    // "^[\\u3000\\u30A1-\\u30FE\\u3099-\\u309E\\u30FC]+$";
    public static final String FORMAT_WIDE_KATAKANA =
      "^[\\u3000\\u30A1-\\u30FE\\u3099-\\u309E\\u30FB\\u30FC\\uFF01\\uFF03-\\uFF06"
      + "\\uFF08-\\uFF3A\\uFF3f\\uFF41-\\uFF5A]+$";

    /**
     * 半角英数字判定の正規表現文字列
     */
    public static final String FORMAT_HANKAKU_EISUUJI = "^[\\w]+$";
    /**
     * 半角数字判定の正規表現文字列
     */
    public static final String FORMAT_HANKAKU_SUUJI = "^[\\d]+$";

    /**
     * 整数数字判定の正規表現文字列
     */
    public static final String FORMAT_INTEGER = "^-?[\\d]+$";
    // /**
    // *使用禁止文字判定の正規表現文字列
    // */
    // // メモ：使用禁止文字コード（unicode)
    // // §：0x00A7 ¨：0x00A8 ゜：0x00B0 ´：0x00B4 ¶：0x00B6
    // // �ａF0x2116 �пF0x2121 �T〜�]：0x2160〜0x2169 �@〜�I：0x2170〜0x2179
    // // �煤F0x2211 ��：0x221F ‖：0x2225 �刀F0x222E �凵F0x22BF
    // // �@〜�S：0x2460〜0x2473 ��：0x301D �＝F0x301F
    // // (株)：0x3231 (有)：0x3232 (代)：0x3239
    // // ��：0x32A4 ��：0x32A5 ��：0x32A6 ��：0x32A7 �堰F0x32A8
    // // �e：0x3303 �i：0x330D �`：0x3314 �c：0x3318 �a：0x3322
    // // �k：0x3323 �j：0x3326 �d：0x3327 �l：0x332B �f：0x3336
    // // �n：0x333B �_：0x3349 �m：0x334A �b：0x334D �g：0x3351
    // // �h：0x3357 �~：0x337B �潤F0x337C �氏F0x337D �香F0x337E
    // // �r：0x338E �s：0x338F �o：0x339C �p：0x339D �q：0x339E
    // // �u：0x33A1 �t：0x33C4 �メF0x33CD ｀：0xFF07 —：0xFF0D
    // // 〜：0xFF5E ¢：0xFFE0 £：0xFFE1 ¬：0xFFE2 �U：0xFFE4
    //
    public static final String VALID_CHAR_PROPERTY_FILE = "/jp/co/lawson/xx/framework/ValidCharProperty.properties";

    // コンストラクタ（非公開）
    private StringCheck() {

    }

    /**
     * 文字列の前後の空白文字を削除する関数
     *
     * @param strData 対象の文字列
     * @return 文字列の前後の空白文字を削除した文字列を返す
     */
    public static String trimSpace(String strData) {

        if (strData == null) {
            return strData;
        }
        int iStart = 0;
        int iEnd = 0;
        // 文字列の先頭から全角空白でない文字開始位置を決定する
        for (iStart = 0; iStart < strData.length() - 1; iStart++) {
            if ((strData.charAt(iStart) != '　') && (strData.charAt(iStart) != ' ')) {
                break;
            }
        }
        // 文字列の終端から全角空白文字でない文字終了位置を決定する
        for (iEnd = strData.length() - 1; iEnd >= iStart; iEnd--) {
            if ((strData.charAt(iEnd) != '　') && (strData.charAt(iEnd) != ' ')) {
                break;
            }
        }
        if (iStart <= iEnd) {
            // 開始位置から終了位置までの文字を抜き出す
            strData = strData.substring(iStart, iEnd + 1);
        } else {
            strData = "";
        }
        return strData;
    }

    /**
     * 文字列の書式チェック関数
     *
     * @param strFormat 正規表現文字列
     *                  <TABLE border=1 cellspacing=0 cellpadding=2>
     *                  <TR bgcolor="#CCCCFF">
     *                  <TD align=center>引数</TD>
     *                  <TD align=center>正規表現文字列</TD>
     *                  </TR>
     *                  <TR>
     *                  <TD>FORMAT_WIDE_KANA</TD>
     *                  <TD>全角かな（ひらがな・カタカナ）判定</TD>
     *                  </TR>
     *                  <TR>
     *                  <TD>FORMAT_HANKAKU_EISUUJI</TD>
     *                  <TD>半角英数字判定</TD>
     *                  </TR>
     *                  <TR>
     *                  <TD>FORMAT_HANKAKU_SUUJI</TD>
     *                  <TD>半角数字判定</TD>
     *                  </TR>
     *                  <TR>
     *                  <TD>FORMAT_NOT_USE_CHAR</TD>
     *                  <TD>使用禁止文字判定</TD>
     *                  </TR>
     *                  </TABLE>
     *                  <BR>
     * @param strData   対象の文字列 <BR>
     * @return 指定した文字列が正規表現に一致する場合、true<BR>
     *         指定した文字列が正規表現に一致しない場合、false<BR>
     */
    public static boolean checkFormat(String strFormat, String strData) {
        boolean bResult = false;
        bResult = Pattern.matches(strFormat, strData);

        return bResult;
    }

    /**
     * 全角かな文字（ひらがな、カタカナ）チェック関数<BR>
     *
     * @param strData 対象の文字列 <BR>
     * @return 判定結果を返す<BR>
     *         全角かなのみで構成されている場合、true<BR>
     *         （空文字("")の場合は、trueを返す）<BR>
     *         全角かな以外の文字が含まれている場合、false<BR>
     */
    public static boolean checkFullWidthKANA(String strData) {
        if (null == strData) {
            return false;
        }

        if (strData.equals("")) {
            return true;
        }

        return StringCheck.checkFormat(StringCheck.FORMAT_WIDE_KANA, strData);
    }

    /**
     * 全角文字チェック関数<BR>
     *
     * @param strData 対象の文字列
     * @return 判定結果を返す<BR>
     *         全角文字のみで構成されている場合、true<BR>
     *         （空文字("")の場合は、trueを返す）<BR>
     *         全角文字以外の文字が含まれている場合、false<BR>
     */
    public static boolean checkFullWidthChar(String strData) {
        // メモ：全角文字コードの調査が十分に行えていないので
        // Unicodeでの文字列バイト長とShift-JISコードに
        // 変換した時のバイト長が一致した場合に
        // 指定された文字列が全角であると判定する
        int iLengthUnicode = 0;
        int iLenfthShiftJIS = 0;

        if (null == strData) {
            return false;
        }

        if (strData.equals("")) {
            return true;
        }

        try {
            // Unicodeでのバイト長を求める（文字列長を２倍する）
            iLengthUnicode = strData.length() * 2;
            // ShiftJISでのバイト長を求める
            iLenfthShiftJIS = StringCheck.getShiftJISByteLength(strData);
            if (iLengthUnicode == iLenfthShiftJIS) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 文字列のバイト数の取得関数
     *
     * @param strData 対象の文字列
     * @return 文字列のバイト数<BR>
     *         null文字を指定された場合、-1を返す<BR>
     */
    public static int getShiftJISByteLength(String strData) {
        int iLength = 0;
        byte[] byteData = null;
        if (null == strData) {
            return -1;
        }
        try {
            byteData = strData.getBytes("Windows-31j");
        } catch (Exception e) {
            iLength = -1;
        } finally {
            iLength = byteData.length;
        }
        return iLength;
    }

    /**
     * 使用禁止文字チェック関数
     *
     * @param strData 対象の文字列
     * @return 判定結果を返す<BR>
     *         使用禁止文字が含まれている場合、true<BR>
     *         使用禁止文字が含まれていない場合、false<BR>
     *         （空文字("")の場合は、falseを返す）<BR>
     *         <BR>
     *         １バイト文字コードの入力禁止文字は0x0a,0x0d,0x20-0x7E以外の文字とする<BR>
     *         ２バイト文字コードの入力禁止文字はJIS X 0208の9区〜15区、85区〜94区とする<BR>
     */
    public static boolean checkProhibitionChar(String strData) {
        if (null == strData) {
            return false;
        }

        if (strData.equals("")) {
            return false;
        }

        int iIndex = 0;
        int iLength = strData.length();
        int iData = 0;
        int iResult = 0;
        ArrayList<Character> charList = new ArrayList<Character>(6000);
        charList.addAll(Arrays.asList(StringCheckCode1.CODE));
        charList.addAll(Arrays.asList(StringCheckCode2.CODE));
        charList.addAll(Arrays.asList(StringCheckCode3.CODE));

        // 文字列数分、ループする
        while (iIndex < iLength) {
            // iIndex番目の文字を取得する
            iData = strData.charAt(iIndex);

            // 分割した物を1つにまとめてから検索をかけるように変更
            iResult = charList.indexOf((char) iData);

            // -1の場合、入力可能文字テーブルに文字がない場合なので、入力禁止文字と判定する
            if (-1 == iResult) {
                // 入力禁止文字が含まれているので、trueを返す
                return true;
            } else if (iResult < -1) {
                //// -1より小さい場合（入力可能文字テーブルに同じコード文字がない）
                return true;
            }
            iIndex++;
        }
        // 全て入力可能文字で構成されている場合、falseを返す
        return false;
    }

    /**
     * 使用禁止文字チェック関数2<BR>
     * 指定されたラベルフォーマット番号がプロパティファイル内にあれば
     * �r（0x338e）を入力可能文字として追加してから使用禁止文字が無いかチェックを行う。<BR>
     *
     * @param strData        対象の文字列
     * @param omoteLabelCode ラベルフォーマット番号
     * @return 判定結果を返す<BR>
     *         使用禁止文字が含まれている場合、true<BR>
     *         使用禁止文字が含まれていない場合、false<BR>
     *         （空文字("")の場合は、falseを返す）<BR>
     */
    public static boolean checkProhibitionChar2(String strData, String omoteLabelCode) {

        if (null == strData) {
            return false;
        }

        if (strData.equals("")) {
            return false;
        }
        int iIndex = 0;
        int iLength = strData.length();
        int iData = 0;
        int iResult = 0;
        // StringCheckCodeの配列をリストに変換する
        ArrayList<Character> charList = new ArrayList<Character>(6000);
        charList.addAll(Arrays.asList(StringCheckCode1.CODE));
        charList.addAll(Arrays.asList(StringCheckCode2.CODE));
        charList.addAll(Arrays.asList(StringCheckCode3.CODE));

        // プロパティファイル読み込み
        if (DataAccessConfig.getLabelFormat() != null) {
            String[] formatNumbers = DataAccessConfig.getLabelFormat().split(",");
            if (Arrays.asList(formatNumbers).contains(omoteLabelCode)) {
                // プロパティファイルのLabelFormat+に対象商品のラベルフォーマット番号が存在すれば
                // �r(0x338e)を入力可能文字リストに追加
                charList.add(StringCheckCode1.MILLIGRAM_CODE);
            }
        }

        // 文字列数分、ループする
        while (iIndex < iLength) {
            // iIndex番目の文字を取得する
            iData = strData.charAt(iIndex);
            iResult = charList.indexOf((char) iData);
            // -1の場合、入力可能文字テーブルに文字がない場合なので、入力禁止文字と判定する
            if (-1 == iResult) {
                // 入力禁止文字が含まれているので、trueを返す
                return true;
            } else if (iResult < -1) {
                // -1より小さい場合（入力可能文字テーブルに同じコード文字がない）
                return true;
            }
            iIndex++;
        }
        // 全て入力可能文字で構成されている場合、falseを返す
        return false;
    }

    /**
     * 半角英数字チェック関数
     *
     * @param strData 対象文字列
     * @return 判定結果を返す<BR>
     *         半角英数字のみで構成されている場合、true<BR>
     *         （空文字("")の場合は、trueを返す）<BR>
     *         半角英数字のみで構成されている場合、false<BR>
     */
    public static boolean checkHalfWidthChar(String strData) {
        if (null == strData) {
            return false;
        }

        if (strData.equals("")) {
            return false;
        }

        return StringCheck.checkFormat(StringCheck.FORMAT_HANKAKU_EISUUJI, strData);
    }

    /**
     * 半角数字チェック関数
     *
     * @param strData 対象の文字列
     * @return 判定結果を返す<BR>
     *         半角数字のみで構成されている場合、true<BR>
     *         （空文字("")の場合は、trueを返す）<BR>
     *         半角数字以外の文字を含む場合、false<BR>
     */
    public static boolean checkNumeric(String strData) {
        if (null == strData) {
            return true;
        }

        if (strData.equals("")) {
            return true;
        }

        return StringCheck.checkFormat(StringCheck.FORMAT_HANKAKU_SUUJI, strData);
    }

    /**
     * 整数チェック関数
     *
     * @param strData 対象の文字列
     * @return 判定結果を返す<BR>
     *         半角数字のみで構成されている場合、true<BR>
     *         （空文字("")の場合は、trueを返す）<BR>
     *         半角数字以外の文字を含む場合、false<BR>
     */
    public static boolean checkInteger(String strData) {
        if (null == strData) {
            return true;
        }

        if (strData.equals("")) {
            return true;
        }

        return StringCheck.checkFormat(StringCheck.FORMAT_INTEGER, strData);
    }

    /**
     * 全半角入力文字チェック関数<BR>
     * 本関数では、使用禁止文字が含まれていないことを判定する
     *
     * @param strData 対象の文字列
     * @return 判定結果を返す<BR>
     *         使用禁止文字が含まれていない場合、true<BR>
     *         （空文字("")の場合は、trueを返す）<BR>
     *         使用禁止文字が含まれている場合、false<BR>
     *         <BR>
     *         １バイト文字コードの入力禁止文字は0x0a,0x0d,0x20-0x7E以外の文字とする<BR>
     *         ２バイト文字コードの入力禁止文字はJIS X 0208の9区〜15区、85区〜94区とする<BR>
     */
    public static boolean checkZenhankaku(String strData) {
        if (null == strData) {
            return true;
        }

        if (strData.equals("")) {
            return true;
        }
        boolean bResult = false;
        // 入力禁止文字が含まれているかを判定する
        bResult = StringCheck.checkProhibitionChar(strData);
        // 入力禁止文字が含まれている場合、falseを返す
        if (bResult) {
            return false;
        }
        // trueを返す
        return true;
    }

    /**
     * 全角入力文字チェック関数<BR>
     * 本関数では、全角文字で構成され、かつ、使用禁止文字が含まれていないことを判定する
     *
     * @param strData 対象の文字列
     * @return 判定結果を返す<BR>
     *         全角文字で構成され、かつ、使用禁止文字が含まれていない場合、true<BR>
     *         （空文字("")の場合は、trueを返す）<BR>
     *         全角文字以外で構成、または、使用禁止文字が含まれている場合、false<BR>
     */
    public static boolean checkZenkaku(String strData) {
        boolean bResult = false;
        if (null == strData) {
            return true;
        }

        if (strData.equals("")) {
            return true;
        }
        // 全角文字チェック
        bResult = StringCheck.checkFullWidthChar(strData);
        // 全角文字のみで構成されていない場合、falseを返す
        if (!bResult) {
            return false;
        }
        // 入力禁止文字が含まれているかを判定する
        bResult = StringCheck.checkProhibitionChar(strData);
        // 入力禁止文字が含まれている場合、falseを返す
        if (bResult) {
            return false;
        }
        // 全ての判定条件をクリアしているのでtrueを返す
        return true;
    }

    /**
     * 全角入力文字チェック関数2<BR>
     * 本関数では、全角文字で構成され、かつ、使用禁止文字が含まれていないことを判定する<BR>
     * 指定された文字リストの文字を入力可能文字として追加してから使用禁止文字が無いかチェックを行う。
     *
     * @param strData        対象の文字列
     * @param omoteLabelCode
     * @return 判定結果を返す<BR>
     *         全角文字で構成され、かつ、使用禁止文字が含まれていない場合、true<BR>
     *         （空文字("")の場合は、trueを返す）<BR>
     *         全角文字以外で構成、または、使用禁止文字が含まれている場合、false<BR>
     */
    public static boolean checkZenkaku2(String strData, String omoteLabelCode) {
        boolean bResult = false;
        if (null == strData) {
            return true;
        }

        if (strData.equals("")) {
            return true;
        }
        // 全角文字チェック
        bResult = StringCheck.checkFullWidthChar(strData);
        // 全角文字のみで構成されていない場合、falseを返す
        if (!bResult) {
            return false;
        }
        // 入力禁止文字が含まれているかを判定する
        bResult = StringCheck.checkProhibitionChar2(strData, omoteLabelCode);
        // 入力禁止文字が含まれている場合、falseを返す
        if (bResult) {
            return false;
        }
        // 全ての判定条件をクリアしているのでtrueを返す
        return true;
    }

    /**
     * 全角入力文字チェック関数<BR>
     * 本関数では、全角文字で構成され、かつ、使用禁止文字が含まれていないことを判定する (改行コードCR-LFはＯＫとする )
     *
     * @param strData 対象の文字列
     * @return 判定結果を返す<BR>
     *         全角文字で構成され、かつ、使用禁止文字が含まれていない場合、true<BR>
     *         （空文字("")の場合は、trueを返す）<BR>
     *         全角文字以外で構成、または、使用禁止文字が含まれている場合、false<BR>
     */
    public static boolean checkZenkakuCRLF(String strData) {
        boolean bResult = false;
        if (null == strData) {
            return true;
        }

        if (strData.equals("")) {
            return true;
        }

        int iStart = 0;
        int iEnd = 0;
        strData = strData.replaceAll("\r\n", "\n").replaceAll("\n", "\r\n");
        do {

            iEnd = strData.indexOf("\r\n", iStart);
            if (-1 == iEnd) {
                iEnd = strData.length();
            } else if (iStart == iEnd) {
                iStart = iEnd + 2;
                continue;
            }
            String strData2 = strData.substring(iStart, iEnd);
            // 全角文字チェック
            bResult = StringCheck.checkFullWidthChar(strData2);
            // 全角文字のみで構成されていない場合、falseを返す
            if (!bResult) {
                return false;
            }
            iStart = iEnd + 2;
        } while (iStart < strData.length());
        // 入力禁止文字が含まれているかを判定する
        bResult = StringCheck.checkProhibitionChar(strData);
        // 入力禁止文字が含まれている場合、falseを返す
        if (bResult) {
            return false;
        }
        // 全ての判定条件をクリアしているのでtrueを返す
        return true;
    }

    /**
     * 文字列長チェック関数<BR>
     * 本関数では、Shift-JISコードでの長さで指定文字列が指定文字数以下であることを判定する
     *
     * @param strData 対象の文字列
     * @param iLength 対象の文字列長さ
     * @return 判定結果を返す<BR>
     *         文字列長が指定文字数以下の場合、true<BR>
     *         文字列長が指定文字数を超える場合、false<BR>
     */
    public static boolean checkLength(String strData, int iLength) {
//        strData = strData.replaceAll("\r\n", "\n").replaceAll("\n", "\r\n");
        if (null == strData || strData.length() == 0) {
            return true;
        }
        // 入力されたデータの文字列長(Shift-JIS)を取得する
        int iStrLength = StringCheck.getShiftJISByteLength(strData);
        // 文字列長を取得できなかった場合、falseを返す
        if (-1 == iStrLength) {
            return false;
        } else if (iStrLength <= iLength) {
            // 文字列長が指定数以下の場合trueを返す
            return true;
        }
        return false;
    }

    /**
     * 文字列長チェック関数<BR>
     * 本関数では、Shift-JISコードでの長さで指定文字列が指定文字数以下であることを判定する
     *
     * @param strData 対象の文字列
     * @param iLength 対象の文字列長さ
     * @return 判定結果を返す<BR>
     *         文字列長が指定文字数以下の場合、true<BR>
     *         文字列長が指定文字数を超える場合、false<BR>
     */
    public static boolean checkEqualLength(String strData, int iLength) {

        if (null == strData || strData.length() == 0) {
            return true;
        }

        // 入力されたデータの文字列長(Shift-JIS)を取得する
        int iStrLength = StringCheck.getShiftJISByteLength(strData);
        // 文字列長を取得できなかった場合、falseを返す
        if (-1 == iStrLength) {
            return false;
        } else if (iStrLength == iLength) {
            // 文字列長が指定数以下の場合trueを返す
            return true;
        }
        return false;
    }

    /**
     * 複数キーワード個数チェック関数<BR>
     * 本関数では、Shift-JISコードでの長さで指定文字列が指定文字数以下であることを判定する
     *
     * @param strData 対象の文字列
     * @param count 複数キーワード最大個数
     * @return 判定結果を返す<BR>
     *         最大個数が指定個数以下の場合、true<BR>
     *         最大個数が指定個数を超える場合、false<BR>
     */
    public static boolean checkMultKeyWordsCount(String strData, int count) {
        if (null == strData || strData.length() == 0) {
            return true;
        }
        return StringUtil.splitBySapce(strData).length <= count;
    }

    /**
     * 全角カタカナ文字チェック関数<BR>
     *
     * @param strData 対象の文字列
     * @return 判定結果を返す<BR>
     *         全角カタカナ（空白文字を含む）のみで構成されている場合、true<BR>
     *         （空文字("")の場合は、trueを返す）<BR>
     *         全角カタカナ（空白文字を含む）以外の文字が含まれている場合、false<BR>
     */
    public static boolean checkFullWidthKATAKANA(String strData) {
        if (null == strData) {
            return true;
        }

        if (strData.equals("")) {
            return true;
        }

        return StringCheck.checkFormat(StringCheck.FORMAT_WIDE_KATAKANA, strData);
    }

    /**
     * 全半角入力文字チェック関数<BR>
     * 本関数では、使用禁止文字、半角スペースが含まれていないことを判定する
     *
     * @param strData 対象の文字列
     * @return 判定結果を返す<BR>
     *         使用禁止文字、半角スペースが含まれていない場合、true<BR>
     *         （空文字("")の場合は、trueを返す）<BR>
     *         使用禁止文字、半角スペースが含まれている場合、false<BR>
     *         <BR>
     *         １バイト文字コードの入力禁止文字は0x0a,0x0d,0x20-0x7E以外の文字とする<BR>
     *         ２バイト文字コードの入力禁止文字はJIS X 0208の9区〜15区、85区〜94区とする<BR>
     */
    public static boolean checkZenhankakuSpace(String strData) {
        if (null == strData) {
            return true;
        }

        if (strData.equals("")) {
            return true;
        }
        boolean bResult = false;
        // 入力禁止文字が含まれているかを判定する
        bResult = StringCheck.checkProhibitionChar(strData);
        // 入力禁止文字が含まれている場合、falseを返す
        if (bResult) {
            return false;
        }
        // 半角スペースが含まれているかを判定する
        int iIndex = strData.indexOf(" ");
        // 半角スペースが含まれている場合、falseを返す
        if (-1 != iIndex) {
            return false;
        }

        // trueを返す
        return true;
    }

    /**
     * Windowsでファイル名,フォルダ名に使用できない文字を全角に変更する。
     *
     * @param fileName ファイル名
     * @return 変更後の文字列
     */
    public static String changeFileProhibitionWordForWindows(String fileName) {
        String result = fileName;
        // \ / : * ? " < > |
        result = result.replace('\\', '￥');
        result = result.replace('/', '／');
        result = result.replace(':', '：');
        result = result.replace('*', '＊');
        result = result.replace('?', '？');
        result = result.replace('"', '”');
        result = result.replace('<', '＜');
        result = result.replace('>', '＞');
        result = result.replace('|', '｜');
        return result;
    }

    /**
     * Access2007以降でファイルからデータをインポートする際にファイル名に「＆」(U+FF06)「＜」(U+FF1C)「＞」(U+FF1E)
     * が付いていると取り込みに失敗するため指定された文字に置換する。
     *
     * @param fileName ファイル名、フォルダ名
     * @param word     この文字列に置換する
     * @return 変更後の文字列
     */
    public static String changeFileProhibitionWordForAccess(String fileName, String word) {
        String result = fileName;
        result = result.replace("＆", word);
        result = result.replace("＜", word);
        result = result.replace("＞", word);
        return result;
    }

    /**
     * 全角ダブルクォーテーションを指定された文字に置換する。
     *
     * @param fileName ファイル名、フォルダ名
     * @param word     この文字列に置換する
     * @return 変更後の文字列
     */
    public static String changeFileProhibitionWordForExcel(String fileName, String word) {
        String result = fileName;
        result = result.replace("”", word);
        return result;
    }
}
