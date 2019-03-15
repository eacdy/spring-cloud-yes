package com.itmuch.yes.repository.content;

import com.itmuch.yes.domain.content.Article;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {
    List<Article> searchByCondition(String keyword);
}