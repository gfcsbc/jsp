<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="<%=path %>/Control?act=expressList">
	<input type="hidden" name="status" value="${type}" />
	<input type="hidden" name="pageNum" value="" />
	<input type="hidden" name="numPerPage" value="" />
	<input type="hidden" name="orderField" value="" />
</form>
<div class="pageHeader">
<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="<%=path %>/Control?act=expressList" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				 <td>
				签收状态: <select name="status">
				 	<option value="">选择签收状态</option>
				 	<option value="未签收">未签收</option>
				 	<option value="已签收">已签收</option>
				 </select>
				 </td>
				<td>
					<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
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
			<li><a class="add" href="<%=path %>/jsp/express/add.jsp" target="navTab"  rel="dlg_page1" title="添加信息"><span>添加</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="<%=path %>/Control?act=expressGet&uid={sid_express}" target="navTab"  rel="dlg_page1" title="修改人员"><span>修改</span></a></li>
			<li><a class="delete" href="<%=path %>/Control?act=expressDel&uid={sid_express}" target="ajaxTodo" title="确定要删除吗"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="100">收件电话</th>
				<th width="100">收件姓名</th>
				<th width="100">单号</th>
				<th width="100">所在区域</th>
				<th width="100">状态</th>
				<th width="100">签收人</th>
				<th width="100">签收日期</th>
			</tr>
		</thead>
		<tbody><c:forEach items="${SESSION_PAGE.list}" var="p">
			<tr target="sid_express" rel="${p.id }">
				<td>${p.phone}</td>
				<td>${p.name}</td>
				<td>${p.sid}</td>
				<td>${p.area}</td>
				<td>${p.status}</td>
				<td>${p.addressee.name} - ${p.addressee.loginId }</td>
				<td>${p.finDate}</td>
			</tr></c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>共${SESSION_PAGE.totalNumber}条</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${SESSION_PAGE.totalNumber}" numPerPage="${SESSION_PAGE.itemsPerPage}" pageNumShown="10" currentPage="${SESSION_PAGE.currentPageNumber}"></div>
	</div>
</div>
<%
util.Page p = (util.Page)session.getAttribute("SESSION_PAGE"); 
if(p!=null&& p.getList()!=null)p.getList().clear();
%>
 