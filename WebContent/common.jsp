<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//项目应用根路径
	String path = request.getContextPath();
	if (path == null) {
		path = "";
	}
	String APP_TITLE = " 校园快递信息管理系统 "; 
%>
<%--
<%
//客户端禁用缓存
response.addHeader("Cache-Control", "no-cache");
response.addHeader("Pragma", "No-cache");
response.addDateHeader("Expires", 0);
%>
--%>