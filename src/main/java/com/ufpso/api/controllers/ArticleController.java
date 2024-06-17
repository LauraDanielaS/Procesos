package com.ufpso.api.controllers;


import com.ufpso.api.enums.Messages;
import com.ufpso.api.dtos.ArticleUpdateRequestDto;
import com.ufpso.api.dtos.GenericRequest;
import com.ufpso.api.dtos.GenericResponse;
import com.ufpso.api.dtos.Response;
import com.ufpso.api.models.Article;
import com.ufpso.api.services.ArticleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController  {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping
    public GenericResponse<List<Article>> getAllArticles() {
        return new GenericResponse<>(new Response<>(this.articleService.getAllArticle(), HttpStatus.OK.value()));
    }

    @GetMapping("/{articleId}")
    public GenericResponse<Article> getArticleById(@PathVariable("articleId") @Positive Long articleId) {
        return new GenericResponse<>(new Response<>(this.articleService.getArticleById(articleId), HttpStatus.OK.value()));
    }

    @PostMapping
    public ResponseEntity<GenericResponse<?>> createArticle(@RequestBody @Valid GenericRequest<Article> article) {
        this.articleService.createArticle(article.requestMessage().getData());
        GenericResponse<?> response = new GenericResponse<>(new Response<>(Messages.ARTICLE_CREATED.getMessage(), HttpStatus.CREATED.value()));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{articleId}")
    public GenericResponse<Article> updateArticleById(@PathVariable("articleId") @Positive Long articleId, @RequestBody @Valid GenericRequest<ArticleUpdateRequestDto> article) {
        return new GenericResponse<>(new Response<>(this.articleService.updateArticle(articleId, article.requestMessage().getData()), HttpStatus.OK.value()));
    }

    @DeleteMapping("/{articleId}")
    public GenericResponse<?> deleteArticleById(@PathVariable("articleId") @Positive Long articleId) {
        articleService.deleteArticleById(articleId);
        return new GenericResponse<>(new Response<>(Messages.ARTICLE_DELETE.getMessage(), HttpStatus.OK.value()));
    }

}