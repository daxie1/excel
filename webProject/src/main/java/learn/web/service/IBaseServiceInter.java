package learn.web.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

public interface IBaseServiceInter<T>
{
	/**
	 * 
	 * @param t
	 * @return
	 */
	T getById(Integer id);
	/**
	 * 根据条件获取单个对象,若存在多个满足，则返回按id排序的第一个
	 * @param t
	 * @return
	 */
	T getUnqiueByCondition(T t);
	/**
	 * 获取满足条件的所有对象
	 * @param t
	 * @return
	 */
	List<T> getAll(T t);
	/**
	 * 根据条件分页查询
	 * @param t
	 * @param page
	 * @param pageSize
	 * @return
	 */
	PageInfo<T> getListToPage(T t,int page,int pageSize);
	/**
	 * 插入一条数据
	 * @param t
	 * @return
	 */
	int insert(T t);
	/**
	 * 批量插入
	 * @param ts
	 * @return
	 */
	int insertList(List<T> ts);
	/**
	 * 删除满足条件的所有记录
	 * @param t
	 * @return
	 */
	int delete(T t);
	/**
	 * 删除满足多个条件的所有记录
	 * @param ts
	 * @return
	 */
	int deleteList(List<T> ts);
	/**
	 * 更新
	 * @param s
	 * @return
	 */
	int update(T s);
	/**
	 * 批量更新
	 * @param ts
	 * @return
	 */
	int updateList(List<T> ts);
	/**
	 * 获取记录总数
	 * @return
	 */
	Long count();
}
