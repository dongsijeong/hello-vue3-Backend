package demo.mo.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import demo.mo.constants.Constant.MESSAGECODE;
import demo.mo.model.MessageModel;


/**
 * 【共通Check基本クラス】<BR>
 *
 */
public final class CommonCheck {
    private CommonCheck() {
    }

/**
 * 入力された郵便番号のチェックをする。
 * @param    params        入力された郵便番号データ
 * @param    msgList    　　　 チェックする項目のマトリクス
 * @param    names    入力された郵便番号項目項目のフラグ
 */
public static void checkYubinInput(String[] params, List<MessageModel> msgList, String[] names) {
    String yubin1 = params[0];
    String yubin2 = params[1];

    // 郵便番号1が未入力
    if (StringUtils.isEmpty(yubin1)) {
        if (!StringUtils.isEmpty(yubin2)) {
            MessageModel msg = new MessageModel();
            msg.setMessageId(MESSAGECODE.TN_CM0000_5001);
            msg.setFleldName(names[0]);
            msgList.add(msg);
        } else {
            return;
        }
    // 郵便番号2は入力
    } else {
        if (StringUtils.isEmpty(yubin2)) {
            MessageModel msg = new MessageModel();
            msg.setMessageId(MESSAGECODE.TN_CM0000_5001);
            msg.setFleldName(names[1]);
            msgList.add(msg);
        }
    }

    // 入力禁止文字チェック
    if (StringCheck.checkProhibitionChar(yubin1)) {
        MessageModel msg = new MessageModel();
        msg.setMessageId(MESSAGECODE.E_CM_0002_1002);
        msg.setParams(new String[]{"郵便番号"});
        msg.setFleldName(names[0]);
        msgList.add(msg);
    }
    if (StringCheck.checkProhibitionChar(yubin2)) {
        MessageModel msg = new MessageModel();
        msg.setMessageId(MESSAGECODE.E_CM_0002_1002);
        msg.setParams(new String[]{"郵便番号"});
        msg.setFleldName(names[1]);
        msgList.add(msg);
    }

    // 半角数字のみで構成されているかチェック
    if (!StringCheck.checkNumeric(yubin1)) {
        MessageModel msg = new MessageModel();
        msg.setMessageId(MESSAGECODE.E_CM_0002_1002);
        msg.setParams(new String[]{"郵便番号"});
        msg.setFleldName(names[0]);
        msgList.add(msg);
    }
    if (!StringCheck.checkNumeric(yubin2)) {
        MessageModel msg = new MessageModel();
        msg.setMessageId(MESSAGECODE.E_CM_0002_1002);
        msg.setParams(new String[]{"郵便番号"});
        msg.setFleldName(names[1]);
        msgList.add(msg);
    }

    // 入力サイズチェック
    if (!StringUtils.isEmpty(yubin1) && yubin1.length() != 3) {
        MessageModel msg = new MessageModel();
        msg.setMessageId(MESSAGECODE.E_CM_0003_1003);
        msg.setParams(new String[]{"郵便番号"});
        msg.setFleldName(names[0]);
        msgList.add(msg);
    }

    if (!StringUtils.isEmpty(yubin2) && yubin2.length() != 4) {
        MessageModel msg = new MessageModel();
        msg.setMessageId(MESSAGECODE.E_CM_0003_1003);
        msg.setParams(new String[]{"郵便番号"});
        msg.setFleldName(names[1]);
        msgList.add(msg);
    }

    return;
 }

/**
 * 入力されたTEL/FAXのチェックをする。
 * @param    params             入力されたTEL/FAXデータ
 * @param    name             入力されたTEL/FAX名称
 * @param    msgList             チェックする項目のマトリクス
 * @param    names         入力されたTEL/FAX項目名称
 */
public static void checkTelFaxInput(String[] params, String name, List<MessageModel> msgList, String[] names) {

    String[] numbers = new String[3];
    int nullNums = 0;

    for (int i = 0; i < 3; i++) {
        numbers[i] = params[i];

        // 必須入力チェック
        if (numbers[i] == null || numbers[i].equals("")) {
            nullNums++;
            continue;
        }

        // 入力禁止文字チェック
        if (StringCheck.checkProhibitionChar(numbers[i])) {
            MessageModel msg = new MessageModel();
            msg.setMessageId(MESSAGECODE.E_CM_0002_1002);
            msg.setParams(new String[]{name});
            msg.setFleldName(names[i]);
            msgList.add(msg);
        }

        // 半角数字のみで構成されているかチェック
        if (!StringCheck.checkNumeric(numbers[i])) {
            MessageModel msg = new MessageModel();
            msg.setMessageId(MESSAGECODE.E_CM_0002_1002);
            msg.setParams(new String[]{name});
            msg.setFleldName(names[i]);
            msgList.add(msg);
        }
    }

    // 空の部分があればエラー
    if (0 != nullNums && 3 != nullNums) {
        MessageModel msg = new MessageModel();
        msg.setMessageId(MESSAGECODE.TN_CM0000_5002);
        msg.setFleldName(names[0]);
        msgList.add(msg);
    }

    // 入力サイズチェック
    if (!StringCheck.checkLength(numbers[0] + numbers[1] + numbers[2], 13)) {
        MessageModel msg = new MessageModel();
        msg.setMessageId(MESSAGECODE.E_CM_0003_1003);
        msg.setParams(new String[]{name});
        msg.setFleldName(names[0]);
        msgList.add(msg);
    }

    return;
  }
}
