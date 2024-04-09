package demo.mo.model;

import lombok.Data;

@Data
public class StatusContextInfo {
    // ステータスID
    private String status;

    // 結果
    private String result;

    /**
     * 初期化メソッド
     * 
     * @param lastatus ステータスID
     * @param laresult 結果
     */
    public StatusContextInfo(final String lastatus, final String laresult) {
        this.status = lastatus;
        this.result = laresult;
    }

}
