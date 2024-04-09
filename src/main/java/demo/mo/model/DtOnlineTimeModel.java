package demo.mo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * エンティティ情報データ
 */

@Getter
@Setter
@Schema(description = "エンティティ情報データ")
public class DtOnlineTimeModel extends DemoBaseModel {

    @JsonProperty("appSyubetu")
    @Schema(description = "APP種別")
    private String appSyubetu = null;

    @JsonProperty("yukokaisiDt")
    @Schema(description = "有効開始日")
    private String yukokaisiDt = null;

    @JsonProperty("yukosyuryoDt")
    @Schema(description = "有効終了日")
    private String yukosyuryoDt = null;

    @JsonProperty("kaisiTm")
    @Schema(description = "開始時間")
    private String kaisiTm = null;

    @JsonProperty("syuryoTm")
    @Schema(description = "終了時間")
    private String syuryoTm = null;
}
