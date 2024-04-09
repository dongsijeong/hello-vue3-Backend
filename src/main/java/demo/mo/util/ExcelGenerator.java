package demo.mo.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Excelユティリティ
 * @author zchang4
 *
 */
public class ExcelGenerator {
      /** 出力ストリームセット */
    private OutputStream outputStream;

    /** Excelワークブック */
    private Workbook userWorkBook;

    /** シート */
    private Sheet userSheet;

    /** ファイル入力ストリーム */
    private FileInputStream fis = null;

    /**
     * <p>
     * 構造子<br>
     *
     * @param template テンプレート
     * @version 1.0
     *
     * @throws Exception 例外
     */
    public ExcelGenerator(final String template) throws Exception {
        File file = new File(template);
        fis = new FileInputStream(file);
        userWorkBook = WorkbookFactory.create(fis);
        this.setSheet(userWorkBook.getSheetAt(0));
    }

    /**
     * <p>
     * 構造子<br>
     *
     * @param inp テンプレート
     * @version 1.0
     *
     * @throws Exception 例外
     */
    public ExcelGenerator(final InputStream inp) throws Exception {
        userWorkBook = WorkbookFactory.create(fis);
        this.setSheet(userWorkBook.getSheetAt(0));
    }

    /**
     * 構造子
     * @param in テンプレート
     * @throws Exception
     */
    public ExcelGenerator(final byte[] in) throws Exception {
        userWorkBook = WorkbookFactory.create(new ByteArrayInputStream(in));
        this.setSheet(userWorkBook.getSheetAt(0));
    }

    /**
     * <p>
     * Excelワークブックに、データをセット<br>
     *
     * @param sheetName シート
     * @param dataList データ
     * @param stratRow 横方向から
     * @param startCol 縦方向から
     * @version 1.0
     *
     * @throws Exception 例外
     */
    public void setValue(String sheetName, List<ArrayList<?>> dataList, int stratRow,
            int startCol) throws Exception {
        this.setSheet(userWorkBook.getSheet(sheetName));
        setValue(dataList, stratRow, startCol);
    }

