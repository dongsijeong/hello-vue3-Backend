package demo.mo.util;

import java.beans.FeatureDescriptor;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;

import demo.mo.config.DataAccessConfig;
import demo.mo.constants.Constant.MESSAGECODE;
import demo.mo.exception.BusinessException;
import demo.mo.exception.SystemException;
import demo.mo.model.CommonDropdownListModel;
import demo.mo.model.MessageModel;

public final class CommonUtil {
    private CommonUtil() {
    }
    /**
     * リストの繰り返しに繰り返し変数を追加する機能
     *
     * @param <T>
     * @param consumer
     * @return 関数
     */
    public static <T> Consumer<T> consumerWithIndex(BiConsumer<T, Integer> consumer) {
        // inner class for counting.
        class Obj {
            private int i;
        }
        Obj obj = new Obj();
        return t -> {
            int index = obj.i++;
            consumer.accept(t, index);
        };
    }

    /**
     * 登録情報の作成関数
     *
     * @return 登録情報の文字列を返す<BR>
     *         例：'01234567',SYSDATE,'01234567',SYSDATE<BR>
     */
    public static String createInsertInfo() {
        String date = DateString.getDateTimeString();
        return "'" + UserInfoManager.getKousinID() + "','" + date
        + "','" + UserInfoManager.getKousinID() + "','" + date + "'";
    }

    /**
     * 更新情報の作成関数
     *
     * @return 更新情報の文字列を返す<BR>
     *         例：KOSIN_ID='01234567',KOSIN_DM=SYSDATE<BR>
     */
    public static String createUpdateInfo() {
        return "KOSIN_ID = '" + UserInfoManager.getKousinID()
        + "', KOSIN_DM = '" + DateString.getDateTimeString() + "'";
    }

    /**
     * 現在日を取得し、SQL挿入用日付文字列(YYYYMMDDhhmmss形式)に変換
     *
     * @return 日付文字列
     */
    public static String convYYYYMMDDhhmmssFromNowDate() {
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        String hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(cal.get(Calendar.MINUTE));
        String second = String.valueOf(cal.get(Calendar.SECOND));

        StringBuffer sqlStr = new StringBuffer(year);
        if (month.length() == 1) {
            sqlStr.append("0");
        }
        sqlStr.append(month);
        if (day.length() == 1) {
            sqlStr.append("0");
        }
        sqlStr.append(day);
        if (hour.length() == 1) {
            sqlStr.append("0");
        }
        sqlStr.append(hour);
        if (minute.length() == 1) {
            sqlStr.append("0");
        }
        sqlStr.append(minute);
        if (second.length() == 1) {
            sqlStr.append("0");
        }
        sqlStr.append(second);
        return sqlStr.toString();
    }

    /**
     * 対象にヌルでないプロパティを取得する
     *
     * @param source 対象オブジェクト
     * @return ヌルでないプロパティ名前の配列
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null).toArray(String[]::new);
    }

    /**
     * 該当クラスの全部のプロパティ空文字→Nullに変換する
     *
     * @param source            対象オブジェクト
     */
    public static void converEmptyToNull(Object source) {
        Class<?> c = source.getClass();
        Field[] fs = c.getDeclaredFields();
        List<String> list = new ArrayList<String>();
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);

