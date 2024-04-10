package com.ufpso.api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "article")

public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "article_name", nullable = false, unique = true, length = 100)
    private String articleName;

    @Column(name = "article_description", nullable = false, length = 200)
    private String articleDescription;

    @Column(name = "article_price", nullable = false)
    private BigDecimal articlePrice;

    @Column(name = "article_quantity_stock", nullable = false)
    private Integer articleQuantityStock;

    @Column(name = "article_image")
    private String articleImage;

    @Column(name = "article_additional_notes")
    private String articleAdditionalNotes;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}


