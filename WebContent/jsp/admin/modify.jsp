<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2 class="contentTitle">修改人员信息</h2>
<div class="pageContent">
	<form method="post" action="<%=path %>/Control?act=userUpdate" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
		<input type="hidden" value="${bean.id }"  name="id"/>
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td colspan="2"><dl>
							<dt>登录名：</dt>
							<dd><input name="loginId"  type="text" readonly="readonly" value="${bean.loginId }"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>姓名：</dt>
							<dd><input name="name"  type="text" class="required" value="${bean.name }"/></dd>
							</dl></td>
					</tr>
					<tr>
						<td><dl>
							<dt>角色：</dt>
							<dd>
								<select name="userRole" style="width:134px" class="required">
									<option value="用户" <c:if test="${bean.userRole=='用户' }">selected="selected"</c:if>>用户</option>
									<option value="管理员" <c:if test="${bean.userRole=='管理员' }">selected="selected"</c:if> >管理员</option>
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
