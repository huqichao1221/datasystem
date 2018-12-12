$(function () {
	var tmpRowId = "";
	obj = {
			adddata:function(){
				tmpRowId = "";
				$('#dlg').dialog('open').dialog('setTitle', '添加数据');
				$('#fm').form('reset');
			},
			
			savedata:function(){
				
				if (tmpRowId != "") {
	                obj.modifySave();
	                return;
	            }
				$.messager.progress();
				var formObj=$("#fm");
				var dgObj=$("#dlg");
				
				
				var isValid =formObj.form('validate');
				if(!isValid){
					$.messager.progress('close');
					return;
				}
				
				var sendData = formObj.serializeJson(); 
				$.post(
					getBaseURL("/digital/saveData.do"),
					sendData,
					function (tmpResult) {
						if (tmpResult.errorMsg) {
							$.messager.alert( 'Error',tmpResult.errorMsg);
						} else {
							
							formObj.form('clear');
							       // close the dialog
							$.messager.show({title: '消息',msg: "添加成功！"});
							    // reload the user data
						}
						$.messager.progress('close');
						dgObj.dialog('close');
						$('#dg').datagrid('reload');
					},
					"json"
				);
			},
			 modify: function () {
		            var row = $('#dg').datagrid('getSelected');
		            if (row) {
		                $('#dlg').dialog('open').dialog('setTitle', '修改车辆');
		                $('#fm').form('load', row);
		                tmpRowId = row.id;
		            }else{
		                $.messager.show({title: '消息',msg: "请选择需要修改的记录！"});
		            }
		        },
		        modifySave:function()
		        {
		        	if (tmpRowId === "") {
						$.messager.alert('Error', "ID不能为空。");
						return;
					}
					$.messager.progress();
					var formObj=$("#fm");
					var dlgObj=$('#dlg');
					var dgObj=$('#dg');
					var isValid =formObj.form('validate');
					if(!isValid){
						$.messager.progress('close');
						return;
					}
					var sendData = formObj.serializeJson();
					sendData.id=tmpRowId;
					$.post(
							getBaseURL("/digital/modify.do"),
							sendData,
							function (tmpResult) {
								if (tmpResult.errorMsg) {
									$.messager.alert( 'Error',tmpResult.errorMsg);
								} else {
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
		        
	}
});