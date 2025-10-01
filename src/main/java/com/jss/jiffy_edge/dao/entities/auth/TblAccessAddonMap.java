package com.jss.jiffy_edge.dao.entities.auth;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_access_addon_map")
public class TblAccessAddonMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addon_access_id")
    private int addonAccessId;

    @Column(name = "access_descp", columnDefinition = "TEXT")
    private String accessDescp;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private TblUserRoles role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private TblUsers user;

    @Enumerated(EnumType.STRING)
    @Column(name = "mapping_level")
    private MappingLevel mappingLevel;

    @ManyToOne
    @JoinColumn(name = "master_policy_id")
    private AccessPolicyMaster masterPolicy;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public enum MappingLevel {
        USER, ROLE
    }

    public enum Status {
        ACTIVE, INACTIVE
    }

    // Getters and Setters

    public int getAddonAccessId() {
        return addonAccessId;
    }

    public void setAddonAccessId(int addonAccessId) {
        this.addonAccessId = addonAccessId;
    }

    public String getAccessDescp() {
        return accessDescp;
    }

    public void setAccessDescp(String accessDescp) {
        this.accessDescp = accessDescp;
    }

    public TblUserRoles getRole() {
        return role;
    }

    public void setRole(TblUserRoles role) {
        this.role = role;
    }

    public TblUsers getUser() {
        return user;
    }

    public void setUser(TblUsers user) {
        this.user = user;
    }

    public MappingLevel getMappingLevel() {
        return mappingLevel;
    }

    public void setMappingLevel(MappingLevel mappingLevel) {
        this.mappingLevel = mappingLevel;
    }

    public AccessPolicyMaster getMasterPolicy() {
        return masterPolicy;
    }

    public void setMasterPolicy(AccessPolicyMaster masterPolicy) {
        this.masterPolicy = masterPolicy;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}