package com.itmuch.crawler.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
    /**
     * 文章管理页面
     *
     * @return 文章管理页
     */
    @GetMapping("/articles/index")
    public String articles() {
        return "admin/contents/articles/index";
    }
}
