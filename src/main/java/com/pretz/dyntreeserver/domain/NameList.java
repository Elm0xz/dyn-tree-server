package com.pretz.dyntreeserver.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class NameList {

    @Id
    @GeneratedValue
    private Long id;

    public NameList(List<Name> names) {
        this.names = names;
    }

    public List<Name> getNames() {
        return names;
    }

    @OneToMany(mappedBy = "id")
    private List<Name> names;
}
