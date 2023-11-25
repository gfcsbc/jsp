<%@ page pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<h2 class="contentTitle">添加快递信息</h2>
<div class="pageContent">
	<form method="post" action="<%=path %>/Control?act=expressAdd" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
		<div class="pageFormContent" layoutH="97">
			<table>
					<tr>
						<td colspan="2"><dl>
							<dt>快递单号：</dt>
							<dd><input name="sid"  type="text" class="required" /></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>收件电话：</dt>
							<dd><input name="phone" maxlength="11" type="text" class="phone required" /></dd>
							</dl></td>
					</tr>
					 
					<tr>
						<td colspan="2"><dl>
							<dt>收件姓名：</dt>
							<dd><input name="name"  type="text" class="required" /></dd>
							</dl></td>
					</tr>
					<tr>
						<td colspan="2"><dl>
							<dt>所在区域：</dt>
							<dd><input name="area"  type="text" class="required" /></dd>
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
