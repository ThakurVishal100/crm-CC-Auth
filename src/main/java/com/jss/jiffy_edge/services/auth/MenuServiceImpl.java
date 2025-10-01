package com.jss.jiffy_edge.services.auth;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.jss.jiffy_edge.convertors.auth.MenuConverter;
import org.springframework.stereotype.Service;

import com.jss.jiffy_edge.dao.entities.auth.TblSystemMenu;
import com.jss.jiffy_edge.dao.repo.auth.ServiceDetailsRepository;
import com.jss.jiffy_edge.dao.repo.auth.TblSystemMenuRepository;
import com.jss.jiffy_edge.models.auth.MenuRequest;
import com.jss.jiffy_edge.models.auth.MenuResponse;

@Service
public class MenuServiceImpl implements MenuService {
    private final TblSystemMenuRepository menuRepository;
    private final ServiceDetailsRepository serviceDetailsRepository;
    private final MenuConverter menuConverter;


    public MenuServiceImpl(TblSystemMenuRepository menuRepository, ServiceDetailsRepository serviceDetailsRepository, MenuConverter menuConverter) {
        this.menuRepository = menuRepository;
        this.serviceDetailsRepository = serviceDetailsRepository;
        this.menuConverter = menuConverter;
    }

    @Override
    public List<MenuResponse> getAllMenus() {
        List<TblSystemMenu> menus = menuRepository.findAll();
        return menus.stream().map(menuConverter::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<MenuResponse> getMenusByServiceId(Integer serviceId) {
        List<TblSystemMenu> menus = menuRepository.findByServiceId(serviceId);
        return menus.stream().map(menuConverter::toResponse).collect(Collectors.toList());
    }

    @Override
    public MenuResponse createMenu(MenuRequest request) {
        validateServiceId(request.getServiceId());
        TblSystemMenu menu = menuConverter.toEntity(request);

        // Logic for generating a hierarchical menu ID
        Integer parentId = request.getParentMenuId() != null ? request.getParentMenuId() : 0;
        Integer newMenuId;

        if (parentId == 0) {
            // Logic for generating a top-level menu ID
            int maxId = menuRepository.findMaxMenuIdByParentId(0).orElse(0);
            newMenuId = maxId + 1;
        } else {
            // Logic for generating a sub-menu ID
            int maxId = menuRepository.findMaxMenuIdByParentId(parentId)
                    .orElse(parentId * 100); // If no children exist, start the sequence
            newMenuId = maxId + 1;
        }
        menu.setMenuId(newMenuId);
        menu.setCreatedOn(LocalDateTime.now());
        TblSystemMenu saved = menuRepository.save(menu);
        return menuConverter.toResponse(saved);
    }

    @Override
    public MenuResponse updateMenu(Integer id, MenuRequest request) {
        validateServiceId(request.getServiceId());
        TblSystemMenu menu = menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu not found"));

        TblSystemMenu updatedMenu = menuConverter.toEntity(request);
        updatedMenu.setMenuId(menu.getMenuId()); // Keep the original ID
        updatedMenu.setCreatedOn(menu.getCreatedOn()); // Keep original creation date
        updatedMenu.setLastUpdated(LocalDateTime.now());

        TblSystemMenu updated = menuRepository.save(updatedMenu);
        return menuConverter.toResponse(updated);
    }

    @Override
    public void deleteMenu(Integer id) {
        TblSystemMenu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));
        menu.setStatus(TblSystemMenu.Status.INACTIVE);
        menu.setLastUpdated(LocalDateTime.now());
        menuRepository.save(menu);
    }


    private void validateServiceId(Integer serviceId) {
        if (serviceId == null || !serviceDetailsRepository.existsById(serviceId)) {
            throw new RuntimeException("Service with ID " + serviceId + " does not exist.");
        }
    }

}