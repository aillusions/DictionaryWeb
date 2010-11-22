<%@ include file="/jsp/fragments/jsp_common_directives.jsp"%>

<html>
<head>
<title><fmt:message key="title" /></title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

<style>
.clearall {
	clear: both;
	height: 1px;
	visibility: hidden;
	display: block;
	line-height: 0;
}

.words {
	font-family: 'Lucida Sans Unicode', 'Lucida Grande', sans-serif;
	font-size: 14;
	height: 20px;
	overflow: hidden;
	border-bottom-width: 0 !important;
	margin: 0 .2em -1px 0;
	padding: 0;
}
</style>

</head>
<body>

<c:forEach items="${manager.workspaceManager.workspace.dictioanries}"
	var="dict">
	<a href="home.do?dictName=${dict.displayName}"><c:out
		value="${dict.displayName}" /></a>
	<br />
</c:forEach>

<a href="home.do?reloadDict=true">Reload</a>
<br />

<a href="addWord.do">Add a new word</a>
<br />

<hr />
<div style="position: relative; float: left;">Words:
<table>
	<c:forEach items="${manager.pairsManager.allPairs}" var="pair">
		<tr class="words">
			<td><a href="home.do?editWord=${pair.english}">edit</a></td>
			<td><a href="home.do?removeWord=${pair.english}">rem</a></td>
			<td><c:out value="${pair.english}" escapeXml="false" /></td>
			<td><c:out value="${pair.transcription}" escapeXml="false" /></td>
			<td><c:out value="${pair.russian}" escapeXml="false" /></td>
		</tr>
	</c:forEach>
</table>
</div>

<!-- div style="position:relative;float:left;"> Trash:
    
    	 <table>	    		
		    <c:forEach items="${manager.trashManager.allTrashPairs}" var="pair">
			    <tr class="words">
				      <td>
					      <c:out value="${pair.english}" escapeXml="false" />
					  </td>
					  <td>
					      <c:out value="${pair.transcription}" escapeXml="false"/>
					  </td>
					  <td>
					      <c:out value="${pair.russian}" escapeXml="false"/>
				      </td>
				</tr>
		    </c:forEach>		    	
	    </table>
    </div-->

</body>
</html>