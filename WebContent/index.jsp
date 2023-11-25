<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<html xmlns="http://www.w3.org/1999/xhtml">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<title><%=APP_TITLE %></title>
			<link href="login/css.css" rel="stylesheet" />
			<link href="login/style.css" rel="stylesheet" />
			<script src="login/jquery.js" type="text/javascript"></script> 
    		<script src="login/bootstrap.min.js" type="text/javascript"></script> 
		</head>
		<body background="">
			<div align="center">
				<div class="navbar navbar-inverse navbar-fixed-top">
					<div class="navbar-inner" style="height: 10px">
					</div>
				</div>
				<div class="container" style="margin-top:50px; width:500px;" align="left">
			<div class="well">
			<c:if test="${not empty loginErrorMessage }">
				<div class="alert alert-error"><strong>提示：</strong><br /><i class="icon-exclamation-sign"></i> ${loginErrorMessage}</div>
			</c:if>
			<%session.removeAttribute("loginErrorMessage"); %>
			<div class="container" style="width: 500px;">
				<form action="<%=path %>/Control?act=login" method="post" id="login" name="login">
					<h3 style="color: #802C59" align="center"><%=APP_TITLE %> 登录</h3>
					<div class="control-group">
						<label style="font-size:18px;">登录帐号:</label>
						<input type="text" name="username" id="username" value="" style=" width: 283px;" maxlength="11" rel="tooltip" data-original-title="请输入您的登陆账号" data-placement="right"/>
					</div>
					
					<div class="control-group">
						<label style="font-size:18px">登录密码:</label>
						<input type="password" name="password" id="password" value="" style="width: 283px;" maxlength="30" rel="tooltip" data-original-title="请输入您的登录密码" data-placement="right"/>
					</div>
					 
					<div class="control-group">
						<label> <span class="labeltext" style="font-size:18px">验 证 码:</span></label>
						<a href="javascript:void(0);"><img style="border: 0px; margin-right: 100px; float: right" src="${pageContext.request.contextPath }/checkcode" alt="验证码" align="left" onclick="this.src = '${pageContext.request.contextPath }/checkcode?' + Math.random();" /></a>
						<input id="captcha-code" name="checkcode" value="" type="text" style="width:80px; padding:14px;" maxlength="6" rel="tooltip" data-original-title="请输入验证码" data-placement="top"/>
					</div>
					
					<div class="control-group" style="margin-bottom:0px;">
						<div class="input-prepend">
						<span class="add-on" style="border:0px;">
								<label style="margin-left:2px; width:1px;">
								</label>
							</span>
							<button type="submit" class="btn btn-info" style="margin-bottom: 10px; margin-left: 80px;width:167px;">登     录</button>
							<button type="button" class="btn btn-info" onclick="toreg()" style="margin-bottom: 10px; margin-left: 10px;width:167px;">注     册</button>
						</div>
					</div>
				</form>
			</div>
			</div>
			<script type="text/javascript">
				$("[rel=tooltip]").tooltip();
<%--				$("#username").keyup(function() {--%>
<%--					if (this.value.match(/[^a-zA-Z0-9 ]/g)) this.value = this.value.replace(/[^a-zA-Z0-9 ]/g, '');--%>
<%--				});--%>
				$("#captcha-code").keyup(function() {
					if (this.value.match(/[^a-zA-Z0-9 ]/g)) this.value = this.value.replace(/[^a-zA-Z0-9 ]/g, '');
				});
				$("#login").submit(function() {
					if($("#username").val().length<2 || $("#username").val().length>14) {
						$("#usernamecontrol").addClass("error");
						$("#username").focus();
						return false;
					} else $("#usernamecontrol").removeClass("error");
					if($("#password").val().length<4 || $("#password").val().length>30) {
						$("#passwordcontrol").addClass("error");
						$("#password").focus();
						return false;
					} else $("#passwordcontrol").removeClass("error");
					if($("#captcha-code").val().length!=4) {
						$("#captcha-codecontrol").addClass("error");
						$("#captcha-code").tooltip("show");
						$("#captcha-code").focus();
						return false;
					} else $("#captcha-codecontrol").removeClass("error");
					return true;
				});
				function toreg(){
					window.location="./reg.jsp";
				}
			</script>
						<div align="left" style="width:100%; border-top:1px #CCC solid; margin-top:10px;">
							<table style="width:100%;">
								<tr>
									<td align="left">  <%=APP_TITLE %></td>
								</tr>
							</table>
						</div>
						
					</div>
				</div>
			</body>
		</html>	
	