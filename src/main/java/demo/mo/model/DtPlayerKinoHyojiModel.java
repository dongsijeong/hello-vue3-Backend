package demo.mo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 *プレイヤー毎機能表示制限データ
*/

@Getter
@Setter
@Schema(description = "プレイヤー毎機能表示制限データ")
public class DtPlayerKinoHyojiModel  extends DemoBaseModel {

    @JsonProperty("gamenId")
    @Schema(description = "画面ID")
    private String gamenId = null;

    @JsonProperty("playerCd")
    @Schema(description = "プレイヤーコード")
    private String playerCd = null;

}
