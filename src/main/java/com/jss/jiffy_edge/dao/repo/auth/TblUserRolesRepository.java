package com.jss.jiffy_edge.dao.repo.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jss.jiffy_edge.dao.entities.auth.TblUserRoles;
import com.jss.jiffy_edge.dao.entities.auth.TblUsers;

@Repository
public interface TblUserRolesRepository extends JpaRepository<TblUserRoles, Integer> {

	Optional<TblUserRoles> findById(Integer roleId);

	Optional<TblUserRoles> findByRoleName(String roleName);

	List<TblUserRoles> findByRoleCatgAndRoleIdNot(TblUsers.UserCategory roleCatg, Integer roleId);

	List<TblUserRoles> findByRoleIdNot(Integer roleId);

}

