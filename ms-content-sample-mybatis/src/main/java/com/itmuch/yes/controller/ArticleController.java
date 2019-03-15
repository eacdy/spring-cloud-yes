package com.itmuch.yes.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itmuch.yes.PageVoWithSort4Mybatis;
import com.itmuch.yes.core.constant.ConstantsCode;
import com.itmuch.yes.core.convert.AjaxResult;
import com.itmuch.yes.core.exception.BizRuntimeException;
import com.itmuch.yes.domain.content.Article;
import com.itmuch.yes.repository.content.ArticleMapper;
import com.itmuch.yes.util.mapper.BeanMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.Set;

/**
 * @author itmuch.com
 */
@RestController
@RequestMapping("/articles")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleController {
    private final ArticleMapper articleMapper;

    @GetMapping("/{id}")
    public AjaxResult<Article> selectById(@PathVariable Long id) {
        return new AjaxResult<Article>().success(
                this.articleMapper.selectByPrimaryKey(id)
        );
    }

    @GetMapping("")
    public PageInfo<Article> search(
            Principal principal,
            @RequestParam(required = false) String keyword,
            PageVoWithSort4Mybatis pageVo
    ) {
        if (principal instanceof KeycloakPrincipal) {
            AccessToken accessToken = ((KeycloakPrincipal) principal).getKeycloakSecurityContext().getToken();
            String preferredUsername = accessToken.getPreferredUsername();
            AccessToken.Access realmAccess = accessToken.getRealmAccess();
            Set<String> roles = realmAccess.getRoles();
            log.info("当前登录用户：{}, 角色：{}", preferredUsername, roles);
        }

        PageHelper.startPage(pageVo.getPage(), pageVo.getRows(), pageVo.getSort());

        if (StringUtils.isEmpty(keyword)) {
            return new PageInfo<>(
                    this.articleMapper.selectAll()
            );
        }

        return new PageInfo<>(
                this.articleMapper.searchByCondition(keyword)
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
        article.setIssueDate(new Date());

        this.articleMapper.insertSelective(article);
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
        Article articleInDB = this.articleMapper.selectByPrimaryKey(article.getId());
        if (articleInDB == null) {
            throw new BizRuntimeException(
                    ConstantsCode.DATA_NOT_FOUND,
                    "该文章不存在",
                    "该文章不存在");
        }

        // 目前只要经过人工编辑，就自动审核。防止这边在编辑，那边采集又覆盖掉。
        BeanMapper.map(article, articleInDB);
        this.articleMapper.updateByPrimaryKey(articleInDB);
        return new AjaxResult().success();
    }

}
