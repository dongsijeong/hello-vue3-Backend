package demo.mo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * アップロード管理データ
 */

@Getter
@Setter
@Schema(description = "アップロード管理データ")
public class DtUploadKanriModel extends DemoBaseModel {

    @JsonProperty("fileCd")
    @Schema(description = "ファイルコード")
    private String fileCd = null;

    @JsonProperty("hozonFileSt")
    @Schema(description = "保存ファイル")
    private byte[] hozonFileSt = null;

    @JsonProperty("fileNa")
    @Schema(description = "ファイル名")
    private String fileNa = null;

    @JsonProperty("objectKey")
    @Schema(description = "オブジェクトキー")
    private String objectKey = null;
}
