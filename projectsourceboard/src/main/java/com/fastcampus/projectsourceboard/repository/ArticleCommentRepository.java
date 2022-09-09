package com.fastcampus.projectsourceboard.repository;

import com.fastcampus.projectsourceboard.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
