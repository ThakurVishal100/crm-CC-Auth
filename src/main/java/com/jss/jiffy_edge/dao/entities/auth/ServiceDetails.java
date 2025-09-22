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
@Table(name = "tbl_service_details")
public class ServiceDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id")
	private int serviceId;

	@Enumerated(EnumType.STRING)
	@Column(name = "service_catg")
	private ServiceCategory serviceCatg;

	@Column(name = "parent_service_id", columnDefinition = "INT DEFAULT 0")
	private Integer parentServiceId;

	@Column(name = "service_name", nullable = false, length = 255)
	private String serviceName;

	@Column(name = "service_descp", nullable = false, length = 255)
	private String serviceDescp;

	@Column(name = "created_on", nullable = false)
	private LocalDateTime createdOn;

	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", columnDefinition = "varchar(255) default 'ACTIVE'")
	private Status status;

	public enum ServiceCategory {
		SYSTEM, SERVICE, FEATURE
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public ServiceCategory getServiceCatg() {
		return serviceCatg;
	}

	public void setServiceCatg(ServiceCategory serviceCatg) {
		this.serviceCatg = serviceCatg;
	}

	public Integer getParentServiceId() {
		return parentServiceId;
	}

	public void setParentServiceId(Integer parentServiceId) {
		this.parentServiceId = parentServiceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDescp() {
		return serviceDescp;
	}

	public void setServiceDescp(String serviceDescp) {
		this.serviceDescp = serviceDescp;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {
		ACTIVE, INACTIVE, DELETED
	}

}
