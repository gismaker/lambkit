/**
 * Copyright (c) 2015-2017, Henry Yang 杨勇 (gismail@foxmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lambkit.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Date;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class JXLExcelUtils {
	
	private WritableWorkbook wwb = null;
	private WritableSheet ws  = null;
	private WritableCellFormat cf = null;
	
	public JXLExcelUtils createWorkBook(OutputStream os, String tempfile) {
		try {
			 // 源excel文件，通常存储模板（无数据）
			InputStream  sourceFile = new FileInputStream(tempfile);
            // 源文件读入
            Workbook template = Workbook.getWorkbook(sourceFile);
            WorkbookSettings settings = new WorkbookSettings ();  
            settings.setWriteAccess(null);  
			wwb = Workbook.createWorkbook(os, template, settings);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JXLExcelUtils createWorkBook(OutputStream os) {
		try {
			wwb = Workbook.createWorkbook(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JXLExcelUtils createSheet(String name, int index) {
		if(wwb!=null) ws = wwb.createSheet(name, 0);
		return this;
	}
	
	public JXLExcelUtils getSheet(int index) {
		if(wwb!=null) ws = wwb.getSheet(index);
		if(ws!=null) ws.getSettings().setSelected(true);  
		return this;
	} 
	
	public JXLExcelUtils getSheet(String name) {
		if(wwb!=null) ws = wwb.getSheet(name);
		if(ws!=null) ws.getSettings().setSelected(true);  
		return this;
	} 
	
	public JXLExcelUtils setCellFormat(boolean center) {
		cf = new WritableCellFormat();
        try {
        	// 设置居中   
			if(center) cf.setAlignment(Alignment.CENTRE);
			 // 设置边框线   
	        cf.setBorder(Border.ALL, BorderLineStyle.THIN);   
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
       
		return this;
	}  
	
	public JXLExcelUtils addCell(int c, int r, String val) {
		if(ws==null) return this;
		try {
			if(cf==null) ws.addCell(new Label(c, r, val));
			else ws.addCell(new Label(c, r, val, cf));
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JXLExcelUtils addCell(int c, int r, Object val) {
		if(ws==null) return this;
		String sval = "";
		if(val!=null) sval = val.toString();
		try {
			if(cf==null) ws.addCell(new Label(c, r, sval));
			else ws.addCell(new Label(c, r, sval, cf));
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JXLExcelUtils addCell(int c, int r, String val, CellFormat format) {
		if(ws==null) return this;
		try {
			ws.addCell(new Label(c, r, val, format));
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JXLExcelUtils addCell(int c, int r, double val) {
		if(ws==null) return this;
		try {
			if(cf==null) ws.addCell(new Number(c, r, val));
			else ws.addCell(new Number(c, r, val, cf));
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JXLExcelUtils addCell(int c, int r, double val, CellFormat format) {
		if(ws==null) return this;
		try {
			ws.addCell(new Number(c, r, val, format));
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JXLExcelUtils addCell(int c, int r, Date d) {
		if(ws==null) return this;
		try {
			if(cf==null) ws.addCell(new DateTime(c, r, d));
			else ws.addCell(new DateTime(c, r, d, cf));
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JXLExcelUtils addCell(int c, int r, Date d, CellFormat format) {
		if(ws==null) return this;
		try {
			ws.addCell(new DateTime(c, r, d, format));
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JXLExcelUtils addCell(int c, int r, Timestamp d) {
		if(ws==null) return this;
		try {
			if(cf==null) ws.addCell(new DateTime(c, r, d));
			else ws.addCell(new DateTime(c, r, d, cf));
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JXLExcelUtils addCell(int c, int r, Timestamp d, CellFormat format) {
		if(ws==null) return this;
		try {
			ws.addCell(new DateTime(c, r, d, format));
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public JXLExcelUtils export() {
		if(wwb==null) return this;
		if(ws==null) return this;
		try {
			wwb.write();
			wwb.close();
			ws = null;
			wwb = null;
		} catch (IOException e) {
			System.err.println("JXLExcel导出错误：" + e.getMessage());
			e.printStackTrace();
		} catch (WriteException e) {
			System.err.println("JXLExcel导出错误：" + e.getMessage());
			e.printStackTrace();
		}
		return this;
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	//
	//////////////////////////////////////////////////////////////////////////////////
	
	/*
	public void exportExcel(Long tbid, String wend, OutputStream os) {
		try {
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			TableRowListValue trlv = null;
			if(wend!=null && !wend.isEmpty()) {
				trlv = BusinessFactory.createTableRow().getList(tbid, wend);
			} else {
				trlv = BusinessFactory.createTableRow().getList(tbid);
			}
			//
			WritableSheet ws = wwb.createSheet(trlv.getTableconfig().getTbcnn(), 0);
			
			//标题
			int n=0;
			for(int j=0; j<trlv.getFieldlist().size(); j++) {
				if(trlv.getField(j).getIsview().equals("N")) continue;
				ws.addCell(new Label(n, 0, trlv.getField(j).getFldcnn()));
				n++;
			}
			int m=1;
			for(int i=0; i<trlv.getRowlist().size(); i++) {
				String delname = trlv.getTableconfig().getDelname();
				if(delname!=null && !delname.isEmpty()) {
					if(trlv.getCell(i, delname).toString().equals("Y")) continue;
				}
				n=0;
				for(int j=0; j<trlv.getFieldlist().size(); j++) {
					if(trlv.getField(j).getIsview().equals("N")) continue;
					ws.addCell(new Label(n, m, trlv.getCellString(i, j)));
					n++;
				}
				m++;
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("JXLExcel导出错误：" + e.getMessage());
		}
	}

	public void exportExcel(TableRowListValue inval, String wend, OutputStream os) {
		if(inval.getTableconfig().getTbid()!=1) return;
		try {
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			for(int k=0; k<inval.getRowlist().size(); k++) {
				Object obj = inval.getCell(k, "isdelete");
				if(obj==null) continue;
				if(obj.toString().equals("Y")) continue;
				TableRowListValue trlv = null;
				if(wend!=null && !wend.isEmpty()) {
					trlv = BusinessFactory.createTableRow().getList(Long.parseLong(inval.getCell(k, "tbid").toString()), wend);
				} else {
					trlv = BusinessFactory.createTableRow().getList(Long.parseLong(inval.getCell(k, "tbid").toString()));
				}
				//
				WritableSheet ws = wwb.createSheet(trlv.getTableconfig().getTbcnn(), 0);
				
				//标题
				int n=0;
				for(int j=0; j<trlv.getFieldlist().size(); j++) {
					if(trlv.getField(j).getIsview().equals("N")) continue;
					ws.addCell(new Label(n, 0, trlv.getField(j).getFldcnn()));
					n++;
				}
				int m=1;
				for(int i=0; i<trlv.getRowlist().size(); i++) {
					String delname = trlv.getTableconfig().getDelname();
					if(delname!=null && !delname.isEmpty()) {
						if(trlv.getCell(i, delname).toString().equals("Y")) continue;
					}
					n=0;
					for(int j=0; j<trlv.getFieldlist().size(); j++) {
						if(trlv.getField(j).getIsview().equals("N")) continue;
						ws.addCell(new Label(n, m, trlv.getCellString(i, j)));
						n++;
					}
					m++;
				}
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("JXLExcel导出错误：" + e.getMessage());
		}
	}
	*/
}
