function modifyPassword() {
	$("#fm").form('clear');
	$('#modifyPasswordDialog').dialog('open');
}
function saveModifyPassword() {
	$.messager.progress();

	var formObj = $("#fm");
	var dlgObj = $('#modifyPasswordDialog');

	var passwordObj1 = $("#oldPassword");
	var passwordObj2 = $("#newPassword");
	var passwordObj3 = $("#reNewPassword");

	var isValid = formObj.form('validate');
	if (!isValid) {
		$.messager.progress('close');
		return;
	} else {
		var tmpPassword = hex_md5(passwordObj1.textbox("getValue"));
		passwordObj1.textbox("setValue", tmpPassword);
		var tmpPassword2 = hex_md5(passwordObj2.textbox("getValue"));
		passwordObj2.textbox("setValue", tmpPassword2);
		passwordObj3.textbox("setValue", tmpPassword2);
	}

	var sendData = formObj.serializeJson();
	$.post(getBaseURL("/operator/modifyPassword.do"), sendData, function(
			tmpResult) {
		if (tmpResult.errorMsg) {
			formObj.form('clear');
			$.messager.alert('Error', tmpResult.errorMsg);
		} else {
			formObj.form('clear');
			dlgObj.dialog('close'); // close the dialog
			$.messager.show({
				title : '消息',
				msg : "修改成功！"
			});
		}
		$.messager.progress('close');
	}, "json");
}
function addTab(title, url) {
	if ($('#tt').tabs('exists', title)) {
		$('#tt').tabs('select', title);
	} else {
		var content = '<div style="width:100%;height:100%;overflow:hidden;"><iframe scrolling="auto" frameborder="0"  src="'
				+ url + '" style="width:100%;height:100%;"></iframe></div>';
		$('#tt').tabs('add', {
			title : title,
			content : content,
			closable : true
		});
	}
}
// 从第二个开始关闭
function removeTab() {
	var tabs = $("#tt").tabs("tabs");
	var length = tabs.length;
	for (var i = 1; i < length; i++) {
		var onetab = tabs[1];
		var title = onetab.panel('options').title;
		$("#tt").tabs("close", title);
	}
}