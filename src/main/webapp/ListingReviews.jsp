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

</div>

<br/>

<div class="container">
	<a class="btn btn-main mb-3" href="createreview?listingid=<c:out value="${fn:escapeXml(param.listingid) }"/>">Add A New Review</a>
    <table class="table table-striped rounded table-style">
        <thead class="thead-dark">
        <tr class="text-nowrap">
            <th>Review ID</th>
            <th>Listing ID</th>
            <th>Guest ID</th>
            <th>Comments</th>
            <th>Created Date</th>
            <th>Delete Review</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${reviews}" var="review">
            <tr>
                <td><c:out value="${review.getId()}"/></td>
                <td><a href="listings?listingid=<c:out value="${fn:escapeXml(param.listingid) }"/>">${review.getListing().getID()}</a></td>
                <td>${review.getGuest().getId()}</td>
                <td>${review.getComments()}</td>
                <td class="text-nowrap"><c:out value="${review.getDate()}"/></td>
                <td><a class="btn btn-main" href="deletereview?listingid=<c:out value="${fn:escapeXml(param.listingid) }"/>&reviewid=<c:out value="${review.getId()}"/>">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>



