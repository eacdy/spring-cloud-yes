package com.itmuch.crawler.controller.admin.content;

import com.itmuch.crawler.core.constants.ConstantsApp;
import com.itmuch.crawler.domain.content.Article;
import com.itmuch.crawler.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/articles")
public class ArticlePreviewController {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticlePreviewController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * 文章预览
     *
     * @param model model
     * @param id    id
     * @return 文章
     */
    @GetMapping("/preview/{id}")
    public String preview(Model model, @PathVariable Long id) {
        Article articleInDB = this.articleRepository.findOne(id);

        // 文章不存在
        if (articleInDB == null) {
            return ConstantsApp.PAGE_NOT_FOUND;
        }
        model.addAttribute("article", articleInDB);

        return "front/article";
    }
}
