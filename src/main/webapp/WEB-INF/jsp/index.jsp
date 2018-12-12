<%@ page pageEncoding="UTF-8" %>
<html>
<head>
<%@ include file="/WEB-INF/jspf/common.jspf"%>
<script src="<%=BaseURL%>/jslib/md5/md5-min.js?ver=<%=VERSION%>" type="text/javascript"></script>
<script src="<%=BaseURL%>/js/autodata.js?ver=<%=VERSION%>" type="text/javascript"></script>
<script type="text/javascript">



         var websocket = new WebSocket("ws://localhost:8080/DataAcquisitionSystem/websocket");
         websocket.onopen = function(){
             console.log("websocket open");
             
         }
         websocket.inclose = function(){
            console.log('websocket close');
         }
         websocket.onmessage = function(e){
             console.log(e.data);
             if(event.data=="1"){
            	// n = $('#dg').datagrid('getData').total;
            	 $('#dg').datagrid('reload');
             }
         }
         window.onbeforeunload = function(event) {
        	    console.log("关闭WebSocket连接！");
        	    webSocket.OnClose();
        	}



</script>
</head>
	
	<div style="padding:5px;height:auto;border-bottom: 1px solid #95b8e7">
		<div>
            
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="obj.adddata()">添加</a>

           
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="obj.modify()">修改</a>
           
            
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="bmgUtil.dg_deleteIt('dg','/digital/delete.do');">删除</a>
          
		</div>
	</div>
	<table id="dg"></table>
	<div id="dlg" class="easyui-dialog" style="width:680px;height:680px;padding:10px 20px"
			 data-options="closed:true,buttons:'#dlg-buttons',modal:true,maximizable:true,maximized:true" >
			<div class="ftitle">数据采集</div>
			<form id="fm" method="post" enctype="multipart/form-data" novalidate='true'>
				<input type="hidden" id="id" name="id" value="">
				<div class="easyui-layout" data-options="width:700,height:700" >
					<div data-options="region:'west',border:false" style="width:330px">
					
						<div class="fitem">
							<label>进气压力：</label>
							<input id="inntakepressure" name="inntakepressure" class="easyui-numberbox" data-options="precision:1,required:true">
						</div>
						<div class="fitem">
							<label>进气温度：</label>
							<input id="intaketemperature" name="intaketemperature" class="easyui-numberbox" data-options="precision:1,required:true">
						</div>
						
				</div>
			</form>
		</div>
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="obj.savedata()" style="width:90px">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
		</div>


<script>
		$("#dg").datagrid({
			fit : true,
			border : false,
			rownumbers : true,
			pagination : true,
			showFooter : true,
			singleSelect : true,
			autoRowHeight : false,
			fitColumns:false,
			nowrap : true,
			//data:data2,
			url : getBaseURL("/digital/list.do"),
			pageSize : 20,
			toolbar : "#toolbar",
			columns : [ [ {
				field : "id",
				title : "ID",
				width:30,
				sortable : true
			}, {
				field : "inntakepressure",
				title : "进气压力",
				width:100,
				sortable : true
			},   {
				field : "intaketemperature",
				width:200,
				title : "进气温度"
			}] ],
			onLoadError : function() {
				$.messager.show({
					title : 'Error',
					msg : "数据加载失败。"
				});
			}
		})
	</script>

</body>
</html>
