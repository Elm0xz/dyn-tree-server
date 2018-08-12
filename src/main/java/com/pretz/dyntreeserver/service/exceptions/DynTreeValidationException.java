package com.pretz.dyntreeserver.service.exceptions;

import com.pretz.dyntreeserver.service.dto.DynTreeDTO;
import org.springframework.validation.BindingResult;

public class DynTreeValidationException extends RuntimeException {
    public DynTreeValidationException(DynTreeDTO dynTreeDTO, BindingResult bindingResult) {
        super("Fuckup!");
    }
}
