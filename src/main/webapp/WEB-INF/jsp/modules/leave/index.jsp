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
				<a> <cite>请假信息管理</cite></a>
			</span>
		</div>
	</div>
	<!-- table -->
	<div class="x-body">
		 <div class="resourceTable demoTableClass">
		  <div class="layui-inline">
		    <input class="layui-input" name="days" id="days" autocomplete="off" placeholder="请输入请假天数">
		  </div>
		  <div class="layui-inline">
		     <input type="text" class="layui-input" id="leaveTimeFromTo" placeholder="请选择请假天数范围">
		  </div>
		  <button class="layui-btn" data-type="tableReload">搜索</button>
		</div>
			<div class="layui-btn-container">
			  <button class="layui-btn" data-type="batchDelete">批量删除</button>
			  <button class="layui-btn" onclick="x_admin_show('添加请假信息','${path}/leave/add',700,400)">添加</button>
			</div>
		<table id="layuiTab" class="layui-table" lay-filter="layuiTable"></table>
		<script type="text/html" id="barDemo">
 			 <button class="layui-btn layui-btn-sm" lay-event="edit">请假修改</button>
   			 <button class="layui-btn layui-btn-sm" lay-event="delete">请假删除</button>
			 <button class="layui-btn layui-btn-sm" lay-event="apply">申请请假</button>
			 <button class="layui-btn layui-btn-sm" lay-event="approve">审批记录</button>
		</script>
	</div>
	<script>
		layui.use(['table','laydate' ], function() {
			var table = layui.table;
			var laydate = layui.laydate;
			//table渲染
			table.render({
				elem: '#layuiTab'
				    ,url: '${path}/leave/tableRender' //数据接口
				    ,request: {pageName: 'pageNum' ,limitName: 'pageSize'}
				    ,method:'post'
				    ,page: true //开启分页
				    ,cols: [[ //表头
				       {type:'checkbox'}
				      ,{field:'id', sort: true,title: 'ID'}
				      ,{field:'days',width:138,sort: true,title: '请假天数'}
				      ,{field:'content', title: '请假内容'}
				      ,{field:'leaveTime',title: '请假时间',templet:'<div>{{ layui.laytpl.toDateString(d.leaveTime) }}</div>'}
				      ,{field:'stateName', title: '流程状态'}
				      ,{field:'remark',title: '备注'}
				      ,{fixed: 'right', align:'center',toolbar: '#barDemo',width:400,title: '操作'}
				    ]]
				});
			
			  //年月范围
			  laydate.render({
			     elem: '#leaveTimeFromTo'
			    ,type: 'month'
			    ,range: true
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
			  table.on('tool(layuiTable)', function(obj){
			    var data = obj.data;
			    if(obj.event === 'edit'){
			    	x_admin_show('修改资源','${path}/leave/toUpdate?id='+data.id,600,400);
			    } else if(obj.event === 'delete'){
				      layer.confirm('真的删除行么', function(index){
				    	  $.ajax({
				        		 data:{"id":data.id},
				        		 type:'POST',
				        		 url:'${path}/leave/delete',
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
			    }else if(obj.event === 'apply'){
			    	 $.ajax({
		        		 data:{"id":data.id},
		        		 type:'POST',
		        		 url:'${path}/leave/apply',
		        		 dataType:'json',
		        		 success:function(mes){
		        			if (mes.success) {
						        layer.msg(mes.msg,{icon:1,time:1000});
							}else{
							    layer.msg(mes.msg, {icon: 5,time:1000});    
							}
		        			table.reload('layuiTab');
		        		 }
		        	 }); 
		        	 return false;  
			    }else if(obj.event === 'approve'){
					x_admin_show('审批记录 ','${path}/leave/approve?leaveId='+data.id);
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
			        		 url:'${path}/leave/batchDelete',
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
				      var days = $('#days');
				      var leaveTimeFromTo = $('#leaveTimeFromTo');
				      //执行重载
				      table.reload('layuiTab', {
				       	where: {
				       		days: days.val()
				            ,leaveTimeFromTo:leaveTimeFromTo.val()
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
