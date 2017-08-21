package cn.pb.smbms.service;

import java.io.Serializable;
import java.util.List;

import cn.pb.smbms.util.PageUtil;

/**
 * 所有service层共同的接口
 * 
 * @author Administrator
 * 
 */
public interface CommentService<T> {
	/**
	 * 查询所有对象列表
	 * 
	 * @return T对象的集合
	 */
	List<T> findList();

	/**
	 * 根据id删除对象的方法
	 * 
	 * @param id
	 *            要删除的对象的编号
	 * @return 返回影响的行数
	 */
	int delById(Serializable id);

	/**
	 * 根据编号 查询对象
	 * 
	 * @param id
	 *            新闻id
	 * @return 返回T对象
	 */
	T findById(Serializable id);

	/**
	 * 根据编号 修改对象
	 * 
	 * @param T对象
	 * 
	 * @return 返回int 影响的行数
	 */
	int update(T t);

	/**
	 * 新增对象
	 * 
	 * @param T对象
	 * @return 返回int 影响的行数
	 */
	int add(T t);

	/**
	 * 获取总记录数的方法
	 * 
	 * @return
	 */
	int getTotalCount();

	/**
	 * 分页查询
	 * 
	 * @param util
	 *            传进参数的对象
	 * @return
	 */
	List<T> getListByPage(PageUtil util);
}
