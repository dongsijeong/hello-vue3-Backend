package demo.mo.model;

import lombok.Data;

@Data
public class ActionContextInfo {
    // アクセションID
    private String actionId;

    // アクセション名前
    private String actionName;

    /**
     * 初期化メソッド
     * 
     * @param lactionId   アクセションID
     * @param lactionName アクセション名前
     */
    public ActionContextInfo(final String lactionId, final String lactionName) {
        this.actionId = lactionId;
        this.actionName = lactionName;
    }

}
