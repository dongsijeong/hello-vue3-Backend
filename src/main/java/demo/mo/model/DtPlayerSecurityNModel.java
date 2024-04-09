package demo.mo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 *プレイヤーセキュリティデータ（社内）
*/

@Getter
@Setter
@Schema(description = "プレイヤーセキュリティデータ（社内）")
public class DtPlayerSecurityNModel  extends DemoBaseModel {

    @JsonProperty("sosikiCd")
    @Schema(description = "組織コード")
    private String sosikiCd = null;

    @JsonProperty("syokuiCd")
    @Schema(description = "職位コード")
    private String syokuiCd = null;

    @JsonProperty("simeiCd")
    @Schema(description = "氏名コード")
    private String simeiCd = null;

    @JsonProperty("playerCd")
    @Schema(description = "プレイヤーコード")
    private String playerCd = null;

}
