<%@ include file="/jsp/fragments/jsp_common_directives.jsp"%>


<html>
<head>
<title><fmt:message key="title" /></title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
<h1><fmt:message key="add-new-word.heading" /></h1>

<form:form method="post" commandName="addWord">
	<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0"
		cellpadding="5">
		<tr>
			<td align="right" width="20%">Increase (%):</td>
			<td width="20%"><form:input path="word" /> <form:input
				path="translate" /> <form:input path="transcrition" /></td>
			<td width="60%"><form:errors path="word" cssClass="error" /></td>
		</tr>
	</table>
	<br>
	<input type="submit" align="center" value="Add">
</form:form>


<a href="<c:url value="home.do"/>">Home</a>
</body>
</html>