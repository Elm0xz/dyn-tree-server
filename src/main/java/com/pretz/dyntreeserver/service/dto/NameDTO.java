package com.pretz.dyntreeserver.service.dto;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public final class NameDTO {

    @Getter
    @NotBlank
    @Min(value = 2)
    private final String name;

    public NameDTO(String name) {
        this.name = name;
    }
}
