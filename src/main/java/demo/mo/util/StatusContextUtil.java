package demo.mo.util;

import demo.mo.model.StatusContextInfo;

/**
 * @author chunzi.tan@dxc.com
 *
 */
public final class StatusContextUtil {
    private StatusContextUtil() {
    }

    private static final ThreadLocal<StatusContextInfo> CONTEXT = new ThreadLocal<StatusContextInfo>();

    /**
     * コンテンツ情報を設定する。
     *
     * @param contextInfo
     */
    public static void setContext(StatusContextInfo contextInfo) {
        CONTEXT.set(contextInfo);
    }

    /**
     * コンテンツ情報を取得する。
     *
     * @return コンテンツ情報
     */
    public static StatusContextInfo getContext() {
        return CONTEXT.get();
    }

    /**
     * コンテンツ情報をクリアする。
     */
    public static void removeContext() {
        CONTEXT.remove();
    }
}
