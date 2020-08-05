<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/resources/commons/basejs.jsp" %>
    <script src="${ctxPath}/resources/js/index.js"></script>
</head>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <%--头部--%>
        <div class="layui-header">
            <div class="layui-logo">贵阳卷烟厂</div>
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item">卷包外观质量缺陷管理系统</a> </li>
            </ul>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <img src="${ctxPath}/upload/headpic.jpg" class="layui-nav-img">${sessionScope.userInfo.name}
                </li>
                <li class="layui-nav-item">
                    <a href="logout">退出</a>
                </li>
            </ul>
        </div>

        <%--左侧导航部分--%>
        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll">
                <ul class="layui-nav layui-nav-tree" lay-filter="test" id="nav_layui"></ul>
            </div>
        </div>

        <%--右侧主体部分--%>
        <div class="layui-body">
            <iframe src="goods/listPage" name="myframe" frameborder="0" style="width: 100%;height: 100%;"></iframe>
        </div>
    </div>
</body>

</html>














