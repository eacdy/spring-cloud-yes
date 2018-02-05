package com.itmuch.yes.repository;

import com.itmuch.yes.domain.content.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@SuppressWarnings("ALL")
@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {
    Stream<Article> findAllByIdIn(Long[] id);
}
