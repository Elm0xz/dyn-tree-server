package com.pretz.dyntreeserver.generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.Year;

@AllArgsConstructor
@Getter
@Builder
public final class DynTreeInput {

    @NonNull
    private final String familyName;
    @NonNull
    private final String mainCharacterName;
    @NonNull
    private final Integer familyCount;
    @NonNull
    private final Integer generationsCount;
    @NonNull
    private final Double childrenPerCharacter;
    @NonNull
    private final Long nameListId;
    @NonNull
    private final Integer maxAge;
    @NonNull
    private final Integer maturityAge;
    @NonNull
    private final Year startingYear;

}
