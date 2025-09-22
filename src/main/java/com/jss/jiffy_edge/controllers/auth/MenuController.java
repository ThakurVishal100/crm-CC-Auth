package com.jss.jiffy_edge.controllers.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jss.jiffy_edge.models.auth.MenuRequest;
import com.jss.jiffy_edge.models.auth.MenuResponse;
import com.jss.jiffy_edge.services.auth.MenuService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/menus")
public class MenuController {

	private final MenuService menuService;

	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping
	@Operation(summary = "get All menus", description = "")
	public ResponseEntity<List<MenuResponse>> getAllMenus() {
		return ResponseEntity.ok(menuService.getAllMenus());
	}

	@PostMapping
	@Operation(summary = "create new menu", description = "this api will create a new menu")
	public ResponseEntity<MenuResponse> createMenu(@RequestBody MenuRequest request) {
		return ResponseEntity.ok(menuService.createMenu(request));
	}

	@PutMapping("/{id}")
	@Operation(summary = "update the menu", description = "this api will update the existing menu")
	public ResponseEntity<MenuResponse> updateMenu(@PathVariable Integer id, @RequestBody MenuRequest request) {
		return ResponseEntity.ok(menuService.updateMenu(id, request));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "delete the menu", description = "")
	public ResponseEntity<Void> deleteMenu(@PathVariable Integer id) {
		menuService.deleteMenu(id);
		return ResponseEntity.noContent().build();
	}
}