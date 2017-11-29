package com.itmuch.crawler.domain.content;

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
//    @JsonSerialize(using = LongToStringJsonSerializer.class)
    private Long id;

    /**
     * 审核状态
     */
    @Field(type = FieldType.String, store = true)
    private AuditEnum audit;

    /**
     * 分类
     */
    private String category;

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
     * hash，是url的md5值
     */
    private String hash;

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

    /**
     * 点击数
     */
    private int click;
}
