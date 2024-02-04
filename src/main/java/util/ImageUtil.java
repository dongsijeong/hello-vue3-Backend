package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import com.spire.xls.WorksheetVisibility;

import jp.co.lawson.mo.config.DataAccessConfig;
import jp.co.lawson.mo.constants.Constant.MESSAGECODE;
import jp.co.lawson.mo.exception.BusinessException;

/**
 *イメージ取得
 * @author zchang4
 *
 */
public final class ImageUtil {

    private ImageUtil() {

    }

    @Autowired
    private static ResourceLoader resourceLoader;

    /**
     *
     * @param path
     * @return イメージ返す
     */
    public static byte[] getImage(Path path)  {
        Workbook wb = null;
        Worksheet ws = null;
        try {
            wb = new Workbook();
            wb.loadFromFile(path.toFile().toString());
            for (int i = 0; i < wb.getWorksheets().size(); i++) {
                ws = wb.getWorksheets().get(i);
                if (ws.getVisibility() == WorksheetVisibility.Visible) {
                    break;
                }
            }
            byte[] imgbyte = getImage(ws);
            return imgbyte;
        } catch (NoSuchFileException ne) {
            throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032), true);
        } catch (Exception e) {
            LogUtil.outAppErrlog("" + "\t" + "" +  "\t" + "" + "\t" + LogUtil.getStackTrace(e));
            throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032), true);
        } finally {
            ws.dispose();
            wb.dispose();
        }
    }

    /**
     *
     * @param imgs
     * @return イメージ返す
     */
    public static byte[] getImage(byte[] imgs) {
        Workbook wb = null;
        Worksheet ws = null;
        try {
            InputStream input = new ByteArrayInputStream(imgs);
            wb = new Workbook();
            wb.loadFromStream(input);
            int sheetCount = wb.getWorksheets().getCount();
            for (int i = 0; i < sheetCount; i++) {
                ws = wb.getWorksheets().get(i);
                if (ws.getVisibility().getValue() == WorksheetVisibility.Visible.getValue()) {
                    break;
                }
            }
            byte[] imgbyte = getImage(ws);
            return imgbyte;
        } catch (NoSuchFileException ne) {
            throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032), true);
        } catch (Exception e) {
            LogUtil.outAppErrlog("" + "\t" + "" +  "\t" + "" + "\t" + LogUtil.getStackTrace(e));
            throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032), true);
        } finally {
            ws.dispose();
            wb.dispose();
        }
    }

    private static byte[] getImage(Worksheet ws) throws Exception {
        File folder = new File(DataAccessConfig.getLocalPath());
        if (!folder.exists()) {
            if (!folder.mkdir()) {
                LogUtil.outAppWarlog("jp.co.lawson.mo.util.ImageUtil" + "\t" + "getImage\t" + "フォルダの作成に失敗しました");
            }
        }
        String profix = UUID.randomUUID().toString() + System.currentTimeMillis();
        String jpg = folder + File.separator + profix + ".jpg";
        ws.saveToImage(jpg);
        try  {
            byte[] byteImg = Files.readAllBytes(new File(jpg).toPath());
            return byteImg;
        } finally {
            (new File(jpg)).delete();
        }
    }

    /**
     * イメージを取得する
     *
     * @param fileName ファイル名
     * @return ファイルストリーム
     */
    public static InputStream getImageBy(String fileName) {
        InputStream imageStrem = null;
        try {
            Resource resource = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
                    .getResource("classpath:images/" + fileName);
            if (resource.exists()) {
                try (InputStream is = resource.getInputStream()) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) != -1) {
                        bos.write(buffer, 0, length);
                    }
                    imageStrem = new ByteArrayInputStream(bos.toByteArray());
                }
            }
            return imageStrem;
        } catch (IOException e) {
        }
        return imageStrem;
    }

}
