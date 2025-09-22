package com.jss.jiffy_edge.dao.repo.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jss.jiffy_edge.dao.entities.auth.SysAccessLevel;

@Repository
public interface SysAccessLevelRepository extends JpaRepository<SysAccessLevel, Integer> {
}
