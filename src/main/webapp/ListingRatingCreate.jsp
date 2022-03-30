<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Gr8BnB</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css"/>
</head>
<body>

	<h1>Create Listing Rating</h1>
	<form action="listingratingcreate" method="post">
		<p>
			<label for="listingid">ListingId</label>
			<input id="listingid" name="listingid" value="">
		</p>
		<p>
			<label for="hostid">HostId</label>
			<input id="hostid" name="hostid" value="">
		</p>
		<p>
			<label for="scoretype">Score type</label>
			<input id="scoretype" name="scoretype" value="">
		</p>
		<p>
			<label for="score">Score</label>
			<input id="score" name="score" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	
</body>
</html>