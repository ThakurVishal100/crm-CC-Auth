package com.jss.jiffy_edge.dao.repo.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jss.jiffy_edge.dao.entities.auth.ServiceDetails;

@Repository
public interface ServiceDetailsRepository extends JpaRepository<ServiceDetails, Integer> {
	
    Optional<ServiceDetails> findByServiceName(String serviceName);
    List<ServiceDetails> findByStatus(ServiceDetails.Status status);

}
