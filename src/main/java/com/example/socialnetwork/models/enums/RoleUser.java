package com.example.socialnetwork.models.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleUser {
    ADMIN(0),
    USER(1);
    private final int value;
}
