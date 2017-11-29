<#macro head title="管理控制台">
    <#assign ctx=request.contextPath>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${title}</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="${ctx}/admin/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${ctx}/admin/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${ctx}/admin/Ionicons/css/ionicons.min.css">
    <!-- bootstrap-table -->
    <link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.css" rel="stylesheet">
    <!-- sweetalert -->
    <link href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.css" rel="stylesheet">

    <!--markdown editor-->
    <link rel="stylesheet" href="http://springforall.ufile.ucloud.com.cn/static/css/editormd.css"/>

    <!-- Theme style -->
    <link rel="stylesheet" href="${ctx}/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${ctx}/dist/css/skins/_all-skins.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    <#nested >
</head>
</#macro>
<#macro body>
<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

<#-- 页头 -->
    <#include "header.ftl">

<#-- 左侧导航栏 -->
    <#include "sidebar.ftl">

    <#nested >

    <#include "footer.ftl">

<#--右侧的设置按钮-->
    <#include "control-bar.ftl">
</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="${ctx}/admin/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${ctx}/admin/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- bootstrap-table -->
<script src="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.js"></script>
<!-- sweetalert -->
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert-dev.js"></script>

<#--editor-->
<script src="${ctx}/ckeditor-dev-4.7.3/ckeditor.js"></script>
<#--<script src="http://springforall.ufile.ucloud.com.cn/static/js/editormd.js"></script>-->


<!-- serializeJSON -->
<script type="text/javascript" src="${ctx}/webjars/jquery.serializeJSON/2.8.1/jquery.serializejson.min.js"></script>
<!-- SlimScroll -->
<script src="${ctx}/admin/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${ctx}/admin/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${ctx}/dist/js/adminlte.min.js"></script>
</body>
</#macro>
