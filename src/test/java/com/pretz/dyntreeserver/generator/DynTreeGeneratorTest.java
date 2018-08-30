package com.pretz.dyntreeserver.generator;

import com.pretz.dyntreeserver.domain.DynTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DynTreeGeneratorTest {

    private static final String TEST_FAMILY_NAME = "Pawlak";
    private static final String TEST_MAIN_CHARACTER_NAME = "Romuald";

    private static final int TEST_FAMILY_COUNT = 6;
    private static final int TEST_GENERATIONS_COUNT = 3;
    private static final double TEST_CHILDREN_PER_CHARACTER = 2.5;

    private static final long TEST_NAME_LIST_ID = 123L;
    private static final int TEST_MAX_AGE = 80;
    private static final int TEST_MATURITY_AGE = 16;

    private static final int TEST_STARTING_YEAR = 1700;

    private DynTreeInput.DynTreeInputBuilder testBuilder;

    private DynTreeGenerator dynTreeGenerator;

    @BeforeEach
    public void initGenerator() {
        dynTreeGenerator = new DynTreeGenerator();
        testBuilder = DynTreeInput.builder()
                .familyName(TEST_FAMILY_NAME)
                .mainCharacterName(TEST_MAIN_CHARACTER_NAME)
                .familyCount(TEST_FAMILY_COUNT)
                .generationsCount(TEST_GENERATIONS_COUNT)
                .childrenPerCharacter(TEST_CHILDREN_PER_CHARACTER)
                .nameListId(TEST_NAME_LIST_ID)
                .maxAge(TEST_MAX_AGE)
                .maturityAge(TEST_MATURITY_AGE)
                .startingYear(Year.of(TEST_STARTING_YEAR));
    }

    @Test
    public void shouldGenerateMainCharacterWithProperFamilyName() {
        DynTreeInput testInput = testBuilder
                .mainCharacterName("Andrzej")
                .familyName("Duda")
                .startingYear(Year.of(1900))
                .build();
        DynTree dynTree = dynTreeGenerator.generateDynTree(testInput);
        assertEquals("Andrzej", dynTree.getFounder().getName());
        assertEquals("Duda", dynTree.getFamilyName());
        assertEquals(Year.of(1900), dynTree.getFounder().getBirthDate());
    }

    @Test
    public void shouldGenerateFamilyTreeThreeCharactersTwoGenerations() {
        DynTreeInput testInput = testBuilder
                .familyCount(3)
                .generationsCount(2)
                .build();
        DynTree dynTree = dynTreeGenerator.generateDynTree(testInput);
        assertEquals(3, dynTree.getFamilyCount());
        assertEquals(2, dynTree.getGenerationsCount());
    }

    @Test
    public void shouldGenerateFamilyTreeFiveCharactersThreeGenerations() {

        DynTreeInput testInput = testBuilder
                .familyCount(5)
                .generationsCount(3)
                .build();
        DynTree dynTree = dynTreeGenerator.generateDynTree(testInput);
        assertEquals(5, dynTree.getFamilyCount());
        assertEquals(3, dynTree.getGenerationsCount());
    }

    @Test
    public void shouldGenerateFamilyTreeWithNamesConsistentWithNameList() {
        DynTreeInput testInput = testBuilder
                .mainCharacterName("Pepe")
                .nameListId(999L)
                .build();
        DynTree dynTree = dynTreeGenerator.generateDynTree(testInput);
        List<String> characterNames = dynTree.getCharacters().stream()
                .filter(character -> !character.equals(dynTree.getFounder()))
                .map(character -> (character.getName())).collect(Collectors.toList());

        List<String> nameList = initMockNameList();

        assertEquals("Pepe", dynTree.getFounder().getName());

        for (String name : characterNames) {
            assertThat(nameList, contains(name));
        }
    }

    private List<String> initMockNameList() {
        return null;
    }
}
