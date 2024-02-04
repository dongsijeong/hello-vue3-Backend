package util;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.springframework.util.StringUtils;

import jp.co.lawson.mo.config.DataAccessConfig;
import jp.co.lawson.mo.constants.Constant.MESSAGECODE;
import jp.co.lawson.mo.exception.BusinessException;
import jp.co.lawson.mo.param.Lcu04021ComListParam;

/**
 * 【ParseMultiCtrlクラス】
 * multipart部分を解析し、contents(アップロードファイル)を取り出す。
*/
public class ParseMultiUtil {

    /*------------------------------------------*/
    /* 定数宣言                                 */
    /*------------------------------------------*/
    /**
    * Boundary
    */
    public static final String BOUNDARY = "boundary=";

    /**
    * カラム長 ファイルコード
    */
    public static final int LEN_FILE_KB     = 12;
    /**
    * getParameter()取得Key アップロードファイル名
    */
    public static final String PRM_UPLOAD_FILE      = "UPLOAD_FILE";
    /**
    * Exception置換え文字列 アップロードファイル名
    */
    public static final String ERR_UPLOAD   = "対象ファイル";
    /**
    * アップロード可能ファイル形式 対応拡張子
    */
    public static final String[] UPLOAD_EXTENSION = {
//        "XLS",                                  // 指定食材企画書
//        "XLS",                                  // 調理半製品フローファイル
//        "JPEG,JPG,JPE,BMP,TIF,TIFF,GIF",        // 商品ラベル有り画像
//        "JPEG,JPG,JPE,BMP,TIF,TIFF,GIF",        // 商品ラベル無し画像
//        "XLS",                                  // 商品盛付情報ファイル
//        "XLS",                                  // 商品補足情報ファイル
            DataAccessConfig.getUploadExtensionSiteisyokuzai(),
            DataAccessConfig.getUploadExtensionHanseihin(),
            DataAccessConfig.getUploadExtensionLevelari(),
            DataAccessConfig.getUploadExtensionLevelnasi(),
            DataAccessConfig.getUploadExtensionSakaritsuki(),
            DataAccessConfig.getUploadExtensionHosoku(),
            DataAccessConfig.getUploadExtensionProductionInfo(),
            DataAccessConfig.getUploadExtensionEBASEStandards(),
            DataAccessConfig.getUploadExtensionTowPoint(),
            DataAccessConfig.getUploadExtensionTestLine(),
            DataAccessConfig.getUploadExtensionOther(),
            DataAccessConfig.getUploadExtensionSPrice(),
            DataAccessConfig.getUploadExtensionCertificate(),
    };
    /**
    * ファイルアップロード選択画面SkBean
    */
    private Lcu04021ComListParam bnSkBean = null;

    /**
     * コンストラクタ<BR>
     *   1.パラメータ格納
     * @param       strBoundary     Boundary文字列
     * @param       intMultipartLen1 multipartサイズ
     * @param       lcu04021Param        ファイルアップロード選択画面SkBean
    */
    public ParseMultiUtil(final String strBoundary,
            final int intMultipartLen1,
            final Lcu04021ComListParam lcu04021Param) {
        // ファイルアップロード選択画面SkBean格納
        this.bnSkBean = lcu04021Param;

    }

    /**
     * multipart部分を解析し、contents(アップロードファイル)を取り出す。
    */
    public void getContents() {

        // アップロードファイル名取得結果フラグ(true：取得成功 false：未入力)
        boolean getResultFlg = false;

        try {

            if (!StringUtils.hasText(bnSkBean.getUploadFile())) {
                getResultFlg = false;
            } else {
                getResultFlg = true;
            }

            /*----------------------------------------------------*/
            /* アップロードファイル名未入力チェック               */
            /*----------------------------------------------------*/
            if (!getResultFlg) {
                throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.E_CM_0001_1001, ERR_UPLOAD));
            }

            /*----------------------------------------------------*/
            /* アップロードファイル拡張子チェック               */
            /*----------------------------------------------------*/
            String strFileNa = null;
            String strFileKb = null;
            // アップロードファイル名取得
            strFileNa = bnSkBean.getUploadFile();

            // ファイル区分取得
            strFileKb = bnSkBean.getFileKb();
            // アップロードファイル拡張子チェック
            if (!checkExtension(strFileNa, strFileKb)) {
                throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4039_4039));
            }

            return;
        } catch (BusinessException be) {
            throw be;
        } catch (Exception e) {
            LogUtil.outAppErrlog(LogUtil.getStackTrace(e));
            throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.E_CM_0043_1043));
        }
    }

 /**
    * アップロードファイル名を取得し拡張子チェックを行う
    * @param strFileNA  拡張子付ファイル名
    * @param strTyp  拡張子区
    * @return 取得結果(true：対応拡張子 false：未対応拡張子)
    */
    private boolean checkExtension(String strFileNA, String strTyp) {
        // ファイル区分
        int intFileKb = 0;
        intFileKb = Integer.parseInt(strTyp);
        // アップロード可能拡張子
        String strExtensionData = "";
        strExtensionData = UPLOAD_EXTENSION[intFileKb - 1];
        //拡張子が一致しなければfalseを返す

        boolean bAns = false;

        try {
            //カンマ区切りの拡張子データより対応拡張子を抽出する準備
            StringTokenizer st = new StringTokenizer(strExtensionData, ",");

            ArrayList<String> al = new ArrayList<String>();
            String str = "";
            //カンマ区切りの拡張子データより対応拡張子を抽出
            while (st.hasMoreTokens()) {
                str = "." + st.nextToken();
                al.add(str.toLowerCase());
                str = null;
            }
            //小文字へ変換する
            strFileNA = strFileNA.toLowerCase();
            //拡張子チェック
            for (int i = 0; al.size() > i; i++) {
                bAns = strFileNA.endsWith((String) al.get(i));
                if (bAns) {
                    break;
                }
            }
        } catch (Exception e) {
            LogUtil.outAppErrlog(LogUtil.getStackTrace(e));
            throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.E_CM_0043_1043));
        }
        return bAns;
    }
}
