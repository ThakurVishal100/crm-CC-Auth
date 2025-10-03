package com.jss.jiffy_edge.models.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblSystemMenu;

public class MenuResponse {
    private Integer menuId;
    private String menuName;
    private String displayName;
    private String description;
    private String menuDescp;
    private Integer menuLevel;
    private Integer parentMenuId;
    private Boolean hasChildren;
    private Boolean isChildMenu;
    private Integer showOrder;
    private String menuIcon;
    private String targetLink;
    private String targetType;
    private Integer serviceId;
    private String status;
    private String superUserOnly;


    public MenuResponse() {}


    public MenuResponse(TblSystemMenu menu) {
        this.menuId = menu.getMenuId();
        this.menuName = menu.getMenuName();
        this.displayName = menu.getDisplayName();
        this.description = menu.getMenuDescp();
        this.menuDescp = menu.getMenuDescp();
        this.menuLevel = menu.getMenuLevel();
        this.parentMenuId = menu.getParentMenuId();
        this.hasChildren = menu.getHasChildren();
        this.isChildMenu = menu.getIsChildMenu();
        this.showOrder = menu.getShowOrder();
        this.menuIcon = menu.getMenuIcon();
        this.targetLink = menu.getTargetLink();
        this.serviceId = menu.getServiceId();


        if (menu.getTargetType() != null) {
            this.targetType = menu.getTargetType().name();
        }
        if (menu.getStatus() != null) {
            this.status = menu.getStatus().name();
        }
        if (menu.getSuperUserOnly() != null) {
            this.superUserOnly = menu.getSuperUserOnly().name();
        }
    }



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

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuperUserOnly() {
        return superUserOnly;
    }

    public void setSuperUserOnly(String superUserOnly) {
        this.superUserOnly = superUserOnly;
    }
}
