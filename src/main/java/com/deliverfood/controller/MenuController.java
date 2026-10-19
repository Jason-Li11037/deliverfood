package com.deliverfood.controller;

import com.deliverfood.entity.MenuItemEntity;
import com.deliverfood.model.RestaurantDto;
import com.deliverfood.service.MenuItemService;
import com.deliverfood.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

    private final RestaurantService restaurantService;
    private final MenuItemService menuItemService;

    public MenuController(RestaurantService restaurantService, MenuItemService menuItemService) {
        this.restaurantService = restaurantService;
        this.menuItemService = menuItemService;
    }

    @GetMapping("/restaurant/{restaurantId}/menu")
    public List<MenuItemEntity> getMenuByRestaurant(@PathVariable("restaurantId") long restaurantId) {
        return menuItemService.getMenuItemsByRestaurantId(restaurantId);
    }

    @GetMapping("/restaurants/menu")
    public List<RestaurantDto> getMenuForAllRestaurants() {
        return restaurantService.getRestaurants();
    }
}
