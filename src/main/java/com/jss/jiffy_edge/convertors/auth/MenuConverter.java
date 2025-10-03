package com.jss.jiffy_edge.convertors.auth;

import com.jss.jiffy_edge.dao.entities.auth.TblSystemMenu;
import com.jss.jiffy_edge.models.auth.MenuRequest;
import com.jss.jiffy_edge.models.auth.MenuResponse;
import org.springframework.stereotype.Component;

@Component
public class MenuConverter {

    public TblSystemMenu toEntity(MenuRequest request) {
        TblSystemMenu entity = new TblSystemMenu();
        entity.setMenuName(request.getMenuName());
        entity.setDisplayName(request.getDisplayName());
        entity.setMenuDescp(request.getDescription());
        entity.setServiceId(request.getServiceId());
        entity.setMenuLevel(request.getMenuLevel());
        entity.setShowOrder(request.getShowOrder());
        entity.setHintText(request.getHintText());
        entity.setTargetLink(request.getTargetLink());
        entity.setParentMenuId(request.getParentMenuId() != null ? request.getParentMenuId() : 0);
        entity.setIsChildMenu(request.getIsChildMenu() != null ? request.getIsChildMenu() : entity.getParentMenuId() > 0);
        entity.setHasChildren(request.getHasChildren() != null ? request.getHasChildren() : false);

        if (request.getTargetType() != null) {
            entity.setTargetType(TblSystemMenu.TargetType.valueOf(request.getTargetType().toUpperCase()));
        }
        if (request.getSuperUserOnly() != null) {
            entity.setSuperUserOnly(TblSystemMenu.YesNo.valueOf(request.getSuperUserOnly().toUpperCase()));
        }
        if (request.getStatus() != null) {
            entity.setStatus(TblSystemMenu.Status.valueOf(request.getStatus().toUpperCase()));
        }
        return entity;
    }

    public MenuResponse toResponse(TblSystemMenu entity) {
        return new MenuResponse(entity);
    }
}