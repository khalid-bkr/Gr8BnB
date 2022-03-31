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
      <!--Navbar -->
      <nav
        class="navbar navbar-expand-lg navbar-dark"
        id="sideNav"
      >
        <div class="container d-flex justify-content-between">
        	<div>
        		<a class="" href="index"><p class="logo navbar-brand">GR8<strong>BnB</strong></p></a>
        	</div>
            

          <div class="" id="">
            <ul class="navbar-nav">
              <li class="nav-item">
                <a class="nav-link" href="hostcreate">Become a Host</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="guestcreate">Sign up</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="">Find Host</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <!--/.Navbar -->
      
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
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>URL</th>
                <th>Title</th>
                <th>Description</th>
                <th>Neighborhood Overview</th>
                <th>Picture URL</th>
                <th>Host</th>
                <th>Neighborhood</th>
                <th>Accommodates</th>
                <th>Bathrooms</th>
                <th>Bedrooms</th>
                <th>Price</th>
                <th>Availability</th>
                <th>Reviews</th>
                <th>First Review</th>
                <th>Last Review</th>
                <th>License</th>
                <th>Instantly Bookable</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>Room Type</th>
                <th>Property Type</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                <c:if test="${not empty listing }">
                    <td><c:out value="${listing.getID()}" /></td>
                    <td><c:out value="${listing.getListingURL()}" /></td>
                    <td><c:out value="${listing.getName()}" /></td>
                    <td><c:out value="${listing.getDescription()}" /></td>
                    <td><c:out value="${listing.getNeighborhoodOverview()}" /></td>
                    <td><c:out value="${listing.getPictureUrl()}" /></td>
                    <td><a class="btn btn-main" href="findhost?username=<c:out value="${listing.getHost().getUserName()}"/>">Host</a></td>
                    <td><c:out value="${listing.getNeighborhood().getNeighborhood()}" /></td>
                    <td><c:out value="${listing.getAccommodates()}" /></td>
                    <td><c:out value="${listing.getBathroomsText()}" /></td>
                    <td><c:out value="${listing.getBedrooms()}" /></td>
                    <td><c:out value="$${listing.getPrice()}0" /></td>
                    <td><c:out value="${listing.isHasAvailability()}" /></td>
                    <td><c:out value="${listing.getNumberOfReviews()}" /></td>
                    <td><c:out value="${listing.getFirstReview()}" /></td>
                    <td><c:out value="${listing.getLastReview()}" /></td>
                    <td><c:out value="${listing.getLicense()}" /></td>
                    <td><c:out value="${listing.isInstantBookable()}" /></td>
                    <td><c:out value="${listing.getLatitude()}" /></td>
                    <td><c:out value="${listing.getLongitude()}" /></td>
                    <td><c:out value="${listing.getRoomType().toString()}" /></td>
                    <td><c:out value="${listing.getPropertyType()}" /></td>

                    <td><a class="btn btn-main" href="listingId=<c:out value=""/>">Update</a></td>
                    <td><a class="btn btn-main" href="listingdelete?listingId=<c:out value="${listing.getID()}"/>">Delete</a></td>
                </c:if>
                </tr>
            </tbody>

       </table>
	
	</div>	

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>