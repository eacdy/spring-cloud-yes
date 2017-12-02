package com.itmuch.content.repository;

import com.itmuch.content.domain.content.AuditEnum;
import com.itmuch.content.domain.content.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {

    Page<Article> findByAuditAndIssueDateGreaterThan(AuditEnum audit, long issueDate, Pageable pageable);

    Page<Article> findByAuditAndIssueDateLessThan(AuditEnum audit, long issueDate, Pageable pageable);

    Page<Article> findByTagsIsIn(List<String> tags, Pageable pageable);

    Page<Article> findByAudit(AuditEnum auditEnum, Pageable pageable);

    Stream<Article> findAllByIdIn(Long[] id);
}
