package demo.mo.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import demo.mo.model.UserInfo;


/**
 * ログ出力ツール<br>
 *
 * @author        DXC zhanglin
 */
public final class LogUtil {

    public enum OperationType {
        /**
         * 検索
         */
        SELECT,

        /**
         * 登録
         */

        INSERT,

        /**
         * 更新
         */
        UPDATE,

        /**
         * 削除
         */
        DELETE,

        /**
         * プロシジャ
         */
        PROCEDURE,
    }

    private LogUtil() {
    }
    //Onlineアプリ実行ログ
    private static Logger app = LogManager.getLogger("app");
    //操作ログ
    private static Logger access = LogManager.getLogger("access");
    //証跡ログ
    private static Logger trail = LogManager.getLogger("trail");

    /**
     *アプリケーションログを出力する。
     * @param msg
     */
    public static void  outApplog(String msg) {
       app.info(msg);
    }

    private static String getType(OperationType type) {
        switch (type) {
        case SELECT :
            return "検索";
        case INSERT :
            return "登録";
        case UPDATE :
            return "更新";
        case DELETE :
            return "削除";
        case PROCEDURE:
            return "プロシジャ";
        default :
            return "";
        }
    }
    /**
     *アプリケーションエラーログを出力する。
     * @param msg
     */
    public static void  outAppErrlog(String msg) {
       app.error(msg);
    }

    /**
     *アプリケーションデバッグログを出力する。
     * @param msg
     */
    public static void  outDebuglog(String msg) {
       app.debug(msg);
    }

    /**
     * 証跡ログを出力する。
     * @param count
     * @param oType
     * @param tableNames
     */
    public static void outDbaccessInfo(int count, OperationType oType, String tableNames) {
        UserInfo userInfo = RequestContextUtil.getContext().getUserInfo();
        String userId = null;
        if (userInfo.getKind() == 0) {
            userId = userInfo.getUserID();
        } else {
            userId = userInfo.getDealingsCode();
        }
        String url = ActionContextUtil.getContext().getActionId();
        String actionName = ActionContextUtil.getContext().getActionName();
        String type = getType(oType);
        String acction = "";
        if (oType != OperationType.PROCEDURE) {
            acction = tableNames + " " + type + ":" + String.valueOf(count);
        } else {
            acction = tableNames + " コール";
        }
        String msg = url + "\t" + userId + "\t" + ActionInfoManger.getActionInfo(actionName);
        msg = msg +  "\t" + acction;
        outTrail(msg);
    }
    /**
     *アプリケーション警告ログを出力する。
     * @param msg
     */
    public static void  outAppWarlog(String msg) {
       app.warn(msg);
    }


    /**
     *操作ログを出力する。
     * @param msg
     */
    public static void  outAccesslog(String msg) {
        access.info(msg);
    }

    /**
     *操作ログを出力する。
     * @param msg
     */
    public static void  outAccessErrlog(String msg) {
        access.error(msg);
    }

    /**
     *証跡ログを出力する。
     * @param msg
     */
    public static void  outTrail(String msg) {
        trail.info(msg);
    }

    /**
     *証跡ログを出力する。
     * @param msg
     */
    public static void  outTrailDetial(String msg) {
        UserInfo userInfo = RequestContextUtil.getContext().getUserInfo();
        String userId = null;
        if (userInfo.getKind() == 0) {
            userId = userInfo.getUserID();
        } else {
            userId = userInfo.getDealingsCode();
        }
        String url = ActionContextUtil.getContext().getActionId();
        String actionName = ActionContextUtil.getContext().getActionName();
        String tempMsg = url + "\t" + userId + "\t" + ActionInfoManger.getActionInfo(actionName) + "\t" + msg;
        outTrail(tempMsg);
    }

    /**
     *getStackTrace。
     * @param e
     * @return Trace message
     */
    public static String getStackTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }
}
