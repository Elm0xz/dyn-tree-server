package com.pretz.dyntreeserver.controller;

import com.pretz.dyntreeserver.service.dto.DynTreeDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.Year;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DynTreeDTOValidationTest {

    private DynTreeDTO testDynTreeDTO;
    private DynTreeDTO.DynTreeDTOBuilder testDynTreeDTOBuilder;

    private static Validator validator;

    private static final String BLANK_VIOLATION_MSG = "must not be blank";
    private static final String NULL_VIOLATION_MSG = "must not be null";
    private static final String FAMILY_COUNT_MIN_VIOLATION_MSC = "must be greater than or equal to 3";
    private static final String GENERATIONS_COUNT_MIN_VIOLATION_MSC = "must be greater than or equal to 2";


    @BeforeAll
    static void createValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    public void arrangeTestBuilder() {
        testDynTreeDTOBuilder = DynTreeDTO.builder()
                .familyName("Duda")
                .familyCount(10)
                .generationsCount(3)
                .childrenPerCharacter(2.2)
                .nameListId(4L)
                .maxAge(90)
                .maturityAge(18)
                .startingYear(Year.of(1950))
                .mainCharacterName("Andrzej");
    }

    @Test
    public void shouldPassValidation() {
        testDynTreeDTO = testDynTreeDTOBuilder.build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldNotPassValidationBlankFamilyName() {
        testDynTreeDTO = testDynTreeDTOBuilder
                .familyName("    ")
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals(BLANK_VIOLATION_MSG, violation.getMessage());
        assertEquals("familyName", violation.getPropertyPath().toString());
        assertEquals("    ", violation.getInvalidValue());
    }

    @Test
    public void shouldNotPassValidationEmptyFamilyName() {
        testDynTreeDTO = testDynTreeDTOBuilder
                .familyName("")
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals(BLANK_VIOLATION_MSG, violation.getMessage());
        assertEquals("familyName", violation.getPropertyPath().toString());
        assertEquals("", violation.getInvalidValue());
    }

    @Test
    public void shouldNotPassValidationNullFamilyName() {
        testDynTreeDTO = testDynTreeDTOBuilder
                .familyName(null)
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals(BLANK_VIOLATION_MSG, violation.getMessage());
        assertEquals("familyName", violation.getPropertyPath().toString());
        assertNull(violation.getInvalidValue());
    }

    @Test
    public void shouldNotPassValidationFamilyCountTooSmall() {
        testDynTreeDTO = testDynTreeDTOBuilder
                .familyCount(1)
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals(FAMILY_COUNT_MIN_VIOLATION_MSC, violation.getMessage());
        assertEquals("familyCount", violation.getPropertyPath().toString());
        assertEquals(1, violation.getInvalidValue());
    }

    @Test
    public void shouldNotPassValidationGenerationsCountTooSmall() {
        testDynTreeDTO = testDynTreeDTOBuilder
                .generationsCount(1)
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals(GENERATIONS_COUNT_MIN_VIOLATION_MSC, violation.getMessage());
        assertEquals("generationsCount", violation.getPropertyPath().toString());
        assertEquals(1, violation.getInvalidValue());
    }

    @Test
    public void shouldNotPassValidationNullYear() {
        testDynTreeDTO = testDynTreeDTOBuilder
                .startingYear(null)
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals(NULL_VIOLATION_MSG, violation.getMessage());
        assertEquals("startingYear", violation.getPropertyPath().toString());
        assertNull(violation.getInvalidValue());
    }

    @Test
    public void shouldNotPassValidationBlankMainCharacterName() {
        testDynTreeDTO = testDynTreeDTOBuilder
                .mainCharacterName("    ")
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals(BLANK_VIOLATION_MSG, violation.getMessage());
        assertEquals("mainCharacterName", violation.getPropertyPath().toString());
        assertEquals("    ", violation.getInvalidValue());
    }

    @Test
    public void shouldNotPassValidationEmptyMainCharacterName() {
        testDynTreeDTO = testDynTreeDTOBuilder
                .mainCharacterName("")
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals(BLANK_VIOLATION_MSG, violation.getMessage());
        assertEquals("mainCharacterName", violation.getPropertyPath().toString());
        assertEquals("", violation.getInvalidValue());
    }

    @Test
    public void shouldNotPassValidationNullMainCharacterName() {
        testDynTreeDTO = testDynTreeDTOBuilder
                .mainCharacterName(null)
                .build();

        Set<ConstraintViolation<DynTreeDTO>> violations
                = validator.validate(testDynTreeDTO);

        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals(BLANK_VIOLATION_MSG, violation.getMessage());
        assertEquals("mainCharacterName", violation.getPropertyPath().toString());
        assertNull(violation.getInvalidValue());
    }
}
