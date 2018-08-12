package com.pretz.dyntreeserver.service;

import com.pretz.dyntreeserver.domain.DynTree;
import com.pretz.dyntreeserver.generator.DynTreeGenerator;
import com.pretz.dyntreeserver.service.dto.DynTreeDTO;
import com.pretz.dyntreeserver.service.dto.DynTreeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynTreeService {

    private DynTreeMapper dynTreeMapper;
    private DynTreeGenerator dynTreeGenerator;

    @Autowired
    public DynTreeService(DynTreeMapper dynTreeMapper, DynTreeGenerator dynTreeGenerator) {
        this.dynTreeMapper = dynTreeMapper;
        this.dynTreeGenerator = dynTreeGenerator;
    }

    public DynTree generateDynTree(DynTreeDTO dynTreeDTO) {
        DynTreeInput dynTreeInput = dynTreeMapper.fromDynTreeDTO(dynTreeDTO);
        return dynTreeGenerator.generateDynTree(dynTreeInput);
    }
}
