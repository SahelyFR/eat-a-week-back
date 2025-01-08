package com.sahelyfr.eataweekback.application.dto;

import com.sahelyfr.eataweekback.domain.enums.RoleEnum;

public record LoginDto(String token, long expiresIn, RoleEnum role) {

}
