package common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil
{
	/**
	 * excel文件处理类型的枚举
	 * @author Administrator
	 *
	 */
	static enum ExcelType
	{
		HSSF,XSSF
	}
	/**
	 * 使用HSSF的方式处理excel文件
	 * @param sheetName sheet名
	 * @param fileName 文件路径
	 * @param clazz 转化成实体类的类型
	 * @return
	 */
	public static <T> List<T> importHSSFExcel(String sheetName,String fileName,Class<T> clazz)
	{
		try(InputStream inputStream=new FileInputStream(fileName))
		{
			return importHSSFExcel(sheetName,inputStream,clazz);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} 
		return null;
	}
	/**
	 * 使用XSSF的方式处理excel文件
	 * @param sheetName sheet名
	 * @param fileName 文件路径
	 * @param clazz 转化成实体类的类型
	 * @return
	 */
	public static <T> List<T> importXSSFExcel(String sheetName,String fileName,Class<T> clazz)
	{
		try(InputStream inputStream=new FileInputStream(fileName))
		{
			return importXSSFExcel(sheetName,inputStream,clazz);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} 
		return null;
	}
/**
 * 将workbook转化为list<T>
 * @param sheetName sheet名称，为空时去第一个sheet
 * @param workbook
 * @param clazz
 * @return
 */
	private static <T> List<T> importExcel(String sheetName,Workbook workbook,Class<T> clazz)
	{
		Sheet sheet;
		if(StringUtils.isNotEmpty(sheetName))
		{
			sheet=workbook.getSheet(sheetName);
		}else
		{
			sheet=workbook.getSheetAt(0);
		}
		int rows=sheet.getPhysicalNumberOfRows();//获取物理（实际使用）列数
		if(rows>0)
		{
			
		}
		return null;
	}
	/**
	 * 
	 * @param sheetName
	 * @param is 输入
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	public static <T> List<T> importHSSFExcel(String sheetName, InputStream is,Class<T> clazz)
	{
		try
		{
			Workbook workbook=new HSSFWorkbook(is);
			return importExcel(sheetName,workbook,clazz);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static <T> List<T> importXSSFExcel(String sheetName, InputStream is,Class<T> clazz) throws IOException
	{
		try
		{
			Workbook workbook=new XSSFWorkbook(is);
			return importExcel(sheetName,workbook,clazz);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}


	public static <T> boolean exportExcel(List<T> list, String sheetName, int sheetSize, OutputStream os)
	{
		// TODO Auto-generated method stub
		return false;
	}


	public static <T> boolean exportExcel(List<T> list, String sheetName, OutputStream os)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
