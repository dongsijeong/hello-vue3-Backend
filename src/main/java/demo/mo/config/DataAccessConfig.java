package demo.mo.config;

import java.util.Locale;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import demo.mo.service.common.JWTProviderService;


@Component
public class DataAccessConfig implements InitializingBean {
    private static DataAccessConfig sInstance;

    @Autowired
    private MessageSource messagesource;
    @Autowired
    private JWTProviderService jwtProviderService;

    @Value("${local.directory}")
    private String tempPath;

    @Value("${LabelFormat}")
    private String labelFormat;

    @Value("${UploadMaxFilesize}")
    private String uploadMaxFilesize;

    @Value("${ExcelToImageMaxFilesize}")
    private String excelToImageMaxFilesize;

    @Value("${UploadFileTemporaryPath}")
    private String uploadFileTemporaryPath;

    @Value("${UploadWaitTime}")
    private String uploadWaitTime;

    @Value("${Syokuzai_Kikaku_Excel}")
    private String syokuzaiKikakuExcel;

    @Value("${Hanseihin_Flow_Excel}")
    private String hanseihinFlowExcel;

    @Value("${Syohin_Morituke_Excel}")
    private String syohinMoritukeExcel;

    @Value("${Syohin_Hosoku_Excel}")
    private String syohinHosokuExcel;

    @Value("${front.end.redirecturl}")
    private String redirecturl;

    @Value("${Upload_Extension_Siteisyokuzai}")
    private String uploadExtensionSiteisyokuzai;

    @Value("${Upload_Extension_Hanseihin}")
    private String uploadExtensionHanseihin;

    @Value("${Upload_Extension_Levelari}")
    private String uploadExtensionLevelari;

    @Value("${Upload_Extension_Levelnasi}")
    private String uploadExtensionLevelnasi;

    @Value("${Upload_Extension_Sakaritsuki}")
    private String uploadExtensionSakaritsuki;

    @Value("${Upload_Extension_Hosoku}")
    private String uploadExtensionHosoku;

    @Value("${Upload_Extension_TowPoint}")
    private String uploadExtensionTowPoint;

    @Value("${Upload_Extension_ProductionInfo}")
    private String uploadExtensionProductionInfo;

    @Value("${Upload_Extension_EBASEStandards}")
    private String uploadExtensionEBASEStandards;

    @Value("${Upload_Extension_TestLine}")
    private String uploadExtensionTestLine;

    @Value("${Upload_Extension_Other}")
    private String uploadExtensionOther;

    @Value("${Upload_Extension_SPrice}")
    private String uploadExtensionSPrice;

    @Value("${Upload_Extension_Certificate}")
    private String uploadExtensionCertificate;

    @Value("${MessageFile:}")
    private String emgMessageFile;

    @Value("${InfomationFile:}")
    private String infomationFile;

    @Value("${restapi.auth.check:false}")
    private boolean apicheck;

    @Value("${In_Encrypt_Key:}")
    private String inEncryptKey;

    @Value("${aws.message.bucket}")
    private String bucket;

    @Value("${aws.downloadKanri.bucket.path}")
    private String downloadKanriBucketPath;

    @Value("${aws.uploadKanri.bucket.path}")
    private String uploadKanriBucketPath;

    @Value("${aws.towPoint.bucket.path}")
    private String towPointBucketPath;

    @Value("${aws.testLine.bucket.path}")
    private String testLineBucketPath;

    @Value("${aws.other.bucket.path}")
    private String otherBucketPath;

    @Value("${aws.sPrice.bucket.path}")
    private String sPriceBucketPath;

    @Value("${aws.Certificate.bucket.path}")
    private String certificateBucketPath;

    @Value("${spring.profiles.active}")
    private String springProfilesActive;

    @Value("${list.display.maximumnumber:10000}")
    private int displayMaximumnumber;

    @Value("${aws.httpclient.maxconnections:200}")
    private int maxconnections;

    @Value("${aws.httpclient.connectiontimeout:5}")
    private int connectiontimeout;

    @Value("${aws.ebase.bucket}")
    private String awsEbaseBucket;

    @Value("${aws.approval.document.path}")
    private String approvalPath;

    @Value("${aws.lastapproval.document.path}")
    private String lastapprovalPath;

    @Value("${SPriceFile_Sheet_Name}")
    private String sPriceFileSheetName;

    @Value("${SPriceFile_Yukokaisi_Row_Col}")
    private String sPriceFileYukokaisiRowCol;

    @Value("${SPriceFile_SPrice_Row_Col}")
    private String sPriceFileSPriceRowCol;

