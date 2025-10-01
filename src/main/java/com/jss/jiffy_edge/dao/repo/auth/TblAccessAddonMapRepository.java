package com.jss.jiffy_edge.dao.repo.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblAccessAddonMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblAccessAddonMapRepository extends JpaRepository<TblAccessAddonMap, Integer> {
}