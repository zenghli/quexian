<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script>
    var table;
    var layer;
    var form;
    var upload;
    var tableIns;

    var windowInitNum = 0;//编辑窗口计数器

    layui.use(['table', 'layer', 'form', 'upload'], function () {
        table = layui.table;
        layer = layui.layer;
        form = layui.form;
        upload = layui.upload;
        onLayuiInit();

        //监听事件
        table.on('toolbar(lineToolFilter)', function (obj) {
            switch (obj.event) {
                case 'search':
                    tableIns.reload({
                        where: {
                            nickName: $('#name_search').val(),
                            name: $('#name_search').val(),
                            state: $('#state_search').val()
                        }
                        , page: {
                            curr: 1 //重新从第 1 页开始
                        }
                    }); //表格重载
                    break;
                case 'add':
                    //新增
                    layer.open({
                        type: 2,
                        title: '添加信息',
                        content: modleName + "/addPage",//这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                        shade: 0.4,
                        area: ['50%', '500px'],
                        success: function (layero, index) {//当前层DOM 当前层索引
                            // 新iframe窗口的对象
                            // iframeWin = layero.find('iframe')[0].contentWindow;
                        }
                        , btn: ['添加', '取消']
                        , yes: function (index, layero) {
                            var tool_event_Form = $(layero).find('iframe').contents().find('#form_submit');
                            tool_event_Form.find('#btn_form_submit').click()
                            tableIns.reload({
                                where: {
                                    nickName: $('#name_search').val(),
                                    name: $('#name_search').val(),
                                    state: $('#state_search').val()
                                }
                                , page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            }); //表格重载
                        }
                    });
                    break;
            }
        });

        //switch开关
        form.on('switch(switchState)', function (data) {
            var id = data.elem.name;
            var isChecked = data.elem.checked;
            $.ajax({
                type: "post",
                url: modleName + "/updateState",
                data: {
                    id: id,
                    state: isChecked ? 2 : 1
                },
                success: function (res) {
                    var str = isChecked ? '禁用' : '启用';
                    if (res.code == 200) {
                        layer.msg(str + "成功！", {icon: 1, time: 2000});
                    } else {
                        layer.msg(str + "失败！请重试！", {icon: 5, time: 2000});
                    }
                }
            });
        });


        //switch开关
        form.on('switch(switchRecommend)', function (data) {
            var id = data.elem.name;
            var isChecked = data.elem.checked;
            $.ajax({
                type: "post",
                url: modleName + "/updateRecommend",
                data: {
                    id: id,
                    recommend: isChecked ? 2 : 1
                },
                success: function (res) {
                    var str = isChecked ? '设置不推荐' : '设置推荐';
                    if (res.code == 200) {
                        layer.msg(str + "成功！", {icon: 1, time: 2000});
                    } else {
                        layer.msg(str + "失败！请重试！", {icon: 5, time: 2000});
                    }
                }
            });
        });
    });


</script>