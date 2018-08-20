package com.pretz.dyntreeserver.service;

import com.pretz.dyntreeserver.domain.DynTree;
import com.pretz.dyntreeserver.generator.DynTreeGenerator;
import com.pretz.dyntreeserver.generator.DynTreeInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynTreeService {


    private DynTreeGenerator dynTreeGenerator;

    @Autowired
    public DynTreeService(DynTreeGenerator dynTreeGenerator) {
        this.dynTreeGenerator = dynTreeGenerator;
    }

    public DynTree generateDynTree(DynTreeInput dynTreeInput) {
        return dynTreeGenerator.generateDynTree(dynTreeInput);
    }
}
