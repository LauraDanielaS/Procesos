package com.ufpso.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufpso.api.models.Category;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ArticleUpdateRequestDto {

    @JsonProperty("article_name")
    private String articleName;

    @JsonProperty("article_description")
    private String articleDescription;

    @JsonProperty("article_price")
    private BigDecimal articlePrice;

    @JsonProperty("article_quantity_stock")
    private Integer articleQuantityStock;

    @JsonProperty("article_image")
    private String articleImage;

    @JsonProperty("article_additional_note")
    private String articleAdditionalNotes;

    @JsonProperty("category_id")
    private Category category_id;



}