    @Value("${SPriceFile_GenkaVl_Row_Col}")
    private String sPriceFileGenkaVlRowCol;

    @Value("${SPriceFile_KakouHi_Row_Col}")
    private String sPriceFileKakouHiRowCol;

    @Value("${SPriceFile_Version_Row_Col}")
    private String sPriceFileVersionRowCol;

    /**
     * sInstanceの初期化
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        sInstance = this;
    }

    /**
     *ローカルパスを返す。
     * @return ローカルパス
     */
    public static String getLocalPath() {
        return sInstance.tempPath;
    }

    /**
     *ローカルパスを返す。
     * @return ローカルパス
     */
    public static String getBucket() {
        return sInstance.bucket;
    }

    /**
     *キーを返す。
     * @return キー
     */
    public static String getInEncryptKey() {
        return sInstance.inEncryptKey;
    }

    /**
     *ローカルパスを返す。
     * @return ローカルパス
     */
    public static boolean getApicheck() {
        return sInstance.apicheck;
    }

    /**
     * 一覧表示上限数取得
     * @return 一覧表示上限数
     */
    public static int getDisplayMaximumnumber() {
        return sInstance.displayMaximumnumber;
    }

    /**
     * リダイレクトURL取得
     * @return リダイレクトURL
     */
    public static String getRedirecturl() {
        return sInstance.redirecturl;
    }

    /**
     *
     * @return uploadMaxFilesize
     */
    public static String getUploadMaxFilesize() {
        return sInstance.uploadMaxFilesize;
    }

    /**
     *
     * @return excelToImageMaxFilesize
     */
    public static String getExcelToImageMaxFilesize() {
        return sInstance.excelToImageMaxFilesize;
    }

    /**
     *
     * @return uploadFileTemporaryPath
     */
    public static String getUploadFileTemporaryPath() {
        return sInstance.uploadFileTemporaryPath;
    }

    /**
     *
     * @return uploadWaitTime
     */
    public static String getUploadWaitTime() {
        return sInstance.uploadWaitTime;
    }
    /**
     *
     * @return LabelFormat
     */
    public static String getLabelFormat() {
        return sInstance.labelFormat;
    }

    /**
     * Token
     * @return Token
     */
    public static String getNewToken() {
        return sInstance.jwtProviderService.getNewToken();
    }

    /**
     * アップロード可能ファイル形式 指定食材企画書の取得
     * @return 指定食材企画書
     */
    public static String getUploadExtensionSiteisyokuzai() {
        return sInstance.uploadExtensionSiteisyokuzai;
    }

    /**
     * アップロード可能ファイル形式 調理半製品フの取得
     * @return 調理半製品
     */
    public static String getUploadExtensionHanseihin() {
        return sInstance.uploadExtensionHanseihin;
    }

    /**
     * アップロード可能ファイル形式 商品ラベル有り画像
     * @return 商品ラベル有り画像
     */
    public static String getUploadExtensionLevelari() {
        return sInstance.uploadExtensionLevelari;
    }

    /**
     * アップロード可能ファイル形式 商品ラベル無し画像
     * @return 商品ラベル無し画像
     */
    public static String getUploadExtensionLevelnasi() {
        return sInstance.uploadExtensionLevelnasi;
    }

    /**
     * アップロード可能ファイル形式 商品盛付情報
     * @return 商品盛付情報
     */
    public static String getUploadExtensionSakaritsuki() {
        return sInstance.uploadExtensionSakaritsuki;
    }

    /**
     * アップロード可能ファイル形式 商品補足情報
     * @return 商品補足情報
     */
    public static String getUploadExtensionHosoku() {
        return sInstance.uploadExtensionHosoku;
    }

    /**
     * アップロード可能ファイル形式 生産加工商品マスター情報
     * @return 生産加工商品マスター情報
     */
    public static String getUploadExtensionProductionInfo() {
        return sInstance.uploadExtensionProductionInfo;
    }

    /**
     * アップロード可能ファイル形式 eBASE指定食材規格書
     * @return eBASE指定食材規格書
     */
    public static String getUploadExtensionEBASEStandards() {
        return sInstance.uploadExtensionEBASEStandards;
    }

    /**
     * アップロード可能ファイル形式 二点印字用画像
     * @return 二点印字用画像
     */
    public static String getUploadExtensionTowPoint() {
        return sInstance.uploadExtensionTowPoint;
    }

    /**
     * アップロード可能ファイル形式 製造テストライン実績
     * @return 製造テストライン実績
     */
    public static String getUploadExtensionTestLine() {
        return sInstance.uploadExtensionTestLine;
    }

