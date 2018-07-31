package com.pretz.dyntreeserver.controller;

import com.pretz.dyntreeserver.service.dto.DynTreeDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.Year;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DynTreeValidationTest {

    private DynTreeDTO testDynTreeDTO;
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void shouldPassValidation() {
        testDynTreeDTO = DynTreeDTO.builder()
                .familyName("Duda")
                .familyCount(10)
                .generationsCount(3)
                .childrenPerCharacter(2.2)
                .nameListId(4L)
                .maxAge(90)
                .maturityAge(18)
                .startingYear(Year.of(1950))
                .mainCharacterName("Andrzej")
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationBlankFamilyName() {
        testDynTreeDTO = DynTreeDTO.builder()
                .familyName("    ")
                .familyCount(10)
                .generationsCount(3)
                .childrenPerCharacter(2.2)
                .nameListId(4L)
                .maxAge(90)
                .maturityAge(18)
                .startingYear(Year.of(1950))
                .mainCharacterName("Andrzej")
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals("must not be blank", violation.getMessage());
        assertEquals("familyName", violation.getPropertyPath().toString());
        assertEquals("    ", violation.getInvalidValue());
    }

    @Test
    public void shouldFailValidationEmptyFamilyName() {
        testDynTreeDTO = DynTreeDTO.builder()
                .familyName("")
                .familyCount(10)
                .generationsCount(3)
                .childrenPerCharacter(2.2)
                .nameListId(4L)
                .maxAge(90)
                .maturityAge(18)
                .startingYear(Year.of(1950))
                .mainCharacterName("Andrzej")
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals("must not be blank", violation.getMessage());
        assertEquals("familyName", violation.getPropertyPath().toString());
        assertEquals("", violation.getInvalidValue());
    }

    @Test
    public void shouldFailValidationNullFamilyName() {
        testDynTreeDTO = DynTreeDTO.builder()
                .familyName(null)
                .familyCount(10)
                .generationsCount(3)
                .childrenPerCharacter(2.2)
                .nameListId(4L)
                .maxAge(90)
                .maturityAge(18)
                .startingYear(Year.of(1950))
                .mainCharacterName("Andrzej")
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals("must not be blank", violation.getMessage());
        assertEquals("familyName", violation.getPropertyPath().toString());
        assertNull(violation.getInvalidValue());
    }

    @Test
    public void shouldFailValidationFamilyCountTooSmall() {
        testDynTreeDTO = DynTreeDTO.builder()
                .familyName("Duda")
                .familyCount(1)
                .generationsCount(3)
                .childrenPerCharacter(2.2)
                .nameListId(4L)
                .maxAge(90)
                .maturityAge(18)
                .startingYear(Year.of(1950))
                .mainCharacterName("Andrzej")
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals("must be greater than or equal to 3", violation.getMessage());
        assertEquals("familyCount", violation.getPropertyPath().toString());
        assertEquals(1, violation.getInvalidValue());
    }

    @Test
    public void shouldFailValidationGenerationsCountTooSmall() {
        testDynTreeDTO = DynTreeDTO.builder()
                .familyName("Duda")
                .familyCount(10)
                .generationsCount(1)
                .childrenPerCharacter(2.2)
                .nameListId(4L)
                .maxAge(90)
                .maturityAge(18)
                .startingYear(Year.of(1950))
                .mainCharacterName("Andrzej")
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals("must be greater than or equal to 2", violation.getMessage());
        assertEquals("generationsCount", violation.getPropertyPath().toString());
        assertEquals(1, violation.getInvalidValue());
    }

    @Test
    public void shouldFailValidationNullYear() {
        testDynTreeDTO = DynTreeDTO.builder()
                .familyName("Duda")
                .familyCount(10)
                .generationsCount(3)
                .childrenPerCharacter(2.2)
                .nameListId(4L)
                .maxAge(90)
                .maturityAge(18)
                .startingYear(null)
                .mainCharacterName("Andrzej")
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals("must not be null", violation.getMessage());
        assertEquals("startingYear", violation.getPropertyPath().toString());
        assertNull(violation.getInvalidValue());
    }

    @Test
    public void shouldFailValidationBlankMainCharacterName() {
        testDynTreeDTO = DynTreeDTO.builder()
                .familyName("Duda")
                .familyCount(10)
                .generationsCount(3)
                .childrenPerCharacter(2.2)
                .nameListId(4L)
                .maxAge(90)
                .maturityAge(18)
                .startingYear(Year.of(1950))
                .mainCharacterName("    ")
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals("must not be blank", violation.getMessage());
        assertEquals("mainCharacterName", violation.getPropertyPath().toString());
        assertEquals("    ", violation.getInvalidValue());
    }

    @Test
    public void shouldFailValidationEmptyMainCharacterName() {
        testDynTreeDTO = DynTreeDTO.builder()
                .familyName("Duda")
                .familyCount(10)
                .generationsCount(3)
                .childrenPerCharacter(2.2)
                .nameListId(4L)
                .maxAge(90)
                .maturityAge(18)
                .startingYear(Year.of(1950))
                .mainCharacterName("")
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals("must not be blank", violation.getMessage());
        assertEquals("mainCharacterName", violation.getPropertyPath().toString());
        assertEquals("", violation.getInvalidValue());
    }

    @Test
    public void shouldFailValidationNullMainCharacterName() {
        testDynTreeDTO = DynTreeDTO.builder()
                .familyName("Duda")
                .familyCount(10)
                .generationsCount(3)
                .childrenPerCharacter(2.2)
                .nameListId(4L)
                .maxAge(90)
                .maturityAge(18)
                .startingYear(Year.of(1950))
                .mainCharacterName(null)
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals("must not be blank", violation.getMessage());
        assertEquals("mainCharacterName", violation.getPropertyPath().toString());
        assertNull(violation.getInvalidValue());
    }
}
