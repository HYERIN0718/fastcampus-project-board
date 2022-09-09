package com.fastcampus.projectsourceboard.repository;

import com.fastcampus.projectsourceboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}