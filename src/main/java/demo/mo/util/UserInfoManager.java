package demo.mo.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

import demo.mo.model.UserInfo;

public final class UserInfoManager {
    private UserInfoManager() {
    }
    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：TOKKEN_USER<BR>
    *説明：isLoginUser()関数で｢特権ユーザー｣を判別するのに使用。<BR>
    */
    public static final int TOKKEN_USER = 1;
    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：LAWSON_MANAGER<BR>
    *説明：isLoginUser()関数で「LAWSON社内部長以上の役職者｣を判別するのに使用。<BR>
    */
    public static final int LAWSON_MANAGER = 2;
    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：LAWSON_MEMBER<BR>
    *説明：isLoginUser()関数で「LAWSON社内部長以下の役職者｣を判別するのに使用。<BR>
    */
    public static final int LAWSON_MEMBER = 3;
    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：FFS_SHIZAI<BR>
    *説明：isLoginUser()関数で｢FFS資材調達担当者｣を判別するのに使用。<BR>
    */
    public static final int FFS_SHIZAI = 4;
    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：FFS_MENU<BR>
    *説明：isLoginUser()関数で｢FFSメニュー開発担当者｣を判別するのに使用。<BR>
    */
    public static final int FFS_MENU = 5;
    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：FFS_AREA_LEADER<BR>
    *説明：isLoginUser()関数で｢FFSエリアリーダー｣を判別するのに使用。<BR>
    */
    public static final int FFS_AREA_LEADER = 6;
    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：VENDER<BR>
    *説明：isLoginUser()関数で｢製造ベンダー｣を判別するのに使用。<BR>
    */
    public static final int VENDER = 7;
    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：DEALER<BR>
    *説明：isLoginUser()関数で｢ディーラー｣を判別するのに使用。<BR>
    */
    public static final int DEALER = 8;
    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：SUPPLYER<BR>
    *説明：isLoginUser()関数で｢サプライヤー｣を判別するのに使用。<BR>
    */
    public static final int SUPPLYER = 9;
    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：YASAI_MANAGER<BR>
    *説明：isLoginUser()関数で｢野菜管理担当者｣を判別するのに使用。<BR>
    */
    public static final int YASAI_MANAGER = 10;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：BEIHAN_DERIKA_GROUP<BR>
    *説明：isLoginUser()関数で｢米飯デリカ部｣を判別するのに使用。<BR>
    */
    public static final int BEIHAN_DERIKA_GROUP = 11;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：AREA_SHOHIN_GROUP<BR>
    *説明：isLoginUser()関数で｢エリア商品部｣を判別するのに使用。<BR>
    */
    public static final int AREA_SHOHIN_GROUP   = 12;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：HINSITU_KANRI_GROUP<BR>
    *説明：isLoginUser()関数で｢品質管理部｣を判別するのに使用。<BR>
    */
    public static final int HINSITU_KANRI_GROUP   = 13;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：FFS_GROUP<BR>
    *説明：isLoginUser()関数で｢ＦＦＳ｣を判別するのに使用。<BR>
    */
    public static final int FFS_GROUP   = 14;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：BEIHAN_DERIKA_MD<BR>
    *説明：isLoginUser()関数で｢米飯デリカ部　ＭＤ｣を判別するのに使用。<BR>
    */
    public static final int BEIHAN_DERIKA_MD   = 15;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：AREA_SHOHIN_BY<BR>
    *説明：isLoginUser()関数で｢エリア商品部　ＢＹ｣を判別するのに使用。<BR>
    */
    public static final int AREA_SHOHIN_BY   = 16;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：LAWSON_MANAGER_AGENCY<BR>
    *説明：isLoginUser()関数で｢ローソン商品部　部長代理｣を判別するのに使用。<BR>
    */
    public static final int LAWSON_MANAGER_AGENCY   = 17;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：NEW_BEIHAN_DERIKA_GROUP<BR>
    *説明：isLoginUser()関数で｢新米飯デリカ部｣を判別するのに使用。<BR>
    */
    public static final int NEW_BEIHAN_DERIKA_GROUP = 18;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：SCI_TOKKEN_USER<BR>
    *説明：isLoginUser()関数で｢SCI特権ユーザー｣を判別するのに使用。<BR>
    */
    public static final int SCI_TOKKEN_USER   = 31;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：SCI_MANAGER<BR>
    *説明：isLoginUser()関数で｢SCI社管理職｣を判別するのに使用。<BR>
    */
    public static final int SCI_MANAGER   = 32;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：SCI_MANAGER_AGENCY<BR>
    *説明：isLoginUser()関数で｢SCI社管理職代理｣を判別するのに使用。<BR>
    */
    public static final int SCI_MANAGER_AGENCY   = 33;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：SCI_CREW_USER<BR>
    *説明：isLoginUser()関数で｢SCI社クルーユーザー｣を判別するのに使用。<BR>
    */
    public static final int SCI_CREW_USER   = 34;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：SCI_MEMBER<BR>
    *説明：isLoginUser()関数で｢SCI社業務担当｣を判別するのに使用。<BR>
    */
    public static final int SCI_MEMBER   = 35;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：SCI_FFS<BR>
    *説明：isLoginUser()関数で｢SCIFFS｣を判別するのに使用。<BR>
    */
    public static final int SCI_FFS   = 36;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：SCI_FFS_MENU<BR>
    *説明：isLoginUser()関数で｢SCIFFSメニュー｣を判別するのに使用。<BR>
    */
    public static final int SCI_FFS_MENU   = 37;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：SCI_FFS_AREA_LEADER<BR>
    *説明：isLoginUser()関数で｢SCIFFSエリアリーダー｣を判別するのに使用。<BR>
    */
    public static final int SCI_FFS_AREA_LEADER   = 38;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：SCI_VENDER<BR>
    *説明：isLoginUser()関数で｢SCIベンダー｣を判別するのに使用。<BR>
    */
    public static final int SCI_VENDER   = 39;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：SCI_DEALER<BR>
    *説明：isLoginUser()関数で｢SCIディーラー｣を判別するのに使用。<BR>
    */
    public static final int SCI_DEALER   = 40;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：SCI_SUPPLIER<BR>
    *説明：isLoginUser()関数で｢SCIサプライヤー｣を判別するのに使用。<BR>
    */
    public static final int SCI_SUPPLIER   = 41;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：SCI_YASAI_MANAGER<BR>
    *説明：isLoginUser()関数で｢SCI野菜管理者｣を判別するのに使用。<BR>
    */
    public static final int SCI_YASAI_MANAGER   = 42;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：SCI_BEIHAN_DERIKA_GROUP<BR>
    *説明：isLoginUser()関数で｢SCI米飯デリカ部｣を判別するのに使用。<BR>
    */
    public static final int SCI_BEIHAN_DERIKA_GROUP   = 43;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：SCI_OTHER<BR>
    *説明：isLoginUser()関数で｢SCIその他｣を判別するのに使用。<BR>
    */
    public static final int SCI_OTHER   = 44;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：NDTANKA_TOKKEN_USER<BR>
    *説明：isLoginUser()関数で｢特権ユーザー（着単価表示なし）｣を判別するのに使用。<BR>
    */
    public static final int NDTANKA_TOKKEN_USER   = 45;

