package com.pretz.dyntreeserver.controller;

import com.pretz.dyntreeserver.domain.DynTree;
import com.pretz.dyntreeserver.domain.NameList;
import com.pretz.dyntreeserver.generator.DynTreeInput;
import com.pretz.dyntreeserver.service.DynTreeService;
import com.pretz.dyntreeserver.service.dto.DynTreeDTO;
import com.pretz.dyntreeserver.service.dto.DynTreeMapper;
import com.pretz.dyntreeserver.service.dto.NameListDTO;
import com.pretz.dyntreeserver.service.dto.NameListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller used for handling dynasty tree.
 */
@RestController
public class DynTreeController {
    private final DynTreeService dynTreeService;
    private final DynTreeMapper dynTreeMapper;
    private final NameListMapper nameListMapper;

    @Autowired
    public DynTreeController(DynTreeService dynTreeService, DynTreeMapper dynTreeMapper, NameListMapper nameListMapper) {
        this.dynTreeService = dynTreeService;
        this.dynTreeMapper = dynTreeMapper;
        this.nameListMapper = nameListMapper;
    }

    @PostMapping(path = "/dyn_tree")
    public ResponseEntity<DynTree> createDynTree(@Valid @RequestBody DynTreeDTO dynTreeDTO) {
        DynTreeInput dynTreeInput = dynTreeMapper.fromDynTreeDTO(dynTreeDTO);
        return new ResponseEntity<>(dynTreeService.generateDynTree(dynTreeInput), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(path = "/dyn_tree")
    public ResponseEntity<DynTreeDTO> getDynTree(Long dynTreeId) {
        return null;
    }

    @PostMapping(path = "dyn_tree/name_list")
    public void createNameList(@Valid @RequestBody NameListDTO nameListDTO) {
        NameList newNameList = nameListMapper.fromNameListDTO(nameListDTO);
        dynTreeService.saveNameList(newNameList);
    }
}
