package com.jss.jiffy_edge.dao.repo.auth;

import com.jss.jiffy_edge.dao.entities.auth.VwAppliedAccessPolicies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VwAppliedAccessPoliciesRepository extends JpaRepository<VwAppliedAccessPolicies, Long> {
    List<VwAppliedAccessPolicies> findByRoleId(Integer roleId);
}