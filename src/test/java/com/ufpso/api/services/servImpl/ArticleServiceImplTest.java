package com.ufpso.api.services.servImpl;

import com.ufpso.api.dtos.ArticleUpdateRequestDto;
import com.ufpso.api.exception.AlreadyExists;
import com.ufpso.api.exception.NotFoundException;
import com.ufpso.api.models.Article;
import com.ufpso.api.models.Category;
import com.ufpso.api.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ArticleServiceImplTest {
    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getArticleById_shouldReturnArticle_whenArticleIdExists() {
        // Arrange
        Long articleId;
        articleId = 1L;
        Long categoryId;
        categoryId = 1L;
        BigDecimal price = new BigDecimal("200.00");

        Category category = new Category(categoryId, "Category");
        Article article = new Article(articleId, "Mock Article", "Description article", price,
                12, "image-image", "additional notes", category);
        when(articleRepository.findById(articleId)).thenReturn(Optional.of(article));

        //Act
        Article retrievedArticle = articleService.getArticleById(articleId);

        //Assert
        assertThat(retrievedArticle).isNotNull();
        assertThat(retrievedArticle.getArticleId()).isEqualTo(articleId);
        assertThat(retrievedArticle.getName()).isEqualTo("Mock Article");
    }

    @Test
    public void getArticleById_shouldThrowNotFoundException_whenArticleIdDoesNotExist() {
        // Arrange
        Long articleId = 1L;
        when(articleRepository.findById(articleId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> articleService.getArticleById(articleId));
    }

    @Test
    public void getAllArticles_shouldReturnListOfArticles() {
        // Arrange
        BigDecimal price = new BigDecimal("230.00");
        Category category = new Category(4L, "Category");
        List<Article> articles = Arrays.asList(
                new Article(1L, "Mock Article", "Description article", price,
                        12, "image-image", "additional notes", category),
                new Article(2L, "Mock Article 2", "Description article", price,
                        12, "image-image", "additional notes", category)
        );
        when(articleRepository.findAll()).thenReturn(articles);

        // Act
        List<Article> retrievedArticles = articleService.getAllArticle();

        // Assert
        assertThat(retrievedArticles).isNotNull();
        assertThat(retrievedArticles.size()).isEqualTo(2);
        assertThat(retrievedArticles).containsAll(articles);
    }

    @Test
    public void createArticle_shouldSaveArticle_whenArticleNameUnique() {
        // Arrange
        BigDecimal price = new BigDecimal("200.00");

        Category category = new Category(6L, "Category");
        Article article = new Article(null, "Mock Article created", "Description article", price,
                12, "image-image", "additional notes", category);

        // Act
        articleService.createArticle(article);

        // Verify
        verify(articleRepository).save(article);
    }

    @Test
    public void createArticle_shouldThrowAlreadyExistsException_whenArticleNameExists() {
        // Arrange
        BigDecimal price = new BigDecimal("200.00");
        Category category = new Category(6L, "Category");

        Article article = new Article(null, "Mock Article exists", "Description article", price,
                12, "image-image", "additional notes", category);
        when(articleRepository.existsByNameIgnoreCase("Mock Article exists")).thenReturn(true);

        // Act & Assert
        assertThrows(AlreadyExists.class, () -> articleService.createArticle(article));
    }

    @Test
    public void updateArticle_shouldUpdateAndSaveArticle_whenArticleIdExistsAndArticleNameUnique() {
        // Arrange
        Long articleId;
        articleId = 1L;
        Long categoryId;
        categoryId = 1L;
        BigDecimal price = new BigDecimal("200.00");

        Category category = new Category(categoryId, "Category");
        Article existingArticle = new Article(articleId, "Mock Article", "Description article", price,
                12, "image-image", "additional notes", category);
        Article updatedArticle = new Article(articleId,"Mock Article 63", "Description article", price,
                12, "image-image", "additional notes", category);
        when(articleRepository.findById(articleId)).thenReturn(Optional.of(existingArticle));

        // Act
        articleService.updateArticle(articleId, ArticleUpdateRequestDto.builder().name(updatedArticle.getName()).build());

        // Verify
        assertThat(existingArticle.getName()).isEqualTo(updatedArticle.getName());
        verify(articleRepository).save(existingArticle);
    }

    @Test
    public void updateArticle_shouldThrowNotFoundException_whenArticleIdDoesNotExist() {
        // Arrange
        Long articleId = 1L;
        when(articleRepository.findById(articleId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> articleService.updateArticle(articleId, ArticleUpdateRequestDto
                .builder().name("Mock Article").description("Description")
                .price(new BigDecimal("2000.0")).quantityStock(3).image("imagee")
                .additionalNotes("additional notes").categoryId(new Category(1L, "category")).build()));
    }

    @Test
    public void updateArticle_shouldThrowAlreadyExistsException_whenArticleNameExistsForDifferentId() {
        // Arrange
        Long articleId = 1L;

        Category category = new Category(1L, "Category");
        Article existingArticle = new Article(articleId, "Mock Article", "Description article", new BigDecimal("200.00"),
                12, "image-image", "additional notes", category);
        Article updatedArticle = new Article(2L, "Mock Article", "Description article", new BigDecimal("200.00"),
                12, "image-image", "additional notes", category);

        when(articleRepository.findById(articleId)).thenReturn(Optional.of(existingArticle));
        when(articleRepository.findByNameAndArticleIdNot(updatedArticle.getName(), articleId)).thenReturn(Optional.of(updatedArticle));

        // Act & Assert
        assertThrows(AlreadyExists.class, () -> articleService.updateArticle(articleId, ArticleUpdateRequestDto.builder()
                .name("Mock Article").description("Description")
                .price(new BigDecimal("2000.0")).quantityStock(3).image("imagee")
                .additionalNotes("additional notes").categoryId(new Category(1L, "category")).build()));
    }

    @Test
    public void deleteArticle_shouldDeleteArticle_whenArticleIdExists() {
        // Arrange
        Long articleId = 1L;
        Category category = new Category(1L, "Category");

        Article article = new Article(articleId, "Mock Article", "Description article", new BigDecimal("200.00"),
                12, "image-image", "additional notes", category);
        when(articleRepository.findById(articleId)).thenReturn(Optional.of(article));

        // Act
        articleService.deleteArticleById(articleId);
    }
}