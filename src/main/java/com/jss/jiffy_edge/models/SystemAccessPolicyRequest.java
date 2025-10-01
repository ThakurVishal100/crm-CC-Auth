package com.jss.jiffy_edge.models;

import com.jss.jiffy_edge.dao.entities.auth.AccessPolicyDetails.Status;

public class SystemAccessPolicyRequest {

    private String accessName;
    private String accessDesc;
    private int masterPolicyId;
    private int serviceId;
    private Integer menuId;
    private int sysAccessLvlId;
    private Status status;



    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    public String getAccessDesc() {
        return accessDesc;
    }

    public void setAccessDesc(String accessDesc) {
        this.accessDesc = accessDesc;
    }

    public int getMasterPolicyId() {
        return masterPolicyId;
    }

    public void setMasterPolicyId(int masterPolicyId) {
        this.masterPolicyId = masterPolicyId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public int getSysAccessLvlId() {
        return sysAccessLvlId;
    }

    public void setSysAccessLvlId(int sysAccessLvlId) {
        this.sysAccessLvlId = sysAccessLvlId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}