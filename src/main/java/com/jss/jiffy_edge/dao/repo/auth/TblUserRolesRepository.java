package com.jss.jiffy_edge.dao.repo.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;

@Repository
public interface TblUserRolesRepository extends JpaRepository<TblUserRoles, Integer> {

	Optional<TblUserRoles> findById(Integer roleId);

	Optional<TblUserRoles> findByRoleName(String roleName);

}

