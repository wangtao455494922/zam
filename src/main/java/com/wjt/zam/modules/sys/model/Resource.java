package com.wjt.zam.modules.sys.model;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.github.pagehelper.PageInfo;

/**
 * @Description:资源-实体
 * @author: wangjintao
 * @date: 2018年6月24日 上午11:14:38
 */
@Alias(value = "Resource")
public class Resource extends PageInfo<Resource> {
	private static final long serialVersionUID = 7273235550364314498L;

	private String id; // 编号
	private String name; // 资源名称
	private String type; // 资源类型
	private String typeName; // 资源名称
	private String url; // 资源路径
	private String permission; // 权限字符串
	private String parentId; // 父编号
	private String parentResourceName;// 父资源名称
	private String parentIds; // 父编号列表
	private String available; // 是否有效
	private String availableName;// 是否有效名称
	private List<Resource> secondResources;// 二级资源菜单

	public String getAvailableName() {
		return availableName;
	}

	public void setAvailableName(String availableName) {
		this.availableName = availableName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getParentResourceName() {
		return parentResourceName;
	}

	public void setParentResourceName(String parentResourceName) {
		this.parentResourceName = parentResourceName;
	}

	public List<Resource> getSecondResources() {
		return secondResources;
	}

	public void setSecondResources(List<Resource> secondResources) {
		this.secondResources = secondResources;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Resource resource = (Resource) o;

		if (id != null ? !id.equals(resource.id) : resource.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Resource{" + "id=" + id + ", name='" + name + '\'' + ", type="
				+ type + ", permission='" + permission + '\'' + ", parentId="
				+ parentId + ", parentIds='" + parentIds + '\''
				+ ", available=" + available + '}';
	}
}
