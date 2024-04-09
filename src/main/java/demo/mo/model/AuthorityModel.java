package demo.mo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 権限情報
 */

@Data
@Schema(description = "権限情報専用モデル")
public class AuthorityModel {

    @JsonProperty("mainMenuCode")
    @Schema(description = "トップメニュー")
    private String mainMenuCode = null;

    @JsonProperty("subMenuCode")
    @Schema(description = "サブメニュー")
    private String subMenuCode = null;

    @JsonProperty("controlCode")
    @Schema(description = "コントロールコード")
    private String controlCode = null;
}
