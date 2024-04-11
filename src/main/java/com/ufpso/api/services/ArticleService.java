package com.ufpso.api.services;

import com.ufpso.api.dtos.ArticleUpdateRequestDto;
import com.ufpso.api.models.Article;

import java.util.List;

public interface ArticleService {

    Article getArticleById(Long articleId);

    List<Article> getAllArticle();

    void createArticle(Article article);

    Article updateArticle(Long articleId, ArticleUpdateRequestDto article);

}
