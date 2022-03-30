<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
      <!--Navbar -->
      <jsp:include page="NavBar.jsp"></jsp:include>
      <!--/.Navbar -->
      
   	<form action="findlistingrating" method="get" class="container">
		<h1 class="header d-flex justify-content-center">Search for Listing Rating by ListingID</h1>
		<p class="d-flex justify-content-center">
			<input id="listingid" name="listingid" placeholder="Listing ID" class="search-box" value="${fn:escapeXml(param.username)}">
		</p>
		<div class="d-flex flex-column" >
			<div class="d-flex justify-content-center">
				<input type="submit"  value="Search" class="btn btn-main">
			</div>
			<br/><br/><br/>
			<span id="successMessage" class="d-flex justify-content-center"><b>${messages.success}</b></span>
			<br/>
		</div>
	</form>
	
		<br/>
	<div class="container">
		<h1 class="d-flex justify-content-center">Matching Ratings</h1>
        <table class="table table-striped rounded table-style">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Host ID</th>
                <th>Score Type</th>
                <th>Score</th>
                <th>Update</th>

            </tr>
            </thead>
            <tbody>
                <c:forEach items="${listingRatings}" var="listingRating" >
                <tr>
                    <td><c:out value="${listingRating.getId()}" /></td>
                    <td><c:out value="${listingRating.getHost().getId()}" /></td>
                    <td><c:out value="${listingRating.getScoreType()}"/></td>
                    <td><c:out value="${listingRating.getScore()}" /></td>

                    <td>
                        <a class="btn btn-main" href="=<c:out value=""/>">Update</a>
                    </td>
                </tr>
                </c:forEach>
            </tbody>

       </table>
	
	</div>	

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>