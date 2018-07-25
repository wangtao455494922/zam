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
				<label class="layui-form-label wid150">请假天数</label>
				<div class="layui-input-inline">
					<input type="text" id="days" name="days" 
						lay-verify="required|number|days" autocomplete="off" class="layui-input" >
				</div>
				<div class="layui-form-mid layui-word-aux">
	                  <span class="x-red">*</span>
	             </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label wid150">请假原因</label>
				<div class="layui-input-inline">
					<input type="text" id="content" name="content" 
						lay-verify="required" autocomplete="off" class="layui-input" >
				</div>
				<div class="layui-form-mid layui-word-aux">
	                  <span class="x-red">*</span>
	             </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label wid150">请假日期</label>
				<div class="layui-input-inline">
					<input type="text" id="leaveTime" name="leaveTime" 
						lay-verify="required|date" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">
	                  <span class="x-red">*</span>
	             </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label wid150">备注</label>
				<div class="layui-input-inline">
					<textarea class="layui-textarea" id="remark" name="remark" lay-verify="" 
						autocomplete="off"></textarea>
				</div>
			</div>
			<div class="layui-form-item div-center">
				<button class="layui-btn wid150" lay-filter="save" lay-submit="">
					保存</button>
			</div>
		</form>
	</div>
	<script>
        layui.use(['form','layer','table','laydate'], function(){
          var form = layui.form;
          var layer = layui.layer;
          var table = layui.table;
          var laydate = layui.laydate;
          
          //日期
          laydate.render({
            elem: '#leaveTime'
          });
          
          //自定义验证规则
          form.verify({
        	 days: function(value){
              if(value.length > 20){
                return '请假日期不能超过20位';
              }
            }
          });
        
          //监听提交
          form.on('submit(save)', function(data){
        	 $.ajax({
        		 data:data.field,
        		 type:'POST',
        		 url:'${path}/leave/save',
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

