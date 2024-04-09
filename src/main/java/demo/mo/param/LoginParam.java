/**
 *
 */
package demo.mo.param;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * ログイン用パラメータ
 *
 */
@Getter
@Setter
@Schema(description = "ストアドプロシージャの呼び出すパラメータ")
public class LoginParam {

    @JsonProperty("sosikiSec")
    @Schema(description = "組織セキュリティグループ区分")
    private String sosikiSec = null;

    @JsonProperty("sosikiGrp")
    @Schema(description = "組織グループコード")
    private String sosikiGrp = null;

    @JsonProperty("namecd")
    @Schema(description = "氏名コード")
    private String namecd = null;

    @JsonProperty("userName")
    @Schema(description = "ユーザー名")
    private String userName = null;

    @JsonProperty("organizationName")
    @Schema(description = "組織名")
    private String organizationName = null;

    @JsonProperty("syokuiSec")
    @Schema(description = "職位セキュリティグループ区分")
    private String syokuiSec = null;

    @JsonProperty("syokuiGrp")
    @Schema(description = "職位グループコード")
    private String syokuiGrp = null;

    @JsonProperty("postName")
    @Schema(description = "職位名")
    private String postName = null;

    @JsonProperty("status")
    @Schema(description = "ストアドプロシージャ返すステータス")
    private String status = null;

    @JsonProperty("proc")
    @Schema(description = "ストアドプロシージャ名")
    private String proc = null;

    @JsonProperty("errMsg")
    @Schema(description = "エラーメッセージ")
    private String errMsg = null;

    @JsonProperty("encryptKey")
    @Schema(description = "暗号化キー")
    private String encryptKey = null;
}