    /**
    *ログインユーザー情報のフィールド文字列<BR>
    *値：NDTANKA_LAWSON_MANAGER<BR>
    *説明：isLoginUser()関数で｢ローソン部長以上（着単価表示なし）｣を判別するのに使用。<BR>
    */
    public static final int NDTANKA_LAWSON_MANAGER   = 46;

    /**
    *ログイン種別取得関数(getLoginKind)の戻り値のフィールド文字列<BR>
    *値：N_LOGIN_USER<BR>
    *説明：ログイン種別：社内ユ－ザ－<BR>
    */
    public static final int N_LOGIN_USER = 0;

    /**
    *ログイン種別取得関数(getLoginKind)の戻り値のフィールド文字列<BR>
    *値：G_LOGIN_USER<BR>
    *説明：ログイン種別：社外ユ－ザ－<BR>
    */
    public static final int G_LOGIN_USER = 1;

    /**
     * 製造ベンダー会社区分
     */
    public static final String KB_VDR_KAISYA = "0";

    /**
     * 製造ベンダー区分
     */
    public static final String KB_VDR = "1";

    /**
     * サプライヤー区分
     */
    public static final String KB_SUPPLIER = "6";

    /**
     * FFS区分
     */
    public static final String KB_FFS = "9";

    /**
     * ディーラー区分
     */
    public static final String KB_DEALER = "5";
    //isLoginUser()関数で使用する配列宣言
    private static final String[] G_TOKKEN_USER_CODE = {"001", "002", "003", "005", "010", "011"};
    private static final String[] G_LAWSON_MANAGER_CODE = {"010", "011", "020"};
    // ローソン特権（着単価表示なし）
    private static final String[] G_ND_TANKA_TOKKEN_USER_CODE = {"501", "502", "503", "510", "511"};
    // ローソン部長以上（着単価表示なし）
    private static final String[] G_ND_TANKA_LAWSON_MANAGER_CODE = {"510", "511", "520"};
    private static final String[] G_LAWSON_MANAGER_AGENCY_CODE = {"220", "229"};
    private static final String[] G_LAWSON_MEMBER_CODE = {"012", "021", "022", "023", "030", "031",
                                                         "040", "041", "051", "001", "002", "003",
                                                         "060", "005"};
    private static final String[] G_FFS_SHIZAI_CODE = {"111", "222"};
    private static final String[] G_FFS_MENU_CODE = {"112"};
    private static final String[] G_FFS_AREA_LEADER_CODE = {"113"};
    private static final String[] G_VENDER_CODE  = {"120", "121", "122"};
    private static final String[] G_DEALER_CODE  = {"130", "901"};
    private static final String[] G_SUPPLYER_CODE = {"140", "141"};
    private static final String[] G_YASAI_MANAGER_CODE = {"902"};
    // 米飯デリカ部
    private static final String[] G_BEIHAN_DERIKA_GROUP_CODE = {"020", "021", "022", "023"};
    // エリア商品部
    private static final String[] G_AREA_SHOHIN_GROUP_CODE = {"030", "031"};
    // 品質管理部
    private static final String[] G_HINSITU_KANRI_GROUP_CODE = {"040", "041"};
    // ＦＦＳ
    private static final String[] G_FFS_GROUP_CODE  = {"110", "111", "112", "113"};
    // 米飯デリカ部ＭＤのプレイヤーコード
    private static final String[] G_BEIHAN_DERIKA_MD_CODE = {"021"};
    // エリア商品部ＢＹのプレイヤーコード
    private static final String[] G_AREA_SHOHIN_BY_CODE = {"031"};
    // 新米飯デリカ部
    private static final String[] G_NEW_BEIHAN_DERIKA_GROUP_CODE = {"220", "229"};
    // SCI特権ユーザー
    private static final String[] G_SCI_TOKKEN_USER_CODE = {"301", "305", "070"};
    // SCI社管理職
    private static final String[] G_SCI_MANAGER_CODE = {"320"};
    // SCI社管理職代理
    private static final String[] G_SCI_MANAGER_AGENCY_CODE = {"321", "329"};
    // SCI社クルーユーザー
    private static final String[] G_SCI_CREW_USER_CODE = {"301", "305", "312"};
    // SCI社業務担当
    private static final String[] G_SCI_MEMBER_CODE = {"333"};
    // SCIFFS
    private static final String[] G_SCI_FFS_CODE = {"333", "410", "412", "413"};
    // SCIFFSメニュー
    private static final String[] G_SCI_FFS_MENU_CODE = {"412"};
    // SCIFFSエリアリーダー
    private static final String[] G_SCI_FFS_AREA_LEADER_CODE = {"413"};
    // SCIベンダー
    private static final String[] G_SCI_VENDER_CODE = {"420", "421", "422"};
    // SCIディーラー
    private static final String[] G_SCI_DEALER_CODE = {"430", "941"};
    // SCIサプライヤー
    private static final String[] G_SCI_SUPPLIER_CODE = {"440"};
    // SCI野菜管理者
    private static final String[] G_SCI_YASAI_MANAGER_CODE = {"942"};
    // SCI米飯デリカ部
    private static final String[] G_SCI_BEIHAN_DERIKA_GROUP_CODE = {"320", "321", "329"};
    // SCIその他
    private static final String[] G_SCI_OTHER_CODE = {"433", "940", "943"};

