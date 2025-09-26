package com.jss.jiffy_edge.models.auth;

import com.jss.jiffy_edge.dao.entities.auth.AccessPolicyMaster;

public class MasterPolicyRequest {

    private String policyName;
    private String descp;
    private AccessPolicyMaster.Status status;
    private AccessPolicyMaster.Category category;
    private Integer avlForSuonly;

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public AccessPolicyMaster.Status getStatus() {
        return status;
    }

    public void setStatus(AccessPolicyMaster.Status status) {
        this.status = status;
    }

    public AccessPolicyMaster.Category getCategory() {
        return category;
    }

    public void setCategory(AccessPolicyMaster.Category category) {
        this.category = category;
    }

    public Integer getAvlForSuonly() {
        return avlForSuonly;
    }

    public void setAvlForSuonly(Integer avlForSuonly) {
        this.avlForSuonly = avlForSuonly;
    }
}