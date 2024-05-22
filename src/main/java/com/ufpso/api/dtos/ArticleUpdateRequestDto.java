package com.ufpso.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufpso.api.models.Category;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleUpdateRequestDto {

    @JsonProperty("article_name")
    @NotNull(message = "name is required")
    @Size(min = 1, max = 255, message = "The name of the article must have a minimum of one character and a maximum of 255")
    private String name;

    @JsonProperty("article_description")
    @NotNull(message = "description is required")
    @Size(min = 1, max = 255, message = "The description of the article must have a minimum of one character and a maximum of 255")
    private String description;

    @NotNull(message = "The price is required")
    @DecimalMin(value = "0.01", message = "price must be greater than or equal to 0.01")
    @JsonProperty("article_price")
    private BigDecimal price;

    @NotNull(message = "The stock quantity must be minimum 1")
    @Max(value = 9999, message = "the price must be less than or equal to 9999")
    @Min(value = 1, message = "price must be greater than or equal to 1" )
    @JsonProperty("article_quantity_stock")
    private Integer quantityStock;

    @JsonProperty("article_image")
    @NotNull(message = "image is required")
    @Size(min = 10, message = "The image of the article must have a minimum of ten character")
    private String image;

    @JsonProperty("article_additional_note")
    @NotNull(message = "additional notes is required")
    @Size(min = 1, max = 255, message = "The additional notes of the article must have a minimum of one character and a maximum of 255")
    private String additionalNotes;

    @NotNull(message = "category is required")
    @JsonProperty("category_id")
    private Category categoryId;

}




