$.fn.serializeJson=function(){  
	var serializeObj={};  
	$(this.serializeArray()).each(function(){  
		serializeObj[this.name]=this.value;  
	});  
	return serializeObj;  
};
$.extend($.fn.validatebox.defaults.rules, {
	equals: {
		validator: function (value, param) {
			return value == $(param[0]).val();
		},
		message: '内容不一致！'
	}
});
var bmgUtil={
	dg_deleteIt:function (dgId,url) {
		var dgObj=$("#"+dgId);
		var row = dgObj.datagrid('getSelected');
		if (row) {
			$.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
				if (r) {
					$.messager.progress();

					var sendData={
						id:row.id
					};
					$.post(
						getBaseURL(url),
						sendData,
						function (tmpResult) {
							if (tmpResult.errorMsg) {
								$.messager.show({title: 'Error',msg: tmpResult.errorMsg});
							} else {
								$.messager.show({title: '消息',msg: "删除成功！"});
								dgObj.datagrid('reload');
							}
							$.messager.progress('close');
						},
						"json"
					);
				}
			});
		}
	},
	/**
	 * 
	 * @param formObj form表单对象
	 * @param dlgObj dialog对象
	 * @param dgObj datagrid对象
	 * @param url
	 */
	common_saveIt:function(formObj,dlgObj,dgObj,url){
		
		var isValid =formObj.form('validate');
		if(!isValid){
			return;
		}
		
		$.messager.progress();
		var sendData = formObj.serializeJson();
		$.post(
			getBaseURL(url),
			sendData,
			function (tmpResult) {
				if (tmpResult.errorMsg) {
					$.messager.alert( 'Error',tmpResult.errorMsg);
				} else {
					formObj.form('clear');
					dlgObj.dialog('close');        // close the dialog
					$.messager.show({title: '消息',msg: "添加成功！"});
					dgObj.datagrid('reload');    // reload the user data
				}
				$.messager.progress('close');
			},
			"json"
		);
	},
	common_modifyIt:function(formObj,dlgObj,dgObj,url,tmpRowId){
		if (tmpRowId === "") {
			$.messager.alert('Error', "ID不能为空。");
			return;
		}
		
		var isValid =formObj.form('validate');
		if(!isValid){
			return;
		}
		
		$.messager.progress();
		var sendData = formObj.serializeJson();
		sendData.id=tmpRowId;
		$.post(
			getBaseURL(url),
			sendData,
			function (tmpResult) {
				if (tmpResult.errorMsg) {
					$.messager.alert( 'Error',tmpResult.errorMsg);
				} else {
					formObj.form('reset');
					dlgObj.dialog('close');        // close the dialog
					tmpRowId="";
					$.messager.show({title: '消息',msg: "修改成功！"});
					dgObj.datagrid('reload');    // reload the user data
				}
				$.messager.progress('close');
			},
			"json"
		);
	}
	
};
//创建地图坐标参数的方法
function _(x,y){
	return new BMap.Point(x,y);
}