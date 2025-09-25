package com.jss.jiffy_edge.dao.repo.auth;

import com.jss.jiffy_edge.dao.entities.auth.AccessPolicyMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessPolicyMasterRepository extends JpaRepository<AccessPolicyMaster, Integer> {
    List<AccessPolicyMaster> findByAvlForSuonlyNot(Integer value);

    List<AccessPolicyMaster> findByCategory(AccessPolicyMaster.Category category);
}