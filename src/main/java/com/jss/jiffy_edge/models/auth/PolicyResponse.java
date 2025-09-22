package com.jss.jiffy_edge.models.auth;

import java.util.List;

import com.jss.jiffy_edge.dao.entities.auth.AccessPolicyDetails;
import com.jss.jiffy_edge.dao.entities.auth.ServiceDetails;

public class PolicyResponse {
	private Long policyId;
	private String policyName;
	private String policyDescription;
	private ServiceDetails serviceDetails;
	private List<String> permissions;
	private int menuId;
	private String menuName;
	private String menuIcon;
	private String menuNameToDisplay;
	private String hintText;
	private String menuDescp;
	private int menuLevel;
	private boolean hasChild;
	private boolean isChildMenu;
	private int showOrder;
	private String targetLink;
	private String targetType;
	private AccessPolicyDetails.Status menuStatus;
	private String suAccessOnly;

	public PolicyResponse() {
		// No-argument constructor
	}

	// Getters
	public Long getPolicyId() {
		return policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public String getPolicyDescription() {
		return policyDescription;
	}

	public ServiceDetails getServiceDetails() {
		return serviceDetails;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public int getMenuId() {
		return menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public String getMenuNameToDisplay() {
		return menuNameToDisplay;
	}

	public String getHintText() {
		return hintText;
	}

	public String getMenuDescp() {
		return menuDescp;
	}

	public int getMenuLevel() {
		return menuLevel;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public boolean isChildMenu() {
		return isChildMenu;
	}

	public int getShowOrder() {
		return showOrder;
	}

	public String getTargetLink() {
		return targetLink;
	}

	public String getTargetType() {
		return targetType;
	}

	public AccessPolicyDetails.Status getMenuStatus() {
		return menuStatus;
	}

	public String getSuAccessOnly() {
		return suAccessOnly;
	}

	// Setters
	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public void setPolicyDescription(String policyDescription) {
		this.policyDescription = policyDescription;
	}

	public void setServiceDetails(ServiceDetails serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public void setMenuNameToDisplay(String menuNameToDisplay) {
		this.menuNameToDisplay = menuNameToDisplay;
	}

	public void setHintText(String hintText) {
		this.hintText = hintText;
	}

	public void setMenuDescp(String menuDescp) {
		this.menuDescp = menuDescp;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public void setChildMenu(boolean childMenu) {
		isChildMenu = childMenu;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	public void setTargetLink(String targetLink) {
		this.targetLink = targetLink;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public void setMenuStatus(AccessPolicyDetails.Status menuStatus) {
		this.menuStatus = menuStatus;
	}

	public void setSuAccessOnly(String suAccessOnly) {
		this.suAccessOnly = suAccessOnly;
	}
}