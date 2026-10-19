package com.deliverfood.model;

import com.deliverfood.entity.MenuItemEntity;
import com.deliverfood.entity.OrderItemEntity;

public record OrderItemDto(
        Long orderItemId,
        Long menuItemId,
        Long restaurantId,
        Double price,
        Integer quantity,
        String menuItemName,
        String menuItemDescription,
        String menuItemImageUrl
) {
    public OrderItemDto(OrderItemEntity orderItemEntity, MenuItemEntity menuItemEntity) {
        this(
                orderItemEntity.id(),
                orderItemEntity.menuItemId(),
                menuItemEntity.restaurantId(),
                orderItemEntity.price(),
                orderItemEntity.quantity(),
                menuItemEntity.name(),
                menuItemEntity.description(),
                menuItemEntity.imageUrl()
        );
    }
}
