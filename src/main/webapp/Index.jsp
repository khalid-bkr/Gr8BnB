<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css"/>

<title>Gr8BnB</title>
<style>
	
	a {
		text-decoration: none;
		color: black;
	}
	
</style>
</head>
<body>
<jsp:include page="NavBar.jsp"></jsp:include>

      
   	<form action="index" method="post" class="container">
		<h1 class="header d-flex justify-content-center">Search for Listings by neighborhood name</h1>
		<p class="d-flex justify-content-center">
			<input id="neighborhood" name="neighborhood" placeholder="Neighborhood Name" class="search-box" value="${fn:escapeXml(param.neighborhood)}">
		</p>
		<div class="d-flex flex-column" >
			<div class="d-flex justify-content-center">
				<input type="submit"  value="Search" class="btn btn-main">
			</div>
			<br/><br/><br/>
			<span id="successMessage" class="d-flex justify-content-center"><b>${messages.success}</b></span>
		</div>
	</form>
	
	<br/>
	<div class="container">
		<h1 class="d-flex justify-content-center">Matching Listings</h1>
		<div class="cards">
			<c:forEach items="${listings}" var="listing" >
				<div class="card-trip">
				  <img src="<c:out value="${listing.getPictureUrl()}" />" />
				  <div class="card-trip-infos">
				    <div>
				      <h2> <a href="findlisting?listingid=<c:out value="${listing.getName()}" />"> <c:out value="${listing.getName()}" /></a></h2>
				      <p>Hosted By: <a href="findhost?username=<c:out value="${listing.getHost().getUserName()}"/>"><c:out value="${listing.getHost().getUserName()}"/></a></p>
	
				    </div>
				    <h2 class="card-trip-pricing">$<c:out value="${listing.getPrice()}0/night" /></h2>
				  </div>
				</div>
			</c:forEach>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
<%-- 		<br/>
	<div class="container">
		<h1 class="d-flex justify-content-center">Matching Listings</h1>
        <table class="table table-striped rounded table-style">
        <thead class="thead-dark">
            <tr>
                <th>Listing ID</th>
                <th>Listing Name</th>
                <th>Description</th>
                <th>Host</th>
                <th>Reviews</th>
                <th>Rating</th>
                <th>Delete Listing</th>
                <th>Update Listing</th>
            </tr>
            </thead>
            <tbody>
	            <c:forEach items="${listings}" var="listing" >
	                <tr>
	                    <td><c:out value="${listing.getID()}" /></td>
	                    <td><c:out value="${listing.getName()}" /></td>
	                    <td><c:out value="${listing.getDescription()}" /></td>
	                    <td><a class="btn btn-main" href="findhost?username=<c:out value="${listing.getHost().getUserName()}"/>">Host</a></td>
	                    <td><a class="btn btn-main" href="listingreviews?listingid=<c:out value="${listing.getID()}"/>">Reviews</a></td>
	                    <td><a class="btn btn-main" href="=<c:out value=""/>">Rating</a></td>
	                    <td><a class="btn btn-main" href="=<c:out value=""/>">Delete</a></td>
	                    <td><a class="btn btn-main" href="=<c:out value=""/>">Update</a></td>
	                </tr>
	            </c:forEach>
            </tbody>

       </table>
	
	</div>	 --%>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>