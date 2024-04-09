package demo.mo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * オンライン時間コントロールデータ
 */

@Getter
@Setter
@Schema(description = "オンライン時間コントロールデータ")
public class DtOnLineControlModel extends DemoBaseModel {

    @JsonProperty("gamenId")
    @Schema(description = "画面ID")
    private String gamenId = null;

    @JsonProperty("kyotuMotoGamenId")
    @Schema(description = "共通呼出し元画面ID")
    private String kyotuMotoGamenId = null;

    @JsonProperty("kaisiTm")
    @Schema(description = "開始時間")
    private String kaisiTm = null;

    @JsonProperty("syuryoTm")
    @Schema(description = "終了時間")
    private String syuryoTm = null;

}
