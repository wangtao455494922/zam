<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/include/head.jsp"%>
</head>
<body>
	<div class="x-nav">
		<div class="x-nav">
			<span class="layui-breadcrumb" style="visibility: visible;"> 
				<a href="">首页</a> 
				<span lay-separator="">/</span>
				<a href="">工作流管理</a> 
				<span lay-separator="">/</span>
				<a> <cite>部署信息管理</cite></a>
			</span>
		</div>
	</div>
	<!-- table -->
	<div class="x-body">
		 <div class="resourceTable demoTableClass">
		  <div class="layui-inline">
		    <input class="layui-input" name="name" id="name" autocomplete="off" placeholder="请输入名称">
		  </div>
		  <button class="layui-btn" data-type="tableReload">搜索</button>
		  <button class="layui-btn" data-type="batchDelete">批量删除</button>
		  <button type="button" class="layui-btn layui-btn-normal" id="selectFile">选择文件</button>
		  <button type="button" class="layui-btn" id="startDeploy" >开始部署</button>
		</div>
		<table id="deploymentTab" class="layui-table" lay-filter="deploymentTable"></table>
		<script type="text/html" id="barDemo">
   			 <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
		</script>
	</div>
	<script>
		layui.use(['table','upload' ], function() {
			var table = layui.table;
			var upload = layui.upload;
			//table渲染
			table.render({
				elem: '#deploymentTab'
			    ,url: '${path}/deployment/tableRender' //数据接口
			    ,request: {pageName: 'pageNum' ,limitName: 'pageSize'}
			    ,method:'post'
			    ,page: true //开启分页
			    ,cols: [[ //表头
			       {type:'checkbox'}
			      ,{field:'id', sort: true,title: 'ID'}
			      ,{field:'name',width:138,title: '流程名称'}
			      ,{field:'deploymentTime', sort: true,title: '发布时间',templet:'<div>{{ layui.laytpl.toDateString(d.deploymentTime) }}</div>'} 
			      ,{fixed: 'right', align:'center', toolbar: '#barDemo',title: '操作'}
			    ]]
			});
			
			//upload初始化
			upload.render({
			   elem: '#selectFile'
			  ,url: '${path}/deployment/startDeploy'
			  ,auto: false //选择文件后不自动上传
			  ,accept:'file'//上传文件类型
			  ,exts:'zip'//允许上传的文件后缀。一般结合 accept 参数类设定。假设 accept 为 file 类型时，那么你设置 exts: 'zip|rar|7z' 即代表只允许上传压缩格式的文件
			  ,bindAction: '#startDeploy' //指向一个按钮触发上传
			  ,done: function(res, index, upload){
				    if(res.success){
				    	layer.msg(res.msg,{icon:1,time:1000});
				    	table.reload('deploymentTab');//table重新渲染
				    }
			  }
			}); 
			
			//时间戳的处理
			layui.laytpl.toDateString = function(d, format) {
				var date = new Date(d || new Date()), ymd = [
						this.digit(date.getFullYear(), 4),
						this.digit(date.getMonth() + 1),
						this.digit(date.getDate()) ], hms = [
						this.digit(date.getHours()),
						this.digit(date.getMinutes()),
						this.digit(date.getSeconds()) ];

				format = format || 'yyyy-MM-dd HH:mm:ss';

				return format.replace(/yyyy/g, ymd[0]).replace(/MM/g, ymd[1])
						.replace(/dd/g, ymd[2]).replace(/HH/g, hms[0]).replace(
								/mm/g, hms[1]).replace(/ss/g, hms[2]);
			};
			//数字前置补零
			layui.laytpl.digit = function(num, length, end) {
				var str = '';
				num = String(num);
				length = length || 2;
				for (var i = num.length; i < length; i++) {
					str += '0';
				}
				return num < Math.pow(10, length) ? str + (num | 0) : num;
			};
			//监听工具条
			table.on('tool(deploymentTable)', function(obj) {
				var data = obj.data;
				if (obj.event === 'delete') {
					layer.confirm('真的删除行么', function(index) {
						$.ajax({
							data : {
								"id" : data.id
							},
							type : 'POST',
							url : '${path}/deployment/deleteById',
							dataType : 'json',
							success : function(mes) {
								if (mes.success) {
									layer.msg(mes.msg, {
										icon : 1,
										time : 1000
									});
								} else {
									layer.msg(mes.msg, {
										icon : 5,
										time : 1000
									});
								}
								layer.close(index);
								table.reload('deploymentTab');
							}
						});
						return false;
					});
				}
			});

			//查询事件
			var $ = layui.$, active = {
				batchDelete:function(){
					var checkStatus = table.checkStatus('deploymentTab')
				     ,data = checkStatus.data;
					 var deList=new Array();
		             data.forEach(function(n,i){
		            	 deList.push(n.id);
		             });
		             if (deList=='') {
		            	 layer.msg("请至少选择一行数据");
					} else {
						$.ajax({
						 data:{"ids":deList},
						 traditional:true,
		        		 type:'POST',
		        		 url:'${path}/deployment/batchDelete',
		        		 dataType:'json',
		        		 success:function(mes){
		        			if (mes.success) {
		        				layer.msg(mes.msg, {
									icon : 1,
									time : 1000
								});
						        table.reload('deploymentTab');
							}else{
								layer.msg(mes.msg, {
									icon : 5,
									time : 1000
								});
							    layer.msg(mes.msg);    
							}
		        		 }	
					 	});
					}
		         }
				,tableReload : function() {
					var name = $('#name');
					//执行重载
					table.reload('deploymentTab', {
						where : {
							name : name.val()
						}
					});
				}
			};
			$('.resourceTable .layui-btn').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
		});
	</script>
	<script type="text/html" id="barDemo">
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>			
</body>
</html>
