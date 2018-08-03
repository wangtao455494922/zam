<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/include/head.jsp"%>
<%@include file="/WEB-INF/jsp/include/ztree.jsp"%>
<script type="text/javascript">
    var resourceIdsStr="";//所选资源ids
	var setting = {
	    check: {
	        enable: true
	    },
	    data: {
	        simpleData: {
	            enable: true
	        }
	    },
	    callback:{
            onCheck:onCheck
       }
	};
	$(document).ready(function(){
		$.ajax({
			type: "POST",
			data:{'id':"${id}"},
			url: "${path}/getResources4Ztree?data="+Date.parse(new Date()),
			dataType: "json",
			success: function(data){
				 $.fn.zTree.init($("#treeDemo"), setting, data);
				 var treeObj = $.fn.zTree.getZTreeObj("treeDemo"); 
				 treeObj.expandAll(true); 
			}
		});
	});
	
	 layui.use(['form','layer'], function(){
     	 var form = layui.form;
         var layer = layui.layer;
         
         //监听提交
         form.on('submit(update)', function(data){
     		 $.ajax({ 
	        		 data:{'id':data.field.id,'resourceIdsStr':resourceIdsStr},
	        		 type:'POST',
	        		 url:'${path}/role/update',
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
	//ztree的checkbox选择事件
	function onCheck(e, treeId, treeNode) {
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo"), nodes = treeObj
				.getCheckedNodes(true),v="";
		for (var i = 0; i < nodes.length; i++) {
			v += nodes[i].id + ",";
		}
		resourceIdsStr=v;
	}
</script>

</head>
<body>
	<div class="x-body">
		<form action="" method="post" class="layui-form layui-form-pane">
			<input class="layui-hide" name="id" value="${id}">
			<div class="zTreeDemoBackground" style="width: 100%;height: 100%;overflow: hidden">
		        <ul id="treeDemo" class="ztree" style="width: 100%;height: 100%;border: none"></ul>
		    </div>
			<div class="layui-form-item div-center" style="margin-top: 1%">
				<button class="layui-btn" lay-submit="" lay-filter="update">更新</button>
			</div>
		</form>
	</div>
</body>
</html>

