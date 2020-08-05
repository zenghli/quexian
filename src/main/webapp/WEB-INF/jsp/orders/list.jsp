<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/resources/commons/basejs.jsp" %>
</head>
<body>
    <table id="id_table" lay-filter="lineToolFilter"></table>
</body>
<script type="text/html" id="toolBar_my">
    <div class="layui-inline">用户昵称：</div>
    <input type="text" class="layui-input" style="display: inline-block;width: 220px;" id="name_search" name="nickName" placeholder="请输入昵称" autocomplete="off">
    <div class="layui-inline">
        <select class="layui-inline" name="state" id="state_search">
            <option value="-1" selected>全部</option>
            <option value="1">待支付</option>
            <option value="2">待发货</option>
            <option value="3">待收货</option>
            <option value="4">已完成</option>
        </select>
    </div>
    <a class="layui-btn" lay-event="search" id="toolBar_btn_search">
        <i class="layui-icon layui-icon-search"></i>搜索
    </a>
</script>
<script>
    var modleName = "${ctxPath}/orders";
    //用layui加载数据
    function onLayuiInit() {
        //渲染表格
        renderTable({
            elem: '#id_table',
            id: 'id_table',
            toolbar: '#toolBar_my',
            url: modleName+"/getList",
            cols:[[
                {type:'checkbox',LAY_CHECKED:false},
                {field:'id',title:'ID',align:'center'},
                {field:'orderSn',title:'订单编号',align: 'center'},
                {field:'nickName',title:'用户昵称',align: 'center'},
                {field:'totalPrice',title:'订单金额',align: 'center'},
                {field:'state',title:'状态',align:'center',templet:function (d) {
                     let str = '';
                     switch (d.state) {
                         case 1:
                             str = '待支付';
                             break;
                         case 2:
                             str = '待发货';
                             break;
                         case 3:
                             str = '待收货';
                             break;
                         case 4:
                             str = '已完成';
                             break;
                     }
                     return str;
                }},
                {field:'addressName',title:'收货人',align:'center',templet:function (d) {
                    return d['address']['name']
                }},
                {field:'addressPhone',title:'联系电话',align:'center',templet:function (d) {
                    return d['address']['phone']
                }},
                {field:'addressAddress',title:'地址',align:'center',templet:function (d) {
                    return d['address']['address']
                }},
                {field:'createTime',title:'下单时间',align: 'center',templet:function (d) {
                        return layui.util.toDateString(d.createTime);
                    }
                },
                {title:'操作',align: 'center',templet:function (d) {
                        let btn = '';
                        switch (d.state) {
                            case 2:
                                btn = '<a href="#" onclick="changeOrderState('+d.id+',3)" class="layui-btn layui-btn-xs" lay-event="edit">发货</a>';
                                break;
                            case 3:
                                btn = '<a href="#" onclick="changeOrderState('+d.id+',4)" class="layui-btn layui-btn-xs" lay-event="edit">完成</a>';
                                break;
                        }
                        return btn;
                    }
                }
            ]]
        });
    }

    function changeOrderState(id,state){
        $.ajax({
            type: "post",
            url: modleName+"/updateState",
            data:{
                id: id,
                state: state
            },
            success: function (res) {
                if(res.code == 200){
                    layer.msg("订单状态修改成功！",{icon:1,time:2000});
                    tableIns.reload({
                        where:{
                            nickName: $('#name_search').val(),
                            state:$('#state_search').val()
                        },page:{
                            curr: 1
                        }
                    });
                }
            }
        });
    }
</script>
</html>














