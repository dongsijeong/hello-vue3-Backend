package demo.mo.util;

import demo.mo.model.RequestContextInfo;

/**
 * @author chunzi.tan@dxc.com
 *
 */
public final class RequestContextUtil {
    private RequestContextUtil() {
    }

    private static final ThreadLocal<RequestContextInfo> CONTEXT = new ThreadLocal<RequestContextInfo>();

    /**
     * コンテンツ情報を設定する。
     *
     * @param contextInfo
     */
    public static void setContext(RequestContextInfo contextInfo) {
        CONTEXT.set(contextInfo);
    }

    /**
     * コンテンツ情報を取得する。
     *
     * @return コンテンツ情報
     */
    public static RequestContextInfo getContext() {
        return CONTEXT.get();
    }

    /**
     * コンテンツ情報をクリアする。
     */
    public static void removeContext() {
        CONTEXT.remove();
    }
}
