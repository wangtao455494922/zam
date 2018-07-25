
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
				<a> <cite>任务管理</cite></a>
			</span>
		</div>
	</div>
	<!-- table -->
	<div class="x-body">
		 <div class="resourceTable demoTableClass">
		  <div class="layui-inline">
		    <input class="layui-input" name="name" id="name" autocomplete="off" placeholder="请输入任务名称">
		  </div>
		  <button class="layui-btn" data-type="tableReload">搜索</button>
		</div>
		<table id="taskTab" class="layui-table" lay-filter="taskTable"></table>
		<script type="text/html" id="barDemo">
   			 <button class="layui-btn layui-btn-sm" lay-event="handle">办理任务</button>
			 <button class="layui-btn layui-btn-sm" lay-event="approve">审批记录</button>
			 <button class="layui-btn layui-btn-sm" lay-event="search">查流程图</button>
		</script>
	</div>
	<script>
		layui.use(['table' ], function() {
			var table = layui.table;
			//table渲染
			table.render({
				elem: '#taskTab'
			    ,url: '${path}/task/tableRender' //数据接口
			    ,request: {pageName: 'pageNum' ,limitName: 'pageSize'}
			    ,method:'post'
			    ,page: true //开启分页
			    ,cols: [[ //表头
			       {type:'checkbox'}
			      ,{field:'id', sort: true,title: 'ID'}
			      ,{field:'name',width:138,title: '任务名称'}
			      ,{field:'createTime', sort: true,title: '发布时间',templet:'<div>{{ layui.laytpl.toDateString(d.createTime) }}</div>'} 
			      ,{field:'assignee',width:138,title: '办理人'}
			      ,{fixed: 'right', align:'center', toolbar: '#barDemo',title: '操作'}
			    ]]
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
			table.on('tool(taskTable)', function(obj) {
				var data = obj.data;
				if (obj.event === 'handle') {
					x_admin_show('办理任务','${path}/task/handle?taskId='+data.id,600,400);
				}else if(obj.event === 'search'){
					x_admin_show('查流程图','${path}/task/search?taskId='+data.id);
				}else if(obj.event === 'approve'){
					x_admin_show('审批记录 ','${path}/task/approve?taskId='+data.id);
				}
			});

			//查询事件
			var $ = layui.$, active = {
    			tableReload : function() {
					var name = $('#name');
					//执行重载
					table.reload('taskTab', {
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
</body>
</html>
