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

<title>Gr8BnB - Find Listing</title>
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
	
	
/* 	.table-style th{
		padding: 1em;
	}
	
	.table-style td {
		padding: 1em;
	} */
	
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
      
   	<form action="findlisting" method="post" class="container">
		<h1 class="header d-flex justify-content-center">Search for Listing by ID</h1>
		<p class="d-flex justify-content-center">
			<input id="listingId" name="listingId" placeholder="Listing ID" class="search-box" value="${fn:escapeXml(param.listingId)}">
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
		<h1 class="d-flex justify-content-center">Matching Listing</h1>
        <table class="table table-striped rounded table-style">
            <tbody>
                <c:if test="${not empty listing }">
                	<tr>
                		<th>ID</th>
                    	<td><c:out value="${listing.getID()}" /></td>
                	</tr>
                    <%-- <td><a class="btn btn-main" href=""=<c:out value="${listing.getListingURL()}"/>">Link</a></td> --%>
                    <tr>
		                <th>URL</th>
                    	<td><c:out value="${listing.getListingURL()}" /></td>

                	</tr>
                	<tr>
		                <th>Title</th>
	                    <td><c:out value="${listing.getName()}" /></td>
                	</tr>
                	<tr>
		                <th>Description</th>
	                    <td><c:out value="${listing.getDescription()}" /></td>
                	</tr>
                	<tr>
		                <th>Neighborhood Overview</th>
	                    <td><c:out value="${listing.getNeighborhoodOverview()}" /></td>
                	</tr>
                    <%-- <td><a class="btn btn-main" href=""=<c:out value="${listing.getPictureUrl()}"/>">Link</a></td> --%>
                    <tr>
		                <th>Picture URL</th>
	                    <td><c:out value="${listing.getPictureUrl()}" /></td>
                    </tr>
                    <tr>
		                <th>Host</th>
	                    <td><a class="btn btn-main" href="findhost?username=<c:out value="${listing.getHost().getUserName()}"/>">Host</a></td>
                    </tr>
                    
                    <tr>
		                <th>Neighborhood</th>
	                    <td><c:out value="${listing.getNeighborhood().getNeighborhood()}" /></td>
                    </tr>
                    <tr>
		                <th>Accommodates</th>
	                    <td><c:out value="${listing.getAccommodates()}" /></td>
                    </tr>
                    <tr>
		                <th>Bathrooms</th>
	                    <td><c:out value="${listing.getBathroomsText()}" /></td>
                    </tr>
                    <tr>
		                <th>Bedrooms</th>
	                    <td><c:out value="${listing.getBedrooms()}" /></td>
                    </tr>
                    <tr>
		                <th>Price</th>
	                    <td><c:out value="$${listing.getPrice()}0" /></td>
                    </tr>
                   	<tr>
		                <th>Availability</th>
	                    <td><c:out value="${listing.isHasAvailability()}" /></td>
                    </tr>
                    <tr>
		                <th>Reviews</th>
	                    <td><c:out value="${listing.getNumberOfReviews()}" /></td>
                    </tr>
                    <tr>
		                <th>First Review</th>
	                    <td><c:out value="${listing.getFirstReview()}" /></td>
                    </tr>
                	<tr>
		                <th>Last Review</th>
	                    <td><c:out value="${listing.getLastReview()}" /></td>
                	</tr>
                	<tr>
		                <th>License</th>
	                    <td><c:out value="${listing.getLicense()}" /></td>
                	</tr>
                	
                	<tr>
		                <th>Instantly Bookable</th>
	                    <td><c:out value="${listing.isInstantBookable()}" /></td>
                	</tr>
                	<tr>
		                <th>Latitude</th>
	                    <td><c:out value="${listing.getLatitude()}" /></td>
                	</tr>
                	<tr>
		                <th>Longitude</th>
	                    <td><c:out value="${listing.getLongitude()}" /></td>
                	</tr>
                	<tr>
		                <th>Room Type</th>
	                    <td><c:out value="${listing.getRoomType().toString()}" /></td>
                	</tr>
                	<tr>
		                <th>Property Type</th>
	                    <td><c:out value="${listing.getPropertyType()}" /></td>
                	</tr>
                	<tr>
		                <th>Update</th>
	                    <td><a class="btn btn-main" href="listingupdate?listingId=<c:out value="${listing.getID()}"/>">Update</a></td>
                	</tr>  
                	<tr>
		                <th>Delete</th>
	                    <td><a class="btn btn-main" href="listingdelete?listingId=<c:out value="${listing.getID()}"/>">Delete</a></td>
                	</tr>
  
                    

                </c:if>
            </tbody>

       </table>
	
	</div>	

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>