package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.lang.Strings;
import jp.co.lawson.mo.config.DataAccessConfig;
import jp.co.lawson.mo.config.InputInfoManager;
import jp.co.lawson.mo.constants.CheckType;
import jp.co.lawson.mo.exception.CheckInputException;
import jp.co.lawson.mo.exception.SystemException;
import jp.co.lawson.mo.model.InputFiledInfo;
import jp.co.lawson.mo.model.MessageModel;
import jp.co.lawson.mo.model.ModelArrayInfo;
import jp.co.lawson.mo.model.ModelInfo;
import lombok.Data;

/**
 * 入力チェック機能
 * @author zchang4
 *
 */
@Data
public class InputCheck {

    /// タイプ定義(カナ)
    private static final int TYPE_KANA = 1;
    /// タイプ定義(半角数字)
    private static final int TYPE_HAN_SU = 2;
    /// タイプ定義(半角英数字)
    private static final int TYPE_HAN_EISU = 3;
    /// タイプ定義(小数点あり)
    private static final int TYPE_SYOSU = 4;
    /// タイプ定義(正数のみ)
    private static final int TYPE_ONLY_SEISU = 5;
    /// タイプ定義(日付)
    private static final int TYPE_DATE = 6;


    /// 必須入力チェックタイプ(入力)
    private static final int REQUIRED_INPUT = 7;
    /// 必須入力チェックタイプ(選択)
    private static final int REQUIRED_SELECT = 8;

    /// 小数以外
    private static final int TYPE_LENGTH = 9;

    /// 小数
    private static final int TYPE_FORMAT = 10;

    /// 文字列長
    private static final int TYPE_EQLENGTH = 11;

    /// 正数
    private static final int TYPE_NOT_NEGATIVE = 12;

    private List<Predicate<MessageModel>> businessChecks = new ArrayList<>();
    private List<Predicate<List<MessageModel>>> businessChecksList = new ArrayList<>();

    private String checkKey = null;

    private List<String> errList = new ArrayList<>();

    private Map<Object, Object> data = new HashMap<>();

    private List<String> filedList = new ArrayList<>();

    private Map<String, List<String>> msgMap = new HashMap<>();

    private String rowNum = "";

    /**
     * コンストラクタ
     */
    public InputCheck() {
    }

    /**
     * コンストラクタ
     * @param checkData 入力データ
     * @param inputKey リクエスト入力チェック設定情報キー
     */
    public InputCheck(final Map<Object, Object> checkData, final String inputKey) {
        this.data = checkData;
        this.checkKey = inputKey;
    }

    /**
     * 入力データをチェックする。
     */
    private void checkInputData() {
        Map<Object, Object> singnMap = new HashMap<>();
        for (Object key :data.keySet()) {
            Map<Object, Object> tmpMap = new HashMap<>();
            if (isSingle(key)) {
                singnMap.put(key, data.get(key));
            } else {
                tmpMap.put(key, data.get(key));
                checkInput(tmpMap, key.toString());
            }
        }
        checkSingnInput(singnMap);
//        if (!errList.isEmpty() && !filedList.isEmpty()) {
//            CheckInputException ce = new CheckInputException(String.join("\r\n", errList),
//                    String.join("\r\n", filedList));
//            throw ce;
//        }
//        if (!errList.isEmpty()) {
//            CheckInputException ce = new CheckInputException(String.join("\r\n", errList));
//            throw ce;
//        }
    }

