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

<title>Gr8BnB</title>
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
		color: black;
	}
	
	
	.card-trip {
	  overflow: hidden;
	  background: white;
	  box-shadow: 0 0 15px rgba(0,0,0,0.2);
	  border-radius: 5px;
	}
	
	.card-trip > img {
	  height: 200px;
	  width: 100%;
	  object-fit: cover;
	}
	
	.card-trip h2 {
	  font-size: 16px;
	  font-weight: bold;
	  margin: 0;
	  color: black;
	}
	
	.card-trip p {
	  font-size: 12px;
	  opacity: .7;
	  margin: 0;
	  color: black;
	}
	
	
	.card-trip .card-trip-infos {
	  padding: 16px;
	  display: flex;
	  justify-content: space-between;
	  align-items: flex-end;
	  position: relative;
	}
	
	.card-trip-infos .card-trip-user {
	  position: absolute;
	  right: 16px;
	  top: -20px;
	  width: 40px;
	}
	
	
	.cards {
	  display: grid;
	  grid-template-columns: 1fr 1fr 1fr;
	  grid-gap: 16px;
	  margin-top: 1em;
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
        		 <a class="" href=""><p class="logo navbar-brand">GR8<strong>BnB</strong></p></a>
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
                <a class="nav-link" href="findhost">Find Host</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
      <!--/.Navbar -->
      
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
				    <h2 class="card-trip-pricing">$<c:out value="${listing.getPrice()}" /></h2>
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