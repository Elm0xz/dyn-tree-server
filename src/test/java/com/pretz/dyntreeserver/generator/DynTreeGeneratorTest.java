package com.pretz.dyntreeserver.generator;

import com.pretz.dyntreeserver.domain.DynTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DynTreeGeneratorTest {

    private DynTreeGenerator dynTreeGenerator;

    @BeforeEach
    public void initGenerator() {
        dynTreeGenerator = new DynTreeGenerator();
    }

    @Test
    public void shouldCreateMainCharacter() {
        DynTreeInput testInput = DynTreeInput.builder()
                .mainCharacterName("Romuald")
                .familyName("Pawlak")
                .familyCount(1)
                .build();
        DynTree dynTree = dynTreeGenerator.generateDynTree(testInput);
        assertEquals("Romuald",dynTree.getMainCharacter().getName());
        assertEquals("Pawlak", dynTree.getFamilyName());
    }
}