    /**
     * シングルデータ判断する。
     * @param key 設定キー
     * @return シングルデータ判断結果
     */
    private boolean isSingle(Object key) {
        List<InputFiledInfo> singleList = InputInfoManager.getSingleList(checkKey);
        for (InputFiledInfo info : singleList) {
            if (info.getName().equals(key.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * シングル入力データチェック
     * @param checkdata シングル入力データ
     */
    private void checkSingnInput(Map<Object, Object> checkdata) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonData = mapper.writeValueAsString(checkdata);
            JsonNode node = mapper.readTree(jsonData);
            List<InputFiledInfo> singleList = InputInfoManager.getSingleList(checkKey);
            for (InputFiledInfo filedInfo : singleList) {
                String filedName = filedInfo.getName();
                String checkType = filedInfo.getChecktype();
                if (checkType.equals("checkdaterange")) {
                    checkDateRange(node, filedInfo);
                    continue;
                } else if (checkType.equals("checkDateInput")) {
                    checkDateInput(node, filedInfo);
                    continue;
                }
                String value = node.get(filedName).textValue();
                checkFiled(value, getCheckType(checkType), filedInfo);

            }
        } catch (Exception ex) {
            SystemException she = new SystemException(DataAccessConfig.getMessage("DE_CM0006.10006"));
            throw she;
        }
    }

    /**
     * 入力データチェックする。
     * @param checkdata 入力データチェック
     * @param key 設定情報キー
     */
    private void checkInput(Map<Object, Object> checkdata, String key) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonData = mapper.writeValueAsString(checkdata);
            JsonNode node = mapper.readTree(jsonData);
            List<ModelInfo> modelInfoList = InputInfoManager.getModelList(checkKey);
            for (ModelInfo modelInfo : modelInfoList) {
                String modelkey = modelInfo.getKey();
                if (!modelkey.equals(key)) {
                    continue;
                }
                String modelStr = node.get(modelkey).toString();
                JsonNode modelNode = mapper.readTree(modelStr);
                List<InputFiledInfo> filedLidt = modelInfo.getFiledList();
                for (InputFiledInfo filedInfo : filedLidt) {
                    String filedName = filedInfo.getName();
                    String checkType = filedInfo.getChecktype();
                    if (checkType.equals("checkdaterange")) {
                        checkDateRange(modelNode, filedInfo);
                        continue;
                    } else if (checkType.equals("checkDateInput")) {
                        checkDateInput(modelNode, filedInfo);
                        continue;
                    }
                    String value = modelNode.get(filedName).textValue();
                    checkFiled(value, getCheckType(checkType), filedInfo);
                }
            }

            List<ModelArrayInfo> modelArrayInfoList = InputInfoManager.getModelarrayList(checkKey);
            for (ModelArrayInfo modelArrayInfo : modelArrayInfoList) {
                List<String> list = new ArrayList<>();
                String modelArrayKey = modelArrayInfo.getKey();
                if (!modelArrayKey.equals(key)) {
                    continue;
                }
//                this.rowNum = String.valueOf(row);
                JsonNode arrNode =  node.get(modelArrayKey);
                arrNode.forEach((JsonNode jsonNode) -> {
                    list.add("");
                    this.rowNum = String.valueOf(list.size());
                    List<InputFiledInfo> filedArray = modelArrayInfo.getFiledList();
                    for (InputFiledInfo filedInfo : filedArray) {
                        String filedName = filedInfo.getName();
                        String checkType = filedInfo.getChecktype();
                        if (checkType.equals("checkdaterange")) {
                            checkDateRange(jsonNode, filedInfo);
                            continue;
                        } else if (checkType.equals("checkDateInput")) {
                            checkDateInput(jsonNode, filedInfo);
                            continue;
                        }
                        String value = jsonNode.get(filedName).textValue();
                        checkFiled(value, getCheckType(checkType), filedInfo);
                    }
                });

            }
            this.rowNum = "";
        } catch (Exception ex) {
            ex.printStackTrace();
            SystemException she = new SystemException(DataAccessConfig.getMessage("DE_CM0006.10006"));
            throw she;
        }
    }

    /**
     * 入力チェックタイプ変換
     * @param type 入力チェックタイプ文字列
     * @return 入力チェックタイプ
     */
    private CheckType getCheckType(String type) {
        switch (type) {
            case "checkempty" :
                return CheckType.NOTNULL;
            case "checklength" :
                return CheckType.LENGTH;
            case "checkfullwidthkana" :
                return CheckType.FULLWIDTHKANA;
            case "checkfullwidthchar" :
                return CheckType.FULLWIDTHCHAR;
            case "checkprohibitionchar" :
                return CheckType.PROHIBITIONCHAR;
            case "checkprohibitioncharother" :
                return CheckType.PROHIBITIONCHAR2;
            case "checkhalfwidthchar" :
                return CheckType.HALFWIDTHCHAR;
            case "checknumeric" :
                return CheckType.NUMERIC;
            case "checkzenhankaku" :
                return CheckType.ZENHANKAKU;
            case "checkzenkaku" :
                return CheckType.ZENKAKU;
            case "checkzenkakuother" :
                return CheckType.ZENKAKU2;
            case "checkzenkakucrlf" :
                return CheckType.ZENKAKUCRLF;
            case "checkfullwidthkatakana" :
                return CheckType.FULLWIDTHKATAKANA;
            case "checkzenhankakuspace" :
                return CheckType.ZENHANKAKUSPACE;
            case "checkequallength" :
                return CheckType.EQUALLENGTH;
            case "checkformat" :
                return CheckType.CHECKFORMAT;
            case "checknumber" :
                return CheckType.CHECKNUMBER;
            case "checkdate" :
                return CheckType.CHECKDATE;
            case "checkdubletype" :
                return CheckType.CHECKDUBLETYPE;
            case "checkmultkeywordscount" :
                return CheckType.MULTKEYWORDSCOUNT;
            case "checkpositiveinteger" :
                return CheckType.POSITIVEINTEGER;
            case "checkinteger" :
                return CheckType.CHECKINTEGER;
            default :
                return null;
        }
    }

