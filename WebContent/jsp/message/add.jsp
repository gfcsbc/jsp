<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="contentTitle">发送消息</h2>
<div class="pageContent">
	<form method="post" action="<%=path %>/Control?act=mesageAdd" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td colspan="2"><dl>
							<dt>收件人电话：</dt>
							<dd><input name="phone" maxlength="11" type="text" class="phone" /></dd>
							</dl></td>
					</tr>
					 
					<tr>
						<td colspan="2"><dl class="nowrap">
							<dt>消息：</dt>
							<dd><input name="content" size="80" type="text" class="required" maxlength="150"/></dd>
							</dl></td>
					</tr>
					<c:if test="${SessionUser.userRole=='管理员' }">
					<tr>
						<td colspan="2"><dl>
							<dt>所有人?</dt>
							<dd><input name="toAll" value="true" type="checkbox"/>是</dd>
							</dl></td>
					</tr>
					</c:if>
					<tr>
						<td colspan="2"><dl>
							<dt>发送给管理员?</dt>
							<dd><input name="toAdmin" value="true" type="checkbox"/>是</dd>
							</dl></td>
					</tr>
					 
				</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset" class="reset">重置</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>
