package com.gh.common;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @ClassName ExcelUtil 
 * @Description Excel公共处理类
 * @author oriental_pearl
 * @date 2013-9-9
 */
public class ExcelUtil {
	private int rowIndex = 0;
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private HSSFRow row;
	private HSSFCell cell;
	private HSSFCellStyle cellStyle;
	private HSSFFont font;
	private List<String> heads = new ArrayList<String>();
	private List<List<String>> datas = new ArrayList<List<String>>();
	private static final String EXCEL_DEFAULT_TITLE = "图景网络统计报表范例";
	private static final String EXCEL_DEFAULT_SHEET_TITLE = "统计报表";
	private static final String EXCEL_DEFAULT_CELL_ALIGN = "center";
	private static final int EXCEL_DEFAULT_TITLE_FONT_SIZE = 16;
	private static final int EXCEL_DEFAULT_DATA_FONT_SIZE = 12;
	
	
	public ExcelUtil(){
		workbook = new HSSFWorkbook();
	}
	
	/** 
	 * 构造函数
	 * @param sheetTitle 工作表标题
	 * @param title 标题
	 * @param titleFontSize 表体字体大小
	 * @param heads 数据集合
	 * @param datas 数据集合
	 */
	public ExcelUtil(String sheetTitle,String title,int titleFontSize,List<String> heads,List<List<String>> datas){
		this();
		this.heads = heads;
		this.datas = datas;
		createSheet(sheetTitle);
		if(title==null||title.equals("")){
			title = EXCEL_DEFAULT_TITLE;
		}
		if(titleFontSize == 0){
			titleFontSize = EXCEL_DEFAULT_TITLE_FONT_SIZE;
		}
		createCellStyle(EXCEL_DEFAULT_CELL_ALIGN,(short)titleFontSize,true);
		createTitle(title,0);
		createHeads();
		setColumnWidth(20);
		addMergedRegion(0,0,0,heads.size()-1);
		createCellStyle(EXCEL_DEFAULT_CELL_ALIGN,(short)EXCEL_DEFAULT_DATA_FONT_SIZE,false);
		createData();
	}
	
	/**
	 * 构造函数
	 * @param sheetTitle 工作表标题
	 * @param titles 标题集合
	 * @param titleFontSize 表体字体大小
	 * @param heads 表头集合
	 * @param datas 数据集合
	 */
	public ExcelUtil(String sheetTitle,List<String> titles,int titleFontSize,List<String> heads,List<List<String>> datas){
		this();
		this.heads = heads;
		this.datas = datas;
		createSheet(sheetTitle);
		if(titleFontSize == 0){
			titleFontSize = EXCEL_DEFAULT_TITLE_FONT_SIZE;
		}
		createCellStyle(EXCEL_DEFAULT_CELL_ALIGN,(short)titleFontSize,true);
		for(int i=0;i<titles.size();i++){
			createTitle(titles.get(i),0);
		}
		createHeads();
		setColumnWidth(20);
		for(int i=0;i<titles.size();i++){
			addMergedRegion(i,i,0,heads.size()-1);
		}
		createCellStyle(EXCEL_DEFAULT_CELL_ALIGN,(short)EXCEL_DEFAULT_DATA_FONT_SIZE,false);
		createData();
	}
	
	/**
	 * 创建工作表
	 * @param sheetTitle 工作表名
	 */
	public void createSheet(String sheetTitle){
		if(sheetTitle==null||sheetTitle.equals("")){
			sheetTitle = EXCEL_DEFAULT_SHEET_TITLE;
		}
		sheet = workbook.createSheet(sheetTitle);
	}
	
	/**
	 * 创建一行
	 */
	public void createRow(){
		row = sheet.createRow(rowIndex);
		rowIndex++;
	}
	
	/**
	 * 创建单元格样式
	 * @param align 对齐方式
	 * @param fontSize 字体大小
	 * @param isBoldWeight 是否加粗
	 */
	public void createCellStyle(String align,short fontSize,boolean isBoldWeight){
		cellStyle = workbook.createCellStyle();
		font = workbook.createFont();
		if(isBoldWeight){
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		}
		font.setFontName("宋体");
		font.setFontHeightInPoints(fontSize);
		cellStyle.setFont(font);
		if(align.equals("left")){
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		}else if(align.equals("right")){
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		}else{
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		}
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	}
	
	/**
	 * 创建标题
	 * @param value 标题
	 * @param index 单元格位置
	 */
	public void createTitle(String value,int index){
		createRow();
		createCell(value,0);
	}
	
	/**
	 * 创建单元格
	 * @param value 单元格
	 * @param index 索引
	 */
	public void createCell(String value,int index){
		cell = row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(value);
	}
	
	public void createHeads(){
		createRow();
		for(int i=0;i<heads.size();i++){
			createCell(heads.get(i),i);
		}
	}
	
	/**
	 * 设置单元格宽度
	 * @param width 宽度
	 */
	public void setColumnWidth(int width){
		for(int i=0;i<heads.size();i++){
			//设置列宽
			sheet.setColumnWidth(i,width*256);
		}
	}
	
	/**
	 * 合并单元格
	 * @param firstRow 起始行
	 * @param lastRow 结束行
	 * @param firstCol 起始单元格
	 * @param lastCol 结束单元格
	 */
	public void addMergedRegion(int firstRow, int lastRow, int firstCol, int lastCol){
		sheet.addMergedRegion(new CellRangeAddress(firstRow,lastRow,firstCol,lastCol));
	}
	
	/**
	 * 填充报表数据
	 */
	public void createData(){
		for(int i=0;i<datas.size();i++){
			List<String> list = datas.get(i);
			createRow();
			for(int j=0;j<list.size();j++){
				createCell(list.get(j),j);
			}
		}
	}
	
	/**
	 * 导出Excel文件流
	 * @return
	 */
	public InputStream export(){
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			workbook.write(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
}