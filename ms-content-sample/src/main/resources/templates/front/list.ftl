<#include "common/layout.ftl">
<!DOCTYPE HTML>
<html>
<head>
<@head title="${title}"></@head>
</head>
<#include "common/ie9.ftl">
<body>
<@body>
<main class="col-md-8 main-content">
    <#if (page.content)??>
        <#list page.content as article>
            <article class="post">
                <div class="post-content">
                    <div class="post-head home-post-head">
                        <h1 class="post-title">
                            <a href="${ctx}/articles/${(article.id)?c}">
                            ${article.title}
                            </a>
                        </h1>
                        <div class="post-meta"> &bull;
                            <time class="post-date">
                            ${(article.issueDate?string('yyyy-MM-dd'))!'无数据'}
                            </time>
                        </div>
                    </div>
                    <p class="brief">
                    ${article.summary}...
                    </p>
                </div>
                <footer class="post-footer clearfix">
                    <div class="pull-left tag-list">
                        <div class="post-meta">
                            <#if (article.tags)??>
                                <span class="fa-wrap">
                                    <i class="fa fa-tags"></i>
                                    <span class="tags-meta">
                                        <#list article.tags as tag>
                                            <a href="${ctx}/tags/${tag}/1">${tag}</a>
                                        </#list>
                                    </span>
                                </span>
                            </#if>
                            <span class="fa-wrap">
                                    <i class="fa fa-clock-o"></i>
                                    <span class="date-meta">
                                    ${(article.issueDate?string('yyyy-MM-dd'))!'无数据'}
                                    </span>
                                </span>
                        </div>
                    </div>
                    <div class="post-permalink">
                        <a href="${ctx}/articles/${(article.id)?c}" class="btn btn-default">阅读全文</a>
                    </div>
                </footer>
            </article>
        </#list>
    </#if>

<#-- 分页器 TODO 目前spring data es返回的总页数永远是1，有问题，所以暂时分页器先简单写着。 -->
    <nav class="pagination" role="navigation">
        <div id="page-nav">
            <#if (pageNo != 1)>
                <a class="extend next" rel="next" href="${ctx}/page/${pageNo}">
                    <i class='fa fa-angle-left'></i>
                </a>
            </#if>
            <a class="extend next" rel="next" href="/page/${pageNo+1}">
                <i class='fa fa-angle-right'></i>
            </a>
        </div>
    </nav>

</main>
</@body>
</body>
</html>