    private boolean isExistMsg(InputFiledInfo filedInfo, String msg) {
        String filedName = filedInfo.getName();
        List<String> msgList = null;
        if (this.msgMap.containsKey(filedName)) {
            msgList = this.msgMap.get(filedName);
            if (msgList.contains(msg)) {
                return true;
            }
            msgList.add(msg);
            return false;
        } else {
            msgList = new ArrayList<String>();
            msgList.add(msg);
            this.msgMap.put(filedName, msgList);
            return false;
        }
    }
    private void checkDateRange(JsonNode jsonNode, InputFiledInfo filedInfo) {
        String filedName = filedInfo.getName();
        String value = jsonNode.get(filedName).textValue();
        String filedCompName = filedInfo.getCompname();
        String compvalue = jsonNode.get(filedCompName).textValue();
        String rowMsg = "";
        String row = this.rowNum;
        if (StringUtils.hasText(row)) {
            rowMsg = " (" + row + "行目)";
        }
        if (DateString.checkDateRange(value, compvalue) != 0) {
            String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
            if (!isExistMsg(filedInfo, msg)) {
                filedList.add(filedName);
                errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
            }
        }
    }

    /**
     * データ入力のチェック
     * @param jsonNode
     * @param filedInfo
     */
    public void checkDateInput(JsonNode jsonNode, InputFiledInfo filedInfo) {
        String filedName = filedInfo.getName();
        String dateStr = jsonNode.get(filedName).textValue();
        boolean isNeed = false;
        String need = filedInfo.getIsneed();
        String rowMsg = "";
        String row = this.rowNum;
        if (StringUtils.hasText(row)) {
            rowMsg = " (" + row + "行目)";
        }
        if (need != null && need.toLowerCase().equals("true")) {
            isNeed = true;
        }
        // 必須入力チェック
        if (dateStr == null || dateStr.equals("")) {
            if (isNeed) {
                String msg = DataAccessConfig.getMessage("E_CM_0001.1001", filedInfo.getArgs()) + rowMsg;
                if (!isExistMsg(filedInfo, msg)) {
                    filedList.add(filedName);
                    errList.add(DataAccessConfig.getMessage("E_CM_0001.1001", filedInfo.getArgs()) + rowMsg);
                }
            } else {
                return;
            }
        }
        // 日付形式をチェック
        int index1 = dateStr.indexOf("/");
        int index2 = dateStr.lastIndexOf("/");
        if (index1 != 4 || index2 != 7) {
            String msg = DataAccessConfig.getMessage("TN_CM0000.5020", filedInfo.getArgs()) + rowMsg;
            if (!isExistMsg(filedInfo, msg)) {
                filedList.add(filedName);
                errList.add(DataAccessConfig.getMessage("TN_CM0000.5020", filedInfo.getArgs()) + rowMsg);
            }
        }

        // スラッシュ取る
        dateStr = StringUtil.dateTOStr(dateStr);

        // 入力禁止文字チェック
        if (StringCheck.checkProhibitionChar(dateStr)) {
            String msg = DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg;
            if (!isExistMsg(filedInfo, msg)) {
                filedList.add(filedName);
                errList.add(DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg);
            }
        }
        // 半角数字のみで構成されているかチェック
        if (!StringCheck.checkNumeric(dateStr)) {
            String msg = DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg;
            if (!isExistMsg(filedInfo, msg)) {
                filedList.add(filedName);
                errList.add(DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg);
            }
        }
        // 入力サイズチェック
        if (!StringCheck.checkLength(dateStr, 8)) {
            String msg = DataAccessConfig.getMessage("E_CM_0003.1003", filedInfo.getArgs()) + rowMsg;
            if (!isExistMsg(filedInfo, msg)) {
                filedList.add(filedName);
                errList.add(DataAccessConfig.getMessage("E_CM_0003.1003", filedInfo.getArgs()) +  rowMsg);
            }
        }
    }

