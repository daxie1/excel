package learn.web.service.impl;


import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import learn.web.bean.Student;
import learn.web.dao.StudentDao;
@Service(value="studentService")
@Transactional
public class StudentService implements learn.web.service.IStudentService
{
	@Autowired
	private StudentDao studentDao;
	@Override
	public Student getById(Integer id)
	{
		if(id==null||id==0)
		{
			return null;
		}else
		{
			return studentDao.getById(id);
		}
	}

	@Override
	public Student getUnqiueByCondition(Student t)
	{
		List<Student> list=studentDao.getByCondition(t);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}else 
		{
			return null;
		}
	}

	@Override
	public List<Student> getAll(Student t)
	{
		return studentDao.getByCondition(t);
	}

	@Override
	public PageInfo<Student> getListToPage(Student t, int page, int pageSize)
	{
		PageHelper.startPage(page,pageSize);
		List<Student> students=studentDao.getByCondition(t);
		PageInfo<Student> pageInfo=new PageInfo<Student>(students);
		return pageInfo;
	}

	@Override
	public int insert(Student t)
	{
		return studentDao.insertSelective(t);
	}

	@Override
	public int insertList(List<Student> ts)
	{
		if(ts==null||ts.size()==0)
		{
			return 0;
		}else
		{
			return studentDao.insertList(ts);
		}
	}

	@Override
	public int delete(Student t)
	{
		return studentDao.delete(t);
	}

	@Override
	public int deleteList(List<Student> ts)
	{
		int result=0;
		for(Student student:ts)
		{
			result+=studentDao.delete(student);
		}
		return result;
	}

	@Override
	public int update(Student t)
	{
		return studentDao.update(t);
	}

	@Override
	public int updateList(List<Student> ts)
	{
		int result=0;
		for(Student student:ts)
		{
			result+=studentDao.update(student);
		}
		return result;
	}

	@Override
	public Long count()
	{
		return studentDao.count();
	}

}
