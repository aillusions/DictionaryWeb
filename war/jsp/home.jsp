<%@ include file="/jsp/fragments/jsp_common_directives.jsp"%>

<html>
<head>
<title><fmt:message key="title" /></title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

<style>
a,a:link {
	color: blue;
	text-decoration: none;
}

a:visited {
	color: blue;
	text-decoration: none;
}

a:hover,a:active {
	color: red;
	text-decoration: underline;
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

.selected{
	background-color: #fff5bf;
}
</style>

</head>
<body>

<c:forEach items="${manager.workspaceManager.workspace.dictioanries}"
	var="dict">

	<c:choose>
		<c:when
			test="${manager.currentStateManager.currentDictionary.displayName == dict.displayName}"> 
	     &gt;<c:out value="${dict.displayName}" />&lt;
	  </c:when>
		<c:otherwise>
			<a href="home.do?dictName=${dict.displayName}"><c:out
				value="${dict.displayName}" /></a>
		</c:otherwise>
	</c:choose>
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
		<c:choose>
			<c:when
				test="${manager.currentStateManager.currentPair.word == pair.word}">
				<tr class="words selected">
					<td><c:out value="${pair.word}" escapeXml="false" /></td>
			</c:when>
			<c:otherwise>
				<tr class="words">
					<td><a href="home.do?selectWord=${pair.word}"><c:out
						value="${pair.word}" escapeXml="false" /></a></td>
			</c:otherwise>
		</c:choose>

		<td><c:out value="${pair.transcription}" escapeXml="false" /></td>
		<td><c:out value="${pair.translation}" escapeXml="false" /></td>

		<c:choose>
			<c:when
				test="${manager.currentStateManager.currentPair.word == pair.word}">
				<td>[<a href="home.do?editWord=${pair.word}">edit</a>] [<a
					href="home.do?removeWord=${pair.word}">rem</a>]</td>
			</c:when>
		</c:choose>
		</tr>
	</c:forEach>
</table>
</div>

<!-- div style="position:relative;float:left;"> Trash:
    
    	 <table>	    		
		    <c:forEach items="${manager.trashManager.allTrashPairs}" var="pair">
			    <tr class="words">
				      <td>
					      <c:out value="${pair.word}" escapeXml="false" />
					  </td>
					  <td>
					      <c:out value="${pair.transcription}" escapeXml="false"/>
					  </td>
					  <td>
					      <c:out value="${pair.translation}" escapeXml="false"/>
				      </td>
				</tr>
		    </c:forEach>		    	
	    </table>
    </div-->

</body>
</html>