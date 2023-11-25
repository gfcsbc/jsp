<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="contentTitle">回复消息</h2>
<div class="pageContent">
	<form method="post" action="<%=path %>/Control?act=mesageReply" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
		<input type="hidden" value="${modifybean.id}" name="id"/>
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td colspan="2"><dl>
							<dt>发送人电话：</dt>
							<dd>${modifybean.fromUser.name }</dd>
							</dl></td>
					</tr>
					 
					<tr>
						<td colspan="2"><dl class="nowrap">
							<dt>消息：</dt>
							<dd><input name="content" value="${modifybean.content}" size="80" type="text" readonly="readonly" maxlength="150"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl class="nowrap">
							<dt>回复：</dt>
							<dd><input name="recontent" value="${modifybean.recontent}" size="80" type="text"  class="required" maxlength="150"/></dd>
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
