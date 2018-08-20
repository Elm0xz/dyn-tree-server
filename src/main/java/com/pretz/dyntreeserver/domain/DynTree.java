package com.pretz.dyntreeserver.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "DynTree")
public class DynTree {

    @Id
    private long id;

    private String familyName;

    @OneToOne
    private DynCharacter mainCharacter;

    public DynTree(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public DynCharacter getMainCharacter() {
        return this.mainCharacter;
    }

    public void setMainCharacter(DynCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
    }
}
