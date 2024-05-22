package com.ufpso.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    @NotNull(message = "Category name  is required")
    @Size(min = 1, max = 255, message = "Category name must have a minimum of one character and a maximum of 255 characters")
    private String categoryName;

    public void updateCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
