<#include "common/layout.ftl">
<!DOCTYPE HTML>
<html>
<head>
<@head title="登录">
</@head>
</head>
<#include "common/ie9.ftl">
<body>
<#include "common/header.ftl">
<section class="content-wrap">
    <div class="container">
        <div class="row">
            <main class="col-md-12 main-content">
                <div class="col-md-4"></div>
                <article class="post col-md-4">
                    <div class="post-head">
                        <h1>登录</h1>
                    </div>
                    <div class="post-body" >
                        <a href="${ctx}/login/github" class="btn btn-primary">
                            <i class="fa fa-github"></i> GitHub登录
                        </a>
                    </div>
                </article>
            </main>
        </div>
    </div>
</section>
<#include "common/footer.ftl">
</body>
</html>