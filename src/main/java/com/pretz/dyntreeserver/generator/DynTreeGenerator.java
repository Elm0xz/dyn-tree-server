package com.pretz.dyntreeserver.generator;

import com.pretz.dyntreeserver.domain.DynCharacter;
import com.pretz.dyntreeserver.domain.DynTree;
import org.springframework.stereotype.Component;

@Component
public class DynTreeGenerator {
    public DynTree generateDynTree(DynTreeInput dynTreeInput) {

        DynTree dynTree = new DynTree(dynTreeInput.getFamilyName());
        DynCharacter mainCharacter = new DynCharacter(dynTreeInput.getMainCharacterName(), dynTreeInput.getStartingYear());
        dynTree.setMainCharacter(mainCharacter);

        return dynTree;
    }
}