    /**
     * ユーザー情報を返す
     * @return ユーザー情報
     */
    public static UserInfo getUserInfo() {
        if (RequestContextUtil.getContext() == null) {
            UserInfo info = new UserInfo();
            info.setDealingsCode("todo");
            info.setDiscernmentCode("todo");
            info.setExternalUserCode("todo");
            info.setKind(0);
            info.setOrgCode("todo");
            info.setOrgName("orger");
            info.setPostCode("todo");
            info.setPostName("todo");
            info.setPrivCode("todo");
            info.setUserID("todo");
            info.setUserName("todo");
            return info;
        }
        return RequestContextUtil.getContext().getUserInfo();
    }
    /**
     * ユーザー種類の取得する
     * @return ユーザー種類
     */
    public static int getKind() {
        return getUserInfo().getKind();
    }

    /**
     * ユーザーIDの取得する
     * @return ユーザーID
     */
    public static String getUserID() {
        return getUserInfo().getUserID();
    }

    /**
     * ユーザー名前の取得する
     * @return ユーザー名前
     */
    public static String getUserName() {
        String strDealingsCode = getUserInfo().getDealingsCode();    //取引先コード
        String userName = null;
        if (strDealingsCode == null || strDealingsCode.length() == 0) {
            return getUserInfo().getUserName();
        } else if (strDealingsCode.length() >= 2) {
            String kubunCode = strDealingsCode.substring(1, 2);
            //製造ベンダー会社の場合
            if (kubunCode.equals(KB_VDR_KAISYA)) {
//                userName = AuthorityConfig.getSeizoVdrKaisyaNa(strDealingsCode);
            //製造ベンダー工場の場合
            } else if (kubunCode.equals(KB_VDR)) {
//                userName = AuthorityConfig.getSeizoVdrNa(strDealingsCode);
            //サプライヤーもしくはFFSの場合
            } else if (kubunCode.equals(KB_SUPPLIER) || kubunCode.equals(KB_FFS)) {
//                userName = AuthorityConfig.getSupplierNa(strDealingsCode);
            //ディーラーの場合
            } else if (kubunCode.equals(KB_DEALER)) {
//                userName = AuthorityConfig.getDealerNa(strDealingsCode);
            }
            //ユーザ名がNULLの場合はプレイヤーマスタから取得
//            if (userName == null) {
//                userName = AuthorityConfig.getPlayerNa(getUserInfo().getDiscernmentCode());
//            }
        }

        if (userName != null && userName.length() > 0) {
            userName = userName.trim();
        } else {
            userName = "利用者氏名該当無し";        //まずありえない条件
        }

        return userName;
    }

