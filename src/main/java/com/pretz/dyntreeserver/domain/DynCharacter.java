package com.pretz.dyntreeserver.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "DynCharacter")
public class DynCharacter {

    @Id
    private long id;
    private String name;

    public DynCharacter(String characterName) {
        this.name = characterName;
    }

    public String getName() {
        return this.name;
    }
}
