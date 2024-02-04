package util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jp.co.lawson.mo.config.DataAccessConfig;
import jp.co.lawson.mo.constants.Constant.MESSAGECODE;
import jp.co.lawson.mo.exception.SystemException;

/**
 * PDFレポート圧縮
 * @author zchang4
 *
 */
public class ZipPdfReport {

    private ByteArrayOutputStream outstreanm = null;
    private ZipOutputStream zos = null;

    /**
     * PDFレポート圧縮
     */
    public ZipPdfReport() {
        outstreanm = new ByteArrayOutputStream();
        zos = new ZipOutputStream(outstreanm);
    }

    /**
     * PDFファイル追加
     * @param templetName
     * @param rowsData
     * @param summary
     * @param entryName
     */
    public void addReport(String templetName, Map<String, List<?>> rowsData, Object summary, String entryName) {
        ZipEntry entry = new ZipEntry(entryName);
        try {
            zos.putNextEntry(entry);
            zos.write(PdfGenerator.getPdfReport(templetName, rowsData, summary));
        } catch (IOException e) {
            throw new SystemException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032));
        }
    }

    /**
     * PDFファイル追加
     * @param templetName
     * @param summary
     * @param entryName
     */
    public void addReport(String templetName, Object summary, String entryName) {
        ZipEntry entry = new ZipEntry(entryName);
        try {
            zos.putNextEntry(entry);
            zos.write(PdfGenerator.getPdfReport(templetName, summary));
        } catch (IOException e) {
            throw new SystemException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032));
        }
    }

    /**
     * PDFファイル追加
     * @param templetName
     * @param reportRowsList
     * @param reportSummaryList
     * @param entryName
     */
    public void addReport(String templetName, List<Map<String, List<?>>> reportRowsList, List<Object> reportSummaryList,
            String entryName) {
        ZipEntry entry = new ZipEntry(entryName);
        try {
            zos.putNextEntry(entry);
            zos.write(PdfGenerator.getMultipleReportPdf(templetName, reportRowsList, reportSummaryList));
        } catch (IOException e) {
            throw new SystemException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032));
        }
    }

    /**
     * PDFファイル追加
     * @param templetList
     * @param reportRowsList
     * @param reportSummaryList
     * @param entryName
     */
    public void addReport(List<String> templetList, List<Map<String, List<?>>> reportRowsList,
            List<Object> reportSummaryList, String entryName) {
        ZipEntry entry = new ZipEntry(entryName);
        try {
            zos.putNextEntry(entry);
            zos.write(PdfGenerator.getMultipleReportPdf(templetList, reportRowsList, reportSummaryList));
        } catch (IOException e) {
            throw new SystemException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032));
        }
    }

    /**
     * PDFファイル追加
     * @param fileByte
     * @param entryName
     */
    public void addReport(byte[] fileByte, String entryName) {
        ZipEntry entry = new ZipEntry(entryName);
        try {
            zos.putNextEntry(entry);
            zos.write(fileByte);
        } catch (IOException e) {
            throw new SystemException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032));
        }
    }

    /**
     * ZIPファイル内容取得
     * @return ZIPファイル内容
     */
    public byte[] getZipfile() {
        try {
            zos.close();
            return outstreanm.toByteArray();
        } catch (IOException e) {
            throw new SystemException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032));
        }

    }

    /**
     * ZIPファイル出力
     * @param name
     */
    public void outZipFile(String name) {
        try (FileOutputStream fos = new FileOutputStream(name);
                BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            zos.close();
            bos.write(outstreanm.toByteArray());
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