    /**
     * 入力チェックを行う。
     * @param value 入力チェック値
     * @param type 入力チェックタイプ
     * @param filedInfo 入力チェック設定情報
     */
    private void checkFiled(String value, CheckType type, InputFiledInfo filedInfo) {
        int length = -1;
        String filedName = filedInfo.getName();
        String rowMsg = "";
        String row = this.rowNum;
        if (StringUtils.hasText(row)) {
            rowMsg = " (" + row + "行目)";
        }
        switch (type) {
            case NOTNULL:
                if (!StringUtils.hasText(value)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case FULLWIDTHKANA:
                if (!StringCheck.checkFullWidthKANA(value)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case FULLWIDTHCHAR:
                if (!StringCheck.checkFullWidthChar(value)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case PROHIBITIONCHAR:
                if (StringCheck.checkProhibitionChar(value)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case PROHIBITIONCHAR2:
                if (StringCheck.checkProhibitionChar2(value, filedInfo.getOmoteLabelCode())) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case HALFWIDTHCHAR:
                if (!StringCheck.checkHalfWidthChar(value)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case NUMERIC:
                if (!StringCheck.checkNumeric(value)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case CHECKINTEGER:
                if (!StringCheck.checkInteger(value)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case ZENHANKAKU:
                if (!StringCheck.checkZenhankaku(value)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case ZENKAKU:
                if (!StringCheck.checkZenkaku(value)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case ZENKAKU2:
                if (!StringCheck.checkZenkaku2(value, filedInfo.getOmoteLabelCode())) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case ZENKAKUCRLF:
                if (!StringCheck.checkZenkakuCRLF(value)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case LENGTH:
                length = Integer.parseInt(filedInfo.getLength());
                if (!StringCheck.checkLength(value, length)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case EQUALLENGTH:
                length = Integer.parseInt(filedInfo.getLength());
                if (!StringCheck.checkEqualLength(value, length)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case FULLWIDTHKATAKANA:
                if (!StringCheck.checkFullWidthKATAKANA(value)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case ZENHANKAKUSPACE:
                if (!StringCheck.checkZenhankakuSpace(value)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case CHECKFORMAT:
                int integerDifits = Integer.parseInt(filedInfo.getIntegerdifits());
                int fractiondigits = Integer.parseInt(filedInfo.getFractiondigits());
                if (!DecimalString.checkFormat(integerDifits, fractiondigits, value)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case CHECKNUMBER:
                if (!DecimalString.checkNumber(value)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case CHECKDATE:
                int dateType = Integer.parseInt(filedInfo.getDatetype());
                if (!DateString.checkDate(dateType, value)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case CHECKDUBLETYPE:
                checkDubleType(value, filedInfo);
                break;
            case MULTKEYWORDSCOUNT:
                int count = Integer.parseInt(filedInfo.getCount());
                if (!StringCheck.checkMultKeyWordsCount(value, count)) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            case POSITIVEINTEGER:
                if (value == null || value.equals("") || !StringCheck.checkInteger(value)) {
                    break;
                }
                int number = Integer.parseInt(value);
                if (number <= 0) {
                    String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                    if (!isExistMsg(filedInfo, msg)) {
                        filedList.add(filedName);
                        errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                    }
                }
                break;
            default:
                break;
        }

    }

    private void checkDubleType(String value, InputFiledInfo filedInfo) {
        String filedName = filedInfo.getName();
        String rowMsg = "";
        String row = this.rowNum;
        if (StringUtils.hasText(row)) {
            rowMsg = " (" + row + "行目)";
        }
        if (value != null) {
            value = StringCheck.trimSpace(value);
        }
        if (value != null && filedInfo.getIsmoney() != null && filedInfo.getIsmoney().toLowerCase().equals("true")) {
            value = DecimalString.removeComma(value);
        }
        if (!StringUtils.hasText(value)) {
            if (isCheck(REQUIRED_INPUT, filedInfo)) {
                String msg = DataAccessConfig.getMessage("E_CM_0001.1001", filedInfo.getArgs()) + rowMsg;
                if (!isExistMsg(filedInfo, msg)) {
                    filedList.add(filedName);
                    errList.add(DataAccessConfig.getMessage("E_CM_0001.1001", filedInfo.getArgs()) + rowMsg);
                }
            } else if (isCheck(REQUIRED_SELECT, filedInfo)) {
                String msg = DataAccessConfig.getMessage("E_CM_0113.1113", filedInfo.getArgs()) + rowMsg;
                if (!isExistMsg(filedInfo, msg)) {
                    filedList.add(filedName);
                    errList.add(DataAccessConfig.getMessage("E_CM_0113.1113", filedInfo.getArgs()) + rowMsg);
                }
            }
            return;
        }
        if (StringCheck.checkProhibitionChar(value)) {
            String msg = DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg;
            if (!isExistMsg(filedInfo, msg)) {
                filedList.add(filedName);
                errList.add(DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg);
            }
            return;
        }
        // ひらがな・カタカナ
        if (isCheck(TYPE_KANA, filedInfo)) {
            if (!StringCheck.checkFullWidthKATAKANA(value)) {
                String msg = DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg;
                if (!isExistMsg(filedInfo, msg)) {
                    filedList.add(filedName);
                    errList.add(DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg);
                }
            }
            return;
        }
        // 半角数字
        if (isCheck(TYPE_HAN_SU, filedInfo)) {
            if (!StringCheck.checkNumeric(value)) {
                String msg = DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg;
                if (!isExistMsg(filedInfo, msg)) {
                    filedList.add(filedName);
                    errList.add(DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg);
                }
            }
            return;
        }
        // 半角英数字
        if (isCheck(TYPE_HAN_EISU, filedInfo)) {
            if (!StringCheck.checkHalfWidthChar(value)) {
                String msg = DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg;
                if (!isExistMsg(filedInfo, msg)) {
                    filedList.add(filedName);
                    errList.add(DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg);
                }
            }
            return;
        }
        // 小数
        if (isCheck(TYPE_SYOSU, filedInfo)) {
            if (!DecimalString.checkNumber(value)) {
                String msg = DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg;
                if (!isExistMsg(filedInfo, msg)) {
                    filedList.add(filedName);
                    errList.add(DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg);
                }
            }
            return;
        }
        // 日付
        if (isCheck(TYPE_DATE, filedInfo)) {
            // 日付形式をチェック
            int index1 = value.indexOf("/");
            int index2 = value.lastIndexOf("/");
            if (index1 != 4 || index2 != 7) {
                String msg = DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg;
                if (!isExistMsg(filedInfo, msg)) {
                    filedList.add(filedName);
                    errList.add(DataAccessConfig.getMessage("E_CM_0002.1002", filedInfo.getArgs()) + rowMsg);
                }
            }
            return;
        }
        // 正数
        if (isCheck(TYPE_ONLY_SEISU, filedInfo)) {
            String firstChar = value.substring(0, 1);
            if (firstChar.equals("-")) {
                String msg = DataAccessConfig.getMessage("E_CM_2006.2006", filedInfo.getArgs()) + rowMsg;
                if (!isExistMsg(filedInfo, msg)) {
                    filedList.add(filedName);
                    errList.add(DataAccessConfig.getMessage("E_CM_2006.2006", filedInfo.getArgs()) + rowMsg);
                }
            }
            return;
        }
        int length = -1;
        // 小数以外
        if (isCheck(TYPE_LENGTH, filedInfo)) {
            length = Integer.parseInt(filedInfo.getLength());
            if (!StringCheck.checkLength(value, length)) {
                String msg = DataAccessConfig.getMessage("E_CM_0003.1003", filedInfo.getArgs()) + rowMsg;
                if (!isExistMsg(filedInfo, msg)) {
                    filedList.add(filedName);
                    errList.add(DataAccessConfig.getMessage("E_CM_0003.1003", filedInfo.getArgs()) + rowMsg);
                }
            }
            return;
        }
        // 小数
        if (isCheck(TYPE_FORMAT, filedInfo)) {
            int integerDifits = Integer.parseInt(filedInfo.getIntegerdifits());
            int fractiondigits = Integer.parseInt(filedInfo.getFractiondigits());
            if (!DecimalString.checkFormat(integerDifits, fractiondigits, value)) {
                String msg = DataAccessConfig.getMessage("E_CM_0003.1003", filedInfo.getArgs()) + rowMsg;
                if (!isExistMsg(filedInfo, msg)) {
                    filedList.add(filedName);
                    errList.add(DataAccessConfig.getMessage("E_CM_0003.1003", filedInfo.getArgs()) + rowMsg);
                }
            }
            return;
        }
        // 文字列長
        if (isCheck(TYPE_EQLENGTH, filedInfo)) {
            length = Integer.parseInt(filedInfo.getLength());
            if (!StringCheck.checkEqualLength(value, length)) {
                String msg = DataAccessConfig.getMessage("E_CM_0003.1003", filedInfo.getArgs()) + rowMsg;
                if (!isExistMsg(filedInfo, msg)) {
                    filedList.add(filedName);
                    errList.add(DataAccessConfig.getMessage("E_CM_0003.1003", filedInfo.getArgs()) + rowMsg);
                }
            }
            return;
        }
        if (isCheck(TYPE_NOT_NEGATIVE, filedInfo)) {
            String firstChar = value.substring(0, 1);
            if (firstChar.equals("-")) {
                String msg = DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg;
                if (!isExistMsg(filedInfo, msg)) {
                    filedList.add(filedName);
                    errList.add(DataAccessConfig.getMessage(filedInfo.getMsgid(), filedInfo.getArgs()) + rowMsg);
                }
            }
            return;
        }
    }

    private boolean isCheck(int  checkType, InputFiledInfo filedInfo) {
        String mod = filedInfo.getMod();
        if (!StringUtils.hasText(mod)) {
            return false;
        }
        String[] types = mod.split(",");
        for (String type : types) {
            int tmpType = Integer.parseInt(type);
            if (tmpType == checkType) {
                return true;
            }
        }
        return false;
    }

    /**
     * 業務チェック関数を追加する。
     * @param func 業務チェック関数。
     */
    public void addBusinessCheck(Predicate<MessageModel> func) {
        businessChecks.add(func);
    }

    /**
     * 業務チェック関数を追加する。
     * @param func 業務チェック関数。
     */
    public void addBusinessCheckList(Predicate<List<MessageModel>> func) {
        businessChecksList.add(func);
    }

    /**
     * 入力チェックを行う
     */
    public void validation() {
        checkInputData();

        for (Predicate<MessageModel> pre : businessChecks) {
            MessageModel message = new MessageModel();
            boolean tmpCheck = pre.test(message);
            if (!tmpCheck) {
                InputFiledInfo info = new InputFiledInfo();
                String filed = "";
                if (Strings.hasText(message.getFleldName())) {
                    filed = message.getFleldName();
                } else {
                    filed = "dummy";
                }
                info.setName(filed);
                int row = message.getRowNumber();
                String rowMsg = "";
                if (row > 0) {
                    rowMsg = " (" + row + "行目)";
                }
                String msg = DataAccessConfig.getMessage(message.getMessageId(), message.getParams()) + rowMsg;
                if (!isExistMsg(info, msg)) {
                    if (Strings.hasText(message.getFleldName())) {
                        filedList.add(message.getFleldName());
                    } else {
                        filedList.add("dummy");
                    }
                    errList.add(DataAccessConfig.getMessage(message.getMessageId(), message.getParams()) + rowMsg);
                }
            }
        }
            List<MessageModel> messageList = new ArrayList<MessageModel>();
            for (Predicate<List<MessageModel>> pre : businessChecksList) {

            boolean tmpCheck = pre.test(messageList);
            if (!tmpCheck) {
                for (MessageModel msg : messageList) {
                    InputFiledInfo info = new InputFiledInfo();
                    String filed = "";
                    if (Strings.hasText(msg.getFleldName())) {
                        filed = msg.getFleldName();
                    } else {
                        filed = "dummy";
                    }
                    info.setName(filed);
                    int row = msg.getRowNumber();
                    String rowMsg = "";
                    if (row > 0) {
                        rowMsg = " (" + row + "行目)";
                    }
                    String errmsg = DataAccessConfig.getMessage(msg.getMessageId(), msg.getParams()) + rowMsg;
                    if (!isExistMsg(info, errmsg)) {
                        if (Strings.hasText(msg.getFleldName())) {
                            filedList.add(msg.getFleldName());
                        } else {
                            filedList.add("dummy");
                        }
                        errList.add(DataAccessConfig.getMessage(msg.getMessageId(), msg.getParams())  + rowMsg);
                    }
                }
            }
        }
        if (!errList.isEmpty() && !filedList.isEmpty()) {
            CheckInputException ce = new CheckInputException(String.join("\r\n", errList),
                    String.join("\r\n", filedList));
            throw ce;
        }
        if (!errList.isEmpty()) {
            CheckInputException ce = new CheckInputException(String.join("\r\n", errList));
            throw ce;
        }
    }

}
