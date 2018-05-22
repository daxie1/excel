package learn.web.service;


import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import learn.web.bean.Student;

/**
 * 学生服务类接口
 * @author yu
 *
 */
public interface IStudentService extends IBaseServiceInter<Student>
{
	/**
	 * 使用多线程插入大批量的数据
	 * @param students
	 * @return 
	 * @throws InterruptedException 
	 */
	boolean insetBigData(List<Student> students) throws InterruptedException;
}
