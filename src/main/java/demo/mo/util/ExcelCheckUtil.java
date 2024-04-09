package demo.mo.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

import demo.mo.config.DataAccessConfig;
import demo.mo.constants.Constant.MESSAGECODE;
import demo.mo.exception.BusinessException;
import demo.mo.model.DtUploadKanriModel;

/**
 * EXCLEはイメージ変換できるかチェックする。
 * @author zchang4
 *
 */
public final class ExcelCheckUtil {

    private static short textVertical = 255;
    private static float times = 1.3f;

    private ExcelCheckUtil() {

    }

    /**
     * checkExcelCanToImage
     * @param uploadData
     */
    public static void checkExcelCanToImage(DtUploadKanriModel uploadData) {
        Workbook wb = null;
        Sheet sheet = null;
        InputStream input = null;
        try {
            input = new ByteArrayInputStream(uploadData.getHozonFileSt());
            wb = WorkbookFactory.create(input);
            sheet = getFirstNotHiddenSheet(wb);
            List<String> ngCells = new ArrayList<>();
            // 最後の行までチェック行う
            for (int rowIdx = 0; rowIdx <= sheet.getLastRowNum(); ++rowIdx) {
                Row row = sheet.getRow(rowIdx);
                if (row == null) {
                    continue;
                }
                // 最後の列までチェック行う
                for (int colIdx = 0; colIdx < row.getLastCellNum(); ++colIdx) {
                    Cell cell = row.getCell(colIdx);

                    // セル有無チェック
                    if (cell == null) {
                        continue;
                    }

                    CellStyle style = cell.getCellStyle();

                    // 縦書きではない場合はスキップする
                    if (style.getRotation() != textVertical) {
                        continue;
                    }

                    // セルタイプでデータ有無のチェック
                    switch (cell.getCellType()) {
                    case BLANK:
                        // データなしの場合チェックしない
                        continue;
                    default:
                        break;
                    }

                    // データありの場合、文字サイズと行の高さを比較する
                    Font font = wb.getFontAt(cell.getCellStyle().getFontIndex());

                    // 行の高さがフォントの高さより大きいの場合、スキップする
                    if (row.getHeight() >= font.getFontHeight() * times) {
                        continue;
                    }

                    CellRangeAddress range = getCellRange(sheet, rowIdx, colIdx);

                    // 結合セルの場合、結合セルの高さで比較する
                    if (range != null) {
                        // 行の高さがフォントの高さより大きいの場合、スキップする
                        if (getMergedRowHeight(sheet, range) >= font.getFontHeight()) {
                            continue;
                        }
                    }
                    ngCells.add("セル：" + new CellReference(rowIdx, colIdx).formatAsString());
                }
            }
            if (ngCells.size() > 0) {
                 throw new BusinessException(getNgMessage(ngCells));
            }
        } catch (EncryptedDocumentException | IOException e) {
             LogUtil.outAppErrlog("" + "\t" + "" +  "\t" + "" + "\t" + LogUtil.getStackTrace(e));
             throw new BusinessException(DataAccessConfig.getMessage(MESSAGECODE.DE_CM4032_4032));
        } finally {
            try {
                input.close();
                wb.close();
            } catch (IOException e) {
            }
        }

    }

    /**
     * エラーメッセージ取得
     * @param ngCells
     * @return message
     */
    private static String getNgMessage(List<String> ngCells) {
        String message = "「";
        for (int i = 0; i < ngCells.size(); i++) {
            message += ngCells.get(i);
            if (i < ngCells.size() - 1) {
                message += "／";
            }
        }
        message += "」";
        return DataAccessConfig.getMessage(MESSAGECODE.M_CM_0013_113, message);
    }
    /**
     * 一つ目の表示シートを取得
     * @param wb
     * @return sheet
     */
    private static Sheet getFirstNotHiddenSheet(Workbook wb) {
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            if (!(wb.isSheetHidden(i) || wb.isSheetVeryHidden(i))) {
                return wb.getSheetAt(i);
            }
        }
        return null;
    }

    /**
     * 結合セルの高さ取得
     * @param sheet
     * @param range
     * @return height
     */
    private static short getMergedRowHeight(Sheet sheet, CellRangeAddress range) {
        int firstRow = range.getFirstRow();
        int lastRow = range.getLastRow();
        short height = 0;

        for (int idx = firstRow; idx <= lastRow; ++idx) {
            Row row = sheet.getRow(idx);

            height += row.getHeight();
        }

        return height;
    }
    /**
     * セルの結合範囲取得
     * @param sheet
     * @param rowIndex
     * @param columnIndex
     * @return range
     */
    private static CellRangeAddress getCellRange(Sheet sheet, int rowIndex, int columnIndex) {
        final int mergedCellNums = sheet.getNumMergedRegions();

        for (int idx = 0; idx < mergedCellNums; ++idx) {
            final CellRangeAddress range = sheet.getMergedRegion(idx);

            // 結合範囲内チェック
            if (range.isInRange(rowIndex, columnIndex)) {
                return range;
            }
        }

        return null;
    }

}
