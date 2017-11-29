<#include "common/layout.ftl">
<!DOCTYPE HTML>
<html>
<head>
<@head title="${article.title}"></@head>
</head>
<#include "common/ie9.ftl">
<body>
<@body>
<main class="col-md-8 main-content m-post">
    <p id="process"></p>
    <article class="post">
        <div class="post-head">
            <h1 id="${article.title}">${article.title}</h1>
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

        <div class="post-body">${article.content}</div>

    <#--<div class="post-footer">-->
    <#--<b>原文链接</b>：<a href="${article.url}">${article.title}</a>-->
    <#--</div>-->
    </article>

    <div class="article-nav prev-next-wrap clearfix">

        <#if prev??>
            <a href="${ctx}/articles/${(prev.id)?c}" class="pre-post btn btn-default">
                <i class="fa fa-angle-left fa-fw"></i>上一篇</a>
        </#if>

        <#if next??>
            <a href="${ctx}/articles/${(next.id)?c}" class="next-post btn btn-default">
                下一篇<i class="fa fa-angle-right fa-fw"></i>
            </a>
        </#if>
    </div>
</main>
</@body>
</body>
</html>