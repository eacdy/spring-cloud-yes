package com.itmuch.crawler.domain.content;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itmuch.crawler.core.convert.LongToStringJsonSerializer;
import com.itmuch.crawler.domain.AuditEnum;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Collection;
import java.util.Date;

@Document(indexName = "article_index", type = "article_type")
@Data
@Builder
@ToString
public class Article {
    /**
     * id
     */
    @Id
    @JsonSerialize(using = LongToStringJsonSerializer.class)
    private Long id;

    /**
     * 审核状态
     */
    @Field(type = FieldType.String, store = true)
    private AuditEnum audit;

    /**
     * 标签
     */
    @Field(type = FieldType.String, store = true)
    private Collection<String> tags;

    /**
     * 标题
     */
    private String title;

    /**
     * 发布时间
     */
    private Date issueDate;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 内容
     */
    private String content;
}
