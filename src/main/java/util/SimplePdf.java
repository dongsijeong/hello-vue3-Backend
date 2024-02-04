package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.lawson.mo.config.DataAccessConfig;
import jp.co.lawson.mo.constants.Constant.MESSAGECODE;
import jp.co.lawson.mo.exception.SystemException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 複数ページPDF生成簡易ツール
 * @author zchang4
 *
 */
public class SimplePdf {
    private List<JasperPrint> jasperPrintList = new ArrayList<>();

    /**
     * ページ追加
     *
     * @param templetName
     * @param rowsData
     * @param summary
     */
    public void addPage(String templetName, Map<String, List<?>> rowsData, Object summary) {
        HashMap<String, Object> parameterMap = new HashMap<String, Object>();
        List<Object> summaryList = new ArrayList<>();
        String jasperFilePath = PdfGenerator.class.getResource("/pdftemplate/" + templetName).getPath();
        if (rowsData != null) {
            for (String key : rowsData.keySet()) {
                parameterMap.put(key, new JRBeanCollectionDataSource(rowsData.get(key)));
            }
        }
        if (summary != null) {
            summaryList.add(summary);
        }
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(summaryList);
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, parameterMap,
                    jrBeanCollectionDataSource);
            jasperPrintList.add(jasperPrint);

        } catch (JRException e) {
            throw new SystemException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032));
        }
    }

    /**
     * ページ追加
     * @param templetName
     * @param summary
     */
    public void addPage(String templetName,  Object summary) {
        HashMap<String, Object> parameterMap = new HashMap<String, Object>();
        List<Object> summaryList = new ArrayList<>();
        String jasperFilePath = PdfGenerator.class.getResource("/pdftemplate/" + templetName).getPath();
        if (summary != null) {
            summaryList.add(summary);
        }
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(summaryList);
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, parameterMap,
                    jrBeanCollectionDataSource);
            jasperPrintList.add(jasperPrint);

        } catch (JRException e) {
            throw new SystemException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032));
        }
    }

    /**
     * PDFファイル出力
     *
     * @param fileName
     */
    public void exportReportPdfFile(String fileName) {

        JasperPrint firstPrint = null;
        for (int i = 0; i < jasperPrintList.size(); i++) {
            if (i == 0) {
                firstPrint = jasperPrintList.get(i);
            } else {
                JasperPrint nextprint = jasperPrintList.get(i);
                for (JRPrintPage page : nextprint.getPages()) {
                    firstPrint.addPage(page);
                }
            }
        }
        try {
            JasperExportManager.exportReportToPdfFile(firstPrint, fileName);
        } catch (JRException e) {
            // TODO 自動生成された catch ブロック
            throw new SystemException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032));
        }
    }

    /**
     * 帳票内容取得
     * @return 帳票内容
     */
    public byte[] getPdfContent() {

        JasperPrint firstPrint = null;
        for (int i = 0; i < jasperPrintList.size(); i++) {
            if (i == 0) {
                firstPrint = jasperPrintList.get(i);
            } else {
                JasperPrint nextprint = jasperPrintList.get(i);
                for (JRPrintPage page : nextprint.getPages()) {
                    firstPrint.addPage(page);
                }
            }
        }
        try {
            return JasperExportManager.exportReportToPdf(firstPrint);
        } catch (JRException e) {
            // TODO 自動生成された catch ブロック
            throw new SystemException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032));
        }
    }
}
