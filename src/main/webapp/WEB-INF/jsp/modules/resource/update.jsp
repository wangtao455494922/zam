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
		<form class="layui-form" >
			<input class="layui-hide" name="id" value="${resource.id}">
			<div class="layui-form-item">
				<label class="layui-form-label wid150">资源名称</label>
				<div class="layui-input-inline">
					<input type="text" id="name" name="name" value="${resource.name}"
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
						<c:forEach items="${types.keySet()}" var="key">
							<c:choose>
								<c:when test="${resource.type==key}">
									<option value="${key}" selected="selected">${types.get(key)}</option>
								</c:when>
								<c:otherwise>
									<option value="${key}">${types.get(key)}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label wid150">URL</label>
				<div class="layui-input-inline">
					<input type="text" id="url" name="url" value="${resource.url}"
						lay-verify="" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
	                  <span class="x-red">*</span>2至25个字符
	             </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label wid150">资源权限</label>
				<div class="layui-input-inline">
					<input type="text" id="permission" name="permission" value="${resource.permission}"
						lay-verify="required" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
	                  <span class="x-red">*</span>2至25个字符
	             </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label wid150">父类资源名称</label>
				<div class="layui-input-inline">
					<select id="parentId" name="parentId" class="valid">
					<c:forEach items="${menus}" var="menu">
						<c:choose>
							<c:when test="${resource.parentId==menu.id}">
								<option value="${menu.id}" selected="selected">${menu.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${menu.id}">${menu.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>	
					</select>
				</div>
			</div>
			<div class="layui-form-item div-center">
				<button class="layui-btn wid150" lay-filter="update" lay-submit="">
					更新</button>
			</div>
		</form>
	</div>
	<script>
        layui.use(['form','layer','table'], function(){
          var form = layui.form;
          var layer = layui.layer;
          var table = layui.table;
          //监听提交
          form.on('submit(update)', function(data){
        	 $.ajax({
        		 data:data.field,
        		 type:'POST',
        		 url:'${path}/resourceUpdate',
        		 dataType:'json',
        		 success:function(obj){
        			if (obj.success) {
        				 layer.alert(obj.msg, {icon: 6},function () {
        					    window.parent.location.reload(); //刷新父页面
        		                // 获得frame索引
        		                var index = parent.layer.getFrameIndex(window.name);
        		                //关闭当前frame
        		                parent.layer.close(index);
        		            }); 
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

