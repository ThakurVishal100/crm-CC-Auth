package com.jss.jiffy_edge.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jss.jiffy_edge.convertors.auth.UserConvertor;
import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import com.jss.jiffy_edge.dao.repo.auth.TblUserRolesRepository;
import com.jss.jiffy_edge.dao.repo.auth.TblUsersRepository;
import com.jss.jiffy_edge.models.auth.UserResponse;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TblUsersRepository userRepository;

    @Autowired
    private UserConvertor userConvertor;

    @Autowired
    private TblUserRolesRepository roleRepository;

    @Override
    public List<TblUserRoles> getAllRoles() {
        return roleRepository.findAll();
    }
    
    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userConvertor::entityToResponse)
                .collect(Collectors.toList());
    }
}