    /**
     * <p>
     * Excelワークブックに、データをセット<br>
     *
     * @param dataList データ
     * @param stratRow 横方向から
     * @param startCol 縦方向から
     * @version 1.0
     *
     * @throws Exception 例外
     */
    public void setValue(List<ArrayList<?>> dataList, int stratRow,
            int startCol) throws Exception {

        ArrayList<?> dataCol = null;
        Row row = null;
        try {


            for (int i = 0; i < dataList.size(); i++) {

                dataCol = dataList.get(i);

                row = this.getRow(this.getSheet(), stratRow - 1 + i);

                for (int j = 0; j < dataCol.size(); j++) {
                    if (dataCol.get(j) != null && !"".equals(dataCol.get(j))) {
                        Cell cell = this.getCell(row, (short) (startCol - 1 + j));
                        if (((String) dataCol.get(j)).indexOf("\r\n") != -1
                                || ((String) dataCol.get(j)).indexOf("\n") != -1) {
                            cell.getCellStyle().setWrapText(true);
                        }
                        cell.setCellValue(convertNull(dataCol.get(j)));
                    }
                }

            }
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Excelワークブックに、単独の数値データをセット
     * @param iRow
     * @param iCol
     * @param value
     * @throws Exception
     */
    public void setSingleNumValue(int iRow, int iCol,
            Object value) throws Exception {


        if (value == null) {
            return;
        }
        Row row = this.getRow(this.getSheet(), iRow - 1);
        Cell cell = this.getCell(row, (short) (iCol - 1));
        cell.setCellValue(Double.parseDouble(value.toString()));
    }

    /**
     * <p>
     * Excelワークブックに、単独のデータをセット<br>
     *
     * @param iRow 横方向
     * @param iCol 縦方向
     * @param value データ
     * @version 1.0
     *
     * @throws Exception 例外
     */
    public void setSingleStringValue(int iRow, int iCol,
            Object value) throws Exception {

        Row row = this.getRow(this.getSheet(), iRow - 1);
        Cell cell = this.getCell(row, (short) (iCol - 1));
        if (value.toString().indexOf("\r\n") != -1) {
            cell.getCellStyle().setWrapText(true);
        }
        cell.setCellValue(convertNull(value));
    }

    /**
     * <p>
     * Excelワークブックに、単独のデータをセット<br>
     *
     * @param sheetName シート名前
     * @param iRow 横方向
     * @param iCol 縦方向
     * @param value データ
     * @version 1.0
     *
     * @throws Exception 例外
     */
    public void setSingleValue(String sheetName, int iRow, int iCol,
            Object value) throws Exception {

        this.setSheet(userWorkBook.getSheet(sheetName));
        setSingleStringValue(iRow, iCol, value);

    }

    /**
     * 計算式を設定
     * @param sheetName シート名前
     * @param iRow 行
     * @param iCol 列
     * @param formula 計算式
     * @throws Exception
     */
    public void setCellFormula(String sheetName, int iRow, int iCol, String formula) throws Exception {
        this.setSheet(userWorkBook.getSheet(sheetName));

        Row row = this.getRow(this.getSheet(), iRow - 1);
        Cell cell = this.getCell(row, (short) (iCol - 1));
        cell.setCellFormula(formula);
    }

    /**
     * <p>
     * 出力ストリームにデータを伝送<br>
     *
     * @version 1.0
     *
     * @throws IOException 例外
     */
    public void save() throws IOException {
        if (this.getOutputStream() == null) {
            throw new IOException("OutputStream Is Null");
        }
        this.getWorkBook().write(this.getOutputStream());
    }

    /**
     * 出力ストリームにデータを伝送
     * @param path
     * @throws IOException
     */
    public void save(String path) throws IOException {
        try (OutputStream output = new FileOutputStream(path)) {
            this.getWorkBook().write(output);
        }
    }

    /**
     * <p>
     * 列を得ます。<br>
     *
     * @version 1.0
     * @param sheet Sheet
     * @param rowNum int
     * @return Row
     * @throws Exception 例外
     */
    private Row getRow(Sheet sheet, int rowNum) throws Exception {
        if (sheet == null) {
            throw new Exception("sheet is null");
        }
        if (sheet.getRow(rowNum) == null) {
            return sheet.createRow(rowNum);
        }

        return sheet.getRow(rowNum);
    }

    /**
     * <p>
     * セルを得ます。<br>
     *
     * @version 1.0
     *
     * @param row Row
     * @param colNum int
     * @return Cell
     * @throws Exception 例外
     */
    private Cell getCell(Row row, int colNum) throws Exception {
        if (row == null) {
            throw new Exception("row is null");
        }
        Cell cell = row.getCell((short) colNum);
        if (cell == null) {
            return row.createCell((short) colNum);
        }
        return cell;
    }

    /**
     * <p>
     * EXCELファイルに、列コピー。<br>
     *
     * @version 1.0
     *
     * @param pSourceSheetName 源シート
     * @param pTargetSheetName 目標シート
     * @param sourceRowIndex 源の列の初めの位置
     * @param cBlockNum コピーの位置の行数に
     * @param pStartRow 目標の初めの位置
     * @param rowNum 行数をコピーします
     * @throws Exception 例外
     */
    public void copyRows(String pSourceSheetName,
                          String pTargetSheetName,
                          int sourceRowIndex,
                          int cBlockNum,
                          int pStartRow,
                          int rowNum) throws Exception {

        Row sourceRow = null;
        Row targetRow = null;
        Cell sourceCell = null;
        Cell targetCell = null;
        Sheet sourceSheet = null;
        Sheet targetSheet = null;
        List<String> regionList = new ArrayList<>();
        List<Row> rowList = new ArrayList<>();
        CellType cType;
        short j;
        //源シートは得ます
        sourceSheet = this.userWorkBook.getSheet(pSourceSheetName);
        //目標シートは得ます
        targetSheet = this.userWorkBook.getSheet(pTargetSheetName);
        //コピーするつもりな行は得ます
        for (int m = 0; m < cBlockNum; m++) {
            sourceRow = getRow(sourceSheet, sourceRowIndex + m - 1);
            rowList.add(sourceRow);
        }
        //もしコピーの地区内にじっとしているならば合併のセルがあって、取り出します
        for (int i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
            CellRangeAddress region = sourceSheet.getMergedRegion(i);
            if (region.getFirstRow() >= sourceRowIndex - 1) {
                regionList.add(region.getFirstRow() + ":" + region.getLastRow()
                       + "&" + region.getFirstColumn() + ":" + region.getLastColumn());
            }
        }

        //コピーの実行
        for (int i = 0; i < rowNum; i++) {
            for (int n = 0; n < rowList.size(); n++) {
                sourceRow = (Row) rowList.get(n);
                targetRow = targetSheet.createRow(pStartRow - 1 + (i) * cBlockNum + n);
                targetRow.setHeight(sourceRow.getHeight());
                targetRow.setHeightInPoints(sourceRow.getHeightInPoints());
                int lastCellNum = sourceRow.getLastCellNum();
                for (j = 0; j < lastCellNum; j++) {
                sourceCell = this.getCell(sourceRow, j);
                if (sourceCell == null) {
                    continue;
                }
                targetCell = targetRow.createCell(j);
                targetCell.setCellStyle(sourceCell.getCellStyle());
                cType = sourceCell.getCellType();
                switch (cType) {
                    case BOOLEAN:
                        targetCell.setCellValue(sourceCell.getBooleanCellValue());
                    break;
                    case ERROR:
                        targetCell.setCellErrorValue(sourceCell.getErrorCellValue());
                    break;
                    case NUMERIC:
                        targetCell.setCellValue(sourceCell.getNumericCellValue());
                    break;
                    case STRING:
                        targetCell.setCellValue(sourceCell.getStringCellValue());
                    break;
                    default:
                    break;
                }
            }
        }
        for (int m = 0; m < regionList.size(); m++) {
            String regionToken = (String) regionList.get(m);
            String rowFrom = (regionToken.split("&")[0]).split(":")[0];
            String rowTo = (regionToken.split("&")[0]).split(":")[1];
            String colFrom = (regionToken.split("&")[1]).split(":")[0];
            String colTo = (regionToken.split("&")[1]).split(":")[1];

            CellRangeAddress targetRegion = new CellRangeAddress(
            Integer.valueOf(rowFrom).intValue() + (i + 1) * cBlockNum,
            Integer.valueOf(rowTo).intValue() + (i + 1) * cBlockNum,
            Integer.valueOf(colFrom).shortValue(),
            Integer.valueOf(colTo).shortValue()
            );

            targetSheet.addMergedRegion(targetRegion);
        }
      }
    }

    /**
     * シートコピー
     * @param sheetName
     * @param srcSheetName
     */
    public void copySheet(String sheetName, String srcSheetName) {
         int index = userWorkBook.getSheetIndex(srcSheetName);
         Sheet tagetSheet = userWorkBook.cloneSheet(index);
         index = userWorkBook.getSheetIndex(tagetSheet);
         userWorkBook.setSheetName(index, sheetName);
    }

    /**
     * シートを設定
     * @param sheetName
     */
    public void setCurrSheet(String sheetName) {
        Sheet sheet = userWorkBook.getSheet(sheetName);
        this.setSheet(sheet);
    }

    /**
     * シートを削除
     * @param sheetName
     */
    public void dropSheet(String sheetName) {
        int index = userWorkBook.getSheetIndex(sheetName);
        userWorkBook.removeSheetAt(index);
    }

    /**
     * 一行データを取得
     * @param rownum
     * @param startColNum
     * @param endColNum
     * @return 一行データ
     */
    public List<String> getCellDatas(int rownum, int startColNum, int endColNum) {
        List<String> result = new ArrayList<>();
        Row row = this.getSheet().getRow(rownum - 1);
        for (int i = startColNum; i <= endColNum; i++) {
            result.add(getCellData(row, i));
        }
        return result;
    }

    /**
     * セルデータ取得
     * @param rownum
     * @param colNum
     * @return セルデータ
     */
    public String getCellData(int rownum, int colNum) {
        Row row = this.getSheet().getRow(rownum - 1);
        if (row == null) {
            return null;
        }
        return getCellData(row, colNum);
    }

    /**
     * セルのデータを取得
     * @param rownum
     * @param colNum
     * @return セルのデータ
     */
    public String getCellDate(int rownum, int colNum) {
        Row row = this.getSheet().getRow(rownum - 1);
        if (row == null) {
            return null;
        }
        Cell cell = row.getCell(colNum - 1);
        CellType cType = cell.getCellType();
        switch (cType) {
        case NUMERIC:
            Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            return sdf.format(date);
        case STRING:
            return cell.getStringCellValue();
        case FORMULA:
            return getStringFormulaValue(cell);
        default:
            break;
        }
        return "";
    }

    /**
     * セルデータ取得
     * @param row
     * @param colNum
     * @return セルデータ
     */
    private String getCellData(Row row, int colNum) {
        Cell cell = row.getCell(colNum - 1);
        CellType cType = cell.getCellType();
        switch (cType) {
        case NUMERIC:
        	DecimalFormat df = new DecimalFormat("#.00");
        	String strval = df.format(cell.getNumericCellValue());
        	return strval;
//            return (String.valueOf(cell.getNumericCellValue()));
        case STRING:
            return cell.getStringCellValue();
        case FORMULA:
            return getStringFormulaValue(cell);
        default:
            break;
        }
        return "";
    }

    /**
     * 計算式のデータ取得
     * @param cell
     * @return 計算式のデータ
     */
    private String getStringFormulaValue(Cell cell) {
        CreationHelper helper = userWorkBook.getCreationHelper();
        FormulaEvaluator evaluator = helper.createFormulaEvaluator();
        CellValue value = evaluator.evaluate(cell);
        switch (value.getCellType()) {
        case STRING:
            return value.getStringValue();
        case NUMERIC:
            return (String.valueOf(cell.getNumericCellValue()));
        default:
            return "";
        }
    }

    /**
     * <p>
     * EXCELファイルに、列コピー。<br>
     *
     * @version 1.0
     *
     * @param sSheetName 源シート
     * @param tSheetName 目標シート
     * @param sRow 源の列の初めの位置
     * @param sCell コピーの位置の行数に
     * @param tRow 目標の列の初めの位置
     * @param tCell 目標の初めの位置
     * @param cellNum 行数をコピーします
     * @throws Exception 例外
     */
    public void insertCell(String sSheetName,
                            String tSheetName,
                            int sRow,
                            int sCell,
                            int tRow,
                            int tCell,
                            int cellNum) throws Exception {
        List<Cell> cellList = new ArrayList<>();
        Sheet sourceSheet = null;
        Sheet targetSheet = null;
        Row sourceRow = null;
        Row targetRow = null;
        Cell sourceCell = null;
        Cell cell = null;
        sourceSheet = userWorkBook.getSheet(sSheetName);
        targetSheet = userWorkBook.getSheet(tSheetName);
        if (sourceSheet == null) {
            return;
        }
        sourceRow = sourceSheet.getRow(sRow - 1);
        targetRow = targetSheet.getRow(tRow - 1);
        if (sourceRow == null || targetRow == null) {
            return;
        }
        sourceCell = this.getCell(sourceRow, sCell - 1);
        int lastCol = targetRow.getLastCellNum();

        if (targetRow.getCell((short) lastCol) != null) {
            lastCol = lastCol + 1;
        }

        for (int i = 0; i <= (lastCol - tCell); i++) {
            cellList.add(this.getCell(targetRow, tCell - 1 + i));
        }
        sourceSheet.getDefaultColumnWidth();
        //store local
        for (int i = 0; i < cellList.size(); i++) {
            cell = this.getCell(targetRow, i + tCell - 1 + cellNum);
            cell.setCellStyle(((Cell) cellList.get(i)).getCellStyle());

            sourceSheet.setColumnWidth((short) (i + tCell - 1 + cellNum),
                    sourceSheet.getColumnWidth(((Cell) cellList.get(i)).getColumnIndex()));
        }

        //insert
        for (int i = 0; i < cellNum; i++) {
            cell = this.getCell(targetRow, tCell - 1 + i);
            cell.setCellStyle(sourceCell.getCellStyle());
            sourceSheet.setColumnWidth((short) (tCell - 1 + i),
                    sourceSheet.getColumnWidth((short) (sCell - 1)));
        }
    }
    /**
     * <p>
     * EXCELファイルに、コラムコピー。<br>
     *
     * @version 1.0
     *
     * @param sSheetName 源シート
     * @param tSheetName 目標シート
     * @param sRow 源の列の位置
     * @param sCell 源のコラムの位置
     * @param tRow 目標コラムの初めの位置
     * @param tCell 目標コラムの数量
     * @param cellNum 目標列の数量
     *
     * @throws Exception 例外
     */
    public void copyCell(String sSheetName,
            String tSheetName,
            int sRow,
            int sCell,
            int tRow,
            int tCell,
            int cellNum) throws Exception {
        Sheet sourceSheet = null;
        Sheet targetSheet = null;
        Row sourceRow = null;
        Row targetRow = null;
        Cell sourceCell = null;
        Cell targetCell = null;
        sourceSheet = userWorkBook.getSheet(sSheetName);
        targetSheet = userWorkBook.getSheet(tSheetName);
        if (sourceSheet == null || targetSheet == null) {
            return;
        }
        sourceRow = sourceSheet.getRow(sRow - 1);
        targetRow = this.getRow(targetSheet, tRow - 1);
        if (sourceRow == null || targetRow == null) {
            return;
        }
        for (int i = 0; i < cellNum; i++) {
            sourceCell = this.getCell(sourceRow, sCell - 1 + i);
            targetCell = this.getCell(targetRow, tCell - 1 + i);
            targetCell.setCellStyle(sourceCell.getCellStyle());
            targetCell.setCellValue(sourceCell.getStringCellValue());
            targetSheet.setColumnWidth((short) (tCell - 1 + i),
                    sourceSheet.getColumnWidth((short) (sCell - 1 + i)));
        }
    }

    /**
     * 範囲をマージ
     * @param sSheetName
     * @param firstRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     * @param name
     */
    public void  mergedRegion(String sSheetName, int firstRow, int lastRow, int firstCol, int lastCol, String name) {
         Sheet sheet = userWorkBook.getSheet(sSheetName);
         sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol,  lastCol));
         Row sourceRow = sheet.getRow(firstRow);
         Cell cell = sourceRow.getCell(firstCol);
         cell.setCellFormula(name);
         CellStyle style = userWorkBook.createCellStyle();
         style.setAlignment(HorizontalAlignment.CENTER);
         cell.setCellStyle(style);
         cell.setCellValue(name);
    }

