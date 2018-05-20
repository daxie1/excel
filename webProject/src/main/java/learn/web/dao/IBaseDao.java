package learn.web.dao;

import java.util.List;

public interface IBaseDao<T>
{
	/**
	 * 插入数据
	 * @param t
	 * @return
	 */
	int insert(T t);
	/**
	 * 选择性的插入数据
	 * @param t
	 * @return
	 */
	int insertSelective(T t);
	/**
	 * 通过id获取数据
	 * @param id
	 * @return
	 */
	T getById(Integer id);
	/**
	 * 根据条件查询
	 * @param t
	 * @return
	 */
	List<T> getByCondition(T t);
	/**
	 * 删除满足条件的所有记录
	 * @param t
	 * @return
	 */
	int delete(T t);
	/**
	 * 跟新记录，以id查找条件
	 * @param t
	 * @return
	 */
	int update(T t);
	
	Long count();
}