    /**
     * アップロード可能ファイル形式 その他ファイル
     * @return その他ファイル
     */
    public static String getUploadExtensionOther() {
        return sInstance.uploadExtensionOther;
    }

    /**
     * アップロード可能ファイル形式S価計算ツール
     * @return S価計算ツール
     */
    public static String getUploadExtensionSPrice() {
        return sInstance.uploadExtensionSPrice;
    }

    /**
     * アップロード可能ファイル形式証明書ファイル
     * @return 証明書ファイル
     */
    public static String getUploadExtensionCertificate() {
        return sInstance.uploadExtensionCertificate;
    }
    /**
     * 指定食材規格書のディフォルトパスの取得
     * @return 指定食材規格書のディフォルトパス
     */
    public static String getSyokuzaiKikakuExcel() {
        return sInstance.syokuzaiKikakuExcel;
    }

    /**
     * 半製品調理フローのディフォルトパスの取得
     *
     * @return 半製品調理フローのディフォルトパス
     */
    public static String getHanseihinFlowExcel() {
        return sInstance.hanseihinFlowExcel;
    }

    /**
     * 商品盛り付け情報のディフォルトパスの取得
     *
     * @return 商品盛り付け情報
     */
    public static String getSyohinMoritukeExcel() {
        return sInstance.syohinMoritukeExcel;
    }

    /**
     * 商品補足情報パスの取得
     *
     * @return 商品補足情報パス
     */
    public static String getSyohinHosokuExcel() {
        return sInstance.syohinHosokuExcel;
    }

    /**
     * メッセージソースの取得
     * @return メッセージソース
     */
    public static MessageSource getMessageSource() {
        return sInstance.messagesource;
    }

    /**
     * パラメーター指定なしメッセージの取得
     * @param msgId
     * @return 指定IDのメッセージ
     */
    public static String getMessage(String msgId) {
        return sInstance.messagesource.getMessage(msgId, null, Locale.getDefault());
    }

    /**
     * パラメーター指定あるメッセージの取得
     * @param msgId
     * @param params
     * @return 指定IDのメッセージ
     */
    public static String getMessage(String msgId, String...params) {
        return sInstance.messagesource.getMessage(msgId, params, Locale.getDefault());
    }


    /**
     * チェック用メッセージファイルパスの取得
     *
     * @return チェック用メッセージファイルパス
     */
    public static String getEmgMessageFile() {
        return sInstance.emgMessageFile;
    }

    /**
     * 知らせファイルパスの取得
     *
     * @return 知らせファイルパス
     */
    public static String getInfomationFile() {
        return sInstance.infomationFile;
    }

    /**
     * マスタダウンロードファイル置くバケットのパス
     *
     * @return マスタダウンロードファイル置くバケットのパス
     */
    public static String getDownloadKanriBucketPath() {
        return sInstance.bucket + "|" + sInstance.downloadKanriBucketPath;
    }

    /**
     * ファイルアップロード置くバケットのパス
     *
     * @return ファイルアップロード置くバケットのパス
     */
    public static String getUploadKanriBucketPath() {
        return sInstance.bucket + "|" + sInstance.uploadKanriBucketPath;
    }

    /**
     * ファイルアップロード置くバケットのパス(二点印字用画像)
     *
     * @return ファイルアップロード置くバケットのパス
     */
    public static String getTowPointBucketPath() {
        return sInstance.towPointBucketPath;
    }

    /**
     * ファイルアップロード置くバケットのパス(製造テストライン実績)
     *
     * @return ファイルアップロード置くバケットのパス
     */
    public static String getTestLineBucketPath() {
        return sInstance.testLineBucketPath;
    }

    /**
     * ファイルアップロード置くバケットのパス(その他ファイル)
     *
     * @return ファイルアップロード置くバケットのパス
     */
    public static String getOtherBucketPath() {
        return sInstance.otherBucketPath;
    }

    /**
     * ファイルアップロード置くバケットのパス(S価計算ツール)
     *
     * @return ファイルアップロード置くバケットのパス
     */
    public static String getSPriceBucketPath() {
        return sInstance.sPriceBucketPath;
    }

    /**
     * ファイルアップロード置くバケットのパス(証明書ファイル)
     *
     * @return ファイルアップロード置くバケットのパス
     */
    public static String getCertificateBucketPath() {
        return sInstance.certificateBucketPath;
    }

