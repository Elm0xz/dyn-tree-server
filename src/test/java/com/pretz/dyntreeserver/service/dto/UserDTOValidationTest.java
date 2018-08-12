package com.pretz.dyntreeserver.service.dto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDTOValidationTest {

    private UserDTO testUserDTO;
    private UserDTO.UserDTOBuilder testUserDTOBuilder;

    private static Validator validator;

    private static final String BLANK_VIOLATION_MSG = "must not be blank";

    @BeforeAll
    static void createValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    public void arrangeTestBuilder() {
        testUserDTOBuilder = UserDTO.builder()
                .name("Romuald")
                .password("buzz123");
    }

    @Test
    public void shouldPassValidation() {
        testUserDTO = testUserDTOBuilder.build();

        Set<ConstraintViolation<UserDTO>> violations
                = validator.validate(testUserDTO);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldNotPassValidationBlankName() {
        testUserDTO = testUserDTOBuilder
                .name("   ")
                .build();

        Set<ConstraintViolation<UserDTO>> violations
                = validator.validate(testUserDTO);

        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals(BLANK_VIOLATION_MSG, violation.getMessage());
        assertEquals("name", violation.getPropertyPath().toString());
        assertEquals("   ", violation.getInvalidValue());
    }

    @Test
    public void shouldNotPassValidationBlankPassword() {
        testUserDTO = testUserDTOBuilder
                .password("   ")
                .build();

        Set<ConstraintViolation<UserDTO>> violations
                = validator.validate(testUserDTO);

        assertEquals(1, violations.size());
        ConstraintViolation violation = violations.iterator().next();
        assertEquals(BLANK_VIOLATION_MSG, violation.getMessage());
        assertEquals("password", violation.getPropertyPath().toString());
        assertEquals("   ", violation.getInvalidValue());
    }
}
