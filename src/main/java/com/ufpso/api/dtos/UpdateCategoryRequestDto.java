package com.ufpso.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryRequestDto {

    @NotNull(message = "Category name is required")
    @Size(min = 1, max = 255, message = "Category name must have a minimum of one character and a maximum of 255 characters")
    @JsonProperty("category_name")
    private String categoryName;
}