<%@ include file="/jsp/fragments/jsp_common_directives.jsp"%>

<html>
<head>
<title><fmt:message key="title" /></title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<style>

</style>

</head>
<body>

<c:out value="${confirmationTitle}" />

<form:form action="home.do" method="post">
	<input name="${actionToBeConfirmed}" type="hidden" value="${valueToBeConfirmed}">
	<input name="actionConfirmation" type="submit" align="center" value="Yes">
	<input name="actionConfirmation" type="submit" align="center" value="No">

</form:form>

</body>
</html>