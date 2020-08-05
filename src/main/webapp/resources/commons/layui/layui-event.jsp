<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script>
    function renderTable(param) {
        //第一个实例
        tableIns = table.render({
            elem: param.elem
            , id: param.id
            , toolbar: param.toolbar
            , defaultToolbar: []
            , url: param.url
            , page: true //开启分页
            , cellMinWidth: 80
            , cols: param.cols
            , limits: [5, 10, 15]
            , limit: 10,
        });
    }

    /**
     * 绑定图片上传的组件
     * @param elementId
     * @param initImage 编辑的时候的图片回显
     */
    function bindUpload(elementId, inputName, initImage) {
        if (initImage) {
            var str = "<input type='hidden' lay-verify='required' name='" + inputName + "' value='" + initImage + "'/>" +
                " <img src='${ctxPath}/" + initImage + "'>"
            $("#" + elementId).parent().find('.' + elementId).html(str);
        }
        var uploadInstance = upload.render({
            url: '${ctxPath}/upload/uploadImg' //上传接口
            , elem: '#' + elementId //绑定元素
            , done: function (res) {
                layer.msg("上传图片成功！", {icon: 1, time: 2000});
                var str = "<input type='hidden' lay-verify='required' name='" + inputName + "' value='" + res.msg + "'/>" +
                    " <img src='${ctxPath}/" + res.msg + "'>"
                $("#" + elementId).parent().find('.' + elementId).html(str);
            }
            , error: function () {
                layer.msg("上传图片失败！请重试！", {icon: 5, time: 2000});
            }
        });

    }


    function bindSubmit(modleName, method, layFilterName, formId) {
        //监听表单提交
        form.on("submit(" + layFilterName + ")", function () {
            //进行网络请求
            $.ajax({
                url: modleName + '/' + method,
                type: 'post',
                data: $('#' + formId).serialize(),
                success: function (res) {
                    if (res.code == 200) {
                        //刷新table表格
                        $("#toolBar_btn_search").click();//重载TABLE
                        layer.msg(res.msg, {icon: 1, shade: 0.4, time: 1000});
                        /**
                         *分成两种情况：
                         1、弹出层不是新的页面的时候,直接获得该弹窗的索引，然后执行close方法
                         layer.close();
                         2、弹出窗是新的页面的时候
                         var index=parent.layer.getFrameIndex(window.name);
                         parent.layer.close(index);
                         */
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    } else {
                        layer.msg(res.msg, {icon: 5, shade: 0.4, time: 1000});
                    }
                }
            });
            return false;
        });
    }


    function loadOptionList(option) {
        var nameKey = option['name'] ? option['name'] : 'name';
        var valueKey = option['id'] ? option['id'] : 'id';
        $.getJSON(option.url, {
            state: 1,
            page: 1,
            limit: 100
        }, function (res) {
            var item;
            $('#' + option['elementId']).empty();
            $.each(res.data, function (index, bean) {
                if (bean[valueKey] === option['selectedValue']) {
                    item = '<option  selected' +
                        ' value="' + bean[valueKey] + '">'
                        + bean[nameKey]
                        + '<option>';
                } else {
                    item = '<option' +
                        ' value="' + bean[valueKey] + '">'
                        + bean[nameKey]
                        + '<option>';
                }

                $('#' + option['elementId']).append(item);
            })
            form.render();
        })
    }

    /**
     * 使用父类的工具栏
     * @param modleName
     */
    function useParentToolEvent(modleName) {
        //监听工具条
        table.on('tool(lineToolFilter)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
            switch (layEvent) {
                case 'del':
                    layer.confirm('是否删除？', {
                        btn: ['确认', '取消'] //可以无限个按钮
                        , yes: function (index, layero) {
                            //删除逻辑
                            $.ajax({
                                url: modleName + '/delete',
                                type: 'post',
                                data: {
                                    id: data.id
                                },
                                success: function (res) {
                                    if (res.code == 200) {
                                        $(tr).remove();
                                        layer.msg('删除成功', {icon: 1, shade: 0.4, time: 1000});
                                    } else {
                                        layer.msg('删除失败', {icon: 5, shade: 0.4, time: 1000});
                                    }
                                }
                            })
                        }
                    })
                    break;
                case 'edit':
                    //编辑
                    layer.open({
                        type: 2,
                        title: '编辑信息',
                        content: modleName + "/editPage",//这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                        shade: 0.4,
                        area: ['50%', '400px'],
                        success: function (layero, index) {//当前层DOM 当前层索引
                            //回显内容
                            var tool_event_Form = $(layero).find('iframe').contents().find('#form_submit');
                            tool_event_Form.initForm(data);
                            //写个回调出去，让自己解决下拉框和图片显示问题
                            // 新iframe窗口的对象
                            var iframeWin = layero.find('iframe')[0].contentWindow;
                            iframeWin.onEditViewCreated(data);//执行子页面的回调
                        }
                        , btn: ['修改', '取消']
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
                    })
                    break;
            }

        });
    }


    var currentItemData;

    function onEditViewCreated(data) {
        currentItemData = data;
        windowInitNum++;
        console.log(windowInitNum)
        if (windowInitNum == 2) {
            initData();
        }
    }

    function onLayuiInit() {
        windowInitNum++;
        console.log(windowInitNum)
        if (windowInitNum == 2) {
            initData();
        }
    }
</script>