<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link href="/static/css/blog/base.css" rel="stylesheet">
    <link href="/static/css/blog/index.css" rel="stylesheet">
    <link href="/static/css/bootstrap3.3.5.css" rel="stylesheet">
    <link href="/static/fileinput/css/fileinput.css" rel="stylesheet">
</head>
<body>
<header>
    <div class="logo" style="font-size: 36px;color: #ffffff">煇の征程</div>
    <nav id="nav">
        <ul>
            <li><a href="/blog/home" >博客园</a></li>
            <li><a href="/game/home" id="selected">游戏乐园</a></li>
        </ul>
    </nav>
</header>
<div class="container my-4">
    <div class="form-group">
        <div class="file-loading">
            <input id="fileupload" type="file" class="file" multiple>
        </div>
    </div>
</div>
</body>
<script language="javascript" type="text/javascript" src="/static/js/jquery-3.3.1.js"></script>
<script language="javascript" type="text/javascript" src="/static/js/bootstrap3.3.5.js"></script>
<script language="javascript" type="text/javascript" src="/static/fileinput/js/fileinput.js"></script>
<script>
    $("#fileupload").fileinput({
        allowedFileExtensions: ['jpg', 'gif', 'png', 'doc', 'docx', 'pdf', 'xlsx', 'txt', 'rar', 'zip'],
        showPreview: true,
        uploadUrl:"/file/fileupload",
        uploadAsync: true,
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        maxFileCount: 10
    }).on("fileuploaded", function (event, data) {
        //alert(data);
    });
</script>
