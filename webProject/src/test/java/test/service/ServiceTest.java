package test.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import learn.web.bean.Student;
import learn.web.service.IStudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class ServiceTest
{
	@Resource(name="studentService")
	private IStudentService studentService;
	@Test
	public void serviceTest()
	{
	    ArrayList<Student> students=new ArrayList<Student>();
		for(int i=1;i<11;i++)
		{
			Student student=new Student();
			student.setId(i);
			student.setAge(28);
			student.setIdentify("2018050"+i);
			student.setName("yu");
			student.setStartDate(new Date());
			students.add(student);
		}
		assertEquals(10, studentService.insertList(students));
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
