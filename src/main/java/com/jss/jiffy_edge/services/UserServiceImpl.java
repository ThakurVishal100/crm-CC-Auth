package com.jss.jiffy_edge.services;

import com.jss.jiffy_edge.convertors.auth.UserConvertor;
import com.jss.jiffy_edge.dao.entities.auth.TblUsers;
import com.jss.jiffy_edge.dao.entities.auth.TblUsers.UserCategory;
import com.jss.jiffy_edge.dao.repo.auth.TblUsersRepository;
import com.jss.jiffy_edge.models.auth.CategorizedUsersResponse;
import com.jss.jiffy_edge.models.auth.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TblUsersRepository userRepository;

    @Autowired
    private UserConvertor userConvertor;

    private static final Integer SUPER_USER_ID = 1;

    @Override
    public ResponseEntity<?> getAllUsers(Integer requesterId) {
        TblUsers requester = userRepository.findById(requesterId)
                .orElseThrow(() -> new RuntimeException("Requester user not found with id: " + requesterId));

        if (requester.getUserCatg() == UserCategory.SYSTEM_USER) {

            List<UserResponse> systemUsers = userRepository.findByUserCatgAndUserIdNot(UserCategory.SYSTEM_USER, SUPER_USER_ID)
                    .stream()
                    .map(userConvertor::entityToResponse)
                    .collect(Collectors.toList());

            List<UserResponse> externalUsers = userRepository.findByUserCatgAndUserIdNot(UserCategory.EXTERNAL_USER, SUPER_USER_ID)
                    .stream()
                    .map(userConvertor::entityToResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new CategorizedUsersResponse(systemUsers, externalUsers));

        } else if (requester.getUserCatg() == UserCategory.EXTERNAL_USER) {

            List<UserResponse> externalUsers = userRepository.findByUserCatgAndUserIdNot(UserCategory.EXTERNAL_USER, SUPER_USER_ID)
                    .stream()
                    .map(userConvertor::entityToResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(externalUsers);

        } else {
            return ResponseEntity.status(403).body("User does not have a valid category to perform this action.");
        }
    }
}