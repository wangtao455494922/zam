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
		<form action="" method="post" class="layui-form layui-form-pane">
			<div class="layui-form-item">
				<label for="name" class="layui-form-label">角色名
				</label>
				<div class="layui-input-inline">
					<input type="text" id="role" name="role" required=""
						lay-verify="required" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label for="desc" class="layui-form-label"> 描述 </label>
				<div class="layui-input-block">
					<textarea placeholder="请输入内容" id="description" name="description"
						class="layui-textarea"></textarea>
				</div>
			</div>
			<div class="layui-form-item div-center">
				<button class="layui-btn" lay-submit="" lay-filter="save">增加</button>
			</div>
		</form>
	</div>
	<script>
        layui.use(['form','layer'], function(){
        	var form = layui.form;
            var layer = layui.layer;
            
            //监听提交
            form.on('submit(save)', function(data){
        		 $.ajax({ 
   	        		 data:data.field,
   	        		 type:'POST',
   	        		 url:'${path}/role/save',
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
        		return false;
          });
        });
    </script>
</body>
</html>

