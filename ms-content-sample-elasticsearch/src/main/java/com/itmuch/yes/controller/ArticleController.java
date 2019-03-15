package com.itmuch.yes.controller;

import com.google.common.collect.Maps;
import com.itmuch.yes.core.constant.ConstantsCode;
import com.itmuch.yes.core.convert.AjaxResult;
import com.itmuch.yes.core.exception.BizRuntimeException;
import com.itmuch.yes.core.page.PageVoWithSort;
import com.itmuch.yes.domain.content.Article;
import com.itmuch.yes.repository.ArticleRepository;
import com.itmuch.yes.util.mapper.BeanMapper;
import com.itmuch.yes.util.snowflake.IDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.validation.annotation.Validated;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

@RestController
@RequestMapping("/articles")
@Slf4j
public class ArticleController {
    private final ElasticsearchTemplate elasticsearchTemplate;
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ElasticsearchTemplate elasticsearchTemplate, ArticleRepository articleRepository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.articleRepository = articleRepository;
    }

    @GetMapping("")
    public HashMap<Object, Object> search(
            Principal principal,
            @RequestParam(required = false) String keyword,
            PageVoWithSort pageVo
    ) {

        if (principal instanceof KeycloakPrincipal) {
            AccessToken accessToken = ((KeycloakPrincipal) principal).getKeycloakSecurityContext().getToken();
            String preferredUsername = accessToken.getPreferredUsername();
            AccessToken.Access realmAccess = accessToken.getRealmAccess();
            Set<String> roles = realmAccess.getRoles();
            log.info("当前登录用户：{}, 角色：{}", preferredUsername, roles);
        }

        String escape = keyword == null ? "" : QueryParser.escape(keyword);

        Criteria criteria = new Criteria("title").fuzzy(escape)
                .or(new Criteria("content").fuzzy(escape));

        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria)
                .setPageable(
                        new PageRequest(pageVo.getPage(), pageVo.getRows(), pageVo.getSpringSort())
                );
        Page<Article> articles = this.elasticsearchTemplate.queryForPage(criteriaQuery,
                Article.class);

        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("content", articles.getContent());
        map.put("totalElements", articles.getTotalElements());
        map.put("totalPages", articles.getTotalPages());
        return map;
    }

    /**
     * 添加文章
     *
     * @param article 文章
     * @return 编辑结果
     */
    @PostMapping("")
    public AjaxResult add(@RequestBody @Validated Article article) {
        article.setId(IDGenerator.genId());
        article.setIssueDate(new Date());

        this.articleRepository.save(article);
        return new AjaxResult().success();
    }

    /**
     * 编辑文章
     *
     * @param article 文章
     * @return 编辑结果
     */
    @PutMapping("")
    public AjaxResult edit(@RequestBody @Validated Article article) {
        Article articleInDB = this.articleRepository.findOne(article.getId());
        if (articleInDB == null) {
            throw new BizRuntimeException(
                    ConstantsCode.DATA_NOT_FOUND,
                    "该文章不存在",
                    "该文章不存在");
        }

        BeanMapper.map(article, articleInDB);
        this.articleRepository.save(articleInDB);
        return new AjaxResult().success();
    }
}
