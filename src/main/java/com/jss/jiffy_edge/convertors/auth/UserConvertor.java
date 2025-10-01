package com.jss.jiffy_edge.convertors.auth;

import org.springframework.stereotype.Component;

import com.jss.jiffy_edge.dao.entities.auth.TblUsers;
import com.jss.jiffy_edge.models.auth.UserResponse;

@Component
public class  UserConvertor {

	public UserResponse entityToResponse(TblUsers entity) {
		UserResponse response = new UserResponse();
		response.setUserId(entity.getUserId());
		response.setName(entity.getName());
		response.setEmail(entity.getEmail());
		response.setMobile(entity.getMobile());
		response.setRoleId(entity.getRole().getRoleId());
		response.setStatus(UserResponse.UserStatus.valueOf(entity.getStatus().name()));

		if (entity.getRole() != null) {
			response.setRoleName(entity.getRole().getRoleName());
		}
		return response;
	}

}
