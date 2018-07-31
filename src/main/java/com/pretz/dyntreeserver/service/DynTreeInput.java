package com.pretz.dyntreeserver.service;

import lombok.AllArgsConstructor;

import java.time.Year;

@AllArgsConstructor
public final class DynTreeInput {

    private final String familyName;
    private final Integer familyCount;
    private final Integer generationsCount;
    private final Double childrenPerCharacter;
    private final Long nameListId;
    private final Integer maxAge;
    private final Integer maturityAge;
    private final Year startingYear;
    private final String mainCharacterName;
}