    /**
     * <p>
     * EXCELファイルに、コラムコピー。<br>
     *
     * @version 1.0
     *
     * @param sSheetName 源シート
     * @param tSheetName 目標シート
     * @param sRow 源の列の位置
     * @param sCell 源のコラムの位置
     * @param tRow 目標コラムの初めの位置
     * @param tCell 目標コラムの数量
     * @param rowCount 目標列の数量
     *
     * @throws Exception 例外
     */
    public void copyColum(String sSheetName,
            String tSheetName,
            int sRow,
            int sCell,
            int tRow,
            int tCell,
            int rowCount) throws Exception {

        Sheet sourceSheet = null;
        Sheet targetSheet = null;
        Row sourceRow = null;
        Row targetRow = null;
        Cell sourceCell = null;
        Cell targetCell = null;
        sourceSheet = userWorkBook.getSheet(sSheetName);
        targetSheet = userWorkBook.getSheet(tSheetName);
        if (sourceSheet == null || targetSheet == null) {
            return;
        }
        for (int i = 0; i < rowCount; i++) {
              sourceRow = sourceSheet.getRow(sRow - 1 + i);
              targetRow = this.getRow(targetSheet, tRow - 1 + i);
              if (sourceRow == null || targetRow == null) {
                  return;
              }
              sourceCell = this.getCell(sourceRow, sCell - 1);
              targetCell = targetRow.createCell((short) (tCell - 1));
//              targetCell = this.getCell(targetRow, tCell - 1 + i);
              CellType cType = sourceCell.getCellType();
              targetCell.setCellStyle(sourceCell.getCellStyle());
           //   targetCell.setCellValue(sourceCell.getStringCellValue());
              switch (cType) {
              case BOOLEAN:
              targetCell.setCellValue(sourceCell.getBooleanCellValue());
              break;
              case ERROR:
              targetCell
                    .setCellErrorValue(sourceCell.getErrorCellValue());
              break;
              case NUMERIC:
              targetCell.setCellValue(sourceCell.getNumericCellValue());
              break;
              case STRING:
              targetCell.setCellValue(sourceCell.getStringCellValue());
              break;
              default:
              break;
             }
              targetSheet.setColumnWidth((short) (tCell - 1),
                      sourceSheet.getColumnWidth((short) (sCell - 1)));
        }
    }

