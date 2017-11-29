<#include "../../common/layout.ftl">
<!DOCTYPE html>
<html>
<@head title="文章管理 - 管理控制台"></@head>
<@body>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            文章
            <small>管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/admin"><i class="fa fa-dashboard"></i> Home</a></li>
            <li><a href="#">文章</a></li>
            <li class="active">文章管理</li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">文章信息</h3>
                    </div>
                    <!-- /.box-header -->
                    <div class="box-body">
                        <div id="toolbar">
                            <div class="box-header with-border">
                                <form class="form-inline">
                                    <select name="audit" id="audit" class="form-group">
                                        <option value="ALL" selected>ALL</option>
                                        <option value="PASSED">PASSED</option>
                                        <option value="NOT_YET">NOT_YET</option>
                                        <option value="FAILED">FAILED</option>
                                    </select>
                                    <!--解决form表单在只有一个input输入框时回车会自动提交表单-->
                                    <input style='display:none' type="text"/>
                                    <input type="text" class="form-control" id="keyword" name="keyword"
                                           placeholder="Search">
                                    <a id="search-me" class="btn btn-primary" href="javascript:void(0)">搜索</a>
                                </form>
                                <button class="btn btn-success" onclick="add()">
                                    <i class="fa fa-edit"></i> 添加
                                </button>
                                <button class="btn btn-success" onclick="edit()">
                                    <i class="fa fa-edit"></i> 编辑
                                </button>

                                <button class="btn btn-primary" onclick="pass()">
                                    <i class="fa fa-edit"></i> 审核通过
                                </button>

                                <button class="btn btn-danger" onclick="fail()">
                                    <i class="fa fa-edit"></i> 审核不通过
                                </button>
                            </div>
                        </div>

                        <table id="table"></table>
                    </div>
                    <!-- /.box-body -->
                </div>
                <!-- /.box -->
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->

    <#include "modal.ftl">
</@body>
<script>
    var editor = CKEDITOR.replace('content', {
        extraPlugins: 'codesnippet',
        codeSnippet_theme: 'monokai_sublime'
    });


    var $table = $('#table');
    // 初始化表格
    $table.bootstrapTable({
        search: false,
        showRefresh: true,
        sidePagination: 'server',
        pageNumber: 1,
        sortOrder: 'desc',
        sortName: 'issueDate',
        pageSize: 50,
        pageList: [50, 100],
        pagination: 'true',
        url: '${ctx}/admin/articles',
        toolbar: '#toolbar',
        queryParams: function (params) {
            // 一页展示几条
            var limit = params.limit;
            // 第几页
            var page = params.offset / limit + 1;

            // 排序ASC/DESC
            var order = params.order;
            // 排序字段
            var sort = params.sort;

            // 搜索词
            var keyword = $('#keyword').val();
            var audit = $('#audit').val();

            var par = {};
            par.page = page;
            par.rows = limit;
            par.order = order;
            if (keyword !== null && keyword !== '') {
                par.keyword = keyword;
            }
            if (audit !== null && audit !== 'ALL') {
                par.audit = audit;
            }
            if (sort !== null && sort !== '') {
                par.sort = sort;
            }
            return par;
        },
        columns: [{
            field: 'state',
            checkbox: true
        }, {
            title: 'ID',
            width: '10%',
            field: 'id'
        }, {
            field: 'title',
            width: '30%',
            title: '标题',

            formatter: function (index, row) {
                return (row.title).substring(0, 25);
            }
        }, {
            field: 'issueDate',
            width: '15%',
            title: '发布时间',
            sortable: true
        }, {
            title: '审核状态',
            width: '8%',
            field: 'audit'
        }, {
            title: '查看',
            formatter: function (index, row) {
                return '<a target="_blank" href="${ctx}/admin/articles/preview/' + row.id + '">预览</a>'
            }
        }],
        responseHandler: function (res) {
            res.rows = res.content;
            res.total = res.totalElements;
            return res;
        }
    });


    // 点击保存按钮
    $('#save').click(function () {
        var formJson = $('#main-form').serializeJSON();
        formJson.content = editor.getData();
        var method = $('#httpMethod').val();
        $.ajax({
            url: '${ctx}/admin/articles',
            dataType: "json",
            data: JSON.stringify(formJson),
            type: method,
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                swal('操作成功', JSON.stringify(data), "success");
            },
            error: function (data) {
                swal('错误', JSON.stringify(data), "error");
            }
        });
    });

    // 点击添加按钮
    function add() {
        // 弹出模态框
        $('#httpMethod').val('POST');
        $("#main-modal").modal();
    }

    // 点击修改按钮
    function edit() {
        var selects = getSelections();
        var select = selects[0];
        var id = getIdSelections();
        if (id === null) {
            swal('错误', '请勾选要修改的任务', "error");
            return;
        }
        if (id.length !== 1) {
            swal('错误', '请勾选, 并确保仅勾选一条任务信息', "error");
            return;
        }
        // 如果允许编辑，准备数据
        $('#id').val(id);
        $('#title').val(select.title);
        $('#summary').val(select.summary);
        editor.setData(select.content);

        // 弹出模态框
        $('#httpMethod').val('PUT');
        $("#main-modal").modal();
    }

    $('#search-me').click(function () {
        {
            $table.bootstrapTable('refresh');
        }
    });

    function pass() {
        var ids = getIdSelections();
        $.ajax({
            url: '${ctx}/admin/articles/passed',
            dataType: "json",
            data: JSON.stringify(ids),
            type: 'PATCH',
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                swal('操作成功', JSON.stringify(data), "success");
            },
            error: function (data) {
                swal('错误', JSON.stringify(data), "error");
            }
        });
    }

    function fail() {
        var ids = getIdSelections();
        $.ajax({
            url: '${ctx}/admin/articles/failed',
            dataType: "json",
            data: JSON.stringify(ids),
            type: 'PATCH',
            contentType: "application/json;charset=utf-8",
            success: function (data) {
                swal('操作成功', JSON.stringify(data), "success");
            },
            error: function (data) {
                swal('错误', JSON.stringify(data), "error");
            }
        });
    }

    // 获取表格选中项（数组）
    function getSelections() {
        return $table.bootstrapTable('getSelections');
    }

    // 获得表格选中项的id数组
    function getIdSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id;
        });
    }
</script>
</html>