package com.wjt.zam.modules.sol.model;

import java.util.List;

/**
 * <p>
 * Description: 商品--实体
 * </p>
 * 
 * @author wjt create
 * @date 2018年8月29日 下午5:20:19
 */
public class Good{

	/**
	 * 商品编号
	 */
	private String id;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 商品分类名称
	 */
	private String catalogName;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 商品描述
	 */
	private String description;
	/**
	 * 图片名称
	 */
	private String picture;
	/**
	 * 排序
	 * */
	private String sort;
	
	/**
	 * 页数
	 * */
	private int start=1;
	/**
	 * 总页数
	 * */
	private Integer totalPage;
	
	/**
	 * 页码
	 * */
	private int rows=10;
	/**
	 * 总记录数
	 */
	private Integer totalSize;
	
	private List<Good> goodList;
	
	public List<Good> getGoodList() {
		return goodList;
	}

	public void setGoodList(List<Good> goodList) {
		this.goodList = goodList;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}
		
	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage() {
		if (totalSize % rows > 0) {
			this.totalPage = (totalSize/rows)+1;
		} else {
			this.totalPage = totalSize/rows;
		}
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}
