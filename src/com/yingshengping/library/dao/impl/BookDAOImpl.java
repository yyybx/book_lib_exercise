package com.yingshengping.library.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yingshengping.library.dao.BookDAO;
import com.yingshengping.library.entity.Book;
import com.yingshengping.library.exception.LibraryException;
import com.yingshengping.library.utils.DBUtils;
import com.yingshengping.library.utils.DateTimeUtil;

public class BookDAOImpl implements BookDAO {

	@Override
	public Integer insertBook(Book book) {
		Integer result = 0;
		if(book == null) {
			return result;
		}
		
		String sql = "INSERT INTO t_book(bname, lang, num, remain_num, author, publishing) VALUE ?,?,?,?,?,?";
		List<Object> params = new ArrayList<>();
		params.add(book.getBname());
		params.add(book.getLang());
		params.add(book.getNum());
		params.add(book.getRemain_num());
		params.add(book.getAuthor());
		params.add(book.getPublishing());
		
		try {
			DBUtils.connect();
			result = DBUtils.execute(sql, params);
			DBUtils.commit();
		}catch(Exception e) {
			try {
				DBUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new LibraryException(DateTimeUtil.getNow() + "执行BookDAO接口实现类BookDAOImpl中insertBook新增图书方法"
						+ "产生异常尝试回滚时产生异常" + e1);
			}
			e.printStackTrace();
			throw new LibraryException(DateTimeUtil.getNow() + "执行BookDAO接口实现类BookDAOImpl中"
					+ "insertBook新增图书方法时产生异常" + e);
		}finally {
			try {
				DBUtils.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new LibraryException(DateTimeUtil.getNow() + "执行BookDAO接口实现类BookDAOImpl中"
						+ "insertBook新增图书方法中尝试关闭纾解连接时产生异常" + e);
			}
		}
		return result;
	}

	@Override
	public Integer deleteBook(Integer id) {
		Integer result = 0;
		if(id == null) {
			return result;
		}
		
		String sql = "DELETE FROM t_book WHERE id = ?";
		List<Object> params = new ArrayList<>();
		params.add(id);
		
		try {
			DBUtils.connect();
			result = DBUtils.execute(sql, params);
			DBUtils.commit();
		}catch(Exception e) {
			try {
				DBUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new LibraryException(DateTimeUtil.getNow() + "执行BookDAO接口实现类BookDAOImpl中"
						+ "deleteBook删除图书方法出现异常，尝试回滚时产生异常" + e1);
			}
			e.printStackTrace();
			throw new LibraryException(DateTimeUtil.getNow() + "执行BookDAO接口实现类BookDAOImpl中"
					+ "deleteBook删除图书方法时产生异常" + e);
		}finally {
			try {
				DBUtils.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new LibraryException(DateTimeUtil.getNow() + "执行BookDAO接口实现类BookDAOImpl中"
						+ "deleteBook删除图书方法后，尝试关闭数据库链接时产生异常" + e);
			}
		}
		
		return result;
	}

	@Override
	public Integer updateBook(Book book) {
		Integer result = 0;
		if(book == null) {
			return result;
		}
		
		String sql = "UPDATE t_book SET bname=?,lang=?,num=?,lend_num=?,remain_num=?,author=?,publishing=? WHERE id=?";
		List<Object> params = new ArrayList<>();
		params.add(book.getBname());
		params.add(book.getLang());
		params.add(book.getNum());
		params.add(book.getLend_num());
		params.add(book.getRemain_num());
		params.add(book.getAuthor());
		params.add(book.getPublishing());
		params.add(book.getId());
		
		try {
			DBUtils.connect();
			result = DBUtils.execute(sql, params);
			DBUtils.close();
		}catch(Exception e) {
			try {
				DBUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new LibraryException(DateTimeUtil.getNow() + "执行BookDAO接口实现类BookDAOImpl中"
						+ "updateBook修改图书信息方法产生异常后尝试回滚时产生异常" + e1);
			}
			e.printStackTrace();
			throw new LibraryException(DateTimeUtil.getNow() + "执行BookDAO接口实现类BookDAOImpl中"
					+ "updateBook修改图书信息方法时产生异常" + e);
		}finally {
			try {
				DBUtils.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new LibraryException(DateTimeUtil.getNow() + "执行BookDAO接口实现类BookDAOImpl中"
						+ "updateBook修改图书信息方法后，尝试关闭数据库链接时产生异常" + e);
			}
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> searchBook(Book book) {
		List<Map<String, Object>> result = null;
		if(book == null) {
			return result;
		}
		
		String sql = "SELECT id,bname,lang,num,lend_num,remain_num,author,publishing FROM t_book WHERE 1=1 ";
		List<Object> params = new ArrayList<>();
		result = new ArrayList<>();
		
		if(book.getId() != null) {
			params.add(book.getId());
			sql += " AND id=?";
		}
		if(book.getBname() != null) {
			params.add("%" + book.getBname() + "%");
			sql += " AND banem LIKE ?";
		}
		if(book.getLang() != null) {
			params.add(book.getLang());
			sql += " AND lang=? ";
		}
		if(book.getAuthor() != null) {
			params.add("%" + book.getAuthor() + "%");
			sql += " AND author LIKE ? ";
		}
		if(book.getPublishing() != null) {
			params.add("%" + book.getPublishing() + "%");
			sql += " AND publishing LIKE ?";
		}
		
		try {
			DBUtils.connect();
			result = DBUtils.executeQuery(sql, params);
		}catch(Exception e) {
			e.printStackTrace();
			throw new LibraryException(DateTimeUtil.getNow() + "执行BookDAO接口实现类BookDAOImpl中"
					+ "searchBook查询图书信息方法时产生异常" + e);
		}finally {
			try {
				DBUtils.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new LibraryException(DateTimeUtil.getNow() + "执行BookDAO接口实现类BookDAOImpl中"
						+ "searchBook查询图书信息方法后，在尝试断开数据库链接时产生异常" + e);
			}
		}
		
		return result;
	}

	@Override
	public List<Map<String, Object>> searchBookByType(String tname) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> result = null;
		if(tname == null) {
			return result;
		}
		
		String sql = "SELECT id,bname,lang,num,lend_num,remain_num,author,publishing "
				+ " FROM t_book,t_book_type,t_type"
				+ "WHERE b.id=bt.bid AND bt.tid=t.id ";
		List<Object> params = new ArrayList<>();
		params.add(tname);
		
		try {
			DBUtils.connect();
			result = DBUtils.executeQuery(sql, params);
		}catch(Exception e) {
			e.printStackTrace();
			throw new LibraryException(DateTimeUtil.getNow() + "执行BookDAO接口实现类BookDAOImpl中"
					+ "searchBookByType依赖分类查询图书信息方法时产生异常" + e);
		}finally {
			try {
				DBUtils.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new LibraryException(DateTimeUtil.getNow() + "执行BookDAO接口实现类BookDAOImpl中"
						+ "searchBookByType依赖分类查询图书信息方法后，尝试断开数据库链接时产生异常" + e);
			}
		}
		
		return result;
	}

}
