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
<jsp:include page="NavBar.jsp"></jsp:include>
	
	  <div class="container">
        <div class="row">
          <div class="col-12">
            <h2 class="d-flex justify-content-center">Create Listing Rating</h2>
          </div>
        </div>
        <div class="row">
          <div class="col-12">
            <div class="contact-form">
              <form action="listingratingcreate" method="post">
                <div class="row">
                  <div class="col-12 col-md-12">
                    <div class="form-group">
                      <input
                        id="listingid"
                        type="text"
                        name="listingid"
                        class="form-control"
                        placeholder="Listing ID"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <input
                        id="hostid"
                        type="text"
                        name="hostid"
                        class="form-control"
                        placeholder="Host ID"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <input
                        id="scoretype"
                        type="text"
                        name="scoretype"
                        class="form-control"
                        placeholder="Score Type"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <input
                        id="score"
                        type="text"
                        name="score"
                        class="form-control"
                        placeholder="Score"
                        value=""
                      />
                    </div>
                    <div class="form-group">
	                      <div class="d-flex justify-content-start align-items-start"  >
	                        <input
	                          type="submit"
	                          name="btnSubmit"
	                          class="btn-main"
	                          value="Create Listing Rating"
	                        />
	                </div>
                   			<p id="status" class="d-flex justify-content-center"></p>
                    </div>
                  </div>
                </div>
              </form>
            </div>
            <span id="successMessage" class="d-flex justify-content-center"><b>${messages.success}</b></span>
          </div>
        </div>
      </div>
	
</body>
</html>