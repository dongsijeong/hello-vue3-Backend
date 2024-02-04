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
 * PDF生成ユティリティ
 * @author lzhang98
 *
 */
public class PdfGenerator {

    /***
     * PDFファイルを指定したフルネームで出力する。
     * @param templetName
     * @param rowsData
     * @param summary
     * @param fileName
     */
    public static void exportReportToPdfFile(String templetName,
            Map<String, List<?>> rowsData, Object summary, String fileName) {
        exportReportPdf(templetName, rowsData, summary, fileName);
    }

    /***
     * PDFを取得
     * @param templetName
     * @param summary
     * @return PDF
     */
    public static byte[] getPdfReport(String templetName, Object summary) {
        return exportReportPdf(templetName, null, summary, null);
    }

    /**
     * PDFファイルを指定したフルネームで出力する。
     * @param templetName
     * @param summary
     * @param fileName
     */
    public static void exportReportToPdfFile(String templetName, Object summary, String fileName) {
        exportReportPdf(templetName, null, summary, fileName);
    }

    /**
     * PDFを取得
     * @param templetName
     * @param rowsData
     * @param summary
     * @return PDF
     */
    public static byte[] getPdfReport(String templetName,
            Map<String, List<?>> rowsData, Object summary) {
        return exportReportPdf(templetName, rowsData, summary, null);
    }

    /**
     * 複数レポートを一つのPDF出力
     * @param templetList
     * @param reportRowsList
     * @param reportSummaryList
     * @param fileName
     */
    public static void exportReportJoinToPdfFile(List<String> templetList, List<Map<String, List<?>>> reportRowsList,
            List<Object> reportSummaryList, String fileName) {
        JasperPrint firstPrint = null;
        for (int i = 0; i < templetList.size(); i++) {
            if (i == 0) {
                firstPrint = getsimplePrint(templetList.get(i), reportRowsList.get(i), reportSummaryList.get(i));
            } else {
                JasperPrint nextprint = getsimplePrint(templetList.get(i),
                    reportRowsList.get(i), reportSummaryList.get(i));
                for (JRPrintPage page : nextprint.getPages()) {
                    firstPrint.addPage(page);
                }
            }
        }
        try {
            JasperExportManager.exportReportToPdfFile(firstPrint, fileName);
        } catch (JRException e) {
            throw new SystemException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032));
        }
    }

    /**
     * 複数レポートを一つのPDF出力
     * @param templetName
     * @param reportRowsList
     * @param reportSummaryList
     * @param fileName
     */
    public static void exportReportJoinToPdfFile(String templetName, List<Map<String, List<?>>> reportRowsList,
            List<Object> reportSummaryList, String fileName) {
        List<String> templetList = new ArrayList<>();
        for (int i = 0; i < reportRowsList.size(); i++) {
            templetList.add(templetName);
        }
        exportReportJoinToPdfFile(templetList, reportRowsList, reportSummaryList, fileName);
    }

    /**
     * 複数レポートのPDF内容取得
     * @param templetName
     * @param reportRowsList
     * @param reportSummaryList
     * @return 複数レポートのPDF内容
     */
    public static byte[] getMultipleReportPdf(String templetName, List<Map<String, List<?>>> reportRowsList,
            List<Object> reportSummaryList) {
        List<String> templetList = new ArrayList<>();
        for (int i = 0; i < reportRowsList.size(); i++) {
            templetList.add(templetName);
        }
        return getMultipleReportPdf(templetList, reportRowsList, reportSummaryList);
    }
    /**
     * 複数レポートのPDF内容取得
     * @param templetList
     * @param reportRowsList
     * @param reportSummaryList
     * @return 複数レポートのPDF内容
     */
    public static byte[] getMultipleReportPdf(List<String> templetList, List<Map<String, List<?>>> reportRowsList,
            List<Object> reportSummaryList) {
        JasperPrint firstPrint = null;
        for (int i = 0; i < templetList.size(); i++) {
            if (i == 0) {
                firstPrint = getsimplePrint(templetList.get(i), reportRowsList.get(i), reportSummaryList.get(i));
            } else {
                JasperPrint nextprint = getsimplePrint(templetList.get(i),
                        reportRowsList.get(i), reportSummaryList.get(i));
                for (JRPrintPage page : nextprint.getPages()) {
                    firstPrint.addPage(page);
                }
            }
        }
        try {
            return JasperExportManager.exportReportToPdf(firstPrint);
        } catch (JRException e) {
            throw new SystemException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032));
        }
    }

    /**
     * PDFレポート生成
     * @param templetName
     * @param rowsData
     * @param summary
     * @param fileName
     * @return PDFレポート
     */
    private static byte[] exportReportPdf(String templetName,
            Map<String, List<?>> rowsData, Object summary, String fileName) {
        HashMap<String, Object> parameterMap = new HashMap<String, Object>();
        List<Object> summaryList = new ArrayList<>();
        String jasperFilePath =  PdfGenerator.class.getResource("/pdftemplate/" + templetName).getPath();
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
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath,
                    parameterMap, jrBeanCollectionDataSource);
            if (fileName == null) {
                return JasperExportManager.exportReportToPdf(jasperPrint);
            }
            JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
            return null;
        } catch (JRException e) {
            throw new SystemException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032));
        }
    }

    /**
     * PDFページ取得
     * @param templetName
     * @param rowsData
     * @param summary
     * @return PDFページ
     */
    private static JasperPrint getsimplePrint(String templetName,
            Map<String, List<?>> rowsData, Object summary) {
        HashMap<String, Object> parameterMap = new HashMap<String, Object>();
        List<Object> summaryList = new ArrayList<>();
        String jasperFilePath =  PdfGenerator.class.getResource("/pdftemplate/" + templetName).getPath();
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
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperFilePath, parameterMap, jrBeanCollectionDataSource);
            return jasperPrint;
        } catch (JRException e) {
            throw new SystemException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032));
        }
    }
}
