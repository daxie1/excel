package common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
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
 * @throws IllegalAccessException 
 * @throws InstantiationException 
 * @throws InvocationTargetException 
 * @throws ParseException 
 */
	private static <T> List<T> importExcel(String sheetName,Workbook workbook,Class<T> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException, ParseException
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
		ArrayList<T> result=null;
		if(rows>0)
		{
			result=new ArrayList<T>();
			Field[] fields=clazz.getDeclaredFields();//获取所有属性
			Map<Integer, Field> filedMap=new HashMap<Integer,Field>();//将字段保存在map中 key为excel表格中的位置 从里开始
			for(Field field:fields)
			{
				if(field.isAnnotationPresent(Excel.class))
				{
					Excel excel=field.getAnnotation(Excel.class);
					Integer order=excel.order();
					field.setAccessible(true);
					filedMap.put(order, field);
				}
			}
			for(int i=1;i<rows;i++)//遍历所有列
			{
				Row row=sheet.getRow(i);
				int nums=row.getPhysicalNumberOfCells();
				T bean=clazz.newInstance();
				for(int j=0;j<nums;j++)
				{
					Field name=filedMap.get(j);
					if(name==null)
					{
						continue;
					}
					
					Cell cell=row.getCell(j);
					String value=null;
					switch (cell.getCellTypeEnum())
					{
					case STRING:
						value=cell.getStringCellValue();
						break;
					case NUMERIC:
					    value=cell.getNumericCellValue()+"";
						break;
					default:
						break;
					}
					if(Date.class==name.getType())
					{
						//日期也会被当成数字来读
						Double d=Double.parseDouble(value);
						Date date=new Date(d.longValue());
						//BeanUtils.setProperty(bean, name.getName(), date);Beanutils中还要遍历属性，影响性能
						name.set(bean, date);
					}else
					{
						if(name.getType()==Integer.class)
						{
							Double d=Double.parseDouble(value);
							//name.setInt(bean,d.intValue());
							name.set(bean, d.intValue());
						}else if(name.getType()==Double.class)
						{
							name.setDouble(bean, Double.parseDouble(value));
						}else if(name.getType()==String.class)
						{
							name.set(bean,value);
						}else if(name.getType()==Float.class)
						{
							name.setFloat(bean, Float.parseFloat(value));
						}else if(name.getType()==Long.class)
						{
							name.setLong(bean, Long.parseLong(value));
						}
					}
				}
				result.add(bean);
			}
		}
		return result;
	}
	/**
	 * HSSF方式导入，只适用于excel2007以下版本
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
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{

			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			e.printStackTrace();
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static <T> List<T> importXSSFExcel(String sheetName, InputStream is,Class<T> clazz) throws IOException, ParseException
	{
		try
		{
			Workbook workbook=new XSSFWorkbook(is);
			return importExcel(sheetName,workbook,clazz);
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		} catch (InvocationTargetException e)
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