    /**
     * 非SCMユーザーチェック
     * @return チェック結果
     */
    public static boolean isNotScmUser() {
        int[] iKindGroup = {
                SCI_TOKKEN_USER,
                SCI_MANAGER,
                SCI_MANAGER_AGENCY,
                SCI_CREW_USER,
                SCI_MEMBER,
                SCI_FFS,
                SCI_FFS_MENU,
                SCI_FFS_AREA_LEADER,
                SCI_VENDER,
                SCI_DEALER,
                SCI_SUPPLIER,
                SCI_YASAI_MANAGER,
                SCI_BEIHAN_DERIKA_GROUP,
                SCI_OTHER
                };

        return isLoginUser(iKindGroup);
    }

    /**
     * ログインユーザー判別関数
     * @return チェック結果
     */
    public static boolean isLoginUser() {
        return isLoginUser(getUserInfo().getKind());
    }

    /**
    * ログインユーザー判別関数<BR>
    *@param  iKindGroup ユーザー種別配列
    *@return 判定結果を返す<BR>
    *         配列で指定されたグループのいずれかに該当する場合、true<BR>
    *        配列で指定されたグループのいずれにも該当しない場合、false<BR>
    *        （sessionよりユーザー情報を取得できない場合、false）
    */
    public static boolean isLoginUser(int[] iKindGroup) {

        boolean checkflg = false;

        for (int iKind : iKindGroup) {
            if (isLoginUser(iKind)) {
                checkflg = true;
                break;
            }
        }
        return checkflg;

    }

