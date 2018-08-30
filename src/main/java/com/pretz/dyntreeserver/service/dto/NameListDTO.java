package com.pretz.dyntreeserver.service.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

public final class NameListDTO {

    @NotNull
    @Getter
    private final List<NameDTO> names;

    public NameListDTO(List<NameDTO> names) {
        this.names = names;
    }
}
