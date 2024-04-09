package demo.mo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 *プレイヤー毎タブ表示制限データ
*/

@Getter
@Setter
@Schema(description = "プレイヤー毎タブ表示制限データ")
public class DtPlayerTabHyojiModel  extends DemoBaseModel {

    @JsonProperty("gamenId")
    @Schema(description = "画面ID")
    private String gamenId = null;

    @JsonProperty("tabId")
    @Schema(description = "タブID")
    private String tabId = null;

    @JsonProperty("playerCd")
    @Schema(description = "プレイヤーコード")
    private String playerCd = null;

}
