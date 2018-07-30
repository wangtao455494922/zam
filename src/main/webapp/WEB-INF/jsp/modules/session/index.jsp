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
				<a href="">系统监控</a> 
				<span lay-separator="">/</span>
				<a> <cite>会话管理</cite></a>
			</span>
		</div>
	</div>
	<!-- table -->
	<div class="x-body">
		 <div class="resourceTable demoTableClass">
		  <div class="layui-inline">
		    <input class="layui-input" name="name" id="name" autocomplete="off" placeholder="请输入用户名">
		  </div>
		  <button class="layui-btn" data-type="tableReload">搜索</button>
		</div>
		<table id="sessionTab" class="layui-table" lay-filter="sessionTable"></table>
		<script type="text/html" id="barDemo">
			 {{#  if(d.forceLogout == '否'){ }}	
   			 	<button class="layui-btn layui-btn-sm" lay-event="forceLogout">强制退出</button>
			 {{#  } }}
		</script>
	</div>
	<script>
		layui.use(['table','layer' ], function() {
			var table = layui.table;
			var layer = layui.layer;
			//table渲染
			table.render({
				elem: '#sessionTab'
			    ,url: '${path}/session/tableRender' //数据接口
			    ,request: {pageName: 'pageNum' ,limitName: 'pageSize'}
			    ,method:'post'
			    ,page: true //开启分页
			    ,cols: [[ //表头
			       {type:'checkbox'}
			      ,{field:'id', sort: true,title: '会话ID'}
			      ,{field:'name',width:138,title: '用户名'}
			      ,{field:'host',title: '主机地址'} 
			      ,{field:'lastAccessTime', sort: true,title: '最后访问时间',templet:'<div>{{ layui.laytpl.toDateString(d.lastAccessTime) }}</div>'} 
			      ,{field:'forceLogout',width:138,title: '是否强制退出'}
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
			table.on('tool(sessionTable)', function(obj) {
				var data = obj.data;
				if (obj.event === 'forceLogout') {
					  $.ajax({
			        		 data:{"id":data.id},
			        		 type:'POST',
			        		 url:"${path}/session/forceLogout",
			        		 dataType:'json',
			        		 success:function(mes){
			        			if (mes.success) {
							        layer.msg(mes.msg,{icon:1,time:1000});
								}else{
								    layer.msg(mes.msg, {icon: 5,time:1000});    
								}
			        			table.reload('sessionTab');
			        		 }
			        	 }); 
			         return false;  
				}
			});

			//查询事件
			var $ = layui.$, active = {
    			tableReload : function() {
					var name = $('#name');
					//执行重载
					table.reload('sessionTab', {
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
