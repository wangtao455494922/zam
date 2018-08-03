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
			<input class="layui-hide" name="id" value="${user.id}">
			<div class="layui-form-item">
				<label class="layui-form-label wid150">用户名称</label>
				<div class="layui-input-inline">
					<input type="text" id="username" name="username" value="${user.username}"
						lay-verify="required" autocomplete="off" class="layui-input" >
				</div>
				<div class="layui-form-mid layui-word-aux">
	                  <span class="x-red">*</span>2至25个字符
	             </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label wid150">用户密码</label>
				<div class="layui-input-inline">
					<input type="password" id="password" name="password" required=""
						lay-verify="password" autocomplete="off" class="layui-input" >
				</div>
				<div class="layui-form-mid layui-word-aux">
	                  <span class="x-red">*</span>2至25个字符
	             </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label wid150">确认密码</label>
				<div class="layui-input-inline">
					<input type="password" id="repass" name="repass" required=""
						lay-verify="repass" autocomplete="off" class="layui-input" >
				</div>
				<div class="layui-form-mid layui-word-aux">
	                  <span class="x-red">*</span>2至25个字符
	             </div>
			</div>
			  <div class="layui-form-item">
			    <label class="layui-form-label wid150">用户角色</label>
			    <div class="layui-input-inline">
			      <c:forEach items="${roles}" var="role">
				      <c:choose>
				      	<c:when test="${fn:contains(user.roleIdsStr, role.id)}">
				      		<input type="checkbox" value="${role.id}" name="roleIdsStr" lay-skin="primary" title="${role.role}" checked="checked">
				      	</c:when>
				      	<c:otherwise>
				      		<input type="checkbox" value="${role.id}" name="roleIdsStr" lay-skin="primary" title="${role.role}">
				      	</c:otherwise>
				      </c:choose>
			      </c:forEach>	
			    </div>
			  </div>
			  <div class="layui-form-item">
				<label class="layui-form-label wid150">上级领导</label>
				<div class="layui-input-inline">
					<select id="managerId" name="managerId" class="valid">
					<c:forEach items="${users}" var="user1">
						<c:choose>
					      	<c:when test="${fn:contains(user.managerId, user1.id)}">
					      		<option value="${user1.id}" selected="selected">${user1.username}</option>
					      	</c:when>
					      	<c:otherwise>
					      		<option value="${user1.id}">${user1.username}</option>
					      	</c:otherwise>
				       </c:choose>
					</c:forEach>	
					</select>
				</div>
			</div>
			<div class="layui-form-item div-center">
				<button class="layui-btn" lay-filter="update" lay-submit="">
					更新</button>
			</div>
		</form>
	</div>
	<script>
        layui.use(['form','layer'], function(){
          var form = layui.form;
          var layer = layui.layer;
          //自定义验证规则
          form.verify({
        	 password: [/(.+){6,12}$/, '密码必须6到12位']
            ,repass: function(value){
                if($('#password').val()!=$('#repass').val()){
                    return '两次密码不一致';
                }
            }
          });	
          //监听提交
          form.on('submit(update)', function(data){
        		if($("input:checked").length==0){
        			layer.msg("请选择角色");
        		}else{
        		 $.ajax({ 
   	        		 data:$("form").serialize(),
   	        		 type:'POST',
   	        		 url:'${path}/user/update',
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

