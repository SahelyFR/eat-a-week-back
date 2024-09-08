package com.sahelyfr.eataweekback.dto;


public record Recipe(Long id, String name, String weblink, String image, boolean spring, boolean summer, boolean autumn, boolean winter) {
}
