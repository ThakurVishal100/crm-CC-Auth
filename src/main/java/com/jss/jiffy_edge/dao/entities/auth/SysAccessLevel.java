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
@Table(name = "tbl_sys_access_levels")
public class SysAccessLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sys_access_lvlid")
    private int sysAccessLvlId;

    @Enumerated(EnumType.STRING)
    @Column(name = "sys_access_lvlname", nullable = false)
    private SysAccessLvlName sysAccessLvlName;

    @Column(name = "sys_access_lvldescp")
    private String sysAccessLvlDescp;

    @Column(name = "creation_on")
    private LocalDateTime creationOn;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public enum SysAccessLvlName {
        READ, READ_WRITE, READ_WRITE_DELETE, READ_WRITE_DELETE_CREATE, SERVICE_OWNER, INSTANCE_ADMIN, SYSTEM_ADMIN, ALL_ACCESS
    }

    public enum Status {
        ACTIVE, INACTIVE
    }

    // Getters and Setters

    public int getSysAccessLvlId() {
        return sysAccessLvlId;
    }

    public void setSysAccessLvlId(int sysAccessLvlId) {
        this.sysAccessLvlId = sysAccessLvlId;
    }

    public SysAccessLvlName getSysAccessLvlName() {
        return sysAccessLvlName;
    }

    public void setSysAccessLvlName(SysAccessLvlName sysAccessLvlName) {
        this.sysAccessLvlName = sysAccessLvlName;
    }

    public String getSysAccessLvlDescp() {
        return sysAccessLvlDescp;
    }

    public void setSysAccessLvlDescp(String sysAccessLvlDescp) {
        this.sysAccessLvlDescp = sysAccessLvlDescp;
    }

    public LocalDateTime getCreationOn() {
        return creationOn;
    }

    public void setCreationOn(LocalDateTime creationOn) {
        this.creationOn = creationOn;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}