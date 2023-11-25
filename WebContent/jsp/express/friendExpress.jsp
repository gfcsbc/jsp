<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="<%=path %>/Control?act=friendExpress">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value="" />
	<input type="hidden" name="pageNum" value="" />
	<input type="hidden" name="numPerPage" value="" />
	<input type="hidden" name="orderField" value="" />
</form>
<div class="pageHeader">
<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="<%=path %>/Control?act=friendExpress" method="post">
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
			<li><a class="edit" href="<%=path %>/Control?act=checkExpress&uid={sid_express}" target="ajaxTodo" title="确定要代签快递"><span>代签快递</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="100">收件电话</th>
				<th width="100">单号</th>
				<th width="100">所在区域</th>
				<th width="100">状态</th>
				<th width="100">签收人</th>
				<th width="100">签收日期</th>
			</tr>
		</thead>
		<tbody><c:forEach items="${list}" var="p">
			<tr target="sid_express" rel="${p.id }">
				<td>${p.phone}</td>
				<td>${p.sid}</td>
				<td>${p.area}</td>
				<td>${p.status}</td>
				<td>${p.addressee.name} - ${p.addressee.loginId }</td>
				<td>${p.finDate}</td>
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
 