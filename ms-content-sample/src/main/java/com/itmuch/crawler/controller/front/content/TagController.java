package com.itmuch.crawler.controller.front.content;

import com.google.common.collect.Lists;
import com.itmuch.crawler.domain.content.Article;
import com.itmuch.crawler.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TagController {
    private final ArticleRepository articleRepository;

    @Autowired
    public TagController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/tags/{name}/{pageNo}")
    public String tag(Model model, @PathVariable String name, @PathVariable Integer pageNo) {
        Page<Article> page = this.articleRepository.findByTagsIsIn(
                Lists.newArrayList(name),
                new PageRequest(pageNo - 1, 10, Sort.Direction.DESC, "issueDate")
        );
        model.addAttribute("title", "标签列表");
        model.addAttribute("page", page);
        return "front/list";
    }
}
