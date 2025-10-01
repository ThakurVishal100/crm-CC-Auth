package com.jss.jiffy_edge.services.auth;

import com.jss.jiffy_edge.dao.entities.auth.AccessPolicyMaster;
import com.jss.jiffy_edge.dao.entities.auth.TblAccessAddonMap;
import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import com.jss.jiffy_edge.dao.entities.auth.TblUsers;
import com.jss.jiffy_edge.dao.repo.auth.AccessPolicyMasterRepository;
import com.jss.jiffy_edge.dao.repo.auth.TblAccessAddonMapRepository;
import com.jss.jiffy_edge.dao.repo.auth.TblUserRolesRepository;
import com.jss.jiffy_edge.dao.repo.auth.TblUsersRepository;
import com.jss.jiffy_edge.models.auth.AddonPolicyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddonPolicyServiceImpl implements AddonPolicyService {

    @Autowired
    private TblAccessAddonMapRepository addonMapRepository;

    @Autowired
    private TblUserRolesRepository userRolesRepository;

    @Autowired
    private TblUsersRepository usersRepository;

    @Autowired
    private AccessPolicyMasterRepository masterPolicyRepository;

    @Override
    public List<TblAccessAddonMap> getAllAddonPolicies() {
        return addonMapRepository.findAll();
    }

    @Override
    public TblAccessAddonMap createAddonPolicy(AddonPolicyRequest request) {
        TblAccessAddonMap entity = new TblAccessAddonMap();
        setAddonPolicyFields(entity, request);
        entity.setCreationDate(LocalDateTime.now());
        return addonMapRepository.save(entity);
    }

    @Override
    public TblAccessAddonMap updateAddonPolicy(Integer addonPolicyId, AddonPolicyRequest request) {
        TblAccessAddonMap entity = addonMapRepository.findById(addonPolicyId)
                .orElseThrow(() -> new RuntimeException("Addon policy not found with id: " + addonPolicyId));
        setAddonPolicyFields(entity, request);
        entity.setLastUpdate(LocalDateTime.now());
        return addonMapRepository.save(entity);
    }

    @Override
    public void deleteAddonPolicy(Integer addonPolicyId) {
        TblAccessAddonMap entity = addonMapRepository.findById(addonPolicyId)
                .orElseThrow(() -> new RuntimeException("Addon policy not found with id: " + addonPolicyId));
        entity.setStatus(TblAccessAddonMap.Status.INACTIVE);
        entity.setLastUpdate(LocalDateTime.now());
        addonMapRepository.save(entity);
    }

    private void setAddonPolicyFields(TblAccessAddonMap entity, AddonPolicyRequest request) {
        entity.setAccessDescp(request.getAccessDescp());
        entity.setMappingLevel(request.getMappingLevel());
        entity.setStatus(request.getStatus());

        if (request.getRoleId() != null) {
            TblUserRoles role = userRolesRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found with id: " + request.getRoleId()));
            entity.setRole(role);
        }

        if (request.getUserId() != null) {
            TblUsers user = usersRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));
            entity.setUser(user);
        }

        if (request.getMasterPolicyId() != null) {
            AccessPolicyMaster masterPolicy = masterPolicyRepository.findById(request.getMasterPolicyId())
                    .orElseThrow(() -> new RuntimeException("Master policy not found with id: " + request.getMasterPolicyId()));
            entity.setMasterPolicy(masterPolicy);
        }
    }
}