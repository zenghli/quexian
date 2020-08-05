<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/resources/commons/basejs.jsp" %>
</head>
<body>
    <div style="width: 400px;padding-top: 10px;">
        <form id="form_submit" class="layui-form">
            <input type="hidden" name="id">
            <div class="layui-form-item">
                <label class="layui-form-label">名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" lay-verify="required" placeholder="请输入轮播图名称" class="layui-input" value="">
                </div>
                <label class="layui-form-label">图片</label>
                <div class="layui-input-inline" style="position: relative">
                    <button type="button" class="layui-btn layui-btn-normal" id="image-upload-show">
                        <i class="layui-icon layui-icon-upload"></i>上传图片
                    </button>
                    <div class="image-upload-show"></div>
                </div>
                <label class="layui-form-label">链接图书</label>
                <div class="layui-input-inline">
                    <select name="goodsId" id="goodsId" lay-verify="required"></select>
                </div>
            </div>
            <div class="layui-form-item" style="display: none">
                <button type="button" lay-submit lay-filter="btn_form_submit" id="btn_form_submit" class="layui-btn layui-btn-lg"></button>
            </div>
        </form>
    </div>
</body>
<script>
    var modleName = "${ctxPath}/advertisement";
    //用layui加载数据
    function onLayuiInit() {
        //加载图书下拉选择框
        loadOptionList({
            url:'${ctxPath}/goods/getList',
            elementId:'goodsId',
            selectedValue: currentItemData.goodsId
        });
        //绑定图片上传的内容(规则就是按你的id名字一定要和图片容器的class名字一样)
        bindUpload("image-upload-show","coverImg",currentItemData.coverImg);
        //绑定监听表单提交事件
        bindSubmit(modleName,'update','btn_form_submit','form_submit');
    }
</script>
</html>
