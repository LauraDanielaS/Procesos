package com.ufpso.api.repository;

import com.ufpso.api.models.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Article> findByNameAndArticleIdNot(String name, Long ArticleId);
}
