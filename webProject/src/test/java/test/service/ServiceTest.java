package test.service;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.swing.plaf.synth.SynthStyleFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;

import common.util.ExcelUtil;
import learn.web.bean.Student;
import learn.web.service.IStudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class ServiceTest
{
	private static Logger logger=LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	@Resource(name="studentService")
	private IStudentService studentService;
	@Test
	public void serviceTest()
	{
		/*String fileName="C:\\Users\\yu\\Desktop\\student.xlsx";
		try(InputStream iStream=new FileInputStream(fileName))
		{
			List<Student> students=ExcelUtil.importXSSFExcel("", iStream, Student.class);
			//studentService.insertList(students);
			studentService.insetBigData(students);
		    System.out.println("ok");
		} catch (Exception e)
		{
			logger.error(e.getMessage());
		}*/
		Student student=new Student();
		student.setAge(28);
		PageInfo<Student> pageInfo=studentService.getListToPage(student, 1, 10);
		assertEquals(10, pageInfo.getList().size());
	}

	public IStudentService getStudentService()
	{
		return studentService;
	}

	public void setStudentService(IStudentService studentService)
	{
		this.studentService = studentService;
	}
}
