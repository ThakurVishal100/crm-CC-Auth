package com.jss.jiffy_edge.services.auth;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

	public MenuServiceImpl(TblSystemMenuRepository menuRepository, ServiceDetailsRepository serviceDetailsRepository) { // Update constructor
		this.menuRepository = menuRepository;
		this.serviceDetailsRepository = serviceDetailsRepository;
	}

	@Override
	public List<MenuResponse> getAllMenus() {
		List<TblSystemMenu> menus = menuRepository.findAll();

		return menus.stream().map(menu -> new MenuResponse(menu.getMenuName())).collect(Collectors.toList());
	}

	@Override
	public MenuResponse createMenu(MenuRequest request) {


		if (request.getServiceId() == null || !serviceDetailsRepository.existsById(request.getServiceId())) {
			throw new RuntimeException("Cannot create menu: Service with ID " + request.getServiceId() + " does not exist.");
		}


		TblSystemMenu menu = new TblSystemMenu();
		menu.setMenuName(request.getMenuName());
		menu.setDisplayName(request.getDisplayName());
		menu.setDescription(request.getDescription());
		menu.setServiceId(request.getServiceId());
		menu.setMenuLevel(request.getMenuLevel());
		menu.setShowOrder(request.getShowOrder());
		menu.setTargetLink(request.getTargetLink());
		menu.setCreatedOn(LocalDateTime.now());
		menu.setLastUpdated(LocalDateTime.now());

		menu.setParentMenuId(request.getParentMenuId() != null ? request.getParentMenuId() : 0);

		if (request.getIsChildMenu() != null) {
			menu.setIsChildMenu(request.getIsChildMenu());
		} else {
			menu.setIsChildMenu(menu.getParentMenuId() > 0);
		}

		menu.setHasChildren(request.getHasChildren() != null ? request.getHasChildren() : false);



		if (request.getTargetType() != null) {
			menu.setTargetType(TblSystemMenu.TargetType.valueOf(request.getTargetType().toUpperCase()));
		}

		if (request.getSuperUserOnly() != null) {
			if (request.getSuperUserOnly().equalsIgnoreCase("true") || request.getSuperUserOnly().equalsIgnoreCase("YES")) {
				menu.setSuperUserOnly(TblSystemMenu.YesNo.YES);
			} else {
				menu.setSuperUserOnly(TblSystemMenu.YesNo.NO);
			}
		} else {
			menu.setSuperUserOnly(TblSystemMenu.YesNo.NO);
		}

		if (request.getStatus() != null) {
			menu.setStatus(TblSystemMenu.Status.valueOf(request.getStatus().toUpperCase()));
		} else {
			menu.setStatus(TblSystemMenu.Status.ACTIVE);
		}

		TblSystemMenu saved = menuRepository.save(menu);
		return new MenuResponse(saved.getMenuName());
	}

	@Override
	public MenuResponse updateMenu(Integer id, MenuRequest request) {

		if (request.getServiceId() == null || !serviceDetailsRepository.existsById(request.getServiceId())) {
			throw new RuntimeException("Cannot update menu: Service with ID " + request.getServiceId() + " does not exist.");
		}

		TblSystemMenu menu = menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu not found"));

		menu.setMenuName(request.getMenuName());
		menu.setDisplayName(request.getDisplayName());
		menu.setDescription(request.getDescription());
		menu.setServiceId(request.getServiceId());
		menu.setMenuLevel(request.getMenuLevel());
		menu.setShowOrder(request.getShowOrder());
		menu.setTargetLink(request.getTargetLink());
		menu.setLastUpdated(LocalDateTime.now());

		menu.setParentMenuId(request.getParentMenuId() != null ? request.getParentMenuId() : 0);
		if (request.getIsChildMenu() != null) {
			menu.setIsChildMenu(request.getIsChildMenu());
		} else {
			menu.setIsChildMenu(menu.getParentMenuId() > 0);
		}
		menu.setHasChildren(request.getHasChildren() != null ? request.getHasChildren() : false);



		if (request.getTargetType() != null) {
			menu.setTargetType(TblSystemMenu.TargetType.valueOf(request.getTargetType().toUpperCase()));
		}
		if (request.getSuperUserOnly() != null) {
			menu.setSuperUserOnly(TblSystemMenu.YesNo.valueOf(request.getSuperUserOnly().toUpperCase()));
		}

		if (request.getStatus() != null) {
			menu.setStatus(TblSystemMenu.Status.valueOf(request.getStatus().toUpperCase()));
		}

		TblSystemMenu updated = menuRepository.save(menu);
		return new MenuResponse(updated.getMenuName());
	}

	@Override
	public void deleteMenu(Integer id) {
		if (!menuRepository.existsById(id)) {
			throw new RuntimeException("Menu not found");
		}
		menuRepository.deleteById(id);
	}

}

