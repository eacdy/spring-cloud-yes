package com.itmuch.yes.repository;

import com.itmuch.yes.domain.content.Article;
import com.itmuch.yes.domain.content.AuditEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Page<Article> findByAuditAndIssueDateGreaterThan(AuditEnum audit, Date issueDate, Pageable pageable);

    Page<Article> findByAuditAndIssueDateLessThan(AuditEnum audit, Date issueDate, Pageable pageable);

    Page<Article> findByTagsIsIn(List<String> tags, Pageable pageable);

    Page<Article> findByAudit(AuditEnum auditEnum, Pageable pageable);

    Stream<Article> findAllByIdIn(Long[] id);
}
