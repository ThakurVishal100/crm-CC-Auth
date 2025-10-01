package com.jss.jiffy_edge.convertors.auth;

import com.jss.jiffy_edge.dao.entities.auth.ServiceDetails;
import com.jss.jiffy_edge.models.auth.ServiceDetailsRequest;
import org.springframework.stereotype.Component;

@Component
public class ServiceDetailsConverter {

    public ServiceDetails toEntity(ServiceDetailsRequest request) {
        ServiceDetails entity = new ServiceDetails();
        entity.setServiceCatg(request.getServiceCatg());
        entity.setServiceName(request.getServiceName());
        entity.setServiceDescp(request.getServiceDescp());
        entity.setStatus(request.getStatus());
        return entity;
    }
}