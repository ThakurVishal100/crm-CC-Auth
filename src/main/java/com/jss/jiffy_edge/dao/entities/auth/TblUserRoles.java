package com.jss.jiffy_edge.dao.entities.auth;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jss.jiffy_edge.dao.entities.auth.TblUsers.UserCategory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tbl_user_roles")
public class TblUserRoles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "role_name", nullable = false, length = 100, unique = true)
	private String roleName;

	@Enumerated(EnumType.STRING)
	@Column(name = "role_catg", nullable = false)
	private UserCategory roleCatg;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<TblUsers> users;

	@Column(name = "role_desc", columnDefinition = "TEXT")
	private String roleDesc;

	@Enumerated(EnumType.STRING)
	@Column(name = "owner_type", nullable = false)
	private OwnerType ownerType;

	@Column(name = "owner_id")
	private Integer ownerId;
	
	@Column(name = "defa_access_policy_id")
    private Integer defaAccessPolicyId;

	public enum UserStatus {
		ACTIVE, INACTIVE, DELETED
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private UserStatus status;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public UserCategory getRoleCatg() {
		return roleCatg;
	}

	public void setRoleCatg(UserCategory roleCatg) {
		this.roleCatg = roleCatg;
	}

	public List<TblUsers> getUsers() {
		return users;
	}

	public void setUsers(List<TblUsers> users) {
		this.users = users;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public OwnerType getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(OwnerType ownerType) {
		this.ownerType = ownerType;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
    public Integer getDefaAccessPolicyId() {
        return defaAccessPolicyId;
    }

    public void setDefaAccessPolicyId(Integer defaAccessPolicyId) {
        this.defaAccessPolicyId = defaAccessPolicyId;
    }


	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date")
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update")
	private Date lastUpdate;
}