package com.pretz.dyntreeserver.service.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DynTreeMapperTest {

    @Test
    public void shouldCreateDynTreeInput() {
/*        DynTreeInput testTreeInput = new DynTreeMapper().fromDynTreeDTO(
                DynTreeDTO.builder()
                        .familyName("Duda")
                        .familyCount(10)
                        .generationsCount(3)
                        .childrenPerCharacter(2.2)
                        .nameListId(4L)
                        .maxAge(90)
                        .maturityAge(18)
                        .startingYear(Year.of(1950))
                        .mainCharacter("Andrzej")
                        .build());*/
        assertFalse(true);
    }

    @Test
    public void shouldNotCreateDynTreeInputFamilyNameNullOrEmpty() {
/*        DynTreeInput testTreeInput = new DynTreeMapper().fromDynTreeDTO(
                DynTreeDTO.builder()
                        .familyName("")
                        .familyCount(10)
                        .generationsCount(3)
                        .childrenPerCharacter(2.2)
                        .nameListId(4L)
                        .maxAge(90)
                        .maturityAge(18)
                        .startingYear(Year.of(1950))
                        .mainCharacter("Andrzej")
                        .build());*/
        assertFalse(true);
    }

/*            this.familyName = familyName;
        this.familyCount = familyCount;
        this.generationsCount = generationsCount;
        this.childrenPerCharacter = childrenPerCharacter;
        this.nameListId = nameListId;
        this.maxAge = maxAge;
        this.maturityAge = maturityAge;
        this.baseDate = baseDate;
        this.mainCharacter = mainCharacter;*/

    @Test
    public void shouldNotCreateDynTreeInputFamilyCountSmallerThanThree() {
        assertFalse(true);
    }

    @Test
    public void shouldNotCreateDynTreeInputFamilyCountNull() {
        assertFalse(true);
    }

    @Test
    public void shouldNotCreateDynTreeInputGenerationsCountSmallerThanTwo() {
        assertFalse(true);
    }

    @Test
    public void shouldNotCreateDynTreeInputGenerationsCountNull() {
        assertFalse(true);
    }

    @Test
    public void shouldNotCreateDynTreeInputChildrenPerCharacterNull() {
        assertFalse(true);
    }

    @Test
    public void shouldNotCreateDynTreeInputChildrenPerCharacterGreaterThanTwenty() {
        assertFalse(true);
    }

    @Test
    public void shouldNotCreateDynTreeInputNameListNull() {
        assertFalse(true);
    }

    @Test
    public void shouldNotCreateDynTreeInputMaxAgeNull() {
        assertFalse(true);
    }

    @Test
    public void shouldNotCreateDynTreeInputMaturityAgeNull() {
        assertFalse(true);
    }

    @Test
    public void shouldNotCreateDynTreeInputMaturityAgeGreaterThanMaxAge() {
        assertFalse(true);
    }

    @Test
    public void shouldNotCreateDynTreeInputBaseDateNull() {
        assertFalse(true);
    }

    @Test
    public void shouldNotCreateDynTreeInputMainCharacterNullOrEmpty() {
        assertFalse(true);
    }
}
