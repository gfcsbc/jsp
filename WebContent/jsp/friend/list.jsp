<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="<%=path %>/Control?act=friendList">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value="" />
	<input type="hidden" name="pageNum" value="" />
	<input type="hidden" name="numPerPage" value="" />
	<input type="hidden" name="orderField" value="" />
</form>
<div class="pageHeader">
<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="<%=path %>/Control?act=friendList" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				 
				<td>
					<div class="subBar">
			<ul>
				<li></li>
			</ul>
		</div>
				</td>
			</tr>
		</table>
		
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<%=path %>/jsp/friend/add.jsp" target="navTab"  rel="dlg_page1" title="发送好友邀请"><span>发送邀请</span></a></li>
			<li><a class="delete" href="<%=path %>/Control?act=friendDel&uid={sid_user}" target="ajaxTodo" title="确定要删除好友吗"><span>删除好友</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="100">好友电话</th>
				<th width="100">好友姓名</th>
			</tr>
		</thead>
		<tbody><c:forEach items="${list}" var="p">
			<tr target="sid_user" rel="${p.id }">
				<td>${p.loginId}</td>
				<td>${p.name}</td>
			</tr></c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
	</div>
</div>
<%
util.Page p = (util.Page)session.getAttribute("SESSION_PAGE"); 
if(p!=null&& p.getList()!=null)p.getList().clear();
%>
 