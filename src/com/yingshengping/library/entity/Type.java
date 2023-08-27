package com.yingshengping.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {

	private Integer id;				//主键，类型编号
	
	private String tname;			//书籍类型
	
}
