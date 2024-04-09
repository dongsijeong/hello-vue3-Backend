package demo.mo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DemoBaseModel {

    @JsonProperty("torokuId")
    @Schema(description = "登録者")
    private String torokuId;

    @JsonProperty("torokuDm")
    @Schema(description = "登録日")
    private String torokuDm;

    @JsonProperty("kosinId")
    @Schema(description = "更新者ID")
    private String kosinId;

    @JsonProperty("kosinDm")
    @Schema(description = "更新日")
    private String kosinDm;
}
