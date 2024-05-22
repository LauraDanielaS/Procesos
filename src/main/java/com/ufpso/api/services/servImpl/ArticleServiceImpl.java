package com.ufpso.api.services.servImpl;

import com.ufpso.api.Messages;
import com.ufpso.api.dtos.ArticleUpdateRequestDto;
import com.ufpso.api.exception.AlreadyExists;
import com.ufpso.api.exception.NotFoundException;
import com.ufpso.api.models.Article;
import com.ufpso.api.repository.ArticleRepository;
import com.ufpso.api.services.ArticleService;
import com.ufpso.api.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryService categoryService;


    public ArticleServiceImpl(ArticleRepository articleRepository, CategoryService categoryService) {
        this.articleRepository = articleRepository;
        this.categoryService = categoryService;

    }

    public Article getArticleById(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new NotFoundException(Messages.ARTICLE_NOT_FOUND.getMessage()));
    }

    public List<Article> getAllArticle() {
        return (List<Article>) articleRepository.findAll();
    }

    public void createArticle(Article article) {
        categoryService.getCategoryById(article.getCategory().getCategoryId());
        this.validateArticle(article.getName());
        articleRepository.save(article);
    }

    public Article updateArticle(Long articleId, ArticleUpdateRequestDto article) {
        categoryService.getCategoryById(article.getCategoryId().getCategoryId());

        Article article1 = this.articleRepository.findById(articleId).orElseThrow(()->  new NotFoundException(Messages.ARTICLE_NOT_FOUND.getMessage()));

        Optional<Article> article2 = this.articleRepository.findByNameAndArticleIdNot(article.getName(), articleId);

        if (article2.isPresent()){
            throw new AlreadyExists(Messages.ARTICLE_ALREADY_EXISTS.getMessage());
        }

        article1.updateArticle(
                article.getName(),
                article.getDescription(),
                article.getPrice(),
                article.getQuantityStock(),
                article.getImage(),
                article.getAdditionalNotes(),
                article.getCategoryId()
        );
        return articleRepository.save(article1);
    }

    @Override
    public void deleteArticleById(Long articleId) {
        Article article1 = articleRepository.findById(articleId).orElseThrow(() -> new NotFoundException(Messages.ARTICLE_NOT_FOUND.getMessage()));
        articleRepository.deleteById(article1.getArticleId());
    }

    public void validateArticle(String name){
        if (articleRepository.existsByNameIgnoreCase(name)){
            throw new AlreadyExists(Messages.ARTICLE_ALREADY_EXISTS.getMessage());
        }
    }
}
