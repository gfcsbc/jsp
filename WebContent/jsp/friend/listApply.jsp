<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="<%=path %>/Control?act=applyList">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value="" />
	<input type="hidden" name="pageNum" value="" />
	<input type="hidden" name="numPerPage" value="" />
	<input type="hidden" name="orderField" value="" />
</form>
<div class="pageHeader">
<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="<%=path %>/Control?act=applyList" method="post">
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
			<li><a class="edit" href="<%=path %>/Control?act=checkApply&uid={sid_express}" target="ajaxTodo" title="确定要加为好友吗?<br/>好友之间可以帮忙签收快递"><span>加为好友</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="100">申请人电话</th>
				<th width="100">申请人姓名</th>
				<th width="100">申请时间</th>
				<th width="500">留言</th>
			</tr>
		</thead>
		<tbody><c:forEach items="${list}" var="p">
			<tr target="sid_express" rel="${p.id }">
				<td>${p.fromUser.loginId}</td>
				<td>${p.fromUser.name}</td>
				<td>${p.addDate}</td>
				<td>${p.content}</td>
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
 