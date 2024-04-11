package com.ufpso.api.controllers;


import com.ufpso.api.dtos.ArticleUpdateRequestDto;
import com.ufpso.api.models.Article;
import com.ufpso.api.services.ArticleService;
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


    /**
     * Traer todos los artículos
     **/
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(this.articleService.getAllArticle());
    }

    /**
     * Traer artículos por Id
     **/
    @GetMapping("/{articleId}")
    public ResponseEntity<Article> getArticleById(@PathVariable("articleId") Long articleId) {
        return ResponseEntity.ok(this.articleService.getArticleById(articleId));
    }

    /**
     * Guardar artículos.
     **/
    @PostMapping
    public ResponseEntity<HttpStatus> createArticle(@RequestBody Article article) {
        this.articleService.createArticle(article);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Actualizar artículos por ID.
     **/
    @PutMapping("/{articleId}")
    public ResponseEntity<Article> updateArticleById(@PathVariable("articleId") Long articleId, @RequestBody ArticleUpdateRequestDto article) {
        return ResponseEntity.ok(this.articleService.updateArticle(articleId, article));
    }

    /**
     * Eliminar artículos por ID.
     **/
    @DeleteMapping("/{articleId}")
    public ResponseEntity<HttpStatus> deleteArticleById(@PathVariable("articleId") Long articleId){
        articleService.deleteArticleById(articleId);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}