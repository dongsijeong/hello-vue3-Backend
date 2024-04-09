package demo.mo.util;

/**
*SQL文字列作成クラス<BR>
*/
public final class SqlString {

    //値設定
    private static final String TOROKU_ID_FIELD = "TOROKU_ID";
    private static final String TOROKU_DATE_FIELD = "TOROKU_DM";
    private static final String KOUSIN_ID_FIELD = "KOSIN_ID";
    private static final String KOUSIN_DATE_FIELD = "KOSIN_DM";

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

    // 2016/07/07 鈴木虎太郎 プレイヤーコード追加 start
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
    *登録情報のフィールド文字列<BR>
    *値：TOROKU_ID,TOROKU_DM,KOSIN_ID,KOSIN_DM<BR>
    */
    public static final String TOUROKU_FIELD = TOROKU_ID_FIELD + "," + TOROKU_DATE_FIELD
      + "," + KOUSIN_ID_FIELD + "," + KOUSIN_DATE_FIELD;

    // コンストラクタ（非公開）
    private SqlString() {
    }

    /**
    *原材料マスターテーブル名取得<BR>
    *@param notScmFlg (非SCMユーザーかどうか)
    *@return <BR>FROM句内で使用される文字列<BR>
    *        非SCMユーザーでは無い場合、WHERE SHOYUSHA_FG = 0<BR>
    */
    public static String getMaGenzairyoTable(boolean notScmFlg) {
        String result = "";
        if (notScmFlg) {
            result = "MA_GENZAIRYO";
        } else {
            result = "(SELECT m.* FROM MA_GENZAIRYO m WHERE m.SHOYUSHA_FG = '0')";
        }
        return result;
    }

    /**
    *原材料ALLマスターテーブル名取得<BR>
    *@param  notScmFlg (非SCMユーザーかどうか)
    *@return <BR>FROM句内で使用される文字列<BR>
    *        非SCMユーザーでは無い場合、WHERE SHOYUSHA_FG = 0<BR>
    */
    public static String getMaGenzairyoAllTable(boolean notScmFlg) {
        String result = "";
        if (notScmFlg) {
            result = "OXX15X.MA_GENZAIRYO_ALL";
        } else {
            result = "(SELECT m.* FROM OXX15X.MA_GENZAIRYO_ALL m WHERE m.SHOYUSHA_FG = '0')";
        }
        return result;
    }
    /**
    *製造ベンダーマスターテーブル名取得<BR>
    *@param  notScmFlg (非SCMユーザーかどうか)
    *@return <BR>FROM句内で使用される文字列<BR>
    *        非SCMユーザーでは無い場合、WHERE SHOYUSHA_FG = 0<BR>
    */
    public static String getMaSeizoVenderTable(boolean notScmFlg) {
        String result = "";
        if (notScmFlg) {
            result = "OXX15X.MA_SEIZO_VENDER";
        } else {
            result = "(SELECT m.* FROM OXX15X.MA_SEIZO_VENDER m WHERE m.SHOYUSHA_FG = '0')";
        }
        return result;
    }

    /**
    *ベンダー会社マスターテーブル名取得<BR>
    *@param  notScmFlg (非SCMユーザーかどうか)
    *@return <BR>FROM句内で使用される文字列<BR>
    *        非SCMユーザーでは無い場合、SCMユーザーが取り扱わないレコードを除外したビューを返す<BR>
    */
    public static String getMaSeizoVKaisyaTable(boolean notScmFlg) {
        String result = "";
        if (notScmFlg) {
            result = "OXX15X.MA_SEIZO_V_KAISYA";
        } else {
            result = "MA_SEIZO_V_KAISYA_SCM_VW";
        }
        return result;
    }

    /**
    *ベンダー親会社マスターテーブル名取得<BR>
    *@param  notScmFlg (非SCMユーザーかどうか)
    *@return <BR>FROM句内で使用される文字列<BR>
    *        非SCMユーザーでは無い場合、SCMユーザーが取り扱わないレコードを除外したビューを返す<BR>
    */
    public static String getMaSeizoVOyakaisyaTable(boolean notScmFlg) {
        String result = "";
        if (notScmFlg) {
            result = "OXX15X.MA_SEIZO_V_OYAKAISYA";
        } else {
            result = "MA_SEIZO_V_OYAKAISYA_SCM_VW";
        }
        return result;
    }

    /**
    *ディーラーマスターテーブル名取得<BR>
    *@param  notScmFlg (非SCMユーザーかどうか)
    *@return <BR>FROM句内で使用される文字列<BR>
    *        非SCMユーザーでは無い場合、WHERE SHOYUSHA_FG = 0<BR>
    */
    public static String getMaDealerTable(boolean notScmFlg) {
        String result = "";
        if (notScmFlg) {
            result = "OXX15X.MA_DEALER";
        } else {
            result = "(SELECT m.* FROM OXX15X.MA_DEALER m WHERE m.SHOYUSHA_FG = '0')";
        }
        return result;
    }

    /**
    *サプライヤーマスターテーブル名取得<BR>
    *@param  notScmFlg (非SCMユーザーかどうか)
    *@return <BR>FROM句内で使用される文字列<BR>
    *        非SCMユーザーでは無い場合、WHERE SHOYUSHA_FG = 0<BR>
    */
    public static String getMaSupplierTable(boolean notScmFlg) {
        String result = "";
        if (notScmFlg) {
            result = "OXX15X.MA_SUPPLIER";
        } else {
            result = "(SELECT m.* FROM OXX15X.MA_SUPPLIER m WHERE m.SHOYUSHA_FG = '0')";
        }
        return result;
    }

}
