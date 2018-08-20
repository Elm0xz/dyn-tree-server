package com.pretz.dyntreeserver.generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Year;

@AllArgsConstructor
@Getter
@Builder
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
