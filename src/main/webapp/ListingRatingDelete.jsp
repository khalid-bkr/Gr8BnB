<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete a ListingRating</title>
</head>
<body>

	<h1>${messages.title}</h1>
	<form action="listingratingdelete" method="post">
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="listingratingid">ListingRating Id</label>
				<input id="listingratingid" name="listingratingid" value="${fn:escapeXml(param.listingratingid)}">
			</div>
		</p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>

</body>
</html>