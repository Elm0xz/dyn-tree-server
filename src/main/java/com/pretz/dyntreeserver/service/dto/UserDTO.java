package com.pretz.dyntreeserver.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public final class UserDTO {

    @NotBlank
    private final String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private final String password;
    private final String email;

    @JsonCreator
    public UserDTO(@JsonProperty("name") String name,
                   @JsonProperty("password") String password,
                   @JsonProperty("email") String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
