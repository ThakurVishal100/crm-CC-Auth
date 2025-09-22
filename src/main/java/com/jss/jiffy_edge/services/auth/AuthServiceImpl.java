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

		TblUsers entity = new TblUsers();
		entity.setName(request.getName());
		entity.setEmail(request.getEmail());
		entity.setPassword(request.getPassword());
		entity.setMobile(request.getMobile());
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

		if (!entity.getPassword().equals(request.getPassword())) {
			throw new RuntimeException("Invalid credentials");
		}

		return userConvertor.entityToResponse(entity);
	}

}
