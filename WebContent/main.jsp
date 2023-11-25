<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title><%=APP_TITLE %></title>
<link href="themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="js/speedup.js" type="text/javascript"></script>
<![endif]-->
<script src="js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="js/jquery.cookie.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>
<script src="uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<!-- <script type="text/javascript" src="chart/raphael.js"></script> -->
<!-- <script type="text/javascript" src="chart/g.raphael.js"></script> -->
<!-- <script type="text/javascript" src="chart/g.bar.js"></script> -->
<!-- <script type="text/javascript" src="chart/g.line.js"></script> -->
<!-- <script type="text/javascript" src="chart/g.pie.js"></script> -->
<!-- <script type="text/javascript" src="chart/g.dot.js"></script> -->

<script src="js/dwz.core.js" type="text/javascript"></script>
<script src="js/dwz.util.date.js" type="text/javascript"></script>
<script src="js/dwz.validate.method.js" type="text/javascript"></script>
<script src="js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="js/dwz.barDrag.js" type="text/javascript"></script>
<script src="js/dwz.drag.js" type="text/javascript"></script>
<script src="js/dwz.tree.js" type="text/javascript"></script>
<script src="js/dwz.accordion.js" type="text/javascript"></script>
<script src="js/dwz.ui.js" type="text/javascript"></script>
<script src="js/dwz.theme.js" type="text/javascript"></script>
<script src="js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="js/dwz.navTab.js" type="text/javascript"></script>
<script src="js/dwz.tab.js" type="text/javascript"></script>
<script src="js/dwz.resize.js" type="text/javascript"></script>
<script src="js/dwz.dialog.js" type="text/javascript"></script>
<script src="js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="js/dwz.cssTable.js" type="text/javascript"></script>
<script src="js/dwz.stable.js" type="text/javascript"></script>
<script src="js/dwz.taskBar.js" type="text/javascript"></script>
<script src="js/dwz.ajax.js" type="text/javascript"></script>
<script src="js/dwz.pagination.js" type="text/javascript"></script>
<script src="js/dwz.database.js" type="text/javascript"></script>
<script src="js/dwz.datepicker.js" type="text/javascript"></script>
<script src="js/dwz.effects.js" type="text/javascript"></script>
<script src="js/dwz.panel.js" type="text/javascript"></script>
<script src="js/dwz.checkbox.js" type="text/javascript"></script>
<script src="js/dwz.history.js" type="text/javascript"></script>
<script src="js/dwz.combox.js" type="text/javascript"></script>
<script src="js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"});
		}
	});
});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="<%=path %>/web">标志</a></span>
				<ul class="themeList" >
					<li><a href="<%=path %>/jsp/admin/passwordSelf.jsp" target="dialog" width="550" mask="true" style="color:#F00">修改密码</a></li>
					<li><a href="<%=path%>/Control?act=logout" style="color:#F00">退出</a></li>
				</ul>
			</div>
			 
		</div>
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<c:if test="${not empty SessionUser }">
				<div class="toggleCollapse"><h2>功能菜单</h2><div>收缩</div></div>
				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>基本信息</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
								<c:if test="${SessionUser.userRole=='管理员' }">
									<li><a href="<%=path %>/Control?act=userList" target="navTab" rel="dlg_page">人员管理</a></li>
									<li><a href="<%=path %>/Control?act=expressList" target="navTab" rel="dlg_page">快递管理</a></li>
									<li><a href="<%=path %>/Control?act=recMessage" target="navTab" rel="dlg_page">我的私信</a></li>
									<li><a href="<%=path %>/Control?act=sedMessage" target="navTab" rel="dlg_page">已发送私信</a></li>
								</c:if>
								<c:if test="${SessionUser.userRole=='用户' }">
									<li><a href="<%=path %>/Control?act=friendList" target="navTab" rel="dlg_page">我的好友</a></li>
									<li><a href="<%=path %>/Control?act=applyList" target="navTab" rel="dlg_page">好友申请</a></li>
									<li><a href="<%=path %>/Control?act=recMessage" target="navTab" rel="dlg_page">我的私信</a></li>
									<li><a href="<%=path %>/Control?act=sedMessage" target="navTab" rel="dlg_page">已发送私信</a></li>
									<li><a href="<%=path %>/Control?act=myExpress" target="navTab" rel="dlg_page">我的快递</a></li>
									<li><a href="<%=path %>/Control?act=friendExpress" target="navTab" rel="dlg_page">好友的快递</a></li>
								</c:if>
						</ul>
					</div>
				</div>
				</c:if>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><span><span class="home_icon">我的主页</span></span></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="/index.jsp">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox"> 
					<div class="accountInfo">
							<div class="alertInfo">
							</div>
							<div class="right">
							</div>
							<p>
								<h2>欢迎您：${SessionUser.name} </h2>
							 </p>
							<p id="nowDiv"></p>
							<p></p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">Copyright &copy;  <%=APP_TITLE %></div>
<script>
var date = new Date(); 
var now = "";
var nowdiv = document.getElementById("nowDiv");
function show(){
date = new Date();
now = "";
now = date.getFullYear()+"年"; 
now = now + (date.getMonth()+1)+"月"; 
now = now + date.getDate()+"日";
now = now + date.getHours()+"时";
now = now + date.getMinutes()+"分";
now = now + date.getSeconds()+"秒";
nowdiv.innerHTML = now; 
setTimeout("show()",1000);
}
show();
</script> 
</body>
</html>