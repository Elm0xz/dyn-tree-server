package com.pretz.dyntreeserver.service.dto;

import com.pretz.dyntreeserver.domain.Name;
import com.pretz.dyntreeserver.domain.NameList;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NameListMapper {

    public NameList fromNameListDTO(NameListDTO nameListDTO) {
        List<Name> mappedNames = nameListDTO.getNames().stream().map(name -> new Name(name.toString())).collect(Collectors.toList());
        return new NameList(mappedNames);
    }
}