    /**
     * ログインユーザーであるかどうかチェック
     * @param iKind
     * @return チェック結果
     */
    public static boolean isLoginUser(int iKind) {

        String[] strCheckCode;

        UserInfo userInfo = getUserInfo();
        // ユーザー識別コード取得
        String strDiscernCode = userInfo.getDiscernmentCode();
        if (!StringUtils.hasText(strDiscernCode)) {
            return false;
        }
        //ログインユーザーコードに一致するか調べる
        switch (iKind) {
            case TOKKEN_USER :
                            strCheckCode = G_TOKKEN_USER_CODE;
                            break;

            case LAWSON_MANAGER :
                            strCheckCode = G_LAWSON_MANAGER_CODE;
                            break;

            case LAWSON_MEMBER :
                            strCheckCode = G_LAWSON_MEMBER_CODE;
                            break;

            case FFS_SHIZAI :
                            strCheckCode = G_FFS_SHIZAI_CODE;
                            break;

            case FFS_MENU :
                            strCheckCode = G_FFS_MENU_CODE;
                            break;

            case FFS_AREA_LEADER :
                            strCheckCode = G_FFS_AREA_LEADER_CODE;
                            break;

            case VENDER :
                            strCheckCode = G_VENDER_CODE;
                            break;

            case DEALER :
                            strCheckCode = G_DEALER_CODE;
                            break;

            case SUPPLYER :
                            strCheckCode = G_SUPPLYER_CODE;
                            break;

            case YASAI_MANAGER :
                            strCheckCode = G_YASAI_MANAGER_CODE;
                            break;
            // 米飯デリカ部
            case BEIHAN_DERIKA_GROUP :
                            strCheckCode = G_BEIHAN_DERIKA_GROUP_CODE;
                            break;
            // エリア商品部
            case AREA_SHOHIN_GROUP :
                            strCheckCode = G_AREA_SHOHIN_GROUP_CODE;
                            break;
            // 品質管理部
            case HINSITU_KANRI_GROUP :
                            strCheckCode = G_HINSITU_KANRI_GROUP_CODE;
                            break;
            // ＦＦＳ
            case FFS_GROUP :
                            strCheckCode = G_FFS_GROUP_CODE;
                            break;
            // 米飯デリカ部ＭＤ
            case BEIHAN_DERIKA_MD :
                            strCheckCode = G_BEIHAN_DERIKA_MD_CODE;
                            break;
            // エリア商品部ＢＹ
            case AREA_SHOHIN_BY :
                            strCheckCode = G_AREA_SHOHIN_BY_CODE;
                            break;
            // ローソン商品部　部長代理
            case LAWSON_MANAGER_AGENCY :
                            strCheckCode = G_LAWSON_MANAGER_AGENCY_CODE;
                            break;
            // 新米飯デリカ部
            case NEW_BEIHAN_DERIKA_GROUP :
                            strCheckCode = G_NEW_BEIHAN_DERIKA_GROUP_CODE;
                            break;

            // SCI特権ユーザー
            case SCI_TOKKEN_USER :
                            strCheckCode = G_SCI_TOKKEN_USER_CODE;
                            break;
            // SCI社管理職
            case SCI_MANAGER :
                            strCheckCode = G_SCI_MANAGER_CODE;
                            break;
            // SCI社管理職代理
            case SCI_MANAGER_AGENCY :
                            strCheckCode = G_SCI_MANAGER_AGENCY_CODE;
                            break;
            // SCI社クルーユーザー
            case SCI_CREW_USER :
                            strCheckCode = G_SCI_CREW_USER_CODE;
                            break;
            // SCI社業務担当
            case SCI_MEMBER :
                            strCheckCode = G_SCI_MEMBER_CODE;
                            break;
            // SCIFFS
            case SCI_FFS :
                            strCheckCode = G_SCI_FFS_CODE;
                            break;
            // SCIFFSメニュー
            case SCI_FFS_MENU :
                            strCheckCode = G_SCI_FFS_MENU_CODE;
                            break;
            // SCIFFSリーダー
            case SCI_FFS_AREA_LEADER :
                            strCheckCode = G_SCI_FFS_AREA_LEADER_CODE;
                            break;
            // SCIベンダー
            case SCI_VENDER :
                            strCheckCode = G_SCI_VENDER_CODE;
                            break;
            // SCIディーラー
            case SCI_DEALER :
                            strCheckCode = G_SCI_DEALER_CODE;
                            break;
            // SCIサプライヤー
            case SCI_SUPPLIER :
                            strCheckCode = G_SCI_SUPPLIER_CODE;
                            break;
            // SCI野菜管理者
            case SCI_YASAI_MANAGER :
                            strCheckCode = G_SCI_YASAI_MANAGER_CODE;
                            break;
            // SCI米飯デリカ部
            case SCI_BEIHAN_DERIKA_GROUP :
                            strCheckCode = G_SCI_BEIHAN_DERIKA_GROUP_CODE;
                            break;
            // SCIその他
            case SCI_OTHER :
                            strCheckCode = G_SCI_OTHER_CODE;
                            break;
            case NDTANKA_TOKKEN_USER :
                            strCheckCode = G_ND_TANKA_TOKKEN_USER_CODE;
                            break;
            // ローソン部長以上（着単価表示なし）
            case NDTANKA_LAWSON_MANAGER :
                            strCheckCode = G_ND_TANKA_LAWSON_MANAGER_CODE;
                            break;

            default : return false;

        }

        for (int iCnt = 0; iCnt < strCheckCode.length; iCnt++) {
            if (strDiscernCode.equals(strCheckCode[iCnt])) {
                return true;
            }
        }
        return false;
    }

