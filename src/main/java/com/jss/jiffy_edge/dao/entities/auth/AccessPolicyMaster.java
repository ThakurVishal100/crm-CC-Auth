package com.jss.jiffy_edge.dao.entities.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tbl_access_policy_master")
public class AccessPolicyMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "master_policy_id")
	private int masterPolicyId;

	public int getMasterPolicyId() {
		return masterPolicyId;
	}

	public void setMasterPolicyId(int masterPolicyId) {
		this.masterPolicyId = masterPolicyId;
	}

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getAvlForSuonly() {
		return avlForSuonly;
	}

	public void setAvlForSuonly(Integer avlForSuonly) {
		this.avlForSuonly = avlForSuonly;
	}

	@Column(name = "policy_name", nullable = false, length = 100)
	private String policyName;

	@Column(name = "descp", columnDefinition = "TEXT")
	private String descp;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private Category category;

	@Column(name = "avl_for_suonly", columnDefinition = "INT DEFAULT 0")
	private Integer avlForSuonly;

	public enum Status {
		ACTIVE, INACTIVE
	}

	public enum Category {
		EXTERNAL, INTERNAL
	}
}
