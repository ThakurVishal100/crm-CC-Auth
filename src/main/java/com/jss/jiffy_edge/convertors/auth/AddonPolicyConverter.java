package com.jss.jiffy_edge.convertors.auth;

import com.jss.jiffy_edge.dao.entities.auth.AccessPolicyMaster;
import com.jss.jiffy_edge.dao.entities.auth.TblAccessAddonMap;
import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import com.jss.jiffy_edge.dao.entities.auth.TblUsers;
import com.jss.jiffy_edge.dao.repo.auth.AccessPolicyMasterRepository;
import com.jss.jiffy_edge.dao.repo.auth.TblUserRolesRepository;
import com.jss.jiffy_edge.dao.repo.auth.TblUsersRepository;
import com.jss.jiffy_edge.models.auth.AddonPolicyRequest;
import com.jss.jiffy_edge.models.auth.AddonPolicyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddonPolicyConverter {

    @Autowired
    private TblUserRolesRepository userRolesRepository;

    @Autowired
    private TblUsersRepository usersRepository;

    @Autowired
    private AccessPolicyMasterRepository masterPolicyRepository;

    public TblAccessAddonMap toEntity(AddonPolicyRequest request) {
        TblAccessAddonMap entity = new TblAccessAddonMap();
        updateEntityFromRequest(entity, request);
        return entity;
    }

    public void updateEntityFromRequest(TblAccessAddonMap entity, AddonPolicyRequest request) {
        entity.setAccessDescp(request.getAccessDescp());
        entity.setMappingLevel(request.getMappingLevel());
        entity.setStatus(request.getStatus());

        if (request.getRoleId() != null) {
            TblUserRoles role = userRolesRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found with id: " + request.getRoleId()));
            entity.setRole(role);
        } else {
            entity.setRole(null);
        }

        if (request.getUserId() != null) {
            TblUsers user = usersRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));
            entity.setUser(user);
        } else {
            entity.setUser(null);
        }

        if (request.getMasterPolicyId() != null) {
            AccessPolicyMaster masterPolicy = masterPolicyRepository.findById(request.getMasterPolicyId())
                    .orElseThrow(() -> new RuntimeException("Master policy not found with id: " + request.getMasterPolicyId()));
            entity.setMasterPolicy(masterPolicy);
        } else {
            entity.setMasterPolicy(null);
        }
    }

    public AddonPolicyResponse toResponse(TblAccessAddonMap entity) {
        AddonPolicyResponse response = new AddonPolicyResponse();
        response.setAddonAccessId(entity.getAddonAccessId());
        response.setAccessDescp(entity.getAccessDescp());
        response.setMappingLevel(entity.getMappingLevel());
        response.setCreationDate(entity.getCreationDate());
        response.setLastUpdate(entity.getLastUpdate());
        response.setStatus(entity.getStatus());

        if (entity.getRole() != null) {
            response.setRoleId(entity.getRole().getRoleId());
            response.setRoleName(entity.getRole().getRoleName());
        }

        if (entity.getUser() != null) {
            response.setUserId(entity.getUser().getUserId());
            response.setUserName(entity.getUser().getName());
        }

        if (entity.getMasterPolicy() != null) {
            response.setMasterPolicyId(entity.getMasterPolicy().getMasterPolicyId());
            response.setMasterPolicyName(entity.getMasterPolicy().getPolicyName());
        }

        return response;
    }

    public List<AddonPolicyResponse> toResponseList(List<TblAccessAddonMap> entities) {
        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }
}

