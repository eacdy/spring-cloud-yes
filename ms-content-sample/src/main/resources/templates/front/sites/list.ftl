<#include "../common/layout.ftl">
<!DOCTYPE HTML>
<html>
<head>
    <@head title="站点列表">
    </@head>
</head>
<#include "../common/ie9.ftl">
<body>
<#include "../common/header.ftl">

<section class="content-wrap">
    <div class="container">
        <div class="row">
            <main class="col-md-12 main-content">
            <#assign sites=page.content>
            <#list sites as site>
                <div class="post col-md-3">
                    <a href="${ctx}/sites/${(site.id)!}/1">${(site.name)!}</a>
                </div>
            </#list>
            </main>
        </div>
    </div>
</section>
<#include "../common/footer.ftl">
<#--<script src="http://www.itmuch.com/lib/jquery/index.js?rev=2.1.3"></script>-->
</body>
</html>

