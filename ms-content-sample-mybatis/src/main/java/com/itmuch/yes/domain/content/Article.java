package com.itmuch.yes.domain.content;

import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "f_article")
public class Article {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 概述
     */
    private String summary;

    /**
     * 发布时间
     */
    @Column(name = "issue_date")
    private Date issueDate;

    /**
     * 内容
     */
    private String content;
}