package com.itmuch.crawler.controller.front.content;

import com.itmuch.crawler.core.constants.ConstantsApp;
import com.itmuch.crawler.domain.AuditEnum;
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
public class ArticleController {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * 首页
     *
     * @param model model
     * @return 文章列表
     */
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "首页");
        return this.queryArticleList(model, 1);
    }

    /**
     * 列表页
     *
     * @param model  model
     * @param pageNo 文章列表
     * @return 列表
     */
    @GetMapping("/page/{pageNo}")
    public String page(Model model, @PathVariable Integer pageNo) {
        model.addAttribute("title", "文章列表");
        return this.queryArticleList(model, pageNo);
    }

    private String queryArticleList(Model model, Integer pageNo) {
        long count = this.articleRepository.count();
        Page<Article> page = null;
        if (count != 0) {
            page = this.articleRepository.findByAudit(
                    AuditEnum.NOT_YET,
                    new PageRequest(pageNo - 1, 10, Sort.Direction.DESC, "issueDate")
            );
        }
        model.addAttribute("page", page)
                .addAttribute("pageNo", pageNo);
        return "front/list";
    }


    /**
     * 内容页
     *
     * @param model model
     * @param id    文章ID
     * @return ID对应的文章
     */
    @GetMapping("/articles/{id}")
    public String article(Model model, @PathVariable Long id) {
        Article articleInDB  = this.articleRepository.findOne(id);

        // 文章不存在
        if (articleInDB == null) {
            return ConstantsApp.PAGE_NOT_FOUND;
        }

        // 如果该文章的审核状态不是通过状态，返回404
        if (!AuditEnum.PASSED.equals(articleInDB.getAudit())) {
            return ConstantsApp.PAGE_NOT_FOUND;
        }

        int click = articleInDB.getClick();
        articleInDB.setClick(click + 1);
        this.articleRepository.save(articleInDB);

        long issueDate = articleInDB.getIssueDate().getTime();

        Article next = this.articleRepository.findByAuditAndIssueDateGreaterThan(
                AuditEnum.PASSED,
                issueDate,
                new PageRequest(0, 10, Sort.Direction.ASC, "issueDate")
        ).getContent()
                .stream()
                .findFirst()
                .orElse(null);

        Article prev = this.articleRepository.findByAuditAndIssueDateLessThan(
                AuditEnum.PASSED,
                issueDate,
                new PageRequest(0, 10, Sort.Direction.DESC, "issueDate")
        ).getContent()
                .stream()
                .findFirst()
                .orElse(null);

        model.addAttribute("article", articleInDB)
                .addAttribute("next", next)
                .addAttribute("prev", prev);

        return "front/article";
    }
}
