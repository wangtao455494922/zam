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
			<span class="layui-breadcrumb" style="visibility: visible;"> <a
				href="">首页</a> <span lay-separator="">/</span> <a href="">系统管理管理</a>
				<span lay-separator="">/</span> <a> <cite>角色管理</cite></a>
			</span>
		</div>
	</div>
	<!-- table -->
	<div class="x-body">
		<div class="resourceTable demoTableClass">
			<div class="layui-inline">
				<input class="layui-input" name="role" id="role" autocomplete="off"
					placeholder="请输入角色名称">
			</div>
			<button class="layui-btn" data-type="tableReload">搜索</button>
		</div>
		<div class="layui-btn-container">
			<button class="layui-btn" data-type="batchDelete">批量删除</button>
			<button class="layui-btn"
				onclick="x_admin_show('添加角色信息','${path}/role/add')">添加</button>
		</div>
		<table id="layuiTab" class="layui-table" lay-filter="layuiTable"></table>
		<script type="text/html" id="barDemo">
 			 <button class="layui-btn layui-btn-sm" lay-event="edit">修改</button>
 			 <button class="layui-btn layui-btn-sm" lay-event="resource">资源</button>
   			 <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
		</script>
	</div>
	<script>
		layui.use(['table'], function() {
			var table = layui.table;
			//table渲染
			table.render({
				elem: '#layuiTab'
				    ,url: '${path}/role/tableRender' //数据接口
				    ,request: {pageName: 'pageNum' ,limitName: 'pageSize'}
				    ,method:'post'
				    ,page: true //开启分页
				    ,cols: [[ //表头
				       {type:'checkbox'}
				      ,{field:'id', sort: true,title: 'ID'}
				      ,{field:'role',title: '角色名称'}
				      ,{field:'description', title: '角色描述'}
				      ,{field:'resourceIdsStr', title: '拥有权限'}
				      ,{field:'available',title: '状态'}
				      ,{fixed: 'right', align:'center',toolbar: '#barDemo',width:300,title: '操作'}
				    ]]
				});
			
			//监听工具条
			  table.on('tool(layuiTable)', function(obj){
			    var data = obj.data;
			    if(obj.event === 'edit'){
			    	x_admin_show('修改角色','${path}/role/toUpdate?id='+data.id);
			    }else if(obj.event === 'resource'){ 
			    	x_admin_show('修改资源','${path}/role/toResource?id='+data.id);
			    }else if(obj.event === 'delete'){
				      layer.confirm('真的删除行么', function(index){
				    	  $.ajax({
				        		 data:{"id":data.id},
				        		 type:'POST',
				        		 url:'${path}/role/delete',
				        		 dataType:'json',
				        		 success:function(mes){
				        			if (mes.success) {
								        layer.msg(mes.msg,{icon:1,time:1000});
									}else{
									    layer.msg(mes.msg, {icon: 5,time:1000});    
									}
				        			layer.close(index);
				        			table.reload('layuiTab');
				        		 }
				        	 }); 
				        	 return false;  
				      });
			    }
			  });
			
			//查询事件
			var $ = layui.$, active = {
					batchDelete:function(){
						var checkStatus = table.checkStatus('layuiTab')
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
			        		 url:'${path}/role/batchDelete',
			        		 dataType:'json',
			        		 success:function(mes){
			        			if (mes.success) {
							        layer.msg(mes.msg);
							        table.reload('layuiTab');
								}else{
								    layer.msg(mes.msg);    
								}
			        			layer.close(index);
			        		 }	
						 	});
						}
			         }
					,tableReload: function(){
				      var role = $('#role');
				      //执行重载
				      table.reload('layuiTab', {
				       	where: {
				       		role: role.val()
				        }
				      });
				    }
				  };
		  $('.resourceTable .layui-btn').on('click', function(){
		    var type = $(this).data('type');
		   	 active[type] ? active[type].call(this) : '';
		  });
		  $('.layui-btn-container .layui-btn').on('click', function(){
			    var type = $(this).data('type');
			   	 active[type] ? active[type].call(this) : '';
			  });
		});
	</script>
</body>
</html>
