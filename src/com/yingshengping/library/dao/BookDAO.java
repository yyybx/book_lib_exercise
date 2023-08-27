package com.yingshengping.library.dao;

import java.util.List;
import java.util.Map;

import com.yingshengping.library.entity.Book;

public interface BookDAO {

	/**
	 * 添加新的书籍
	 * @param book
	 * 		待添加图书信息
	 * @return
	 * 		影响行数
	 * 		0-添加失败，1-添加成功
	 */
	public Integer insertBook(Book book);
	
	/**
	 * 删除书籍
	 * @param id
	 * 		待删除书籍id
	 * @return
	 * 		影响行数
	 * 		0-删除失败，1-删除成功
	 */
	public Integer deleteBook(Integer id);
	
	/**
	 * 更新书籍信息
	 * @param book
	 * 		待更新书籍信息
	 * @return
	 * 		影响行数
	 * 		0-修改失败
	 * 		>0-修改成功
	 */
	public Integer updateBook(Book book);
	
	/**
	 * 查询书籍
	 * @param book
	 * 		书籍查询条件
	 * @return
	 * 		符合条件的结果数据集
	 * 		null-查询失败
	 * 		非空-查询成功
	 */
	public List<Map<String, Object>> searchBook(Book book);
	
	/**
	 * 依赖书籍分类查询书籍
	 * @param tname
	 * 		书籍类型
	 * @return
	 * 		符合条件的结果集数据
	 * 		null-查询失败
	 * 		非空-查询成功
	 */
	public List<Map<String, Object>> searchBookByType(String tname);
}
