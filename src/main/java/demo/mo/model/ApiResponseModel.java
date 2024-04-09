package demo.mo.model;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

import demo.mo.config.DataAccessConfig;
import demo.mo.constants.Constant.MESSAGECODE;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * APIリスポンス
 *
 * @param <T>
 */
@Schema(description = "APIリスポンス")
public class ApiResponseModel<T> {

    @JsonProperty("code")
    private Integer code = null;

    @JsonProperty("message")
    private String message = null;

    @JsonProperty("errinfo")
    private ErrInfo errinfo = null;

    @JsonProperty("data")
    private List<T> data = null;

    @JsonProperty("token")
    private String token = null;

    /**
     * 初期化メソッド
     */
    public ApiResponseModel() {
    }

    /**
     * 初期化メソッド
     * 
     * @param pCcode
     * @return APIリスポンスインスタンス
     */
    public ApiResponseModel<T> code(Integer pCcode) {
        this.code = pCcode;
        return this;
    }

    /**
     * リスポンスのコード
     *
     * @return code
     **/
    @Schema(required = true, description = "リスポンスのコード")
    public Integer getCode() {
        return code;
    }

    /**
     * コードの設定
     * 
     * @param pCode
     */
    public void setCode(Integer pCode) {
        this.code = pCode;
    }

    /**
     * メッセージの設定
     * 
     * @param pMessage
     * @return APIリスポンスインスタンス
     */
    public ApiResponseModel<T> message(String pMessage) {
        this.message = pMessage;
        return this;
    }

    /**
     * リターンメッセージ
     *
     * @return message
     **/
    @Schema(description = "リターンメッセージ")

    public String getMessage() {
        return message;
    }

    /**
     * メッセージの設定
     * 
     * @param pMessage
     */
    public void setMessage(String pMessage) {
        this.message = pMessage;
    }

    /**
     * tokenの設定
     * 
     * @param ptoken
     * @return APIリスポンスインスタンス
     */
    public ApiResponseModel<T> token(String ptoken) {
        this.token = ptoken;
        return this;
    }

    /**
     * リターンメッセージ
     *
     * @return message
     **/
    @Schema(description = "リターントークン")

    public String getToken() {
        return token;
    }

    /**
     * tokenの設定
     * 
     * @param pToken
     */
    public void setToken(String pToken) {
        this.token = pToken;
    }

    /**
     * APIリスポンスの結果リストを設定する
     * 
     * @param responseResult
     * @return APIリスポンスインスタンス
     */
    public ApiResponseModel<T> data(List<T> responseResult) {
        this.data = responseResult;
        return this;
    }

    /**
     * レスポンス結果
     *
     * @return data
     **/
    @Schema(description = "レスポンス結果")

    public List<T> getData() {
        return data;
    }

    /**
     * APIリスポンスの結果リストを取得する
     * 
     * @param pData
     */
    public void setData(List<T> pData) {
        this.data = pData;
    }

    /**
     * エラー情報の取得
     * 
     * @return エラー情報
     */
    public ErrInfo getErrinfo() {
        return errinfo;
    }

    /**
     * エラー情報を設定する
     * 
     * @param err
     */
    public void setErrInfo(ErrInfo err) {
        this.errinfo = err;
    }

    /**
     * レスポンスをラップする。
     * 
     * @param <T>            データタイプ
     * @param responseResult 結果
     * @return ラップしたレスポンス
     */
    public static <T> ResponseEntity<ApiResponseModel<T>> wrapSuccessResponseResult(List<T> responseResult) {
        ApiResponseModel<T> apiResponsemodel = new ApiResponseModel<T>();
        apiResponsemodel.code(HttpStatus.OK.value());
        apiResponsemodel.data(responseResult);
        apiResponsemodel.token(DataAccessConfig.getNewToken());
        return new ResponseEntity<ApiResponseModel<T>>(apiResponsemodel, HttpStatus.OK);
    }

    /**
     * レスポンスをラップする。
     * 
     * @param <T>            データタイプ
     * @param responseResult 結果
     * @return ラップしたレスポンス
     */
    public static <T> ResponseEntity<ApiResponseModel<T>> wrapSuccessInfoResponseResult(List<T> responseResult) {
        ApiResponseModel<T> apiResponsemodel = new ApiResponseModel<T>();
        apiResponsemodel.code(HttpStatus.OK.value());
        apiResponsemodel.data(responseResult);
        apiResponsemodel.token(DataAccessConfig.getNewToken());
        return new ResponseEntity<ApiResponseModel<T>>(apiResponsemodel, HttpStatus.OK);
    }

