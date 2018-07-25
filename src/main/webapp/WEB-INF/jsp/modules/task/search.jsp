<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/include/head.jsp"%>
</head>
<body>
	<!-- 1.获取到规则流程图 -->
	<img style="position: absolute;top: 0px;left: 0px;" src="${path}/task/viewImage?deploymentId=${deploymentId}&imageName=${imageName}">
	<!-- 2.根据当前活动的坐标，动态绘制DIV -->
	<div style="position: absolute;border:1px solid red;top:${acs.y}px;left:${acs.x}px;width:${acs.width}px;height:${acs.height}px; ">
	</div>
</body>
</body>
</html>

