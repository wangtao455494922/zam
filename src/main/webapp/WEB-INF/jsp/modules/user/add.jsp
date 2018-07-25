<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/include/head.jsp"%>
</head>
<body>
	<div class="x-body">
		<form class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label wid150">用户名称</label>
				<div class="layui-input-inline">
					<input type="text" id="username" name="username" 
						lay-verify="required" autocomplete="off" class="layui-input" >
				</div>
				<div class="layui-form-mid layui-word-aux">
	                  <span class="x-red">*</span>2至25个字符
	             </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label wid150">用户密码</label>
				<div class="layui-input-inline">
					<input type="password" id="password" name="password" 
						lay-verify="required" autocomplete="off" class="layui-input" >
				</div>
				<div class="layui-form-mid layui-word-aux">
	                  <span class="x-red">*</span>2至25个字符
	             </div>
			</div>
			  <div class="layui-form-item">
			    <label class="layui-form-label wid150">用户角色</label>
			    <div class="layui-input-inline">
			      <c:forEach items="${roles}" var="role">
			      	<input type="checkbox" value="${role.id}" name="roleIdsStr" lay-skin="primary" title="${role.role}">
			      </c:forEach>	
			    </div>
			  </div>
			<div class="layui-form-item div-center">
				<button class="layui-btn wid150" lay-filter="save" lay-submit="">
					保存</button>
			</div>
		</form>
	</div>
	<script>
        layui.use(['form','layer'], function(){
          var form = layui.form;
          var layer = layui.layer;
        
          //监听提交
          form.on('submit(save)', function(data){
        		if($("input:checked").length==0){
        			layer.msg("请选择角色");
        		}else{
        		 $.ajax({ 
   	        		 data:$("form").serialize(),
   	        		 type:'POST',
   	        		 url:'${path}/user/save',
   	        		 dataType:'json',
   	        		 success:function(obj){
   	        			if (obj.success) {
   	        				 layer.msg(obj.msg,{icon:6,time:1000});
   	        				 setTimeout(function() {
   	        					 window.parent.location.reload(); //刷新父页面
   	        				 }, 1000);
   						}else{
   							 layer.msg(obj.msg, {icon: 5});    
   						}
   	        		 } 
   	        	 });  
        	}
        	return false;
          });
        });
    </script>
</body>
</html>

