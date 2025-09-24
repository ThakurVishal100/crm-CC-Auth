package com.jss.jiffy_edge.services.auth;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jss.jiffy_edge.dao.entities.auth.ServiceDetails;
import com.jss.jiffy_edge.dao.repo.auth.ServiceDetailsRepository;
import com.jss.jiffy_edge.models.auth.ServiceDetailsRequest;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ServiceDetailsService {

	private final ServiceDetailsRepository serviceDetailsRepository;

	public ServiceDetailsService(ServiceDetailsRepository serviceDetailsRepository) {
		this.serviceDetailsRepository = serviceDetailsRepository;
	}

	public ServiceDetails createService(ServiceDetailsRequest request) {
		ServiceDetails newService = new ServiceDetails();

		Integer parentId = 0;

		if (request.getParentServiceName() != null && !request.getParentServiceName().trim().isEmpty()
				&& !request.getParentServiceName().equalsIgnoreCase("No Parent")) {
			Optional<ServiceDetails> parentServiceOptional = serviceDetailsRepository
					.findByServiceName(request.getParentServiceName());

			if (parentServiceOptional.isPresent()) {
				parentId = parentServiceOptional.get().getServiceId();
			} else {
				throw new EntityNotFoundException(
						"Parent service with name '" + request.getParentServiceName() + "' not found.");
			}
		}

		newService.setParentServiceId(parentId);

		newService.setServiceCatg(request.getServiceCatg());
		newService.setServiceName(request.getServiceName());
		newService.setServiceDescp(request.getServiceDescp());
		newService.setStatus(request.getStatus());
		newService.setCreatedOn(LocalDateTime.now());

		return serviceDetailsRepository.save(newService);
	}

	public ServiceDetails updateService(Integer id, ServiceDetailsRequest request) {
		ServiceDetails existingService = serviceDetailsRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Service with ID " + id + " not found."));

		Integer parentId = 0;

		if (request.getParentServiceName() != null && !request.getParentServiceName().trim().isEmpty()
				&& !request.getParentServiceName().equalsIgnoreCase("No Parent")) {
			Optional<ServiceDetails> parentServiceOptional = serviceDetailsRepository
					.findByServiceName(request.getParentServiceName());

			if (parentServiceOptional.isPresent()) {
				parentId = parentServiceOptional.get().getServiceId();
			} else {
				throw new EntityNotFoundException(
						"Parent service with name '" + request.getParentServiceName() + "' not found.");
			}
		}

		existingService.setParentServiceId(parentId);

		existingService.setServiceCatg(request.getServiceCatg());
		existingService.setServiceName(request.getServiceName());
		existingService.setServiceDescp(request.getServiceDescp());
		existingService.setStatus(request.getStatus());
		existingService.setUpdatedOn(LocalDateTime.now());

		return serviceDetailsRepository.save(existingService);
	}

    public ServiceDetails deleteService(Integer serviceId) {
		ServiceDetails service=serviceDetailsRepository.findById(serviceId).
				orElseThrow(()->new RuntimeException("ServiceId not found with id:"+serviceId));
		service.setStatus(ServiceDetails.Status.INACTIVE);
		service.setUpdatedOn(LocalDateTime.now());
		serviceDetailsRepository.save(service);
		return service;
	}
}
