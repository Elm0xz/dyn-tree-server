package com.pretz.dyntreeserver.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Year;

@Builder
public final class DynTreeDTO {

    @NotBlank
    @Getter
    private final String familyName;

    @NotBlank
    @Getter
    private final String mainCharacterName;

    @Min(value = 3)
    @Getter
    private final int familyCount;

    @Min(value = 2)
    @Getter
    private final int generationsCount;

    @Getter
    private final double childrenPerCharacter;

    @Getter
    private final long nameListId;

    @Getter
    private final int maxAge;

    @Getter
    private final int maturityAge;

    @NotNull
    @Getter
    private final Year startingYear;

    @JsonCreator
    public DynTreeDTO(@JsonProperty("family_name") String familyName,
                      @JsonProperty("main_character") String mainCharacterName,
                      @JsonProperty("family_count") Integer familyCount,
                      @JsonProperty("generations_count") Integer generationsCount,
                      @JsonProperty("children_per_character") Double childrenPerCharacter,
                      @JsonProperty("name_list") Long nameListId,
                      @JsonProperty("max_age") Integer maxAge,
                      @JsonProperty("maturity_age") Integer maturityAge,
                      @JsonProperty("starting_year") Year startingYear) {

        this.familyName = familyName;
        this.mainCharacterName = mainCharacterName;
        this.familyCount = familyCount;
        this.generationsCount = generationsCount;
        this.childrenPerCharacter = childrenPerCharacter;
        this.nameListId = nameListId;
        this.maxAge = maxAge;
        this.maturityAge = maturityAge;
        this.startingYear = startingYear;
    }
}
