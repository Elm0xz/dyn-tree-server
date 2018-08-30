package com.pretz.dyntreeserver.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Name {

    @Id
    @GeneratedValue
    private Long id;

    public Name(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String name;
}
