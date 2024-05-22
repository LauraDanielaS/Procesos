package com.ufpso.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "article")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long articleId;

    @NotNull(message = "name is required")
    @Size(min = 1, max = 255, message = "The name of the article must have a minimum of one character and a maximum of 255")
    @Column(name = "name", unique = true)
    private String name;

    @NotNull(message = "description is required")
    @Size(min = 1, max = 255, message = "The description of the article must have a minimum of one character and a maximum of 255")
    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @NotNull(message = "The price is required")
    @DecimalMin(value = "0.01", message = "price must be greater than or equal to 0.01")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @NotNull(message = "The stock quantity must be minimum 1")
    @Max(value = 9999, message = "the price must be less than or equal to 9999")
    @Min(value = 1, message = "price must be greater than or equal to 1" )
    @Column(name = "quantity_stock", nullable = false)
    private Integer quantityStock;

    @NotNull(message = "image is required")
    @Size(min = 10, message = "The image of the article must have a minimum of ten character")
    @Column(name = "image")
    private String image;

    @NotNull(message = "additional notes is required")
    @Size(min = 1, max = 255, message = "The additional notes of the article must have a minimum of one character and a maximum of 255")
    @Column(name = "additional_notes")
    private String additionalNotes;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "category is required")
    private Category category;

    public void updateArticle(String name, String articleDescription, BigDecimal articlePrice, Integer articleQuantityStock, String articleImage, String articleAdditionalNotes, Category category) {
        this.name = name;
        this.description = articleDescription;
        this.price = articlePrice;
        this.quantityStock = articleQuantityStock;
        this.image = articleImage;
        this.additionalNotes = articleAdditionalNotes;
        this.category = category;
    }

}