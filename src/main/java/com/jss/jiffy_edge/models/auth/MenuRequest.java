package com.jss.jiffy_edge.models.auth;

public class MenuRequest {
	private String menuName;
	private String displayName;
	private String description;
	private Integer serviceId;
	private Integer menuLevel;
	private Integer showOrder;
	private String targetLink;
	private String targetType;
	private Boolean hasChildren;
	private String superUserOnly;
	private String status;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Integer getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}

	public String getTargetLink() {
		return targetLink;
	}

	public void setTargetLink(String targetLink) {
		this.targetLink = targetLink;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public Boolean getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(Boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public String getSuperUserOnly() {
		return superUserOnly;
	}

	public void setSuperUserOnly(String superUserOnly) {
		this.superUserOnly = superUserOnly;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}