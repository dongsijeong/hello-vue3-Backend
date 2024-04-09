package demo.mo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserInfo {
    /**
     * ログイン種別
     */
    @JsonProperty("kind")
    @Schema(description = "ログイン種別")
    private int kind;

    /**
     * ユーザID
     */
    @JsonProperty("userID")
    @Schema(description = "ユーザID")
    private String userID;

    /**
     * ユーザ名
     */
    @JsonProperty("userName")
    @Schema(description = "ユーザ名")
    private String userName;

    /**
     * ユーザの組織コード
     */
    @JsonProperty("sosiki")
    @Schema(description = "ユーザの組織コード")
    private String sosiki;

    /**
     * ユーザの組織セキュリティグループ区分
     */
    @JsonProperty("sosikiSec")
    @Schema(description = "ユーザの組織セキュリティグループ区分")
    private String sosikiSec;

    /**
     * ユーザの所属する組織グループコード
     */
    @JsonProperty("orgCode")
    @Schema(description = "ユーザの所属する組織グループコード")
    private String orgCode;

    /**
     * ユーザの所属する組織名
     */
    @JsonProperty("orgName")
    @Schema(description = "ユーザの所属する組織名")
    private String orgName;

    /**
     * 職位コード
     */
    @JsonProperty("syokui")
    @Schema(description = "職位コード")
    private String syokui;

    /**
     * 職位セキュリティグループ区分
     */
    @JsonProperty("syokuiSec")
    @Schema(description = "職位セキュリティグループ区分")
    private String syokuiSec;

    /**
     * 職位グループコード
     */
    @JsonProperty("postCode")
    @Schema(description = "職位グループコード")
    private String postCode;

    /**
     * 職位名
     */
    @JsonProperty("postName")
    @Schema(description = "職位名")
    private String postName;
    /**
     * 利用者コード
     */
    @JsonProperty("externalUserCode")
    @Schema(description = "利用者コード")
    private String externalUserCode;

    /**
     * 取引者コード
     */
    @JsonProperty("dealingsCode")
    @Schema(description = "取引者コード")
    private String dealingsCode;

    /**
     * 識別コード
     */
    @JsonProperty("discernmentCode")
    @Schema(description = "識別コード")
    private String discernmentCode;

    /**
     * 権限コード
     */
    @JsonProperty("privCode")
    @Schema(description = "権限コード")
    private String privCode;

    /**
     * 運営会社コード（ＩＤ）
     */
    @JsonProperty("uneiCoCd")
    @Schema(description = "運営会社コード（ＩＤ）")
    private String uneiCoCd;

    /**
     * 運営会社コード（所属）
     */
    @JsonProperty("syozokuUneiCoCd")
    @Schema(description = "運営会社コード（所属）")
    private String syozokuUneiCoCd;

    /**
     * 画面処理日
     */
    @JsonProperty("syoribi")
    @Schema(description = "画面処理日")
    private String syoribi;

    /**
     * 旧所属メニューフラグ
     */
    @JsonProperty("kyumenu")
    @Schema(description = "旧所属メニューフラグ")
    private String kyumenu;

    /**
     * （新）氏名コード
     */
    @JsonProperty("newNamecd")
    @Schema(description = "（新）氏名コード")
    private String newNamecd;

    /**
     * メニューＩＤ
     */
    @JsonProperty("menuId")
    @Schema(description = "メニューＩＤ")
    private String menuId;

    /**
     * PRiSM社コード
     */
    @JsonProperty("prismCoCd")
    @Schema(description = "PRiSM社コード")
    private String prismCoCd;

    /**
     * 取引相手先コード
     */
    @JsonProperty("cstPrtCd")
    @Schema(description = "取引相手先コード")
    private String cstPrtCd;

}
