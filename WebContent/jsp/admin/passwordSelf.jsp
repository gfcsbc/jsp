<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	<form method="post" action="<%=path %>/Control?act=passwordSelf" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td ><dl>
							<dt>旧密码：</dt>
							<dd><input name="oldPassword" type="password"  class="required" minlength="6" maxlength="20"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td ><dl>
							<dt>新密码：</dt>
							<dd><input name="userPassword" id="w_validation_pwd" type="password"  class="required alphanumeric" minlength="6" maxlength="20"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td ><dl>
							<dt>新密码确认：</dt>
							<dd><input name="repassword"  type="password" class="required" equalto="#w_validation_pwd"/></dd>
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
