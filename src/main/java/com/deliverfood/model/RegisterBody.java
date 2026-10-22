package com.deliverfood.model;

public record RegisterBody(
        String email,
        String password,
        String firstName,
        String lastName
) {
}
