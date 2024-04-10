package com.ufpso.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UpdateCategoryRequestDto {

    @JsonProperty("category_name")
    private String categoryName;
}