    /**
     *  米飯デリカ部MDチェック
     * @return チェック結果
     */
    public static boolean isBeihanDerikaMd() {
        int[] iKindGroup = {
                BEIHAN_DERIKA_MD
                };

        return isLoginUser(iKindGroup);
    }

    /**
     * FFS資材担当チェック
     * @return チェック結果
     */
    public static boolean isFfsShizai() {
        int[] iKindGroup = {
                FFS_SHIZAI,
                SCI_MEMBER
                };

        return isLoginUser(iKindGroup);
    }

    /**
     * 野菜管理者チェック
     * @return チェック結果
     */
    public static boolean isYasaiManager() {
        int[] iKindGroup = {
                YASAI_MANAGER,
                SCI_YASAI_MANAGER
                };

        return isLoginUser(iKindGroup);

    }

    /**
     * サプライヤーチェック
     * @return チェック結果
     */
    public static boolean isSupplier() {
        int[] iKindGroup = {
                SUPPLYER,
                SCI_SUPPLIER
                };

        return isLoginUser(iKindGroup);
    }

    /**
     *  ディーラーチェック
     * @return チェック結果
     */
    public static boolean isDealer() {
        int[] iKindGroup = {
                DEALER,
                SCI_DEALER
                };

        return isLoginUser(iKindGroup);
    }

    /**
     * ユーザの所属する組織情報コード
     * @return ユーザの所属する組織情報コード
     */
    public static String getOrgCode() {
        return getUserInfo().getOrgCode();
    }

    /**
     *  製造ベンダーチェック
     * @return チェック結果
     */
    public static boolean isSeizoVender() {
        int[] iKindGroup = {
                VENDER,
                SCI_VENDER
                };

        return isLoginUser(iKindGroup);
    }

    /**
     * 消費税登録ユーザーチェック
     * @return チェック結果
     */
    public static boolean isSyouhizeirituUser() {
        int[] iKindGroup = {
                TOKKEN_USER,
                SCI_TOKKEN_USER
                };

        return isLoginUser(iKindGroup);
    }

