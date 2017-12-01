package com.itmuch.cloud.study.user.feign;

import com.itmuch.cloud.study.user.domain.Article;
import com.itmuch.crawler.core.convert.AjaxResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@FeignClient(name = "ms-content-sample")
public interface ArticleFeignClient {
    @GetMapping("/articles")
    HashMap<Object, Object> search(
            @RequestParam("keyword") String keyword,
            @RequestParam("audit") String audit,
            @RequestParam("page") int page,
            @RequestParam("rows") int rows,
            @RequestParam("sort") String sort,
            @RequestParam("order") String order
    );

    @PostMapping("/articles")
    AjaxResult add(@RequestBody Article article);

    @PutMapping("/articles")
    AjaxResult edit(@RequestBody Article article);
}
