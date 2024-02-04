
package util;

/**
 * 各種マスター管理のプレイヤー制限処理関数<br>
 *
 */
public final class LmvPlayer {
    private LmvPlayer() {
    }
    /**
     * 社内ユーザー
     */
    public static final int SHANAI_USER = 0;
    /**
     * サプライヤーユーザー
     */
    public static final int SUPPLIER_USER = 1;
    /**
     * ディーラーユーザー
     */
    public static final int DEALER_USER = 2;

    /**
     * 製造ベンダー
     */
    public static final int VENDER_USER = 3;
    /**
     * その他のユーザー
     */
    public static final int OTHER_USER = 9999;

    /**
     * サプライヤーの取引先CDの先頭
     */
    public static final String PREFIX_SUPPLIER_CD = "56";
    /**
     * ディーラーの取引先CDの先頭
     */
    public static final String PREFIX_DEALEAR_CD = "55";
    /**
     * 製造ベンダーの取引先CDの先頭
     */
    public static final String PREFIX_VENDER_CD = "51";


    /**
    * プレイヤー制限モードの取得関数
    * @return プレイヤーでの画面制限コード
    * 社内ユーザー：SHANAI_USER
    * サプライヤー：SUPPLIER_USER
    * ディーラー　：DEALER_USER
    * その他    　：OTHER_USER
    * @since  1.00
    */
    public static int getPlayerSeigenMode() {
        int iPlayerSeigenCode = OTHER_USER;
        // ログイン種別を取得する
        int iLoginKind = UserInfoManager.getKind();
        switch (iLoginKind) {
        // 社内ユーザーの場合
        case SqlString.N_LOGIN_USER :
            // プレイヤー制限コードを社内に設定
            iPlayerSeigenCode = SHANAI_USER;
            break;
        // 社外ユーザーの場合
        case SqlString.G_LOGIN_USER :
            // 取引先CDを取得する
            String strTorihikisakiCD = UserInfoManager.getDealingsCode();
            // 製造ベンダーの場合
            if (strTorihikisakiCD.startsWith(PREFIX_VENDER_CD)) {
                iPlayerSeigenCode = VENDER_USER;
            } else if (strTorihikisakiCD.startsWith(PREFIX_DEALEAR_CD)) {
                // ディーラーの場合
                iPlayerSeigenCode = DEALER_USER;
            } else if (strTorihikisakiCD.startsWith(PREFIX_SUPPLIER_CD)) {
                // サプライヤーの場合
                iPlayerSeigenCode = SUPPLIER_USER;
            } else {
                // 該当プレイヤーなし
                iPlayerSeigenCode = OTHER_USER;
            }
            break;
        // その他の場合
        default :
            iPlayerSeigenCode = OTHER_USER;
            break;
        }

        return iPlayerSeigenCode;
    }
}
