<div class="modal fade" id="main-modal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">任务管理</h4>
            </div>

            <div class="modal-body col-md-12">
                <form role="form" id="main-form">
                    <div class="form-group">
                        <label for="site-name">标题(*)</label>
                        <input type="text" id="id" name="id">
                        <input name="title" id="title" type="text" class="form-control"
                               placeholder="请输入标题">
                    </div>

                    <div class="form-group">
                        <label for="summary">摘要(*)</label>
                        <textarea name="summary" id="summary"  rows="5" class="form-control"
                                  placeholder="请输入摘要"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="content">内容(*)</label>
                        <div id="editor-md">
                        <textarea name="content" id="content" class="form-control"
                                  placeholder="请输内容">
                            </textarea>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="save">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<input type="text" id="httpMethod">