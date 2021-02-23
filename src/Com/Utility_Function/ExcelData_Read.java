package Com.Utility_Function;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData_Read {

	static int _number;
	static String _textValue;
	static boolean _booleanValue;
	static XSSFCell _cell;
	static XSSFRow _row;
	static String data = "";

	public static void setTheNumericValue(int num) {
		_number = num;
	}

	public static String getTheNumericValue() {
		return String.valueOf(_number);
	}

	public static void setTheBooleanValue(boolean bool) {

		_booleanValue = bool;
	}

	public static String getTheBooleanValue() {
		return String.valueOf(_booleanValue);
	}

	public static String getTheExcelData(String testCaseid, int sheetIndex, String colName, String path)
			throws FileNotFoundException, IOException {
		InputStream input = null;
		XSSFWorkbook wb = null;
		XSSFSheet sheet = null;
		int colIndex = -1;
		try {
			input = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			System.out.println("No file found");
		}
		try {
			wb = new XSSFWorkbook(input);
		} catch (IOException e) {
			System.out.println("No input object");
		}
		sheet = wb.getSheetAt(sheetIndex);
		Iterator<Row> rows = sheet.rowIterator();
		while (rows.hasNext()) {
			_row = (XSSFRow) rows.next();
			Iterator<Cell> cells = _row.cellIterator();
			//while (cells.hasNext()) {
			if (_row.getRowNum() == 0) {
				while (cells.hasNext()) {
					_cell = (XSSFCell) cells.next();

					if (_cell.getStringCellValue().equals(colName)) {
						colIndex = _cell.getColumnIndex();
						//System.out.println("wdkbchdwbchbdw " + colIndex);
					}
				}
				try {
					if (colIndex == -1) {
						throw new IndexOutOfBoundsException();
					}
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Column name is not correct");
			}
			} else if (_row.getRowNum() != 0) {
				try {
					_cell = (XSSFCell) cells.next();
					if (_cell.getStringCellValue().equals(testCaseid)) {
						// System.out.println("wdlcnljwncwljwcwc : "+
						// testCaseid);
						while (_cell.getColumnIndex() != colIndex) {
							_cell = (XSSFCell) cells.next();
                         //System.out.println(_cell.getCellType());
						}
					
                      if(_cell.getCellType() == CellType.NUMERIC){
						//XSSFCell.CELL_TYPE_NUMERIC:
                    	   setTheNumericValue((int) _cell.getNumericCellValue());
                    	   //System.out.println(_cell.getStringCellValue());
							data = getTheNumericValue();
                       }

                       else if(_cell.getCellType() == CellType.STRING){
							data = _cell.getStringCellValue();
							
                       }
                          
                       else if(_cell.getCellType() == CellType.BOOLEAN){ 		
							setTheBooleanValue(_cell.getBooleanCellValue());
							data = getTheBooleanValue();
						
                       }

                       else if(_cell.getCellType().BLANK !=null){
							throw new NoSuchElementException();
                       }
						}					
				} catch (NoSuchElementException e) {
					data = "";
				}
			}

		}
		wb.close();
		return data;
	}
	
/*	  public static void main(String[] arg) throws FileNotFoundException,
	  IOException {
       String str = ExcelData_Read.getTheExcelData("TC_003", 0, "ME","./PeopleHub_TestData.xlsx"); 
       System.out.println("sdccdn :" + str);
	  }*/
	 
}