    /**
     * 特権ユーザーチェック
     * @return チェック結果
     */
    public static boolean isTokkenUser() {
        int[] iKindGroup = {
                TOKKEN_USER,
                NDTANKA_TOKKEN_USER,
                SCI_TOKKEN_USER
                };

        return isLoginUser(iKindGroup);
    }

    /**
     * ユーザの所属する組織名
     * @return ユーザの所属する組織名
     */
    public static String getOrgName() {
        return getUserInfo().getOrgName();
    }

    /**
     * 職位コード
     * @return 職位コード
     */
    public static String getPostCode() {
        return getUserInfo().getPostCode();
    }

    /**
     * 職位名
     * @return 職位名
     */
    public static String getPostName() {
        return getUserInfo().getPostName();
    }
    /**
     * 利用者コード
     * @return 利用者コード
     */
    public static String getExternalUserCode() {
        return getUserInfo().getExternalUserCode();
    }

    /**
     * 取引者コード
     * @return 取引者コード
     */
    public static String getDealingsCode() {
        return getUserInfo().getDealingsCode();
    }

    /**
     * 識別コード
     * @return 識別コード
     */
    public static String getDiscernmentCode() {
        return getUserInfo().getDiscernmentCode();
    }

    /**
     * 権限コード
     * @return 権限コード
     */
    public static  String getPrivCode() {
        return getUserInfo().getPrivCode();
    }

    /**
    *取引先コードの取得
    *@return 取引先コードの文字列を返す<BR>
    *        取得できない場合、空白文字（""）を返す。
    *@since 1.17
    */
    public static String getTorihikisakiCode() {

        //初期化
        String strTorihikisakiCode = "";
        UserInfo userInfo = null;

        // UserInfo情報を取得する
        userInfo = getUserInfo();
        if (null == userInfo) {
            return "";
        }

        //(社外)取引先コード取得
        strTorihikisakiCode = userInfo.getDealingsCode();

        if (null == strTorihikisakiCode) {
            return "";
        }

        return strTorihikisakiCode;
    }


    /**
    *更新者IDの取得
    *@return 更新者IDの文字列を返す<BR>
    *        取得できない場合、空白文字（""）を返す。
    */
    public static String getKousinID() {
        //初期化
        String strKousinID = "";
        String strKind = "";

        // UserInfo情報を取得する
        UserInfo userInfo = getUserInfo();
        if (null == userInfo) {
            return "";
        }

        // ログイン種別取得
        int iInsertKind = userInfo.getKind();
        String strUserID = null;
        if (0 == iInsertKind) {
            strKind = "0";
            //（社内）ユーザーID取得
            strUserID = userInfo.getUserID();
        } else {
            strKind = "1";
            //（社外）利用者コード取得
            strUserID = userInfo.getExternalUserCode();
        }

        if (null == strUserID) {
            return "";
        }
        strKousinID = strKind + strUserID;
        // 更新者ＩＤを返す
        return strKousinID;
    }

    public static boolean isCanReferCostUser(String strDiscernCode) {
    	return checkReferCostUser(strDiscernCode);
    	
    }
    