    /**
     * レスポンスをラップする
     * 
     * @param <T>            データタイプ
     * @param responseResult 結果
     * @param messageId      メッセージID
     * @return ラップしたレスポンス
     */
    public static <T> ResponseEntity<ApiResponseModel<T>> wrapSuccessResponseResult(List<T> responseResult,
            String messageId) {
        ApiResponseModel<T> apiResponsemodel = new ApiResponseModel<T>();
        apiResponsemodel.code(HttpStatus.OK.value());
        apiResponsemodel.message(DataAccessConfig.getMessage(messageId));
        apiResponsemodel.token(DataAccessConfig.getNewToken());
        apiResponsemodel.data(responseResult);
        return new ResponseEntity<ApiResponseModel<T>>(apiResponsemodel, HttpStatus.OK);
    }

    /**
     * レスポンスをラップする
     * 
     * @param <T>            データタイプ
     * @param responseResult 結果
     * @param messageId      メッセージID
     * @param args           メッセージパラメータ
     * @return ラップしたレスポンス
     */
    public static <T> ResponseEntity<ApiResponseModel<T>> wrapSuccessResponseResult(List<T> responseResult,
            String messageId, String... args) {
        ApiResponseModel<T> apiResponsemodel = new ApiResponseModel<T>();
        apiResponsemodel.code(HttpStatus.OK.value());
        apiResponsemodel.message(DataAccessConfig.getMessage(messageId, args));
        apiResponsemodel.token(DataAccessConfig.getNewToken());
        apiResponsemodel.data(responseResult);
        return new ResponseEntity<ApiResponseModel<T>>(apiResponsemodel, HttpStatus.OK);
    }

    /**
     * レスポンスをラップする
     * 
     * @param <T>       データタイプ
     * @param messageId メッセージID
     * @return ラップしたレスポンス
     */
    public static <T> ResponseEntity<ApiResponseModel<T>> wrapSuccessResponse(String messageId) {
        ApiResponseModel<T> apiResponsemodel = new ApiResponseModel<T>();
        apiResponsemodel.code(HttpStatus.OK.value());
        apiResponsemodel.message(DataAccessConfig.getMessage(messageId));
        apiResponsemodel.token(DataAccessConfig.getNewToken());
        return new ResponseEntity<ApiResponseModel<T>>(apiResponsemodel, HttpStatus.OK);
    }

    /**
     * レスポンスをラップする
     * 
     * @param <T>       データタイプ
     * @param messageId メッセージID
     * @param message   ラップしたレスポンス
     * @return ラップしたレスポンス
     */
    public static <T> ResponseEntity<ApiResponseModel<T>> wrapSuccessResponse(String messageId, String... message) {
        ApiResponseModel<T> apiResponsemodel = new ApiResponseModel<T>();
        apiResponsemodel.code(HttpStatus.OK.value());
        apiResponsemodel.message(DataAccessConfig.getMessage(messageId));
        apiResponsemodel.token(DataAccessConfig.getNewToken());
        return new ResponseEntity<ApiResponseModel<T>>(apiResponsemodel, HttpStatus.OK);
    }

    /**
     * 更新処理が成功するレスポンス
     * 
     * @return 成功するレスポンス
     */
    public static ResponseEntity<ApiResponseModel<Object>> wrapSuccessResponse() {
        ApiResponseModel<Object> apiResponsemodel = new ApiResponseModel<Object>();
        apiResponsemodel.code(HttpStatus.OK.value());
        apiResponsemodel.message(DataAccessConfig.getMessage(MESSAGECODE.M_CM_0002_102));
        apiResponsemodel.token(DataAccessConfig.getNewToken());
        return new ResponseEntity<ApiResponseModel<Object>>(apiResponsemodel, HttpStatus.OK);
    }

    /**
     * 更新処理が成功するレスポンス
     * 
     * @return 成功するレスポンス
     */
    public static ResponseEntity<ApiResponseModel<Object>> wrapSuccessResponseNoMsg() {
        ApiResponseModel<Object> apiResponsemodel = new ApiResponseModel<Object>();
        apiResponsemodel.code(HttpStatus.OK.value());
        apiResponsemodel.token(DataAccessConfig.getNewToken());
        return new ResponseEntity<ApiResponseModel<Object>>(apiResponsemodel, HttpStatus.OK);
    }
}
