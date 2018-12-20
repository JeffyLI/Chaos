<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link href="/static/css/blog/base.css" rel="stylesheet">
    <link href="/static/css/blog/index.css" rel="stylesheet">
    <link href="/static/css/blog/m.css" rel="stylesheet">
    <link href="/static/css/blog/info.css" rel="stylesheet">
    <link href="/static/css/bootstrap3.3.5.css" rel="stylesheet">
    <link href="/static/summernote-dist/summernote.css" rel="stylesheet">
</head>
<body>
<header>
    <div class="logo" style="font-size: 36px;color: #ffffff">煇の征程</div>
    <nav id="nav">
        <ul>
            <li><a href="/blog/home" id="selected">博客园</a></li>
            <li><a href="/game/home">游戏乐园</a></li>
        </ul>
    </nav>
</header>
<article>

    <div class="l_box">
        <!--
        <div class="about_me">
            <h2>关于我</h2>
            <ul>
                <i><img src="/static/img/blog/4.jpg"></i>
                <p><b>李健煇</b>，90后IT宅男，贪吃贪睡，最喜欢不务正业，搞东搞西，特开此站，记录荒唐人生。</p>
            </ul>
        </div>
        -->
        <div class="search">
            <!--
            <form action="/e/search/index.php" method="post" name="searchform" id="searchform">
                <input name="keyboard" id="keyboard" class="input_text" value="请输入关键字词" style="color: rgb(153, 153, 153);" onfocus="if(value=='请输入关键字词'){this.style.color='#000';value=''}" onblur="if(value==''){this.style.color='#999';value='请输入关键字词'}" type="text">
                <input name="Submit" class="input_submit" value="搜索" type="submit">
            </form>
            -->
            <input id="searchWords" class="input_text" placeholder="请输入关键字词" type="text">
            <input name="submit" class="input_submit" value="搜索" type="submit" onclick="search()">
        </div>
        <div class="fenlei">
            <h2>文章分类</h2>
            <ul>
                <li><a href="#" onclick="changeType('')">全部</a></li>
                <li><a href="#" onclick="changeType(1)">博客</a></li>
                <li><a href="#" onclick="changeType(2)">随笔</a></li>
                <li><a href="#" onclick="changeType(3)">小说</a></li>
            </ul>
        </div>
        <div class="fenlei" id="fenlei">
            <h2>创作园</h2>
            <ul>
                <li><a href="#" onclick="generalEdit('博客','save');">写博文</a></li>
                <li><a href="#" onclick="generalEdit('随笔','save');">吐心声</a></li>
                <li><a href="#" onclick="generalEdit('小说','save');">编小说</a></li>
            </ul>
        </div>
        <div class="tuijian">
            <h2>友情链接</h2>
            <ul>
                <li><a href="https://www.bilibili.com/" target="_blank">哔哩哔哩 (゜-゜)つロ 干杯~-bilibili</a></li>
                <li><a href="http://www.yangqq.com" target="_blank">杨青个人博客-免费个人博客模板下载</a></li>
            </ul>
        </div>
        <!--
        <div class="links">
            <h2>友情链接</h2>
            <ul>
                <a href="http://www.yangqq.com">杨青个人博客</a> <a href="http://www.yangqq.com">杨青博客</a>
            </ul>
        </div>
        -->
    </div>
    <div id="articalList"></div>
    <div align="center"><ul id="pageLimit"></ul></div>
</article>
<!--
<footer>
    <p>Design by <a href="http://www.yangqq.com" target="_blank">杨青个人博客</a> <a href="/">蜀ICP备11002373号-1</a></p>
