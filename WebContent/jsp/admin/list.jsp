<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="<%=path %>/Control?act=userList">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value="" />
	<input type="hidden" name="pageNum" value="" />
	<input type="hidden" name="numPerPage" value="" />
	<input type="hidden" name="orderField" value="" />
</form>
<div class="pageHeader">
<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="<%=path %>/Control?act=userList" method="post">
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
			<li><a class="add" href="<%=path %>/jsp/admin/add.jsp" target="navTab"  rel="dlg_page1" title="添加人员"><span>添加</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="<%=path %>/Control?act=userGet&uid={sid_user}" target="navTab"  rel="dlg_page1" title="修改人员"><span>修改</span></a></li>
			<li><a class="edit" href="<%=path %>/jsp/admin/password.jsp?uid={sid_user}" target="dialog" width="400" mask="true" ><span>更改密码</span></a></li>
			<li><a class="delete" href="<%=path %>/Control?act=userDel&uid={sid_user}" target="ajaxTodo" title="确定要删除吗"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="100">登录名</th>
				<th width="100">姓名</th>
				<th width="100">角色</th>
			</tr>
		</thead>
		<tbody><c:forEach items="${SESSION_PAGE.list}" var="p">
			<tr target="sid_user" rel="${p.id }">
				<td><a href="<%=path %>/Control?act=userGet&uid=${p.id}" target="navTab"  rel="dlg_page1" title="修改管理员">${p.loginId}</a></td>
				<td>${p.name}</td>
				<td>${p.userRole}</td>
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
 