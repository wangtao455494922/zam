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
				<a href="">系统管理</a> 
				<span lay-separator="">/</span>
				<a> <cite>资源管理</cite></a>
			</span>
		</div>
	</div>
	<!-- table -->
	<div class="x-body">
		 <div class="resourceTable demoTableClass">
		  <div class="layui-inline">
		    <input class="layui-input" name="resourceName" id="resourceName" autocomplete="off" placeholder="请输入资源名">
		  </div>
		  <button class="layui-btn" data-type="tableReload">搜索</button>
		</div>
			<div class="layui-btn-container">
			  <button class="layui-btn" data-type="deleteAll">批量删除</button>
			  <button class="layui-btn" onclick="x_admin_show('添加资源','${path}/resourceAdd')">添加</button>
			</div>
		<table id="resourceTab" class="layui-table" lay-filter="resourceTable"></table>
		<script type="text/html" id="barDemo">
 			 <button class="layui-btn layui-btn-sm" lay-event="edit">修改</button>
   			 <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
		</script>
	</div>
	<script>
		layui.use(['table' ], function() {
			var table = layui.table;
			var options={
				elem: '#resourceTab'
			    ,url: '${path}/resourceTableRender' //数据接口
			    ,request: {pageName: 'pageNum' ,limitName: 'pageSize'}
			    ,method:'post'
			    ,page: true //开启分页
			    ,cols: [[ //表头
			       {type:'checkbox'}
			      ,{field:'id', sort: true,title: 'ID'}
			      ,{field:'name',width:138,title: '资源名称'}
			      ,{field:'typeName', sort: true,title: '资源类型'}
			      ,{field:'parentResourceName',title: '上级资源'}
			      ,{field:'icon',title: '图标',templet:function(d){
			    	 if (d.icon!=null) {
			    		  return '<i class="icon iconfont">'+ d.icon +'</i>'
					} else {
						 return '无'	
					}
			      }}
			      ,{field:'permission',title: '资源权限'}
			      ,{field:'availableName', sort: true,title: '权限'}
			      ,{fixed: 'right', align:'center', sort: true,toolbar: '#barDemo',title: '操作'}
			    ]]
			};
			//table渲染
			table.render(options);
			
			//监听工具条
			  table.on('tool(resourceTable)', function(obj){
			    var data = obj.data;
			    if(obj.event === 'edit'){
			    	x_admin_show('修改资源','${path}/resourceToUpdate?id='+data.id);
			    } else if(obj.event === 'delete'){
				      layer.confirm('真的删除行么', function(index){
				    	  $.ajax({
				        		 data:{"id":data.id},
				        		 type:'POST',
				        		 url:'${path}/resourceDelete',
				        		 dataType:'json',
				        		 success:function(mes){
				        			if (mes.success) {
								        layer.msg(mes.msg,{icon:1,time:1000});
									}else{
									    layer.msg(mes.msg, {icon: 5,time:1000});    
									}
				        			layer.close(index);
				        			table.reload('resourceTab');
				        		 }
				        	 }); 
				        	 return false;  
				      });
			    } 
			  });
			
			//查询事件
			var $ = layui.$, active = {
					deleteAll:function(){
						var checkStatus = table.checkStatus('resourceTab')
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
			        		 url:'${path}/resouceDeleteAll',
			        		 dataType:'json',
			        		 success:function(mes){
			        			if (mes.success) {
							        layer.msg(mes.msg);
							        table.reload('resourceTab');
								}else{
								    layer.msg(mes.msg);    
								}
			        			layer.close(index);
			        		 }	
						 	});
						}
			         }
					,tableReload: function(){
				      var resourceName = $('#resourceName');
				      //执行重载
				      table.reload('resourceTab', {
				       	where: {
				            name: resourceName.val()
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
	<script type="text/html" id="barDemo">
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>			
</body>
</html>
