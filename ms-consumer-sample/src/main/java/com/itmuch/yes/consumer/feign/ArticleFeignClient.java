package com.itmuch.yes.consumer.feign;

import com.itmuch.yes.consumer.domain.Article;
import com.itmuch.yes.consumer.feign.config.FeignConfiguration;
import com.itmuch.yes.core.convert.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@FeignClient(name = "ms-content-sample", configuration = FeignConfiguration.class)
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
