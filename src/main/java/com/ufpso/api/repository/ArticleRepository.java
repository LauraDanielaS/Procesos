package com.ufpso.api.repository;

import com.ufpso.api.models.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
