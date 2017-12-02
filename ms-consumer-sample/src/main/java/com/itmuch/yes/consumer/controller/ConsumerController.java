package com.itmuch.yes.consumer.controller;

import com.itmuch.yes.consumer.domain.Article;
import com.itmuch.yes.consumer.feign.ArticleFeignClient;
import com.itmuch.yes.consumer.page.PageVo;
import com.itmuch.yes.core.convert.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/articles")
public class ConsumerController {
    @Autowired
    private ArticleFeignClient articleFeignClient;

    @GetMapping("")
    public HashMap<Object, Object> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String audit,
            PageVo pageVo
    ) {
        return this.articleFeignClient.search(
                keyword,
                audit,
                pageVo.getPage(),
                pageVo.getRows(),
                pageVo.getSort(),
                pageVo.getOrder()

        );
    }

    /**
     * 添加文章
     *
     * @param article 文章
     * @return 编辑结果
     */
    @PostMapping("")
    public AjaxResult add(@RequestBody @Validated Article article) {
        return this.articleFeignClient.add(article);
    }

    /**
     * 编辑文章
     *
     * @param article 文章
     * @return 编辑结果
     */
    @PutMapping("")
    public AjaxResult edit(@RequestBody @Validated Article article) {
        return this.articleFeignClient.edit(article);
    }
}
