$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/seller/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '商户名称', name: 'name', index: 'name', width: 80},
			{label: '商户号', name: 'code', index: 'code', width: 80},
			{
	            label: '商户门头', name: 'logo', index: 'logo', width: 80, formatter: function (value) {
	                return transImg(value);
	            }
	        },
			{label: '微信id', name: 'appid', index: 'appid', width: 80},
			{label: '微信密钥', name: 'appsecret', index: 'appsecret', width: 80},
			{label: '微信号', name: 'originalid', index: 'originalid', width: 80},
			{
                label: '状态', name: 'status', width: 80, formatter: function (value) {
                return value === 1 ?
                    '<span class="label label-danger">禁用</span>' :
                    '<span class="label label-success">正常</span>';
            }
            },
            {
                label: '创建时间', name: 'createTime', index: "create_time", width: 80, formatter: function (value) {
                return transDate(value);
            }
            },
			{label: '创建者', name: 'createUserId', index: 'create_user_id', width: 80}],
		viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

let vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		seller: {logo: ''},
		ruleValidate: {
			name: [
				{required: true, message: '商户名称不能为空', trigger: 'blur'}
			],
			code: [
                {required: true, message: '商户号不能为空', trigger: 'blur'}
            ],
            appid: [
                {required: true, message: '微信id不能为空', trigger: 'blur'}
            ],
            appsecret: [
                {required: true, message: '微信密钥不能为空', trigger: 'blur'}
            ],
            originalid: [
                {required: true, message: '微信号不能为空', trigger: 'blur'}
            ]
		},
		q: {
		    name: ''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.seller = {};
		},
		update: function (event) {
            let id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
            let url = vm.seller.id == null ? "../sys/seller/save" : "../sys/seller/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.seller),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
			});
		},
		del: function (event) {
            let ids = getSelectedRows();
			if (ids == null){
				return;
			}

			confirm('确定要删除选中的记录？', function () {
				$.ajax({
					type: "POST",
				    url: "../sys/seller/delete",
				    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function (r) {
						if (r.code == 0) {
							alert('操作成功', function (index) {
								$("#jqGrid").trigger("reloadGrid");
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../sys/seller/info/"+id, function (r) {
                vm.seller = r.seller;
            });
		},
		reload: function (event) {
			vm.showList = true;
            let page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        },
        handleFormatError: function (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
        },
        handleSuccessListPicUrl: function (res, file) {
            vm.seller.logo = file.response.url;
        },
        eyeImageListPicUrl: function () {
            var url = vm.seller.logo;
            eyeImage(url);
        },
        eyeImage: function (e) {
            eyeImage($(e.target).attr('src'));
        }
	}
});