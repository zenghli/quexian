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
    var modleName = "${ctxPath}/advertisement";
    //用layui加载数据
    function onLayuiInit() {
        //渲染表格
        renderTable({
            elem: '#id_table',
            id: 'id_table',
            toolbar: '#toolBar',
            url: modleName+"/getList",
            cols:[[
                {type:'checkbox',LAY_CHECKED:false},
                {field:'id',title:'ID',align:'center'},
                {field:'name',title:'名称',align: 'center'},
                {field:'coverImg',title:'图片',align: 'center',templet:function (d) {
                        return '<img src="${ctxPath}/'+d.coverImg+'"/>';
                    }
                },
                {field:'goodsName',title:'书名',align: 'center'},
                {field:'state',title:'状态',align:'center',templet:function (d) {
                        if(d.state==1){
                            return "<input type='checkbox' lay-filter='switchState' name='"+d.id+"' lay-skin='switch' lay-text='禁用|正常'>";
                        }else{
                            return "<input type='checkbox' lay-filter='switchState' name='"+d.id+"' lay-skin='switch' lay-text='禁用|正常' checked>";
                        }
                    }
                },
                {field:'createTime',title:'创建时间',align: 'center',templet:function (d) {
                        return layui.util.toDateString(d.createTime);
                    }
                },
                {field:'updateTime',title:'更新时间',align: 'center',templet:function (d) {
                        return layui.util.toDateString(d.updateTime);
                    }
                },
                {title:'操作',align: 'center',toolbar: '#bar2'}
            ]]
        });
        //使用父类的航工具栏
        useParentToolEvent(modleName);
    }
</script>
</html>














