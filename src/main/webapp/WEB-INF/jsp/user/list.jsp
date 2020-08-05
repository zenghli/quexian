<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/resources/commons/basejs.jsp" %>
</head>
<body>
    <table id="id_table" lay-filter="lineToolFilter"></table>
</body>
<script>
    var modleName = "${ctxPath}/user";
    //用layui加载数据
    function onLayuiInit() {
        //渲染表格
        renderTable({
            elem: '#id_table',
            id: 'id_table',
            url: modleName+"/getList",
            cols:[[
                {field:'id',title:'ID',align:'center'},
                {field:'openid',title:'openid',align: 'center'},
                {field:'coverImg',title:'头像',align: 'center',templet:function (d) {
                        return '<img src="${ctxPath}/'+d.avatarUrl+'" style="width:20px;height:20px;"/>';
                    }
                },
                {field:'nickName',title:'昵称',align: 'center'},
                {field:'gender',title:'性别',align:'center',templet:function (d) {
                        let str = '';
                        switch (d.gender) {
                            case 0:
                                str = '未知';
                                break;
                            case 1:
                                str = '男';
                                break;
                            case 2:
                                str = '女';
                                break;
                        }
                        return str;
                    }},
                {field:'country',title:'国家',align: 'center'},
                {field:'province',title:'省',align: 'center'},
                {field:'city',title:'市',align: 'center'},
                {field:'createTime',title:'创建时间',align: 'center',templet:function (d) {
                        return layui.util.toDateString(d.createTime);
                    }
                },
                {field:'updateTime',title:'更新时间',align: 'center',templet:function (d) {
                        return layui.util.toDateString(d.updateTime);
                    }
                }
            ]]
        });
        //使用父类的航工具栏
        useParentToolEvent(modleName);
    }
</script>
</html>














