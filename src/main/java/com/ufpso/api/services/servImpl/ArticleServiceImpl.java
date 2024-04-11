package com.ufpso.api.services.servImpl;

import com.ufpso.api.dtos.ArticleUpdateRequestDto;
import com.ufpso.api.models.Article;
import com.ufpso.api.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl {
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article getArticleById(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Artículo no encontrado"));
    }

    public List<Article> getAllArticle() {
        return (List<Article>) articleRepository.findAll();
    }

    public void createArticle(Article article) {
        articleRepository.save(article);
    }

    public Article updateArticle(Long articleId, ArticleUpdateRequestDto article) {
        Article article1 = this.articleRepository.findById(articleId).orElseThrow(()-> new RuntimeException("Articulo no encontrado"));

        article1.updateArticle(
                article.getArticleName(),
                article.getArticleDescription(),
                article.getArticlePrice(),
                article.getArticleQuantityStock(),
                article.getArticleImage(),
                article.getArticleAdditionalNotes(),
                article.getCategory_id()
        );
        return articleRepository.save(article1);
    }
}
