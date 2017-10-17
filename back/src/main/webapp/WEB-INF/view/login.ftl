<head>
    <#include "macro/_global_macro.ftl">
   <#-- <#import "../include/spring.ftl" as spring/>-->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>登录系统</title>
    <link href="${context}/static/site/css/login.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="${context}/static/site/images/ico/favicon.ico"/>
</head>
<body>

<div id="login">
    <div id="login_header">
        <h1 class="login_logo">
            <a href="/login"><img src="${context}/static/site/images/login/login_logo.gif"/></a>
        </h1>

        <div class="login_headerContent">
            <div class="navList">
                <ul>
                    <li><a href="#"></a></li>
                    <li><a href="#"></a></li>
                    <li><a href="#" target="_blank"></a></li>
                </ul>
            </div>
            <h2 class="login_title"><img src="${context}/static/site/images/login/login_title.png"/></h2>
        </div>
    </div>
    <div id="login_content">
        <div class="loginForm">
            <form action="${context}/login" method="post">
            <#if error??> <font color="red" size="+3"> ${error}  </font>   </#if>
                <p>
                    <label>用户名：</label>
                    <input type="text" name="username" id="username" size="20" class="login_input"/>
                </p>

                <p>
                    <label>密码：</label>
                    <input type="password" name="password" id="password" size="20" class="login_input"/>
                </p>

                <p>
                    <label> 自动登录 </label>
                     <span>    <input type="checkbox" value="remember-me"
                                      name="rememberMe"></span>

                </p>

                <div class="login_bar">
                    <input class="sub" type="submit" value=" "/>
                </div>
            </form>
        </div>
        <div class="login_banner"><img src="${context}/static/site/images/login/login_banner.jpg"/></div>
        <div class="login_main">
            <ul class="helpList">
                <li><a href="#"></a></li>
                <li><a href="#"></a></li>
                <li><a href="#"></a></li>
                <li><a href="#"></a></li>
            </ul>
            <div class="login_inner">

            </div>
        </div>
    </div>
    <div id="login_footer">

    </div>
</div>
</body>
</html>