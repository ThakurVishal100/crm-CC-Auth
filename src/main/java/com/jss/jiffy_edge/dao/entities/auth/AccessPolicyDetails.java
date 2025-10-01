package com.jss.jiffy_edge.dao.entities.auth;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_access_policy_details")
public class AccessPolicyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_id")
    private long policyId;

    @Column(name = "access_name", nullable = false, length = 191, unique = true)
    private String accessName;

    @Column(name = "access_desc", columnDefinition = "TEXT")
    private String accessDesc;

    @ManyToOne
    @JoinColumn(name = "master_policy_id", nullable = false)
    private AccessPolicyMaster accessPolicyMaster;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceDetails serviceDetails;

    @ManyToOne
    @JoinColumn(name = "menu_id") // Added mapping for menu_id
    private TblSystemMenu menu;

    @ManyToOne
    @JoinColumn(name = "sys_access_lvlid", nullable = false)
    private SysAccessLevel sysAccessLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public enum Status {
        ACTIVE, INACTIVE, DELETED
    }

    // Getters and Setters
    public long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(long policyId) {
        this.policyId = policyId;
    }

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

    public AccessPolicyMaster getAccessPolicyMaster() {
        return accessPolicyMaster;
    }

    public void setAccessPolicyMaster(AccessPolicyMaster accessPolicyMaster) {
        this.accessPolicyMaster = accessPolicyMaster;
    }

    public ServiceDetails getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(ServiceDetails serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    public TblSystemMenu getMenu() {
        return menu;
    }

    public void setMenu(TblSystemMenu menu) {
        this.menu = menu;
    }

    public SysAccessLevel getSysAccessLevel() {
        return sysAccessLevel;
    }

    public void setSysAccessLevel(SysAccessLevel sysAccessLevel) {
        this.sysAccessLevel = sysAccessLevel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}