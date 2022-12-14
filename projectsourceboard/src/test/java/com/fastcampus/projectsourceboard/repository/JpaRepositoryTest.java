package com.fastcampus.projectsourceboard.repository;

import com.fastcampus.projectsourceboard.config.JpaConfig;
import com.fastcampus.projectsourceboard.domain.Article;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(JpaConfig.class)
@DisplayName("JPA 연결 테스트")
class JpaRepositoryTest {

    private ArticleRepository articleRepository;
    private ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givienTestData_whenSelecting_thenWorksFine() {

        List<Article> articles = articleRepository.findAll();

        assertThat(articles)
                .isNotNull()
                .hasSize(123);

    }

    @DisplayName("insert 테스트")
    @Test
    void givienTestData_whenInserting_thenWorksFine() {

       long previousCount = articleRepository.count();
       Article article = Article.of("new article", "new content", "#spring");

       Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));

        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);

    }

    @DisplayName("update 테스트")
    @Test
    void givienTestData_whenUpdating_thenWorksFine() {

        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);

        Article savedArticle = articleRepository.saveAndFlush(article);

        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);

    }

    @DisplayName("delete 테스트")
    @Test
    void givienTestData_whenDeleting_thenWorksFine() {

        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentsSize = article.getArticleComments().size();

        articleRepository.delete(article);

        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);

    }


}









