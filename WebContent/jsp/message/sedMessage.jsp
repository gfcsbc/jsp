<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="<%=path %>/Control?act=sedMessage">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value="" />
	<input type="hidden" name="pageNum" value="" />
	<input type="hidden" name="numPerPage" value="" />
	<input type="hidden" name="orderField" value="" />
</form>
<div class="pageHeader">
<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="<%=path %>/Control?act=recMessage" method="post">
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
			<li><a class="add" href="<%=path %>/jsp/message/add.jsp" target="navTab"  rel="dlg_page1" title="发送消息"><span>发送消息</span></a></li>
<!-- 			<li class="line">line</li> -->
<!-- 			<li><a class="edit" href="<%=path %>/Control?act=expressGet&uid={sid_express}" target="navTab"  rel="dlg_page1" title="修改人员"><span>修改</span></a></li> -->
<!-- 			<li><a class="delete" href="<%=path %>/Control?act=expressDel&uid={sid_express}" target="ajaxTodo" title="确定要删除吗"><span>删除</span></a></li> -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="100">发送人</th>
				<th width="100">发送时间</th>
				<th width="100">接收人</th>
				<th width="500">内容</th>
				<th width="400">回复内容</th>
			</tr>
		</thead>
		<tbody><c:forEach items="${list}" var="p">
			<tr target="sid_express" rel="${p.id }">
				<td>${p.fromUser.name}</td>
				<td>${p.addDate}</td>
				<td>${p.toUser.name}
				<c:if test="${empty p.toUser }">所有人</c:if>
				</td>
				<td>${p.content}</td>
				<td>${p.recontent}</td>
				
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
 