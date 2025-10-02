package com.jss.jiffy_edge.services.auth;

import com.jss.jiffy_edge.convertors.auth.AddonPolicyConverter;
import com.jss.jiffy_edge.dao.entities.auth.TblAccessAddonMap;
import com.jss.jiffy_edge.dao.repo.auth.TblAccessAddonMapRepository;
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
    private AddonPolicyConverter addonPolicyConverter;

    @Override
    public List<TblAccessAddonMap> getAllAddonPolicies() {
        return addonMapRepository.findAll();
    }

    @Override
    public TblAccessAddonMap createAddonPolicy(AddonPolicyRequest request) {
        TblAccessAddonMap entity = addonPolicyConverter.toEntity(request);
        entity.setCreationDate(LocalDateTime.now());
        return addonMapRepository.save(entity);
    }

    @Override
    public TblAccessAddonMap updateAddonPolicy(Integer addonPolicyId, AddonPolicyRequest request) {
        TblAccessAddonMap entity = addonMapRepository.findById(addonPolicyId)
                .orElseThrow(() -> new RuntimeException("Addon policy not found with id: " + addonPolicyId));
        addonPolicyConverter.updateEntityFromRequest(entity, request);
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

    @Override
    public List<TblAccessAddonMap> getAddonPoliciesByRoleId(Integer roleId) {
        return addonMapRepository.findByRole_RoleId(roleId);
    }

    @Override
    public List<TblAccessAddonMap> getAddonPoliciesByUserId(Integer userId) {
        return addonMapRepository.findByUser_UserId(userId);
    }
}

