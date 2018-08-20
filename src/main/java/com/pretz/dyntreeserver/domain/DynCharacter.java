package com.pretz.dyntreeserver.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Year;

@Entity(name = "DynCharacter")
public class DynCharacter {

    @Id
    private long id;
    private String name;
    private Year birthDate;
    private Year deathDate;

    public DynCharacter(String name, Year birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return this.name;
    }

    public Year getBirthDate() {
        return this.birthDate;
    }
}
