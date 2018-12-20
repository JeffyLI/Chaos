<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="/static/css/normalize.css">
    <script src="/static/js/static-value.js"></script>
    <script src="/static/js/md5.js"></script>
</head>
<body background="/static/img/banner.jpg">
    <div class="login-bg"></div>
    <div class="login">
        <h1>博罗博罗</h1>
        <input type="text" name="u" placeholder="用户名" id="account" />
        <input type="password" name="p" placeholder="密码" id="password" />
        <button type="submit" class="btn btn-primary btn-block btn-large" onclick="login()">登录</button>
        <a href="/login/register">注册</a>
    </div>
</body>
</html>
<script language="javascript" type="text/javascript" src="/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    $(document).keydown(function(event){
        if(event.keyCode == 13) {
            login();
        }
    });

    function login() {
        var account=$("#account").val();
        var password=$("#password").val();
        var code='${code}';
        if(account==""){
            alert("请填写账号！");
            return;
        }
        if(password==""){
            alert("请填写密码！");
            return;
        }
        $.ajax({
            url:"/login/loginIn",
            data:{
                account:account,
                password:hex_md5(hex_md5(password)+code),
                code:code
            },
            success:function (data) {
                if(data=="success"){
                    window.location.href="/blog/home";
                }else{
                    alert(data);
                }
            }
        });
    }
</script>