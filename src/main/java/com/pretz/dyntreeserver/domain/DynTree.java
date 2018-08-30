package com.pretz.dyntreeserver.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "DynTree")
public class DynTree {

    @Id
    private long id;

    private String familyName;

    @OneToOne
    private DynCharacter founder;

    @OneToMany(mappedBy = "dynTree")
    private List<DynCharacter> characters;

    @OneToOne
    private NameList nameList;

    public DynTree(String familyName, DynCharacter mainCharacter) {
        this.familyName = familyName;
        this.founder = mainCharacter;
        characters = new ArrayList<>();
        characters.add(founder);
    }

    public String getFamilyName() {
        return familyName;
    }

    public DynCharacter getFounder() {
        return founder;
    }

    public void setFounder(DynCharacter founder) {
        this.founder = founder;
        characters.add(founder);
    }

    public int getFamilyCount() {
        return characters.size();
    }

    public void addCharacter(DynCharacter newCharacter) {
        characters.add(newCharacter);
    }

    public int getGenerationsCount() {
        int generationsCount = 1;
        DynCharacter character = founder;
        while (character.getEldestChild() != null) {
            character = character.getEldestChild();
            generationsCount++;
        }
        return generationsCount;
    }

    public List<DynCharacter> getCharacters() {
        return characters;
    }
}
