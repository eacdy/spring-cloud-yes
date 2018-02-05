package com.itmuch.yes.domain.content;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itmuch.yes.core.convert.LongToStringJsonSerializer;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

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
