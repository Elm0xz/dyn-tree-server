package com.pretz.dyntreeserver.generator;

import com.pretz.dyntreeserver.domain.DynTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DynTreeGeneratorTest {

    private DynTreeGenerator dynTreeGenerator;

    @BeforeEach
    public void initGenerator() {
        dynTreeGenerator = new DynTreeGenerator();
    }

    @Test
    public void shouldGenerateMainCharacter() {
        DynTreeInput testInput = DynTreeInput.builder()
                .mainCharacterName("Romuald")
                .familyName("Pawlak")
                .familyCount(1)
                .startingYear(Year.of(1700))
                .build();
        DynTree dynTree = dynTreeGenerator.generateDynTree(testInput);
        assertEquals("Romuald",dynTree.getMainCharacter().getName());
        assertEquals("Pawlak", dynTree.getFamilyName());
        assertEquals(Year.of(1700), dynTree.getMainCharacter().getBirthDate());
    }
}
