<#macro head title="管理控制台">
    <#include "meta.ftl">
<title>${title}</title>
    <#include "link.ftl">
    <#nested >
</#macro>
<#macro body>
    <#include "header.ftl">
<section class="content-wrap">
    <div class="container">
        <div class="row">
            <#nested >
            <#include "aside.ftl">
        </div>
    </div>
</section>
    <#include "footer.ftl">
    <#include "script.ftl">
</#macro>