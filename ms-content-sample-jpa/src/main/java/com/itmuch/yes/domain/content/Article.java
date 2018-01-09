package com.itmuch.yes.domain.content;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "f_article")
@Data
@Builder
@ToString
public class Article {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 审核状态
     */
    @Column
    @Enumerated(EnumType.STRING)
    private AuditEnum audit;

    /**
     * 标签
     */
    @Column
    private Collection<String> tags;

    /**
     * 标题
     */
    @Column
    private String title;

    /**
     * 发布时间
     */
    @Column
    private Date issueDate;

    /**
     * 摘要
     */
    @Column
    private String summary;

    /**
     * 内容
     */
    @Column
    private String content;
}
