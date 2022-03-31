<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gr8BnB</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css"/>
</head>
<body>
<jsp:include page="NavBar.jsp"></jsp:include>

<div class="container">
    <br/>
	<h1 class="d-flex justify-content-center">Update Listing</h1>
	<h4 class="header d-flex justify-content-center">${messages.title}</h4>
	<br/>
	
    <form action="listingupdate" method="post" class="container">
		<p class="d-flex justify-content-center">
			<input id="listingId" name="listingId" placeholder="Listing ID" class="search-box" value="${fn:escapeXml(param.listingId)}">
		</p>
		<p class="d-flex justify-content-center">
			$ <input id="price" name="price" placeholder="New Price" class="search-box" value="${fn:escapeXml(param.price)}">
		</p>
		<div class="d-flex flex-column" >
			<div class="d-flex justify-content-center">
				<input type="submit"  value="Submit" class="btn btn-main">
			</div>
			<br/><br/><br/>
			<span id="successMessage" class="d-flex justify-content-center"><b>${messages.success}</b></span>
		</div>
	</form>
	
    <br/>
</div>




<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>