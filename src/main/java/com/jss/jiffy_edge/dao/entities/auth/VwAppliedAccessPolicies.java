package com.jss.jiffy_edge.dao.entities.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "vw_applied_access_policies")
public class VwAppliedAccessPolicies {

    @Id
    @Column(name = "policy_id")
    private long policyId;

    @Column(name = "access_name")
    private String accessName;

    @Column(name = "access_desc")
    private String accessDesc;

    @Enumerated(EnumType.STRING)
    @Column(name = "policy_status")
    private AccessPolicyDetails.Status policyStatus;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Column(name = "master_policy_id")
    private int masterPolicyId;

    @Column(name = "policy_name")
    private String policyName;

    @Column(name = "master_policy_descp")
    private String masterPolicyDescp;

    @Enumerated(EnumType.STRING)
    @Column(name = "master_policy_status")
    private AccessPolicyMaster.Status masterPolicyStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "master_policy_category")
    private AccessPolicyMaster.Category masterPolicyCategory;

    @Column(name = "avl_for_suonly")
    private int avlForSuonly;

    @Column(name = "is_addon_policy")
    private String isAddonPolicy;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_desc")
    private String roleDesc;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_catg")
    private TblUsers.UserCategory roleCatg;

    @Enumerated(EnumType.STRING)
    @Column(name = "owner_type")
    private OwnerType ownerType;

    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "applied_master_policy_id")
    private int appliedMasterPolicyId;

    @Column(name = "sys_access_lvlid")
    private int sysAccessLvlid;

    @Enumerated(EnumType.STRING)
    @Column(name = "sys_access_lvlname")
    private SysAccessLevel.SysAccessLvlName sysAccessLvlname;

    @Column(name = "sys_access_lvldescp")
    private String sysAccessLvldescp;

    @Enumerated(EnumType.STRING)
    @Column(name = "access_level_status")
    private SysAccessLevel.Status accessLevelStatus;

    @Column(name = "service_id")
    private int serviceId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_descp")
    private String serviceDescp;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_catg")
    private ServiceDetails.ServiceCategory serviceCatg;

    @Column(name = "parent_service_id")
    private int parentServiceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_status")
    private ServiceDetails.Status serviceStatus;

    @Column(name = "menu_id")
    private int menuId;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_icon")
    private String menuIcon;

    @Column(name = "menu_name_to_display")
    private String menuNameToDisplay;

    @Column(name = "hint_text")
    private String hintText;

    @Column(name = "menu_descp")
    private String menuDescp;

    @Column(name = "menu_level")
    private int menuLevel;

    @Column(name = "has_child")
    private boolean hasChild;

    @Column(name = "is_child_menu")
    private boolean isChildMenu;

    @Column(name = "show_order")
    private int showOrder;

    @Column(name = "target_link")
    private String targetLink;

    @Column(name = "target_type")
    private String targetType; 

    @Enumerated(EnumType.STRING)
    @Column(name = "menu_status")
    private com.jss.jiffy_edge.dao.entities.auth.AccessPolicyDetails.Status menuStatus;

    @Column(name = "su_access_only")
    private String suAccessOnly;

    // Getters
    public long getPolicyId() {
        return policyId;
    }

    public String getAccessName() {
        return accessName;
    }

    public String getAccessDesc() {
        return accessDesc;
    }

    public AccessPolicyDetails.Status getPolicyStatus() {
        return policyStatus;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public int getMasterPolicyId() {
        return masterPolicyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public String getMasterPolicyDescp() {
        return masterPolicyDescp;
    }

    public AccessPolicyMaster.Status getMasterPolicyStatus() {
        return masterPolicyStatus;
    }

    public AccessPolicyMaster.Category getMasterPolicyCategory() {
        return masterPolicyCategory;
    }

    public int getAvlForSuonly() {
        return avlForSuonly;
    }

    public String getIsAddonPolicy() {
        return isAddonPolicy;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public TblUsers.UserCategory getRoleCatg() {
        return roleCatg;
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getAppliedMasterPolicyId() {
        return appliedMasterPolicyId;
    }

    public int getSysAccessLvlid() {
        return sysAccessLvlid;
    }

    public SysAccessLevel.SysAccessLvlName getSysAccessLvlname() {
        return sysAccessLvlname;
    }

    public String getSysAccessLvldescp() {
        return sysAccessLvldescp;
    }

    public SysAccessLevel.Status getAccessLevelStatus() {
        return accessLevelStatus;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceDescp() {
        return serviceDescp;
    }

    public ServiceDetails.ServiceCategory getServiceCatg() {
        return serviceCatg;
    }

    public int getParentServiceId() {
        return parentServiceId;
    }

    public ServiceDetails.Status getServiceStatus() {
        return serviceStatus;
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
}