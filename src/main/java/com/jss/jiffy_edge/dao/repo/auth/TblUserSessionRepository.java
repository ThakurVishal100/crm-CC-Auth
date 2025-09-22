package com.jss.jiffy_edge.dao.repo.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jss.jiffy_edge.dao.entities.auth.TblUserSession;

@Repository
public interface TblUserSessionRepository extends JpaRepository<TblUserSession, String> {

	Optional<TblUserSession> findByJwtToken(String jwtToken);
}