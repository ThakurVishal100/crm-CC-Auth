package com.jss.jiffy_edge.services.auth;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jss.jiffy_edge.convertors.auth.UserConvertor;
import com.jss.jiffy_edge.dao.entities.auth.TblUsers;
import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import com.jss.jiffy_edge.dao.repo.auth.TblUserRolesRepository;
import com.jss.jiffy_edge.dao.repo.auth.TblUsersRepository;

import com.jss.jiffy_edge.models.auth.UserLoginRequest;
import com.jss.jiffy_edge.models.auth.UserResponse;
import com.jss.jiffy_edge.models.auth.UserSignupRequest;
import com.jss.jiffy_edge.models.auth.UserUpdateRequest;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private TblUsersRepository userRepository;

	@Autowired
	private TblUserRolesRepository userRoleRepository;

	@Autowired
	private UserConvertor userConvertor;

	@Override
	public UserResponse signup(UserSignupRequest request) {
		TblUserRoles userRole = userRoleRepository.findById(5)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));

		if (userRole.getStatus() != TblUserRoles.UserStatus.ACTIVE) {
			throw new RuntimeException("The assigned role is not active.");
		}

		TblUsers entity = userConvertor.signupRequestToEntity(request);
		entity.setStatus(TblUsers.UserStatus.ACTIVE);
		entity.setRole(userRole);
		entity.setCreationDate(new Date());
		entity.setLastUpdate(new Date());
		entity.setUserCatg(TblUsers.UserCategory.EXTERNAL_USER);

		entity = userRepository.save(entity);
		return userConvertor.entityToResponse(entity);
	}

	@Override
	public UserResponse login(UserLoginRequest request) {
		TblUsers entity = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (entity.getStatus() != TblUsers.UserStatus.ACTIVE) {
			throw new RuntimeException("User account is not active.");
		}

		if (entity.getRole().getStatus() != TblUserRoles.UserStatus.ACTIVE) {
			throw new RuntimeException("User role is not active.");
		}

		if (!entity.getPassword().equals(request.getPassword())) {
			throw new RuntimeException("Invalid credentials");
		}

		return userConvertor.entityToResponse(entity);
	}

	@Override
	public UserResponse updateUser(Integer userId, UserUpdateRequest request) {
		TblUsers user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

		userConvertor.updateEntityFromRequest(user, request);

		if (request.getRoleId() != null) {
			TblUserRoles role = userRoleRepository.findById(request.getRoleId())
					.orElseThrow(() -> new RuntimeException("Role not found with id: " + request.getRoleId()));

			if (role.getStatus() != TblUserRoles.UserStatus.ACTIVE) {
				throw new RuntimeException("Cannot assign an inactive role.");
			}
			user.setRole(role);
		}
		user.setLastUpdate(new Date());
		TblUsers updatedUser = userRepository.save(user);
		return userConvertor.entityToResponse(updatedUser);
	}

	@Override
	public void deactivateUser(Integer userId) {
		TblUsers user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

		user.setStatus(TblUsers.UserStatus.INACTIVE);

		user.setLastUpdate(new Date());

		userRepository.save(user);
	}

}