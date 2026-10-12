package com.deliverfood.repository;

import com.deliverfood.entity.MenuItemEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;


public interface MenuItemRepository extends ListCrudRepository<MenuItemEntity, Long> {

    List<MenuItemEntity> getByRestaurantId(Long restaurantId);
}
