package demo.mo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作情報を管理する。
 * @author zchang4
 *
 */
public final class ActionInfoManger {
    private static ActionInfoManger sInstace = null;
    private  Map<String, String> actionInfoMap = null;
    private  Map<String, String> gamenNameMap = null;
    private ActionInfoManger() {
    }

    /**
     * 情報をロードする。
     */
    public static void loadInfo() {
        if (sInstace == null) {
            sInstace = new ActionInfoManger();
            sInstace.init();
        }
    }
    private  void init() {
        actionInfoMap = new HashMap<>();
        actionInfoMap.put("reg", "登録ボタン押下");
        actionInfoMap.put("del", "削除ボタン押下");
        actionInfoMap.put("upd", "更新ボタン押下");
        actionInfoMap.put("sel", "検索ボタン押下");
        actionInfoMap.put("init", "画面初期化");
        actionInfoMap.put("processDownload", "ダウンロードデータ作成ボタン押下");
        actionInfoMap.put("checkStatus", "ファイルダウンロードステータスチェック");
        actionInfoMap.put("down", "ダウンロードボタン押下");
        actionInfoMap.put("up", "アップロードボタン押下");
        actionInfoMap.put("admit", "承認ボタン押下");
        actionInfoMap.put("print", "印刷ボタン押下");
        actionInfoMap.put("execute", "実行ボタン押下");
        actionInfoMap.put("tabChange", "タブ切換える");
        actionInfoMap.put("sealView", "サーマルラベル表示内容確認表ボタン押下");
        actionInfoMap.put("fill", "盛付ボタン押下");
        actionInfoMap.put("division", "分割ボタン押下");
        actionInfoMap.put("throw", "原材料/半製品投入ボタン押下");
        actionInfoMap.put("raw", "原材料一覧ボタン押下");
        actionInfoMap.put("component", "構成表示ボタン押下");
        actionInfoMap.put("kakaku", "価格確認ボタン押下");
        actionInfoMap.put("cal", " 再計算ボタン押下");
        actionInfoMap.put("add", "実原材料追加ボタン押下");
        actionInfoMap.put("onChange", "値が変更され、かつフォーカスアウト");
        actionInfoMap.put("select", "選択ボタン押下");
        actionInfoMap.put("changeCombobox", "コンボボックスの選択項目変更");
        actionInfoMap.put("jumpCheck", "遷移イベント発生前処理");
        actionInfoMap.put("getDetail", "詳細画面データ検索イベント");
        actionInfoMap.put("confirm", "確定ボタン押下");
        actionInfoMap.put("getCtrlStatus", "画面コントローラー状態の取得");
        actionInfoMap.put("simulation", "シミュレーションボタン押下");
        actionInfoMap.put("home", "システムメインメニュー画面初期化");
        actionInfoMap.put("saiyo", "採用ボタン押下");
        actionInfoMap.put("iKatsuDel", "一括削除ボタン押下");
        actionInfoMap.put("siteiNasi", "指定なしボタン押下");

        gamenNameMap = new HashMap<>();
        gamenNameMap.put("lmv01071", "サプライヤー一覧選択");
        gamenNameMap.put("lmv01031", "サプライヤーマスター登録");
        gamenNameMap.put("lmv02071", "ディーラーマスター一覧選択");
        gamenNameMap.put("lmv02031", "ディーラーマスター登録");
        gamenNameMap.put("lmv03071", "ディーラーDCマスター一覧選択");
        gamenNameMap.put("lmv03031", "ディーラーDCマスター登録");
        gamenNameMap.put("lmv04031", "ディーラー標準休業日登録");
        gamenNameMap.put("lmv04071", "ディーラー標準休業日登録状況一覧選択");
        gamenNameMap.put("lmv05031", "ディーラー納品リードタイム登録");
        gamenNameMap.put("lmv05071", "ディーラー納品リードタイム登録状況一覧選択");
        gamenNameMap.put("lmv06031", "ディーラー特別休業日登録");
        gamenNameMap.put("lmv06071", "ディーラー特別休業日登録状況一覧選択");
        gamenNameMap.put("lmv07071", "製造ベンダー一覧選択");
        gamenNameMap.put("lmv07031", "製造ベンダーマスター登録");
        gamenNameMap.put("lmv08071", "製造ベンダー親会社一覧選択");
        gamenNameMap.put("lmv08031", "製造ベンダー親会社マスター登録");
        gamenNameMap.put("lmv09071", "製造ベンダー会社一覧選択");
        gamenNameMap.put("lmv09031", "製造ベンダー会社マスター登録");
        gamenNameMap.put("lmv13051", "CDCマスター追加項目一覧登録");
        gamenNameMap.put("lmv17031", "法定添加物マスター登録");
        gamenNameMap.put("lmv20021", "マスターダウンロード対象選択");
        gamenNameMap.put("lmv20022", "マスターダウンロード処理選択画面");
        gamenNameMap.put("lmv21051", "ディーラーDC供給製造ベンダー一覧登録");
        gamenNameMap.put("lmv22011", "SCI原材料分類マスター処理選択");
        gamenNameMap.put("lmv22031", "SCI原材料分類マスター　大分類登録");
        gamenNameMap.put("lmv22032", "SCI原材料分類マスター　中分類登録");
        gamenNameMap.put("lmv22033", "SCI原材料分類マスター　小分類登録");
        gamenNameMap.put("lmr01071", "指定食材一覧選択");
        gamenNameMap.put("lmr01031", "指定食材登録");
        gamenNameMap.put("lmr02071", "指定外食材一覧選択");
        gamenNameMap.put("lmr02031", "指定外食材登録");
        gamenNameMap.put("lmr03071", "指定包材一覧選択");
        gamenNameMap.put("lmr03041", "指定包材登録");
        gamenNameMap.put("lmr04071", "指定外包材一覧選択");
        gamenNameMap.put("lmr04031", "指定外包材登録");
        gamenNameMap.put("lmr05071", "代表原材料一覧選択");
        gamenNameMap.put("lmr05031", "代表原材料登録");
        gamenNameMap.put("lmr06071", "代表原材料製造ベンダー一覧選択");
        gamenNameMap.put("lmr06051", "代表原材料製造ベンダー一覧登録");
        gamenNameMap.put("lmr07071", "指定食材単価変更一覧選択");
        gamenNameMap.put("lmr07051", "指定食材単価変更一覧登録");
        gamenNameMap.put("lmr08051", "製造ベンダー追加原材料一覧登録");
        gamenNameMap.put("lmr09061", "原材料別使用商品一覧照会");
        gamenNameMap.put("lmr13061", "原材料別使用半製品一覧照会");
        gamenNameMap.put("lmc01071", "半製品一覧選択");
        gamenNameMap.put("lmc01031", "半製品登録");
        gamenNameMap.put("lmc01061", "半製品構成表示一覧照会");
        gamenNameMap.put("lmc01062", "半製品別使用原材料一覧照会");
        gamenNameMap.put("lmc01101", "半製品印刷");
        gamenNameMap.put("lmp01071", "生産加工用商品一覧選択");
        gamenNameMap.put("lmp01031", "生産加工商品マスター登録");
        gamenNameMap.put("lmp01032", "サーマルシール表示内容確認表登録");
        gamenNameMap.put("lmp01033", "栄養成分　試験・検査成績表登録");
        gamenNameMap.put("lmp01061", "生産加工商品構成表示一覧照会");
        gamenNameMap.put("lmp01062", "商品別使用原材料一覧照会");
        gamenNameMap.put("lmp01101", "盛付加工表印刷");
        gamenNameMap.put("lmp01102", "生産加工商品規格情報印刷");
        gamenNameMap.put("lmp01103", "生産加工商品品質・鮮度情報印刷");
        gamenNameMap.put("lmp01104", "生産加工商品サーマルラベル情報印刷");
        gamenNameMap.put("lmp01105", "生産加工商品盛付情報印刷");
        gamenNameMap.put("lmp01106", "サーマルラベル表示内容確認表印刷");
        gamenNameMap.put("lmp01107", "栄養成分　試験・検査成績表印刷");
        gamenNameMap.put("lmp02051", "生産加工用商品製造ベンダー一覧登録");
        gamenNameMap.put("lmp03061", "製造ベンダー別商品一覧照会");
        gamenNameMap.put("lgn01031", "承認登録");
        gamenNameMap.put("lgn01071", "未承認生産加工商品一覧選択");
        gamenNameMap.put("lgn01072", "未承認半製品一覧選択");
        gamenNameMap.put("lgn01073", "未承認指定食材一覧選択");
        gamenNameMap.put("lgn01074", "未承認指定外食材一覧選択");
        gamenNameMap.put("lgn01075", "未承認指定外包材一覧選択");
        gamenNameMap.put("lcu0107e", "指定食材単価変更一覧選択");
        gamenNameMap.put("lsm01051", "マスターコピー配信一覧登録");
        gamenNameMap.put("lcu01076", "原材料・半製品一覧選択");
        gamenNameMap.put("lcu01071", "サプライヤー一覧選択");
        gamenNameMap.put("lcu01072", "ディーラー一覧選択");
        gamenNameMap.put("lcu0107b", "製造ベンダー親会社一覧選択");
        gamenNameMap.put("lcu0107a", "製造ベンダー会社一覧選択");
        gamenNameMap.put("lcu01073", "製造ベンダー一覧選択");
        gamenNameMap.put("lcu01078", "代表商品一覧選択");
        gamenNameMap.put("lcu0107c", "実原材料一覧選択");
        gamenNameMap.put("lcu0107e", "指定食材単価変更一覧選択");
        gamenNameMap.put("lcu0107d", "法定添加物一覧選択");
        gamenNameMap.put("lcu02081", "システムメインメニュー画面");
        gamenNameMap.put("lcu03091", "処理結果表示画面");
        gamenNameMap.put("lcu0107j", "SCI原材料分類一覧選択");
        gamenNameMap.put("lcu04021", "ファイルアップロード選択画面");
        gamenNameMap.put("lcu05041", "原材料費変更内容確認画面");
        gamenNameMap.put("lmt01071", "生産加工用商品テストマスター一覧選択");
        gamenNameMap.put("lmt01031", "生産加工用商品テストマスター登録");
        gamenNameMap.put("lmt02071", "半製品テストマスター一覧選択");
        gamenNameMap.put("lmt02031", "半製品テストマスター登録");
        gamenNameMap.put("lmt02062", "半製品テストマスター別使用原材料一覧照会");
        gamenNameMap.put("lmt03071", "仮食材一覧選択");
        gamenNameMap.put("lmt03031", "仮食材登録");
        gamenNameMap.put("lmt03032", "仮食材指定食材紐付");
        gamenNameMap.put("lmt04071", "仮包材一覧選択");
        gamenNameMap.put("lmt04031", "仮包材登録画面");
        gamenNameMap.put("lmt01061", "生産加工用商品テストマスター構成表示一覧照会");
    }

    /**
     * アクセション情報取得する。
     * @param key アクセション
     * @return アクセション情報
     */
    public static String getActionInfo(String key) {
//        if (sInstace == null) {
//            sInstace = new ActionInfoManger();
//            sInstace.init();
//        }
        if (sInstace.actionInfoMap.containsKey(key)) {
            return sInstace.actionInfoMap.get(key);
        }
        return "";
    }

    /**
     * 画面情報
     * @param key 画面ID
     * @return 画面情報
     */
    public static String getGamenInfo(String key) {
        if (sInstace.gamenNameMap.containsKey(key)) {
            return sInstace.gamenNameMap.get(key);
        }
        return "";
    }
}
