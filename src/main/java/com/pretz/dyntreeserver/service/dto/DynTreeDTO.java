package com.pretz.dyntreeserver.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Year;

@Builder @Getter
public final class DynTreeDTO {

    @NotBlank private final String familyName;
    @Min(value = 3) private final int familyCount;
    @Min(value = 2) private final int generationsCount;
    private final double childrenPerCharacter;
    private final long nameListId;
    private final int maxAge;
    private final int maturityAge;
    @NotNull private final Year startingYear;
    @NotBlank private final String mainCharacterName;

    @JsonCreator
    public DynTreeDTO(@JsonProperty("family_name") String familyName,
                      @JsonProperty("family_count") Integer familyCount,
                      @JsonProperty("generations_count") Integer generationsCount,
                      @JsonProperty("children_per_character") Double childrenPerCharacter,
                      @JsonProperty("name_list") Long nameListId,
                      @JsonProperty("max_age") Integer maxAge,
                      @JsonProperty("maturity_age") Integer maturityAge,
                      @JsonProperty("starting_year") Year startingYear,
                      @JsonProperty("main_character") String mainCharacterName) {

        this.familyName = familyName;
        this.familyCount = familyCount;
        this.generationsCount = generationsCount;
        this.childrenPerCharacter = childrenPerCharacter;
        this.nameListId = nameListId;
        this.maxAge = maxAge;
        this.maturityAge = maturityAge;
        this.startingYear = startingYear;
        this.mainCharacterName = mainCharacterName;
    }
}
