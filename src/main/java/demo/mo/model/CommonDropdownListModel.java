package demo.mo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * DropDownList専用モデル
 */

@Getter
@Setter
@Schema(description = "DropDownList専用モデル")
public class CommonDropdownListModel {

    @JsonProperty("value")
    @Schema(description = "コード")
    private String code = null;

    @JsonProperty("text")
    @Schema(description = "名称")
    private String name = null;
}