    /**
     * <p>
     * EXCELファイルに、コラムコピー。<br>
     *
     * @version 1.0
     *
     * @param pSourceSheetName 源シート
     * @param pTargetSheetName 目標シート
     * @param sourceRowIndex 源の列の位置
     * @param sourceColIndex 源のコラムの位置
     * @param colStart 目標コラムの初めの位置
     * @param columnNum 目標コラムの数量
     * @param rowNum 目標列の数量
     *
     * @throws Exception 例外
     */
    public void copyColumns(String pSourceSheetName,
                     String pTargetSheetName,
                     int sourceRowIndex,
                     int sourceColIndex,
                     int colStart,
                     int columnNum,
                     int rowNum) throws Exception {
        Row sourceRow = null;
        Cell sourceCell = null;
        Cell targetCell = null;
        Sheet sourceSheet = null;
        Sheet tartgetSheet = null;
        Row targetRow = null;
        CellType cType;
        sourceSheet = this.userWorkBook.getSheet(pSourceSheetName);
        tartgetSheet = this.userWorkBook.getSheet(pTargetSheetName);
        for (int i = 0; i < rowNum; i++) {
            sourceRow = this.getRow(sourceSheet, sourceRowIndex - 1 + i);
            targetRow =  this.getRow(tartgetSheet, sourceRowIndex - 1 + i);
            sourceCell = sourceRow.getCell((short) (sourceColIndex - 1));
            if (sourceRow == null || sourceCell == null) {
                continue;
            }
            for (int n = 0; n < columnNum; n++) {
                   targetCell = targetRow.createCell((short) (colStart - 1 + n));
                if (targetCell == null) {
                    continue;
                }
                targetCell.setCellStyle(sourceCell.getCellStyle());
                cType = sourceCell.getCellType();
                sourceSheet.setColumnWidth((short) (colStart - 1 + n),
                sourceSheet.getColumnWidth((short) (sourceColIndex - 1)));

                switch (cType) {
                case BOOLEAN:
                targetCell.setCellValue(sourceCell.getBooleanCellValue());
                break;
                case ERROR:
                targetCell
                      .setCellErrorValue(sourceCell.getErrorCellValue());
                break;
                case NUMERIC:
                targetCell.setCellValue(sourceCell.getNumericCellValue());
                break;
                case STRING:
                targetCell.setCellValue(sourceCell.getStringCellValue());
                break;
                default:
                break;
               }
            }
        }
    }

