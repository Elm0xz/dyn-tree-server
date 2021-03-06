package com.pretz.dyntreeserver.service.dto;

import com.pretz.dyntreeserver.service.DynTreeInput;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class DynTreeMapper {
    public DynTreeInput fromDynTreeDTO(@Valid DynTreeDTO dynTreeDTO) {
        return new DynTreeInput(
                dynTreeDTO.getFamilyName(),
                dynTreeDTO.getFamilyCount(),
                dynTreeDTO.getGenerationsCount(),
                dynTreeDTO.getChildrenPerCharacter(),
                dynTreeDTO.getNameListId(),
                dynTreeDTO.getMaxAge(),
                dynTreeDTO.getMaturityAge(),
                dynTreeDTO.getStartingYear(),
                dynTreeDTO.getMainCharacterName());
    }
}
