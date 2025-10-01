package com.jss.jiffy_edge.models.auth;

import com.jss.jiffy_edge.dao.entities.auth.ServiceDetails.ServiceCategory;
import com.jss.jiffy_edge.dao.entities.auth.ServiceDetails.Status;

public class ServiceDetailsRequest {

	private ServiceCategory serviceCatg;
	private String serviceName;
	private String serviceDescp;
	private Status status;
	private String parentServiceName;



	public ServiceCategory getServiceCatg() {
		return serviceCatg;
	}

	public void setServiceCatg(ServiceCategory serviceCatg) {
		this.serviceCatg = serviceCatg;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getParentServiceName() {
		return parentServiceName;
	}

	public void setParentServiceName(String parentServiceName) {
		this.parentServiceName = parentServiceName;
	}

}