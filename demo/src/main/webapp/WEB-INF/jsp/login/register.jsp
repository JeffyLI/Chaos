<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" href="/static/css/normalize.css">
    <script src="/static/js/static-value.js"></script>
    <script src="/static/js/md5.js"></script>
</head>
<body background="/static/img/banner.jpg">
    <div class="login-bg"></div>
    <div class="login">
        <h1>博罗博罗</h1>
        <input type="text" name="u" placeholder="账号名" id="account" />
        <input type="password" name="p" placeholder="密码" id="password" />
        <input type="password" name="p" placeholder="确认密码" id="password2" />
        <button type="submit" class="btn btn-primary btn-block btn-large" onclick="register()">注册</button>
        <a href="/login/login">已有账号?直接登录</a>
    </div>
</body>
</html>
<script language="javascript" type="text/javascript" src="/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    function register(){
        var account=$("#account").val();
        var password=$("#password").val();
        var password2=$("#password2").val();
        if(account==""){
            alert("请填写账号！");
            return;
        }
        if(account.indexOf("@")==-1){
            alert("邮箱格式不正确！");
            return;
        }
        if(password==""){
            alert("请填写密码！");
            return;
        }
        if(password!=password2){
            alert("输入密码不一致，请检查！")
            return;
        }
        $.ajax({
            url:"/login/registerIn",
            data:{
                account:account,
                password:hex_md5(password),
            },
            success:function (data) {
                if(data=="注册成功！"){
                    alert(data);
                    window.location.href=urlhead+"/login/login";
                }else{
                    alert(data);
                }
            }
        });
    }
</script>