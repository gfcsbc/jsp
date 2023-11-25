<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<h2 class="contentTitle">添加人员信息</h2>
<div class="pageContent">
	<form method="post" action="<%=path %>/Control?act=userAdd" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td colspan="2"><dl>
							<dt>账号：</dt>
							<dd><input name="loginId" maxlength="11" type="text" class="phone required" /></dd>
							</dl></td>
					</tr>
					<tr>
						<td ><dl>
							<dt>用户密码：</dt>
							<dd><input name="password"   id="w_validation_pwd" type="password"  class="required alphanumeric" minlength="6" maxlength="20"/></dd>
							</dl></td>
						<td ><dl>
							<dt>密码确认：</dt>
							<dd><input name="repassword"    type="password" class="required" equalto="#w_validation_pwd"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>姓名：</dt>
							<dd><input name="name"  type="text" class="required" /></dd>
							</dl></td>
					</tr>
					<tr>
						<td><dl>
							<dt>角色：</dt>
							<dd>
								<select name="userRole" style="width:134px" class="required">
									<option value="用户">用户</option>
									<option value="管理员">管理员</option>
								</select>
							</dd>
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
