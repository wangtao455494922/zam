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
				<a href="">AMQ管理</a> 
				<span lay-separator="">/</span>
				<a> <cite>生产者管理</cite></a>
			</span>
		</div>
	</div>
	<!-- table -->
	<div class="x-body">
		<form action="" method="post" class="layui-form layui-form-pane">
			<blockquote class="layui-elem-quote layui-text">
			  	<b>此页面仅提供测试功能,发送数据格式为JSON字符串 ,如:{"userName":"张三","password":"000000"}</b>
			</blockquote>
			<div class="layui-form-item layui-form-text">
				<label for="desc" class="layui-form-label">发送信息</label>
				<div class="layui-input-block">
					<textarea placeholder="请输入信息内容" id="sendMessage" name="sendMessage"
						class="layui-textarea"></textarea>
				</div>
			</div>
			<div class="layui-form-item div-center">
				<button class="layui-btn" lay-submit="" lay-filter="send">发送</button>
			</div>
		</form>
	</div>
	<script>
        layui.use(['form','layer'], function(){
        	var form = layui.form;
            var layer = layui.layer;
            
            //监听提交
            form.on('submit(send)', function(data){
        		 $.ajax({ 
   	        		 data:data.field,
   	        		 type:'POST',
   	        		 url:'${path}/amq/producer/send',
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
