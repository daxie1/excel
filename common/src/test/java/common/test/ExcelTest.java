package common.test;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import common.util.ExcelUtil;

public class ExcelTest
{
	@Test
	public void excelTest()
	{
		String filePath="C:\\Users\\yu\\Desktop\\student.xlsx";
		try(InputStream iStream=new FileInputStream(filePath))
		{
			List<Student> students=ExcelUtil.importXSSFExcel("", iStream,Student.class);
			assertEquals("p2018051800001", students.get(0).getIdentify());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
