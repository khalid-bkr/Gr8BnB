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

<title>Gr8BnB - Find Host</title>
<style>
	body {
		background-color: #30475E;
	  	background-size: contain;
	  	height: 90vh;
	  	color: #F5F5F5;
	}
	
	.table-style {
		background-color: #F5F5F5;
		padding: 1em;
		border-color: #e43f5a;
	}
	
	
	.table-style th{
		padding: 1em;
	}
	
	.table-style td {
		padding: 1em;
	}
	
	input::placeholder { 
 	 	color: grey;
  		opacity: 1; 
	}
	
	nav {
		background-color: #e43f5a;
		margin-bottom: 3em;
	}
	
	.logo {
		margin: 0;
	}
	
	.btn-main {
	  border: none;
	  border-radius: 8px;
	  padding: 12px;
	  background: #e43f5a;
	  font-size: 16px;
	  line-height: normal;
	  color: white;
	  cursor: pointer;
	  margin: 0;
	}
	
	.header {
		margin-bottom: 1em;
	}
	
	.search-box {
		border: 1px solid #121212;
		border-radius: 3px;
	}
	
	a {
		text-decoration: none;
	}


</style>
</head>
<body>
      <jsp:include page="NavBar.jsp"></jsp:include>
      
   	<form action="findcalendar" method="post" class="container">
		<h1 class="header d-flex justify-content-center">Search for Calendar by ListingID</h1>
		<p class="d-flex justify-content-center">
			<input id="listingid" name="listingid" placeholder="Listing ID" class="search-box" value="${fn:escapeXml(param.username)}">
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
		<h1 class="d-flex justify-content-center">Matching Calendar</h1>
        <table class="table table-striped rounded table-style">
        <thead class="thead-dark">
            <tr>
                <th>Calendar ID</th>
                <th>Listing</th>
                <th>Date</th>
                <th>Available</th>
                <th>Price</th>
                <th>Adjusted Price</th>
                <th>Minimum Nights</th>
                <th>Maximum Nights</th>
                <th>Delete Calendar</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${calendars}" var="calendar" >
                <tr>
                    <td><c:out value="${calendar.getId()}" /></td>
                    <td><a class="btn btn-main" href="=<c:out value=""/>">Listing</a></td>
                    <td><c:out value="${calendar.getDate()}" /></td>
                    <td><c:out value="${calendar.isAvailable()}" /></td>
                    <td><c:out value="${calendar.getPrice()}" /></td>
                    <td><c:out value="${calendar.getAdjustedPrice()}" /></td>
                    <td><c:out value="${calendar.getMinimumNights()}" /></td>
                    <td><c:out value="${calendar.getMaximumNights()}" /></td>
                    <td><a class="btn btn-main" href="=<c:out value=""/>">Delete</a></td>
                </tr>
                </c:forEach>
            </tbody>

       </table>
	
	</div>	

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>