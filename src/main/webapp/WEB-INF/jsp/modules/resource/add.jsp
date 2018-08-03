<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/include/head.jsp"%>
<style type="text/css">
	.wid150{
		width: 150px;
	}
	.div-center{
		text-align: center;
	}
</style>
</head>
<body>
	<div class="x-body">
		<form class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label wid150">资源名称</label>
				<div class="layui-input-inline">
					<input type="text" id="name" name="name" 
						lay-verify="required" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
	                  <span class="x-red">*</span>2至25个字符
	             </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label wid150"> 资源类型</label>
				<div class="layui-input-inline">
					<select name="type">
						<option value="menu">菜单</option>
						<option value="button">按钮</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label wid150">资源URL</label>
				<div class="layui-input-inline">
					<input type="text" id="url" name="url" 
						lay-verify="" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
	                  <span class="x-red">*</span>2至25个字符
	             </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label wid150">资源权限</label>
				<div class="layui-input-inline">
					<input type="text" id="permission" name="permission" 
						lay-verify="required" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
	                  <span class="x-red">*</span>2至25个字符
	             </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label wid150">资源父类</label>
				<div class="layui-input-inline">
					<select id="shipping" name="parentId" class="valid">
					<c:forEach items="${menus}" var="menu">
						<option value="${menu.id}">${menu.name}</option>
					</c:forEach>	
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label wid150">资源图标</label>
				<div class="layui-input-inline">
					<input type="icon" id="permission" name="icon" 
						lay-verify="required" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
	                  <span class="x-red">*</span>具体参照  系统管理→图标管理
	             </div>
			</div>
			<div class="layui-form-item div-center">
				<button class="layui-btn wid150" lay-filter="add" lay-submit="">
					保存</button>
			</div>
		</form>
	</div>
	<script>
        layui.use(['form','layer','table'], function(){
          var form = layui.form;
          var layer = layui.layer;
          var table = layui.table;
          //监听提交
          form.on('submit(add)', function(data){
        	 $.ajax({
        		 data:data.field,
        		 type:'POST',
        		 url:'${path}/resourceSave',
        		 dataType:'json',
        		 success:function(obj){
        			if (obj.success) {
        				 layer.msg(obj.msg,{icon:6,time:1000});
        				 setTimeout(function() {
        					 window.parent.location.reload(); //刷新父页面
        				 }, 1000);
					}else{
						 layer.alert(obj.msg, {icon: 5});    
					}
        		 }
        	 }); 
        	 return false;
          });
        });
    </script>
</body>
</html>

