package com.platform.entity;

import java.io.Serializable;

/**
 * 规格表
 * 
 * @author qins
 * @email 506657803@qq.com
 * @date 2017-08-13 10:41:10
 */
public class SpecificationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//规范名称
	private String name;
	//排序
	private Integer sortOrder;
	
	private Long sellerId;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：规范名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：规范名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：排序
	 */
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSortOrder() {
		return sortOrder;
	}
	
	public Long getSellerId() {
		return sellerId;
	}
	
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
}
