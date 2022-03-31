<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css"/>
<title>Delete a ListingRating</title>
</head>
<body>
<jsp:include page="NavBar.jsp"></jsp:include>

	
	<form  action="listingratingdelete" method="post" class="container">
		<h1 class="header d-flex justify-content-center">${messages.title}</h1>
		<p <c:if test="${messages.disableSubmit}">style="display: none !important"</c:if> class="d-flex justify-content-center">
			<input id="listingratingid" name="listingratingid" placeholder="ListingRating ID" class="search-box" value="${fn:escapeXml(param.listingratingid)}">
		</p>
		<div <c:if test="${messages.disableSubmit}">style="display:none !important"</c:if> class="d-flex flex-column" >
			<div class="d-flex justify-content-center">
				<input type="submit"  value="Delete" class="btn btn-main">
			</div>
		</div>
	</form>

</body>
</html>