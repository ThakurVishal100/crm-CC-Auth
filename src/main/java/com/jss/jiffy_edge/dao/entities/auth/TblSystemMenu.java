package com.jss.jiffy_edge.dao.entities.auth;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_system_menu")
public class TblSystemMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_id")
	private Integer menuId;

	@Column(name = "menu_name", nullable = false, length = 50)
	private String menuName;

	@Column(name = "visible_name", nullable = false, length = 50)
	private String displayName;

	@Column(name = "hint_text", length = 250)
	private String description; // mapped with frontend "Description"

	@Column(name = "menu_descp", length = 250)
	private String menuDescp;

	@Column(name = "menu_level", nullable = false)
	private Integer menuLevel;

	@Column(name = "parent_menu_id")
	private Integer parentMenuId;

	@Column(name = "has_child", nullable = false)
	private Boolean hasChildren;

	@Column(name = "is_child_menu", nullable = false)
	private Boolean isChildMenu;

	@Column(name = "show_order", nullable = false)
	private Integer showOrder;

	@Column(name = "menu_icon", length = 500)
	private String menuIcon;

	@Column(name = "target_link", nullable = false, length = 500)
	private String targetLink;

	@Enumerated(EnumType.STRING)
	@Column(name = "target_type")
	private TargetType targetType;

	@Column(name = "service_id", nullable = false)
	private Integer serviceId;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private Status status;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "last_updated")
	private LocalDateTime lastUpdated;

	@Enumerated(EnumType.STRING)
	@Column(name = "su_access_only")
	private YesNo superUserOnly;

	// --- ENUMS ---
	public enum TargetType {
		LINK, NONE
	}

	public enum Status {
		ACTIVE, INACTIVE
	}

	public enum YesNo {
		YES, NO
	}

	// --- Getters and Setters ---
	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

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

	public String getMenuDescp() {
		return menuDescp;
	}

	public void setMenuDescp(String menuDescp) {
		this.menuDescp = menuDescp;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Integer getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(Integer parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public Boolean getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(Boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public Boolean getIsChildMenu() {
		return isChildMenu;
	}

	public void setIsChildMenu(Boolean isChildMenu) {
		this.isChildMenu = isChildMenu;
	}

	public Integer getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getTargetLink() {
		return targetLink;
	}

	public void setTargetLink(String targetLink) {
		this.targetLink = targetLink;
	}

	public TargetType getTargetType() {
		return targetType;
	}

	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public YesNo getSuperUserOnly() {
		return superUserOnly;
	}

	public void setSuperUserOnly(YesNo superUserOnly) {
		this.superUserOnly = superUserOnly;
	}
}