        for (int i = 0; i < fs.length; i++) {
            if (fs[i].getType().equals(String.class)) {
                list.add(fs[i].getName());
                String value = (String) wrappedSource.getPropertyValue(fs[i].getName());
                if ("".equals(value)) {
                    wrappedSource.setPropertyValue(new PropertyValue(fs[i].getName(), null));
                }
            }
        }
    }

    /**
     * 該当クラスの全部のプロパティtrimSpaceメソッドを利用してTrimします。
     *
     * @param source            対象オブジェクト
     */
    public static void trimObject(Object source) {
        if (source == null) {
            return;
        }
        Class<?> c = source.getClass();
        Field[] fs = c.getDeclaredFields();
        List<String> list = new ArrayList<String>();
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);

        for (int i = 0; i < fs.length; i++) {
            if (fs[i].getType().equals(String.class)) {
                list.add(fs[i].getName());
                String value = (String) wrappedSource.getPropertyValue(fs[i].getName());
                wrappedSource.setPropertyValue(new PropertyValue(fs[i].getName(), StringCheck.trimSpace(value)));
            }
        }
    }

    /**
     * 該当リスト中のクラスの全部のプロパティtrimSpaceメソッドを利用してTrimします。
     *
     * @param sourceList            対象オブジェクト
     */
    public static void trimObjectList(List<?> sourceList) {
        if (sourceList == null || sourceList.size() == 0) {
            return;
        }
        for (Object source: sourceList) {
            trimObject(source);
        }
    }

    /**
     * 該当クラスの全部のプロパティ（親クラスのプロパティが対象外）に操作を適応する
     *
     * @param source            対象オブジェクト
     * @param operationFunction 操作関数
     */
    public static void applyOperation2Properties(Object source, Function<String, String> operationFunction) {
        Class<?> c = source.getClass();
        Field[] fs = c.getDeclaredFields();
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < fs.length; i++) {
            if (fs[i].getType().equals(String.class)) {
                list.add(fs[i].getName());
            }
        }

        applyOperation2Properties(source, list.toArray(new String[0]), operationFunction);
    }

    /**
     * プロパティに操作を適応する
     *
     * @param source            対象オブジェクト
     * @param properitesArray   プロパティ配列
     * @param operationFunction 操作関数(一つパラメーターと一つ返し結果)
     */
    public static void applyOperation2Properties(Object source, String[] properitesArray,
            Function<String, String> operationFunction) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        Stream.of(properitesArray).forEach(propertyName -> {
            String value = (String) wrappedSource.getPropertyValue(propertyName);
            wrappedSource.setPropertyValue(new PropertyValue(propertyName, operationFunction.apply(value)));
        });
    }

    /**
     * プロパティに操作を適応する
     *
     * @param source            対象オブジェクト
     * @param properitesArray   プロパティ配列
     * @param intArray   パラメーター配列
     * @param operationFunction 操作関数(2つパラメーター(String,Integer)と一つ返し結果)
     */
    public static void applyOperation2Properties(Object source, String[] properitesArray, Integer[] intArray,
            BiFunction<String, Integer, String> operationFunction) {

        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        Stream.of(properitesArray).forEach(consumerWithIndex((propertyName, index) -> {
            String value = (String) wrappedSource.getPropertyValue(propertyName);
            wrappedSource
                    .setPropertyValue(new PropertyValue(propertyName, operationFunction.apply(value, intArray[index])));
            index++;
        }));
    }

    /**
     * プロパティに操作を適応する
     *
     * @param source            対象オブジェクト
     * @param properitesArray   プロパティ配列
     * @param operationFunction 操作関数(一つパラメーターと一つ返し結果)
     */
    public static void applyOperation2PropertiesNullCase(Object source, String[] properitesArray,
            Function<String, String> operationFunction) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        Stream.of(properitesArray).forEach(propertyName -> {
            String value = (String) wrappedSource.getPropertyValue(propertyName) == null ? ""
                    : (String) wrappedSource.getPropertyValue(propertyName);
            wrappedSource.setPropertyValue(new PropertyValue(propertyName, operationFunction.apply(value)));
        });
    }

    /**
     * 現在日を取得し、SQL挿入用日付文字列(YYYYMMDDhhmmss形式)に変換
     *
     * @return 日付文字列
     */
    public static String convYYYYMMDDFromNowDate() {
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));

        StringBuffer sqlStr = new StringBuffer(year);
        if (month.length() == 1) {
            sqlStr.append("0");
        }
        sqlStr.append(month);
        if (day.length() == 1) {
            sqlStr.append("0");
        }
        sqlStr.append(day);
        return sqlStr.toString();
    }

    /**
     * プロパティの数字チェック
     *
     * @param valueOfProperty プロパティの値
     * @param msgList         メッセージリスト
     * @param param           メッセージパラメーター
     */
    public static void numberCheck(String valueOfProperty, List<MessageModel> msgList, String param) {
        if (!DecimalString.checkNumber(valueOfProperty)) {
            addErrorMsg(msgList, MESSAGECODE.E_CM_0002_1002, new String[] {param});
        }
    }


    /**
     * プロパティの数字チェック
     *
     * @param valueOfProperty プロパティの値
     * @param msgList         メッセージリスト
     * @param fieldName       フィルド名称
     * @param param           メッセージパラメーター
     */
    public static void numberCheck(String valueOfProperty, List<MessageModel> msgList, String fieldName, String param) {
        if (!DecimalString.checkNumber(valueOfProperty)) {
            addErrorMsg(msgList, MESSAGECODE.E_CM_0002_1002, fieldName, new String[] {param});
        }
    }

    /**
     * プロパティの空欄チェック
     *
     * @param valueOfProperty プロパティの値
     * @param msgList         メッセージリスト
     * @param param           メッセージパラメーター
     */
    public static void emptyCheck(String valueOfProperty, List<MessageModel> msgList, String param) {
        emptyCheck(valueOfProperty, msgList, MESSAGECODE.E_CM_0001_1001, new String[] {param});
    }

    /**
     * プロパティの空欄チェック
     *
     * @param valueOfProperty プロパティの値
     * @param msgList         メッセージリスト
     * @param fieldName       フィルド名称
     * @param param           メッセージパラメーター
     */
    public static void emptyCheck(String valueOfProperty, List<MessageModel> msgList, String fieldName, String param) {
        emptyCheck(valueOfProperty, msgList, MESSAGECODE.E_CM_0001_1001, fieldName, new String[] {param});
    }

    /**
     * プロパティの空欄チェック
     *
     * @param valueOfProperty プロパティの値
     * @param msgList         メッセージリスト
     * @param messageId       メッセージID
     * @param params          メッセージパラメーター配列
     */
    public static void emptyCheck(String valueOfProperty, List<MessageModel> msgList, String messageId,
            String[] params) {
        if (StringUtils.isEmpty(valueOfProperty)) {
            addErrorMsg(msgList, messageId, params);
        }
    }


    /**
     * プロパティの空欄チェック
     *
     * @param valueOfProperty プロパティの値
     * @param msgList         メッセージリスト
     * @param messageId       メッセージID
     * @param fieldName       フィルド名称
     * @param params          メッセージパラメーター配列
     */
    public static void emptyCheck(String valueOfProperty, List<MessageModel> msgList, String messageId,
            String fieldName, String[] params) {
        if (StringUtils.isEmpty(valueOfProperty)) {
            addErrorMsg(msgList, messageId, fieldName, params);
        }
    }
    /**
     * エラーメッセージの追加
     *
     * @param msgList   メッセージリスト
     * @param messageId メッセージID
     * @param params    メッセージパラメーター
     */
    public static void addErrorMsg(List<MessageModel> msgList, String messageId, String[] params) {
        MessageModel msg = new MessageModel();
        msg.setMessageId(messageId);
        if (params != null) {
            msg.setParams(params);
        }

        msgList.add(msg);
    }

    /**
     * エラーメッセージの追加
     *
     * @param msgList   メッセージリスト
     * @param messageId メッセージID
     * @param fieldName フィルド名称
     * @param params    メッセージパラメーター
     */
    public static void addErrorMsg(List<MessageModel> msgList, String messageId, String fieldName, String[] params) {
        MessageModel msg = new MessageModel();
        msg.setMessageId(messageId);
        msg.setFleldName(fieldName);
        if (params != null) {
            msg.setParams(params);
        }

        msgList.add(msg);
    }

    /**
     * 共通単価チェック
     * @param target
     * @param msgList
     * @param params
     */
    public static void commonTankaCheck(String target, List<MessageModel> msgList, String[] params) {
        if (StringUtils.isEmpty(target)) {
            /* 空文字列の場合はエラーとする */
            addErrorMsg(msgList, MESSAGECODE.E_CM_0001_1001, params);
        }
        if (!DecimalString.checkNumber(target)) {
            /* 数字以外の場合はエラーとする */
            addErrorMsg(msgList, MESSAGECODE.E_CM_0002_1002, params);
        }
        if (!DecimalString.checkFormat(6, 2, target)) {
            /* 数字フォーマットが異なる場合はエラーとする */
            addErrorMsg(msgList, MESSAGECODE.E_CM_0003_1003, params);
        }
    }

    /**
     * 整合性ある文字列を返す
     * @param str
     * @return 文字列
     */
    public static String getValidateString(String str) {
        return str == null ? "" : StringCheck.trimSpace(str);
    }

    /**
     * #.500とデータが入力された場合、#.5の数値に修正
     *
     * @param strNumber 要な小数点を含む数値
     * @return 不要な小数点を削除した数値
     */
    public static String removeNumber(String strNumber) {
        int iLength = 0;
        int iCnt = 0;
        char chrC = 0;
        String newNumberSeisuu = "";
        String addComma = "";
        String newNumberSyousuu = "";
        String numberTemp = "";
        int flg = 0;
        int iCount = 0;
        try {
            if (0 != strNumber.length()) {
                // 数値の長さを取得する
                iLength = strNumber.length();
                // 文字列を整数、"."、少数別に格納する
                for (iCnt = 0; iCnt < iLength; iCnt++) {
                    chrC = strNumber.charAt(iCnt);

                    // 数字のみを抜き出す
                    if (chrC == '.') {
                        addComma = addComma + chrC;
                        flg = 1;
                    } else if (flg == 0) {
                        // 整数を連結させる
                        newNumberSeisuu = newNumberSeisuu + chrC;
                    } else {
                        // 小数を連結させる
                        newNumberSyousuu = newNumberSyousuu + chrC;
                    }

                }

                // 少数の長さを取得する
                iLength = newNumberSyousuu.length();
                for (iCnt = iLength - 1; iCnt >= 0; iCnt--) {
                    // 現在の文字列をチェック
                    chrC = newNumberSyousuu.charAt(iCnt);

                    // 現在の文字列が'0'かチェック
                    if (chrC != '0') {
                        break;
                    } else {
                        iCount++;
                    }
                }

                if (iCount != newNumberSyousuu.length()) {
                    for (iCnt = 0; iCnt < newNumberSyousuu.length() - iCount; iCnt++) {
                        // 文字列を一文字づつ取得
                        chrC = newNumberSyousuu.charAt(iCnt);
                        // 小数を連結させる
                        numberTemp = numberTemp + chrC;
                    }
                    strNumber = newNumberSeisuu + addComma + numberTemp;
                } else {
                    strNumber = newNumberSeisuu;
                }

            } else {
            // 文字列がNullの場合空白を返す
                return newNumberSeisuu;
            }
        } catch (Exception e) {
            // 例外処理
            return newNumberSeisuu;
        }
        return strNumber;
    }

    /**
     * #.500とデータが入力された場合、#.5の数値に修正
     *
     * @param strData 要な小数点を含む数値
     * @return 不要な小数点を削除した数値
     */
    public static String removeNumberWithNull(String strData) {
        String removedNumberStr = CommonUtil.removeNumber(strData);
        return "".equals(removedNumberStr) ? null : removedNumberStr;
    }

    /**
     * <pre>
     * 指定された日付の前日を取得する。
     * </pre>
     *
     * @param year  年（例：２００１）
     * @param month 月（例：０１ or ２）
     * @param day   日付（例：１０）
     * @return 前日の日付（フォーマット：2001/10/21）
     */
    public static String getYesterday(String year, String month, String day) {
        Calendar cal = Calendar.getInstance();
        int iyear = Integer.parseInt(year);
        int imonth = Integer.parseInt(month);
        imonth--; // 月は0オリジン
        int iday = Integer.parseInt(day) - 1; // 前日を指定

        cal.set(iyear, imonth, iday);

        java.util.Date date = cal.getTime();

        DateFormat dfDate = DateFormat.getDateInstance();
        return dfDate.format(date);
    }

    /**
     * <pre>
     * 指定された日付の前日を取得する。
     * </pre>
     *
     * @param day yyyy-mm-dd
     * @return 前日の日付（フォーマット：2001-10-21）
     */
    public static String getYesterdayNew(String day) {

        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(day);
        } catch (ParseException e) {
            throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4024_4024));
        }
        c.setTime(date);
        int day1 = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day1 - 1);
        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

        return dayAfter;
    }

    /**
     * <pre>
     * 指定された日付の前日を取得する。
     * 年月日で８桁とする。
     * </pre>
     *
     * @param today （例：20020805）
     * @return 前日の日付（フォーマット：2001/10/21
     */
    public static String getYesterday(String today) {

        String year = today.substring(0, 4);
        String month = today.substring(4, 6);
        String day = today.substring(6, 8);
//        return getYesterday(year, month, day);
        return getYesterdayNew(year + "-" + month + "-" + day);
    }

    /**
     * <pre>
     * 投入する原材料/半製品の投入番号の番号抜けチェックを行う。
     * </pre>
     *
     * @param tonyuNo 投入番号の配列
     * @return true：番号に抜けがない場合、false：番号に抜けがある場合
     */
    public static boolean checkTonyuNoOut(String[] tonyuNo) {
        try {
            for (int j = 1; j < tonyuNo.length + 1; j++) {
                for (int i = 0; i < tonyuNo.length; i++) {
                    /* 該当の番号が存在する場合はループをひとつ抜ける */
                    if (j == Integer.parseInt(tonyuNo[i])) {
                        break;
                    } else if (i == tonyuNo.length - 1) {
                        /* 最後までループがまわっても該当番号がない場合はエラー */
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * <pre>
     * 投入する原材料/半製品の投入番号が
     * １より大きく、かつ、指定されたMaxNoを超えていないかチェックを行う。
     * </pre>
     *
     * @param tonyuNo 投入番号の配列
     * @param maxNo   投入番号最大値
     * @return true：正常値、false：正常値以外を含む
     */
    public static boolean checkTonyuNoOver(String[] tonyuNo, int maxNo) {
        try {
            for (int i = 0; i < tonyuNo.length; i++) {
                /* １以下、またはMaxNo以上の場合はエラーとする */
                if ((1 > (Integer.parseInt(tonyuNo[i]))) || (maxNo < (Integer.parseInt(tonyuNo[i])))) {
                    return false;
                }
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * <pre>
     * 投入する原材料/半製品の投入番号の重複チェックを行う。
     * </pre>
     *
     * @param tonyuNo 投入番号の配列
     * @return true：重複していない、false：重複している
     * @throws なし
     * @since 1.00
     */
    public static boolean checkTonyuNoDuplication(String[] tonyuNo) {
        for (int i = 0; i < (tonyuNo.length - 1); i++) {
            for (int j = i + 1; j < tonyuNo.length; j++) {
                if (StringUtils.isNotEmpty(tonyuNo[i]) && StringUtils.isNotEmpty(tonyuNo[j])
                        && (tonyuNo[i]).equalsIgnoreCase(tonyuNo[j])) {
                    /* 投入原品が重複している場合、エラーとする */
                    return false;
                } // End if
            } // End for(j)
        } // End for(i)
        return true;
    }

    /**
     * Vuejsフロントエンド専用ドロップダウンリストの作成
     * @param codeList
     * @param nameList
     * @return 専用ドロップダウンリスト
     */
    public static List<CommonDropdownListModel> convert2DropdownList(String[] codeList, String[] nameList) {
        List<CommonDropdownListModel> jibinList = new ArrayList<>();
        int count = codeList.length;

        for (int i = 0; i < count; i++) {
            CommonDropdownListModel dropdownModel = new CommonDropdownListModel();
            dropdownModel.setCode(codeList[i]);
            dropdownModel.setName(nameList[i]);
            jibinList.add(dropdownModel);
        }
        return jibinList;
    }

    /**
     * 引数が0未満かどうかを判定
     *@param strNumber 対象となる数値
     *@return 引数が0未満ならfalse
     *        引数が0以上ならtrue
     */
    public static boolean hanteiNumber(String strNumber) {
        boolean seisuu = true;
        try {
            if (0 <= Double.parseDouble(strNumber)) {
                //引数が0以上の場合
                seisuu = true;

            } else {
                //引数が0未満の場合
                seisuu = false;
            }
            return seisuu;
        } catch (Exception e) {
            //例外処理
            return seisuu;
        }
    }

    /**
    *
    * 「現在システムは使用制限中です」表示用メッセージファイル有無を判断する。
    * メッセージ制限ファイルが存在する場合は、生産加工例外のスローを行う
    *
    */
   public static void checkMessage() {
       //チェック用メッセージファイルパス
       String fileName = StringCheck.trimSpace(DataAccessConfig.getEmgMessageFile());
       if (StringUtils.isNotEmpty(fileName) && Files.exists(Paths.get(fileName))) {
           //定義された制限ファイル存在の場合、異常終了とする
           throw new BusinessException("現在システムは使用制限中です。");
       }
   }

   /**
    * 情報取得
    * @param localFile
    * @return 情報
    */
   public static String getInfomation(String localFile) {
//       String inprefix = StringCheck.trimSpace(DataAccessConfig.getInfomationFile());
//       String profix = UUID.randomUUID().toString() + System.currentTimeMillis();
//       String tmpDir = DataAccessConfig.getUploadFileTemporaryPath();
//       String localFile = tmpDir + profix + ".txt";
//
//       String inbucketName = DataAccessConfig.getBucket();
//       try {
//           AwsS3Util aws = new AwsS3Util();
//           aws.downloadFile(inbucketName, inprefix, localFile);
//       } catch (AmazonServiceException e1) {
//           LogUtil.outAppWarlog( "\t" + ""  + "\t" + "" + "\t" + "" + "\t" + e1.getMessage());
//           LogUtil.outAppWarlog( "\t" + ""  + "\t" + "" + "\t" + "" + "\t" + "awsダウンロード失敗");
//           return null;
//       } catch (AmazonClientException e1) {
//           LogUtil.outAppWarlog( "\t" + ""  + "\t" + "" + "\t" + "" + "\t" + e1.getMessage());
//           LogUtil.outAppWarlog( "\t" + ""  + "\t" + "" + "\t" + "" + "\t" + "awsダウンロード失敗");
//           return null;
//       } catch (InterruptedException e1) {
//           LogUtil.outAppWarlog( "\t" + ""  + "\t" + "" + "\t" + "" + "\t" + e1.getMessage());
//           LogUtil.outAppWarlog( "\t" + ""  + "\t" + "" + "\t" + "" + "\t" + "awsダウンロード失敗");
//           return null;
//       } catch (Exception e1) {
//           LogUtil.outAppWarlog( "\t" + ""  + "\t" + "" + "\t" + "" + "\t" + e1.getMessage());
//           LogUtil.outAppWarlog( "\t" + ""  + "\t" + "" + "\t" + "" + "\t" + "awsダウンロード失敗");
//           return null;
//       }
       try (FileInputStream fileStream = new FileInputStream(localFile);
//               InputStreamReader inStream = new InputStreamReader( fileStream, "Windows-31J");
               InputStreamReader inStream = new InputStreamReader(fileStream, "UTF-8");
               BufferedReader bufReader = new BufferedReader(inStream);) {

           StringBuffer strBuffer = new StringBuffer();
           String strReadData = null;
           while (true) {
               strReadData = bufReader.readLine();
               if (null == strReadData) {
                   break;
               }
               strBuffer.append(strReadData).append("<br>");
           }
           return strBuffer.toString();
       } catch (FileNotFoundException fe) {
           return null;
       } catch (Exception e) {
           return null;
       } finally {
           File file = new File(localFile);
           if (file.exists()) {
               file.delete();
           }
       }
   }

   /**
    * emg情報取得
    * @return emg情報
    */
   public static String getEmgMessage() {
       String strMessageFile = StringCheck.trimSpace(DataAccessConfig.getEmgMessageFile());
       if (!StringUtils.isNotEmpty(strMessageFile) || !Files.exists(Paths.get(strMessageFile))) {
           return null;
       }
       try (FileInputStream fileStream = new FileInputStream(strMessageFile);
               InputStreamReader inStream = new InputStreamReader(fileStream);
               BufferedReader bufReader = new BufferedReader(inStream);) {

           StringBuffer strBuffer = new StringBuffer();
           String strReadData = null;
           while (true) {
               strReadData = bufReader.readLine();
               if (null == strReadData) {
                   break;
               }
               strBuffer.append(strReadData).append("<br>");
           }
           return strBuffer.toString();
       } catch (FileNotFoundException fe) {
           return null;
       } catch (Exception e) {
           throw new SystemException(DataAccessConfig.getMessage("DE_CM0006.10006"));
       }
   }
   /**
    * 指定したディレクトリ下のすべてファイル一覧を取得
    * @param file
    * @param fileList
    */
   private static void getFileList(File file, List<String> fileList) {
        File[] files = file.listFiles();

        if (files == null) {
            return;
        }
        for (File tmpFile : files) {

            // ディレクトリの場合
            if (tmpFile.isDirectory()) {

                // 再帰呼び出し
                getFileList(tmpFile, fileList);

                // ファイルの場合
            } else {
                fileList.add(tmpFile.getPath());
            }
        }

    }

   /**
    * 指定ファルダのファイルを全て削除する。
    * @param file
    */
   private static void delFile(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }
        file.delete();
    }
}
