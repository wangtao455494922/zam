
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
	<!-- table -->
	<table class="layui-table">
        <thead>
          <tr>
            <th>批注时间</th>
            <th>批注人</th>
            <th>批准信息</th>
            </tr>
        </thead>
        <tbody>
          <c:forEach items="${commentList}" var="comment">	
	          <tr>
	            <td><fmt:formatDate value="${comment.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	            <td>${comment.userId}</td>
	            <td>${comment.fullMessage}</td>
	          </tr>
          </c:forEach>
        </tbody>
      </table>
     </div> 
</body>
</html>
