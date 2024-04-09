package demo.mo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 *プレイヤーセキュリティデータ（社外）
*/

@Getter
@Setter
@Schema(description = "プレイヤーセキュリティデータ（社外）")
public class DtPlayerSecurityGModel  extends DemoBaseModel {

    @JsonProperty("torihikisakiCd")
    @Schema(description = "取引先コード")
    private String torihikisakiCd = null;

    @JsonProperty("riyosyaCd")
    @Schema(description = "利用者コード")
    private String riyosyaCd = null;

    @JsonProperty("seigenCd")
    @Schema(description = "権限コード")
    private String seigenCd = null;

    @JsonProperty("playerCd")
    @Schema(description = "プレイヤーコード")
    private String playerCd = null;

}
