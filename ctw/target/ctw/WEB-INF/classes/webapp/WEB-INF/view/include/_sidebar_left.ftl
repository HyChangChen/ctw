<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- Sidebar user panel (optional) -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${context}/static/plugins/adminlte/dist/img/fashion2010-04.png" class="img-circle"
                     alt="User Image">
            </div>
            <div class="pull-left info">
                <p>
                <#if Session["user"].fullName??>
                ${Session["user"].fullName}
                <#elseif Session["user"].chinaName??>
                ${Session["user"].chinaName}
                <#else >
                ${Session["user"].loginName}
                </#if>
              </p>
                <!-- Status -->
                <a href="#"><i class="fa fa-circle text-success"></i>

                <#if Session["user"].orgName??>${Session["user"].orgName}
                <#else></#if>
                    <small> <#if Session["user"].postName>${Session["user"].postName}
                                </#if></small>

            </div>
        </div>
        <!-- Sidebar Menu -->
        <ul class="sidebar-menu">
            <!-- Optionally, you can add icons to the links -->
        ${sidebarMenu}
        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>
