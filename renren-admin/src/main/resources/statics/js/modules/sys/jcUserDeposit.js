$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/JcUserDeposit/list',
        datatype: "json",
        colModel: [
            { label: 'id', name: 'id', index: 'id', width: 60 },
            { label: '用户名称', name: 'username', index: 'username', width: 80 },
            { label: '用户手机号', name: 'phone', index: 'phone', width: 80 },
            { label: '柜子编号', name: 'luggageNum', index: 'luggageNum', width: 80 },
            { label: '柜子名称', name: 'luggageTitle', index: 'luggageTitle', width: 80 },
            { label: '备注信息', name: 'bz', index: 'bz', width: 80 },
            { label: '入柜时间', name: 'createTime', index: 'createTime', width: 80 },
            { label: '管理员联系方式', name: 'adminUser', index: 'adminUser', width: 80 },
    ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});

var vm = new Vue({
    el:'#rrapp',
    data:{
        q:{
            name: null
        },
        showList: true,
        title: null,
        JcUserDeposit: {},
    },
    methods: {
        query: function () {
            vm.reload();
        },


        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.JcUserDeposit = {};
        },
        update: function (event) {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },

        saveOrUpdate: function (event) {
            var pname=$("#pname option:selected").text(); //获取选中的项
            var num=$("#pname option:selected").val(); //获取选中的项
            vm.JcUserDeposit.luggageTitle=pname;
            vm.JcUserDeposit.luggageNum=num;
            var url = vm.JcUserDeposit.id == null ? "sys/JcUserDeposit/save" : "sys/JcUserDeposit/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.JcUserDeposit),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/JcUserDeposit/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(index){
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function(id){
            $.get(baseURL + "sys/JcUserDeposit/info/"+id, function(r){
                vm.JcUserDeposit = r.JcUserDeposit;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'username': vm.q.username},
                page:page
            }).trigger("reloadGrid");
        }
    }
});