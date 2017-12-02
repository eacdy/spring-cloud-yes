package com.itmuch.yes.consumer.domain;

import java.util.Collection;
import java.util.Date;

public class Article {
    /**
     * id
     */
    private Long id;

    /**
     * 审核状态
     */
    private AuditEnum audit;

    /**
     * 标签
     */
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
