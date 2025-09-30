package com.jss.jiffy_edge.services.auth;

import java.util.List;

import com.jss.jiffy_edge.models.auth.MenuRequest;
import com.jss.jiffy_edge.models.auth.MenuResponse;

public interface MenuService {
	List<MenuResponse> getAllMenus();

    List<MenuResponse> getMenusByServiceId(Integer serviceId);


    MenuResponse createMenu(MenuRequest request);
    MenuResponse updateMenu(Integer id, MenuRequest request);
    void deleteMenu(Integer id);
}
