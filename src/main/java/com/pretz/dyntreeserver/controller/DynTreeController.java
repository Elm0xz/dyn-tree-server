package com.pretz.dyntreeserver.controller;

import com.pretz.dyntreeserver.service.DynTreeService;
import com.pretz.dyntreeserver.service.dto.CreateDynTreeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Controller used for handling dynasty tree.
 */
@RestController
public class DynTreeController {
    private DynTreeService dynTreeService;

    @Autowired
    public DynTreeController(DynTreeService dynTreeService) {
        this.dynTreeService = dynTreeService;
    }

    @RequestMapping(method = POST, path = "/dyn_tree")
    public ResponseEntity<String> createDynTree(@RequestBody CreateDynTreeDTO createDynTreeDTO) {
        dynTreeService.generateDynTree(createDynTreeDTO);
        return new ResponseEntity<>("New tree successfully  generated", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(method = GET, path = "/dyn_tree")
    public ResponseEntity<CreateDynTreeDTO> getDynTree(Long dynTreeId) {
        return null;
    }
}
