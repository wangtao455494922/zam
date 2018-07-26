<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>后台登录-wjt-admin2.0</title>
	<%@include file="/WEB-INF/jsp/include/head.jsp" %>
</head>
<body class="login-bg">
    <div class="login layui-anim layui-anim-up">
        <div class="message">wjt-admin2.0-管理登录</div>
        <div id="darkbannerwrap"></div>
        <form method="post" class="layui-form" >
            <input name="username" lay-verify="required" placeholder="用户名"  type="text" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
		    <input type="checkbox" name="rememberMe" lay-skin="primary" title="记住我">
		    <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
    </div>
	<script>
		window.onload = function(){
			if(window.parent!=window){
				window.parent.location.href=path+"/login";
			}
		}	
		$(function() {
			layui.use('form', function() {
				var form = layui.form;
				//监听提交
				 form.on('submit(login)', function(data) {
					 $.ajax({
						data : {
							'username' : data.field.username,
							'password' : data.field.password,
							'rememberMe':$("input:checked").length==0?false:true
						},
						dataType:"json",
						type : 'post',
						url : "${path}/login",
						success : function(data) {
							if(data.success){
								location.href="${path}/index";
							} else{
								layer.msg(data.msg);
							}
						}
					});
					return false; 
				}); 
			});
		})
	</script></html>