    /**
     * ファイルアップロード置くバケットのパス
     *
     * @return ファイルアップロード置くバケットのパス
     */
    public static String getUploadKanriBucketPathLocal() {
        return sInstance.bucket + sInstance.uploadKanriBucketPath;
    }

    /**
     * ファイルアップロード置くバケットのパス(二点印字用画像)
     *
     * @return ファイルアップロード置くバケットのパス
     */
    public static String getTowPointBucketPathLocal() {
        return sInstance.bucket + sInstance.towPointBucketPath;
    }

    /**
     * ファイルアップロード置くバケットのパス(製造テストライン実績)
     *
     * @return ファイルアップロード置くバケットのパス
     */
    public static String getTestLineBucketPathLocal() {
        return sInstance.bucket + sInstance.testLineBucketPath;
    }

    /**
     * ファイルアップロード置くバケットのパス(その他ファイル)
     *
     * @return ファイルアップロード置くバケットのパス
     */
    public static String getOtherBucketPathLocal() {
        return sInstance.bucket + sInstance.otherBucketPath;
    }

    /**
     * ファイルアップロード置くバケットのパス(S価計算ツール)
     *
     * @return ファイルアップロード置くバケットのパス
     */
    public static String getSPriceBucketPathLocal() {
        return sInstance.bucket + sInstance.sPriceBucketPath;
    }

    /**
     * ファイルアップロード置くバケットのパス(証明書ファイル)
     *
     * @return ファイルアップロード置くバケットのパス
     */
    public static String getCertificateBucketPathLocal() {
        return sInstance.bucket + sInstance.certificateBucketPath;
    }

    /**
     * ファイルアップロード置くバケットのパス
     *
     * @return ファイルアップロード置くバケットのパス
     */
    public static String getUploadKanriPath() {
        return sInstance.uploadKanriBucketPath;
    }

    /**
     * 現在の実行している環境設定値(local/dev/prod)
     *
     * @return 現在の実行している環境設定値
     */
    public static String getSpringProfilesActive() {
        return sInstance.springProfilesActive;
    }

    /**
     * HTTP最大接続数取得
     *
     * @return HTTP最大接続数
     */
    public static int getMaxconnections() {
        return sInstance.maxconnections;
    }

    /**
     * タイムアウト時間取得
     *
     * @return タイムアウト時間
     */
    public static int getConnectiontimeout() {
        return sInstance.connectiontimeout;
    }

    /**
     * ebaseパケット取得
     *
     * @return ebaseパケット取得
     */
    public static String getAwsEbaseBucket() {
        return sInstance.awsEbaseBucket;
    }

    /**
     * 承認ドキュメントパス取得
     *
     * @return 承認ドキュメントパス
     */
    public static String getApprovalPath() {
        return sInstance.approvalPath;
    }

    /**
     * 最終承認ドキュメントパス取得
     *
     * @return 最終承認ドキュメントパス取得
     */
    public static String getLastapprovalPath() {
        return sInstance.lastapprovalPath;
    }

    /**
     * S価アップロードファイル取り込み シート名取得
     *
     * @return S価アップロードファイル取り込み シート名
     */
    public static String getSPriceFileSheetName() {
        return sInstance.sPriceFileSheetName;
    }

    /**
     * S価アップロードファイル取り込み S価有効開始日の行列取得
     *
     * @return S価アップロードファイル取り込み S価有効開始日の行列
     */
    public static String getSPriceFileYukokaisiRowCol() {
        return sInstance.sPriceFileYukokaisiRowCol;
    }

    /**
     * S価アップロードファイル取り込み S価の行列取得
     *
     * @return S価アップロードファイル取り込み S価の行列
     */
    public static String getSPriceFileSPriceRowCol() {
        return sInstance.sPriceFileSPriceRowCol;
    }

    /**
     * S価アップロードファイル取り込み 原材料費の行列取得
     *
     * @return S価アップロードファイル取り込み 原材料費の行列
     */
    public static String getSPriceFileGenkaVlRowCol() {
        return sInstance.sPriceFileGenkaVlRowCol;
    }

    /**
     * S価アップロードファイル取り込み 加工費の行列取得
     *
     * @return S価アップロードファイル取り込み 加工費の行列
     */
    public static String getSPriceFileKakouHiRowCol() {
        return sInstance.sPriceFileKakouHiRowCol;
    }

    /**
     * S価アップロードファイル取り込み バージョンの行列取得
     *
     * @return S価アップロードファイル取り込み バージョンの行列
     */
    public static String getSPriceFileVersionRowCol() {
        return sInstance.sPriceFileVersionRowCol;
    }

}
