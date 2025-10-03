package com.jss.jiffy_edge.dao.repo.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblSystemMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TblSystemMenuRepository extends JpaRepository<TblSystemMenu, Integer> {
    List<TblSystemMenu> findByServiceId(Integer serviceId);

    List<TblSystemMenu> findByStatus(TblSystemMenu.Status status);

    List<TblSystemMenu> findByServiceIdAndStatus(Integer serviceId, TblSystemMenu.Status status);

    @Query("SELECT MAX(m.menuId) FROM TblSystemMenu m WHERE m.parentMenuId = :parentId")
    Optional<Integer> findMaxMenuIdByParentId(@Param("parentId") Integer parentId);

}