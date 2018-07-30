package com.pretz.dyntreeserver.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Year;

@Builder
public final class DynTreeDTO {

    @NotEmpty
    @NotNull
    @Getter
    private final String familyName;

    @Min(value = 3)
    @NotNull
    @Getter
    private final int familyCount;

    @Min(value = 2)
    @NotNull
    @Getter
    private final int generationsCount;

    @NotNull
    @Getter
    private final double childrenPerCharacter;

    @NotNull
    @Getter
    private final long nameListId;

    @NotNull
    @Getter
    private final int maxAge;

    @NotNull
    @Getter
    private final int maturityAge;

    @NotNull
    @Getter
    private final Year startingYear;

    @NotEmpty
    @NotNull
    @Getter
    private final String mainCharacter;

    @JsonCreator
    public DynTreeDTO(@JsonProperty("family_name") String familyName,
                      @JsonProperty("family_count") Integer familyCount,
                      @JsonProperty("generations_count") Integer generationsCount,
                      @JsonProperty("children_per_character") Double childrenPerCharacter,
                      @JsonProperty("name_list") Long nameListId,
                      @JsonProperty("max_age") Integer maxAge,
                      @JsonProperty("maturity_age") Integer maturityAge,
                      @JsonProperty("starting_year") Year startingYear,
                      @JsonProperty("main_character") String mainCharacter) {

        this.familyName = familyName;
        this.familyCount = familyCount;
        this.generationsCount = generationsCount;
        this.childrenPerCharacter = childrenPerCharacter;
        this.nameListId = nameListId;
        this.maxAge = maxAge;
        this.maturityAge = maturityAge;
        this.startingYear = startingYear;
        this.mainCharacter = mainCharacter;
    }
}
