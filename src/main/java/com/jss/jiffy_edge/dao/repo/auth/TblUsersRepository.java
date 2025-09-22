package com.jss.jiffy_edge.dao.repo.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jss.jiffy_edge.dao.entities.auth.TblUsers;

import jakarta.transaction.Transactional;

@Repository
public interface TblUsersRepository extends JpaRepository<TblUsers, Integer> {

	Optional<TblUsers> findByUserId(Integer userId);

	Optional<TblUsers> findByUserIdAndRole_RoleId(Integer userId, Integer roleId);


	List<TblUsers> findByLoginName(String loginName);

	
	Optional<TblUsers> findByEmail(String email);

	@Transactional
	@Modifying
	@Query("UPDATE TblUsers u SET u.userCatg = :role WHERE u.userId = :userId")
	void updateUserRole(Integer userId, String role);

	
}