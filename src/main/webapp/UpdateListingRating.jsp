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
	<h1 class="d-flex justify-content-center">Update Listing Rating</h1>
	<h4 class="header d-flex justify-content-center">${messages.title}</h4>
	<br/>
	
    <form action="updatelistingrating" method="post">
        <div class="d-flex justify-content-center">
                                   
        <table class="table table-striped rounded table-style">
        <thead class="thead-dark " >
        	<tr>
                <th>Rating ID</th>
                <th>Host ID</th>
                <th>Listing ID</th>
                <th>Rating Type</th>
                <th>New Rating</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                  <c:if test="${not empty listingRating}">
                  	<td><c:out value="${listingRating.getId()}" /></td>
                    <td><c:out value="${listingRating.getHost().getId()}" /></td>
                    <td><c:out value="${listingRating.getListing().getID()}" /></td>
                    <td><c:out value="${listingRating.getScoreType()}"/></td>
                	<td> <input name="newRating" type="number" placeholder="Select new rating" min="0.0" max="5.0" step="0.1"  style ="width: 10em; height:3em"> </td>
                </c:if>
                </tr>
            </tbody>

       </table>            
        </div>
        
        <input type="hidden" name="ratingid" value="${param.ratingid}">
        <div class="d-flex justify-content-center mt-5"><input type="submit" class="btn btn-main" value="Update Your Rating" />
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