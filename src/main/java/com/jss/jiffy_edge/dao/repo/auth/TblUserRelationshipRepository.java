package com.jss.jiffy_edge.dao.repo.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblUserRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TblUserRelationshipRepository extends JpaRepository<TblUserRelationship, Long> {
    boolean existsByManager_UserIdAndSubordinate_UserId(Integer managerId, Integer subordinateId);
    List<TblUserRelationship> findByManager_UserId(Integer managerId);
}