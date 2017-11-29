<header class="main-header">
    <!-- Logo -->
    <a href="${ctx}/admin" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>I</b>TC</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>ITMuch</b>Schedule</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <li>
                    <a target="_blank" href="${ctx}/">查看首页</a>
                </li>

                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="头像" class="user-image" alt="User Image">
                        <span class="hidden-xs">用户名</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="头像" class="img-circle" alt="User Image">
                            <p>
                                用户签名
                            </p>
                        </li>
                        <!-- Menu Body -->
                        <#--<li class="user-body">-->
                            <#--<div class="row">-->
                                <#--<div class="col-xs-4 text-center">-->
                                    <#--<a href="#">Followers</a>-->
                                <#--</div>-->
                                <#--<div class="col-xs-4 text-center">-->
                                    <#--<a href="#">Sales</a>-->
                                <#--</div>-->
                                <#--<div class="col-xs-4 text-center">-->
                                    <#--<a href="#">Friends</a>-->
                                <#--</div>-->
                            <#--</div>-->
                            <#--<!-- /.row &ndash;&gt;-->
                        <#--</li>-->
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <#--<div class="pull-left">-->
                                <#--<a href="#" class="btn btn-default btn-flat">Profile</a>-->
                            <#--</div>-->
                            <div class="pull-right">
                                <a href="${ctx}/logout" class="btn btn-default btn-flat">退出</a>
                            </div>
                        </li>
                    </ul>
                </li>
                <!-- Control Sidebar Toggle Button -->
                <li>
                    <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                </li>
            </ul>
        </div>
    </nav>
</header>