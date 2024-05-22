package com.ufpso.api;

import lombok.Getter;

@Getter
public enum Messages {

    ARTICLE_NOT_FOUND("Article not found"),
    ARTICLE_ALREADY_EXISTS("An article with the entered name already exists"),
    ARTICLE_CREATED("Item successfully registered"),
    ARTICLE_DELETE("Item successfully removing"),

    CATEGORY_NOT_FOUND("Category not found"),
    CATEGORY_ALREADY_EXISTS("An category with the entered name already exists"),
    CATEGORY_NOT_EXISTS("Category you are trying to update does not exist"),
    CATEGORY_DELETE_NOT_EXISTS("Category you are trying to removing does not exist"),
    CATEGORY_DELETE("Category successfully removing"),
    CATEGORY_CREATED("Category successfully registered");


    private final String message;

    Messages(String message) {
        this.message = message;
    }

}
