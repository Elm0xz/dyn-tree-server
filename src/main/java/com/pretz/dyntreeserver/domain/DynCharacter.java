package com.pretz.dyntreeserver.domain;

import javax.persistence.*;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;

@Entity(name = "DynCharacter")
public class DynCharacter {

    @Id
    private long id;
    private String name;
    private Year birthDate;
    private Year deathDate;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private DynCharacter parent;

    @OneToMany(mappedBy = "parent_id")
    private List<DynCharacter> children;

    @ManyToOne
    @JoinColumn(name = "dynasty_tree_id")
    private DynTree dynTree;

    public DynCharacter(String name, Year birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.children = new LinkedList<>();
    }

    public String getName() {
        return this.name;
    }

    public Year getBirthDate() {
        return this.birthDate;
    }

    private void setParent(DynCharacter parent) {
        this.parent = parent;
    }

    public void addChild(DynCharacter child) {
        children.add(child);
        child.setParent(this);
    }

    public int getChildrenCount() {
        return children.size();
    }

    public DynCharacter getEldestChild() {
        if (!children.isEmpty()) {
            return children.get(0);
        }
        else return null;

    }

    public boolean hasSiblings() {
        return parent.getChildrenCount() > 1;
    }
}
