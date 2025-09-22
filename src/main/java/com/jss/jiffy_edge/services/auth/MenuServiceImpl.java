package com.jss.jiffy_edge.services.auth;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jss.jiffy_edge.dao.entities.auth.TblSystemMenu;
import com.jss.jiffy_edge.dao.repo.auth.TblSystemMenuRepository;
import com.jss.jiffy_edge.models.auth.MenuRequest;
import com.jss.jiffy_edge.models.auth.MenuResponse;

@Service
public class MenuServiceImpl implements MenuService {
	private final TblSystemMenuRepository menuRepository;

	public MenuServiceImpl(TblSystemMenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	@Override
	public List<MenuResponse> getAllMenus() {
		List<TblSystemMenu> menus = menuRepository.findAll();

		return menus.stream().map(menu -> new MenuResponse(menu.getMenuName())).collect(Collectors.toList());
	}

	@Override
	public MenuResponse createMenu(MenuRequest request) {
		TblSystemMenu menu = new TblSystemMenu();
		menu.setMenuName(request.getMenuName());
		menu.setDisplayName(request.getDisplayName());
		menu.setDescription(request.getDescription());
		menu.setServiceId(request.getServiceId());
		menu.setMenuLevel(request.getMenuLevel());
		menu.setShowOrder(request.getShowOrder());
		menu.setTargetLink(request.getTargetLink());

		// Convert String → Enum
		if (request.getTargetType() != null) {
			menu.setTargetType(TblSystemMenu.TargetType.valueOf(request.getTargetType().toUpperCase()));
		}
		menu.setHasChildren(request.getHasChildren());
		if (request.getSuperUserOnly() != null) {
			if (request.getSuperUserOnly().equalsIgnoreCase("true")) {
				menu.setSuperUserOnly(TblSystemMenu.YesNo.YES);
			} else {
				menu.setSuperUserOnly(TblSystemMenu.YesNo.NO);
			}
		}

		if (request.getStatus() != null) {
			menu.setStatus(TblSystemMenu.Status.valueOf(request.getStatus().toUpperCase()));
		}

		TblSystemMenu saved = menuRepository.save(menu);
		return new MenuResponse(saved.getMenuName());
	}

	@Override
	public MenuResponse updateMenu(Integer id, MenuRequest request) {
		TblSystemMenu menu = menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu not found"));

		menu.setMenuName(request.getMenuName());
		menu.setDisplayName(request.getDisplayName());
		menu.setDescription(request.getDescription());
		menu.setServiceId(request.getServiceId());
		menu.setMenuLevel(request.getMenuLevel());
		menu.setShowOrder(request.getShowOrder());
		menu.setTargetLink(request.getTargetLink());

		// Convert String → Enum
		if (request.getTargetType() != null) {
			menu.setTargetType(TblSystemMenu.TargetType.valueOf(request.getTargetType().toUpperCase()));
		}
		menu.setHasChildren(request.getHasChildren());
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
