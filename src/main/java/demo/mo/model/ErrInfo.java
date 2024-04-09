package demo.mo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ErrInfo {

    @JsonProperty("type")
    @Schema(description = "発生したエラーのコードを表す文字列")
    private String type;

    @JsonProperty("title")
    @Schema(description = "短めのメッセージ")
    private String title;

    @JsonProperty("status")
    @Schema(description = "ステータスコード")
    private Integer status;

    @JsonProperty("detailMsg")
    @Schema(description = "ユーザー向けのメッセージ")
    private String[] detailMsg;

    @JsonProperty("instance")
    @Schema(description = "(オプション)エラー対象")
    private String instance;

    @JsonProperty("others")
    @Schema(description = "その他、必要となる情報をフィールドとして追加してよい")
    private String[] others;

}
