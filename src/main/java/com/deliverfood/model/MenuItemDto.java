package com.deliverfood.model;

import com.deliverfood.entity.MenuItemEntity;

public record MenuItemDto(
        Long id,
        String name,
        String description,
        Double price,
        String imageUrl
) {

    public MenuItemDto(MenuItemEntity entity) {
        this(entity.id(), entity.name(), entity.description(), entity.price(), entity.imageUrl());
    }
}
