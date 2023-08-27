package com.yingshengping.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	private Integer id;				//主键，书籍编号
	
	private String bname;			//书名
	private Integer lang;			//书籍语言，0-中文，1-外文
	private Integer num;			//图书总数
	private Integer lend_num;		//借出数量
	private Integer remain_num;		//归还数量
	private String author;			//作者
	private String publishing;		//出版社
	
}
