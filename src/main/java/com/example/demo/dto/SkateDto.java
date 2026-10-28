package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;

public record SkateDto(
        Long id,
        @NotEmpty
        Double size,
        UserDto user
) {
}
