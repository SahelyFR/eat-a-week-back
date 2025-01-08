package com.sahelyfr.eataweekback.application.dto;

import com.sahelyfr.eataweekback.infrastructure.entities.Role;

public record RegisterUserDto(String email, String password, String username, Role role) {
}
