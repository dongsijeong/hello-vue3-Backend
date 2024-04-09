package demo.mo.util;

import demo.mo.model.ActionContextInfo;

/**
 * @author chunzi.tan@dxc.com
 *
 */
public final class ActionContextUtil {
    private ActionContextUtil() {
    }

    private static final ThreadLocal<ActionContextInfo> CONTEXT = new ThreadLocal<ActionContextInfo>();

    /**
     * コンテンツ情報を設定する。
     *
     * @param contextInfo
     */
    public static void setContext(ActionContextInfo contextInfo) {
        CONTEXT.set(contextInfo);
    }

    /**
     * コンテンツ情報を取得する。
     *
     * @return コンテンツ情報
     */
    public static ActionContextInfo getContext() {
        return CONTEXT.get();
    }

    /**
     * コンテンツ情報をクリアする。
     */
    public static void removeContext() {
        CONTEXT.remove();
    }
}
