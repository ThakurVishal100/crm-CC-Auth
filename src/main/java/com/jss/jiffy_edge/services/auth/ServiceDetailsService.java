package com.jss.jiffy_edge.services.auth;

import java.time.LocalDateTime;
import java.util.Optional;

import com.jss.jiffy_edge.convertors.auth.ServiceDetailsConverter;
import org.springframework.stereotype.Service;

import com.jss.jiffy_edge.dao.entities.auth.ServiceDetails;
import com.jss.jiffy_edge.dao.repo.auth.ServiceDetailsRepository;
import com.jss.jiffy_edge.models.auth.ServiceDetailsRequest;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ServiceDetailsService {

	private final ServiceDetailsRepository serviceDetailsRepository;
	private final ServiceDetailsConverter serviceDetailsConverter;


	public ServiceDetailsService(ServiceDetailsRepository serviceDetailsRepository, ServiceDetailsConverter serviceDetailsConverter) {
		this.serviceDetailsRepository = serviceDetailsRepository;
		this.serviceDetailsConverter = serviceDetailsConverter;
	}

	public ServiceDetails createService(ServiceDetailsRequest request) {
		ServiceDetails newService = serviceDetailsConverter.toEntity(request);

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
		newService.setCreatedOn(LocalDateTime.now());

		return serviceDetailsRepository.save(newService);
	}

	public ServiceDetails updateService(Integer id, ServiceDetailsRequest request) {
		ServiceDetails existingService = serviceDetailsRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Service with ID " + id + " not found."));

		ServiceDetails updatedService = serviceDetailsConverter.toEntity(request);
		updatedService.setServiceId(existingService.getServiceId()); // Keep original ID
		updatedService.setCreatedOn(existingService.getCreatedOn()); // Keep original creation date

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

		updatedService.setParentServiceId(parentId);
		updatedService.setUpdatedOn(LocalDateTime.now());

		return serviceDetailsRepository.save(updatedService);
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