    private static boolean checkReferCostUser(String strDiscernCode) {
    	 if (!StringUtils.hasText(strDiscernCode)) {
             return false;
         }
    	 List<String> playCodeList = new ArrayList<>();
         //特権ユーザー
         playCodeList.addAll(Arrays.asList(G_TOKKEN_USER_CODE));
         //ローソン特権（着単価表示なし）
         playCodeList.addAll(Arrays.asList(G_ND_TANKA_TOKKEN_USER_CODE));
         //SCI特権ユーザー
         playCodeList.addAll(Arrays.asList(G_SCI_TOKKEN_USER_CODE));
         //ローソン社部長以上
         playCodeList.addAll(Arrays.asList(G_LAWSON_MANAGER_CODE));
         //ローソン部長以上（着単価表示なし）
         playCodeList.addAll(Arrays.asList(G_ND_TANKA_LAWSON_MANAGER_CODE));
         //SCI社管理職
         playCodeList.addAll(Arrays.asList(G_SCI_MANAGER_CODE));
         //ローソン商品部 部長代理
         playCodeList.addAll(Arrays.asList(G_LAWSON_MANAGER_AGENCY_CODE));
         //SCI社管理職代理
         playCodeList.addAll(Arrays.asList(G_SCI_MANAGER_AGENCY_CODE));
         //ローソン社内
         playCodeList.addAll(Arrays.asList(G_LAWSON_MEMBER_CODE));
         //SCI社クルーユーザ
         playCodeList.addAll(Arrays.asList(G_SCI_CREW_USER_CODE));
         //SCI社業務担当
         playCodeList.addAll(Arrays.asList(G_SCI_MEMBER_CODE));
         //米飯デリカ部
         playCodeList.addAll(Arrays.asList(G_BEIHAN_DERIKA_GROUP_CODE));
         //米飯デリカ部MD
         playCodeList.addAll(Arrays.asList(G_BEIHAN_DERIKA_MD_CODE));
         //エリア商品部
         playCodeList.addAll(Arrays.asList(G_AREA_SHOHIN_GROUP_CODE));
         //品質管理部
         playCodeList.addAll(Arrays.asList(G_HINSITU_KANRI_GROUP_CODE));
         if (playCodeList.contains(strDiscernCode)) {
             return true;
         }
         return false;

    }
    
    /**
     * 原価や値入率が参照できるユーザーをチェックする。
     * @return チェック結果
     */
    public static boolean isCanReferCostUser() {
        UserInfo userInfo = getUserInfo();
        // ユーザー識別コード取得
        String strDiscernCode = userInfo.getDiscernmentCode();
        return checkReferCostUser(strDiscernCode);
//        if (!StringUtils.hasText(strDiscernCode)) {
//            return false;
//        }
//
//        List<String> playCodeList = new ArrayList<>();
//        //特権ユーザー
//        playCodeList.addAll(Arrays.asList(G_TOKKEN_USER_CODE));
//        //ローソン特権（着単価表示なし）
//        playCodeList.addAll(Arrays.asList(G_ND_TANKA_TOKKEN_USER_CODE));
//        //SCI特権ユーザー
//        playCodeList.addAll(Arrays.asList(G_SCI_TOKKEN_USER_CODE));
//        //ローソン社部長以上
//        playCodeList.addAll(Arrays.asList(G_LAWSON_MANAGER_CODE));
//        //ローソン部長以上（着単価表示なし）
//        playCodeList.addAll(Arrays.asList(G_ND_TANKA_LAWSON_MANAGER_CODE));
//        //SCI社管理職
//        playCodeList.addAll(Arrays.asList(G_SCI_MANAGER_CODE));
//        //ローソン商品部 部長代理
//        playCodeList.addAll(Arrays.asList(G_LAWSON_MANAGER_AGENCY_CODE));
//        //SCI社管理職代理
//        playCodeList.addAll(Arrays.asList(G_SCI_MANAGER_AGENCY_CODE));
//        //ローソン社内
//        playCodeList.addAll(Arrays.asList(G_LAWSON_MEMBER_CODE));
//        //SCI社クルーユーザ
//        playCodeList.addAll(Arrays.asList(G_SCI_CREW_USER_CODE));
//        //SCI社業務担当
//        playCodeList.addAll(Arrays.asList(G_SCI_MEMBER_CODE));
//        //米飯デリカ部
//        playCodeList.addAll(Arrays.asList(G_BEIHAN_DERIKA_GROUP_CODE));
//        //米飯デリカ部MD
//        playCodeList.addAll(Arrays.asList(G_BEIHAN_DERIKA_MD_CODE));
//        //エリア商品部
//        playCodeList.addAll(Arrays.asList(G_AREA_SHOHIN_GROUP_CODE));
//        //品質管理部
//        playCodeList.addAll(Arrays.asList(G_HINSITU_KANRI_GROUP_CODE));
//        if (playCodeList.contains(strDiscernCode)) {
//            return true;
//        }
//        return false;

    }
}
