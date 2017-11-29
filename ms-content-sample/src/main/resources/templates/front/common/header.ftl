<nav class="main-navigation">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="navbar-header">
                    <span class="nav-toggle-button collapsed"
                          data-toggle="collapse"
                          data-target="#main-menu"
                          id="mnav">
                    <span class="sr-only">Toggle navigation</span>
                    <i class="fa fa-bars"></i>
                    </span>
                </div>
                <div class="collapse navbar-collapse" id="main-menu">
                    <ul class="menu pull-left">
                        <li role="presentation">
                            <a href="${ctx}/">
                                LOGO
                            </a>
                        </li>
                    </ul>

                <#if user??>
                    <ul class="menu pull-right">

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <img alt="eacdy" src="${user.avatar}" class="img-circle" height="30px;">
                            ${user.username}
                                <b class="caret"></b>
                            </a>

                            <ul class="dropdown-menu">
                                <@shiro.hasRole name="ADMIN">
                                    <li><a href="${ctx}/admin/jobs/index" target="_blank">后台管理</a></li>
                                </@shiro.hasRole>
                                <li><a href="${ctx}/logout">退出</a></li>
                            </ul>
                        </li>
                    </ul>
                <#else>
                    <ul class="menu pull-right">
                        <li role="presentation">
                            <a href="${ctx}/admin/articles/index"><i class="fa fa-user-circle"></i>管理</a>
                        </li>
                    </ul>
                </#if>
                </div>
            </div>
        </div>
    </div>
</nav>