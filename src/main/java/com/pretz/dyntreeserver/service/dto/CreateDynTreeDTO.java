package com.pretz.dyntreeserver.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CreateDynTreeDTO {

    @JsonProperty("family_name")
    @NotEmpty
    private String familyName;

    @JsonProperty("family_count")
    @Min(value = 3)
    @NotNull
    private Integer familyCount;

    @JsonProperty("generations_count")
    @Min(value = 2)
    @NotNull
    private Integer generationsCount;

    @JsonProperty("children_per_character")
    @NotNull
    private Double childrenPerCharacter;

    @JsonProperty("name_list")
    @NotNull
    private Long nameListId;

    @JsonProperty("max_age")
    @NotNull
    private Integer maxAge;

    @JsonProperty("maturity_age")
    @NotNull
    private Integer maturityAge;

    @JsonProperty("base_date")
    @NotNull
    private Date baseDate;

    @JsonProperty("main_character")
    @NotEmpty
    private String mainCharacter;

    public CreateDynTreeDTO() {
    }

    public String getFamilyName() {
        return familyName;
    }

    public Integer getFamilyCount() {
        return familyCount;
    }

    public Integer getGenerationsCount() {
        return generationsCount;
    }

    public Double getChildrenPerCharacter() {
        return childrenPerCharacter;
    }

    public Long getNameListId() {
        return nameListId;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public Integer getMaturityAge() {
        return maturityAge;
    }

    public Date getBaseDate() {
        return baseDate;
    }

    public String getMainCharacter() {
        return mainCharacter;
    }
}
