<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link type="text/css" rel="stylesheet" href="./easyui/themes/default/easyui.css">
	<link type="text/css" rel="stylesheet" href="./easyui/themes/icon.css">
	<script type="text/javascript" src="./easyui/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="./easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	var grid;
	var win;
	var form;
	var win_img;
	var form_img;
	$(function(){
		grid = $('#tt').datagrid({
			title:'产品管理',
			method: 'GET',
			iconCls:'icon-save',
			width:666,
			height:300,
			url:'./data.do',
			columns:[[
				{field:'itemid',title:'Item ID',width:80},
				{field:'productid',title:'Product ID',width:80},
				{field:'listprice',title:'List Price',width:80,align:'right'},
				{field:'unitcost',title:'Unit Cost',width:80,align:'right'},
				{field:'attr1',title:'Attribute',width:100},
				{field:'status',title:'Status',width:80},
				{field:'image',title:'图片',width:100,align:'center',
			        formatter:function(value,row,index){return '<img src="./data_img.do?itemid='+row.itemid+'" />';}
			    }
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:newItem
			},'-',{
				text:'修改',
				iconCls:'icon-edit',
				handler:editItem
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:delItem
			},'-',{
				text:'图片上传',
				iconCls:'icon-save',
				handler:img
			}],
			pagination:true
		});
		$('#btn-save,#btn-cancel').linkbutton();
		
		$('#btn-save_img,#btn-cancel_img').linkbutton();
		win = $('#item-window').window({
			closed:true
		});
		form = win.find('form');
		
		win_img = $('#img-window').window({
			closed:true
		});
		form_img = win_img.find('form');
	});
	function newItem(){
		win.window('open');
		form.form('clear');
		form.url = './save.do';
	}
	function saveItem(){
		form.form('submit', {
			url:form.url,
			success:function(data){
				eval('data='+data);
				if (data.success){
					grid.datagrid('reload');
					win.window('close');
				} else {
					$.messager.alert('错误',data.msg,'error');
				}
			}
		});
	}
	function editItem(){
		var row = grid.datagrid('getSelected');
		if (row){
			win.window('open');
			form.form('load', './data1.do?itemid='+row.itemid);
			form.url = './update.do';
		} else {
			$.messager.show({
				title:'警告', 
				msg:'请先选择用户资料。'
			});
		}
	}
	
	function delItem(){
		var row = grid.datagrid('getSelected');
		if (row){
			win.window('open');
			form.form('load', './data1.do?itemid='+row.itemid);
			form.url = './del.do';
		} else {
			$.messager.show({
				title:'警告', 
				msg:'请先选择用户资料。'
			});
		}
	}
	function img(){
		var row = grid.datagrid('getSelected');
		if (row){
			win_img.window('open');
			form_img.form('load', './data1.do?itemid='+row.itemid);
			form_img.url = './saveimg.do';
		}else {
			$.messager.show({
				title:'警告', 
				msg:'请先选择用户资料。'
			});
		}
	}
	
	function saveImg(){
		form_img.form('submit', {
			url:form_img.url,
			success:function(data){
				eval('data='+data);
				if (data.success){
					grid.datagrid('reload');
					win_img.window('close');
				} else {
					$.messager.alert('错误',data.msg,'error');
				}
			}
		});
	}
	function closeWindow(){
		win.window('close');
		win_img.window('close');
	}
	</script>
</head>
<body>
	<table id="tt">
	
	</table>
	
	<div id="item-window" title="产品窗口" style="width:400px;height:300px;">
		<div style="padding:20px 20px 10px 60px;">
			<form method="post">
				<table>
					<tr>
						<td>订单编号：</td>
						<td><input name="itemid" ></input></td>
					</tr>
					<tr>
						<td>产品编号：</td>
						<td><input name="productid"></input></td>
					</tr>
					<tr>
						<td>总价格：</td>
						<td><input name="listprice"></input></td>
					</tr>
					<tr>
						<td>单价：</td>
						<td><input name="unitcost"></input></td>
					</tr>
					<tr>
						<td>属性：</td>
						<td><input name="attr1"></input></td>
					</tr>
					<tr>
						<td>状态：</td>
						<td><input name="status"></input></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="text-align:center;padding:5px;">
			<a href="javascript:void(0)" onclick="saveItem()" id="btn-save" icon="icon-save">保存</a>
			<a href="javascript:void(0)" onclick="closeWindow()" id="btn-cancel" icon="icon-cancel">取消</a>
		</div>
	</div>
	
	<div id="img-window" title="图片窗口" style="width:400px;height:150px;">
		<div style="padding:20px 20px 10px 60px;">
			<form method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>订单编号：</td>
						<td><input name="itemid" ></input></td>
					</tr>
					<tr>
						<td width="100" align="right">照片：</td>  
            			<td><input type="file" name="photo"/></td> 
					</tr>
					
				</table>
			</form>
		</div>
		<div style="text-align:center;padding:5px;">
			<a href="javascript:void(0)" onclick="saveImg()" id="btn-save_img" icon="icon-save">上传</a>
			<a href="javascript:void(0)" onclick="closeWindow()" id="btn-cancel_img" icon="icon-cancel">取消</a>
		</div>
	</div>
</body>
</html>