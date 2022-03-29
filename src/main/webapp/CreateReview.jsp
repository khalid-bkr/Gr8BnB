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
	<h1 class="header d-flex justify-content-center">${messages.title}</h1>
	
	<form action="createreview" method="post">
	<div class="d-flex justify-content-center">
		<textarea rows="5" cols="20" placeholder="Please input your comment here." class="form-control w-50 my-5" name="newcomment"></textarea>
	</div>
	<input type="hidden" name="listingId" value="${param.listingid}">
	<div class="d-flex justify-content-center mt-5"><input type="submit" class="btn btn-main" value="Submit Your Review" /></div>
	</form>
</div>
	
	
	
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>