</footer>
--->
</body>
</html>
<script language="javascript" type="text/javascript" src="/static/js/jquery-3.3.1.js"></script>
<script language="javascript" type="text/javascript" src="/static/js/bootstrap3.3.5.js"></script>
<script language="javascript" type="text/javascript" src="/static/js/bootstrap-paginator.js"></script>
<script language="javascript" type="text/javascript" src="/static/summernote-dist/summernote.js"></script>
<script type="text/javascript">
    var typeId;
    var currentPage;
    var pageSize;
    var map;
    var updateId;
    var noArtical;
    var tempcontent;
    var readonly;

    //初始化值
    function initData(){
        typeId='';
        currentPage=1;
        pageSize=3;
        updateId="";
        noArtical='<div style="text-align: center;color: #337ab7;"><h4><strong>作者还未发表该类别文章喔~</strong><h4></div>';
        tempcontent='';
        map=new Map();
        map.set("博客",["标题","简介","内容","博客正文。。。","1"]);
        map.set("随笔",["标题","主题","心声","我想说一说~~","2"]);
        map.set("小说",["标题","序言","正文","本章内容。。。","3"]);
        $("#fenlei").hide();
        checkAuthority();
        getArticalList();
    }

    function checkAuthority(){
        $.ajax({
            url:"/blog/checkAuthority",
            type:"POST",
            success:function(data){
                if(data=="0"){
                    $("#fenlei").show();
                }
                readonly=data;
            }
        });
    }

    function search(){
        currentPage=1;
        getArticalList();
    }

    function concatLiElement(title,label,date,id,type){
        var times=date.split("T");
        var str='<li><i>'+times[0]+' '+times[1].substr(0,8)+'</i>';
        str+='<h3><a href="#" onclick="showArtical(\''+id+'\')">['+type+']'+title+'</a></h3>';
        str += "<p>" + label + "</p></li>";
        return str;
    }

    //获取文章列表
    function getArticalList(){
        $.ajax({
            url:"/blog/articalList",
            data:{
                pageNum:currentPage,
                pageSize:pageSize,
                eqTypeId:typeId,
                likeSearch:$("#searchWords").val(),
                orderByTime:"1"
            },
            success:function(data){
                //$("#searchWords").val("");
                $('#articalList').attr("class","r_box");
                var artical=data['data'];
                if(artical.length==0){
                    $('#articalList').html(noArtical);
                    generalPage('', getArticalList);
                }else {
                    currentPage = data['currentPage'];
                    generalArticalHtml(artical, data['totalPages']);
                }
            }
        });
    }

    //根据选择的文章类别展示文章列表
    function changeType(selectId){
        $("#searchWords").val("");
        currentPage=1;
        typeId=selectId;
        getArticalList();
    }

    //根据ID展示文章内容
    function showArtical(id){
        $.ajax({
            url:"/blog/artical",
            data:{
                eqId:id
            },
            success:function(data){
                $('#articalList').attr("class","infosbox");
                var times=data['updateTime'].split('T');
                var html='<div class="newsview">';
                html+='<h3 class="news_title">'+data['title']+'</h3>'
                html+='<div class="bloginfo"><ul><li class="author">作者：'+data['authorId']+'</li>';
                html+='<li class="lmname"><a href="#" onclick="changeType('+map.get(data['typeName'])[4]+');">'+data['typeName']+'</a></li>';
                html+='<li class="timer">时间：'+times[0]+' '+times[1].substring(0,8)+'</li></ul></div>';
                html+='<div class="news_about"><strong>简介</strong>'+data['label']+'</div>';
                html+='<div class="news_con">'+data['content']+'<p>&nbsp;</p></div>';
                html+='<hr style="margin-top:5px;margin-bottom:5px;"/>'
                tempcontent=data['content'];
                updateId=data['id'];
                html+='<input type="submit" class="btn btn-default" style="float:right;margin:5px;" value="返回列表" onclick="getArticalList()">'
                if(readonly=="0") {
                    html += '<input type="submit" class="btn btn-danger" style="float:right;margin:5px;" value="删除" onclick="deleteArtical(\'' + updateId + '\')">'
                    html += '<input type="submit" class="btn btn-success" style="float:right;margin:5px;" value="修改"';
                    html += 'onclick="generalUpdateEdit(\'' + data['typeName'] + '\',\'' + data['title'] + '\',\'' + data['label'] + '\')">';
                }
                html+='</div>';
                $('#pageLimit').hide();
                $('#articalList').html(html);
            }
        });
    }

    //根据id删除文章
    function deleteArtical(id){
        $.ajax({
            url: "/blog/delete",
            type:"POST",
            data: {
                eqId: id
            },
            success: function (data) {
                alert(data['msg']);
                getArticalList();
            }
        });
    }

    //生成修改界面
    function generalUpdateEdit(type,title,label){
        generalEdit(type,"update");
        $("#"+type+"Title").val(title);
        $("#"+type+"Label").val(label);
        $("#summernoteContent").summernote('code',tempcontent);
    }

    //根据文章类型生成文章编辑界面
    function generalEdit(type,editType){
        var data=map.get(type);
        $('#articalList').attr("class","r_box");
        var html="<div style='color:#FFFFFF;'>";
        html+=generalNomalInput(data[0],type+"Title");
        html+=generalNomalInput(data[1],type+"Label");
        html+='<p style="padding: 7px 0px;">'+data[2]+'：</p>'
        //html+='<textarea name="txt" style="border-radius: 3px;width:100%;color:#000000;"'
        //html+=' id="'+type+'Content" rows="14" placeholder="'+data[3]+'" warp="virtual" ></textarea>'
        html+='<div id="summernoteContent"></div>';
        html+='<input type="submit" class="btn btn-default" style="float:right;margin:5px;" value="返回列表" onclick="getArticalList()">'
        html+='<input type="submit" class="btn btn-success" style="float:right;margin:5px;" value="提交" onclick="saveArtical(\''+type+'\',\''+editType+'\')">'
        html+="</div>"
        $('#pageLimit').hide();
        $('#articalList').html(html);
        $('#summernoteContent').summernote({
            height: 300,
            minHeight: null,             // set minimum height of editor
            maxHeight: 500,             // set maximum height of editor
            focus: true,
            placeholder: data[3]
        });
    }

    //生成普通的输入框
    function generalNomalInput(name,elementId){
        var html="";
        html+="<span style='float:left;padding: 7px 0px;'>"+name+"：</span>";
        html+='<p style="overflow:hidden">';
        html+='<input style="display:inline-block;width:100%;color:#000000;border: 1px solid #ccc;border-radius: 3px;padding: 7px 0px;padding-left:5px;"'
        html+='type="text" id="'+elementId+'" placeholder="请输入'+name+'" >';
        html+="</p>";
        return html;
    }

    //生成文章列表的HTML
    function generalArticalHtml(data,totalPages) {
        var html = '';
        for (var i = 0; i < data.length; i++) {
            html += concatLiElement(data[i]['title'], getNullString(data[i]['label']), data[i]['updateTime']
                ,data[i]['id'], data[i]['typeName']);
        }
        $('#articalList').html(html);
        generalPage(totalPages, getArticalList);
    }

    //换页控件
    function generalPage(totalPages,callback){
        $('#pageLimit').show();
        $('#pageLimit').bootstrapPaginator({
            currentPage: currentPage,
            totalPages: totalPages,
            size:"normal",
            bootstrapMajorVersion: 3,
            alignment:"right",
            itemTexts: function (type, page, current) {
                switch (type) {
                    case "first": return "首页";
                    case "prev": return "上一页";
                    case "next": return "下一页";
                    case "last": return "末页";
                    case "page": return page;
                }
            },
            onPageClicked: function (event,originalEvent,type,page) {
                // 把当前点击的页码赋值给currentPage, 调用ajax,渲染页面
                currentPage=page;
                callback && callback();
            }
        });
    }

    //保存文章
    function saveArtical(type,editType){
        var authorId=2;  //创建人暂时是写死的
        var eqId="";
        if(editType=='update'){
            eqId=updateId;
        }
        $.ajax({
            url:"/blog/"+editType,
            type:"POST",
            data:{
                title:$("#"+type+"Title").val(),
                label:$("#"+type+"Label").val(),
                content:$("#summernoteContent").summernote('code'),
                typeId:map.get(type)[4],
                authorId:authorId,
                eqId:eqId,
                attachmentPath:""
            },
            success:function(data){
                alert(data);
                if(editType=='update') {
                    showArtical(eqId);
                }else{
                    $("#searchWords").val("");
                    getArticalList();
                }
            }
        });
    }

    function getNullString(str){
        if(str==null){
            return '';
        }
        return str;
    }

    initData();
</script>