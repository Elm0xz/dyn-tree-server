package com.pretz.dyntreeserver.generator;

import com.pretz.dyntreeserver.domain.DynCharacter;
import com.pretz.dyntreeserver.domain.DynTree;
import org.springframework.stereotype.Component;

@Component
public class DynTreeGenerator {
    public DynTree generateDynTree(DynTreeInput dynTreeInput) {

        String familyName = dynTreeInput.getFamilyName();

        String mainCharacterName = dynTreeInput.getMainCharacterName();

        DynTree dynTree = new DynTree(familyName);

        DynCharacter mainCharacter = new DynCharacter(mainCharacterName);

        dynTree.setMainCharacter(mainCharacter);

        return dynTree;
    }
}