    /**
     * <p>
     * 地区の設置をタイプ印刷します。<br>
     *
     * @version 1.0
     *
     * @param sheetIndex シートインデックス(logic順序)
     * @param startColumn 初めのコラム(logic順序)
     * @param endColumn 終わりのコラム(logic順序)
     * @param startRow 初めの列(logic順序)
     * @param endRow 終わりの列(logic順序)
     *
     * @throws Exception 例外
     */
    public void setPrintArea(int sheetIndex,
                              int startColumn,
                              int endColumn,
                              int startRow,
                              int endRow) throws Exception {
        if (userWorkBook == null) {
            return;
        }
        userWorkBook.setPrintArea(sheetIndex - 1, startColumn - 1, endColumn - 1, startRow - 1, endRow - 1);
    }

    /**
     * 出力ストリームを得ます
     *
     * @return outputStream
     */
    public OutputStream getOutputStream() {
      return outputStream;
    }

    /**
     * 出力ストリームをセット
     * @param outputStream1
     */
    public void setOutputStream(OutputStream outputStream1) {
      this.outputStream = outputStream1;
    }

    /**
     * Excelシートを得ます
     *
     * @return hssfSheet
     */
    public Sheet getSheet() {
      return userSheet;
    }

    /**
     * Excelシートをセット
     *
     * @param userSheet1 シート
     */
    public void setSheet(Sheet userSheet1) {
      this.userSheet = userSheet1;
    }

    /**
     * Excelワークブックを得ます
     *
     * @return hssfWorkBook
     */
    public Workbook getWorkBook() {
      return userWorkBook;
    }

    /**
     * Excelワークブックをセット
     *
     * @param userWorkBook1 ワークブック
     */
    public void setWorkBook(Workbook userWorkBook1) {
      this.userWorkBook = userWorkBook1;
    }

    /**
     * NULL変換
     * @param s
     * @return NULL変換
     */
    private  String convertNull(Object s) {
        if (s == null) {
            s = "";
        }
        return s.toString();
    }

    /**
     * ブックを閉じる。
     */
    public void close()  {
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
            }
        }
        if (userWorkBook != null) {
            try {
                userWorkBook.close();
            } catch (IOException e) {
            }
        }
    }
}

