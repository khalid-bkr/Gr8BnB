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
	
	.form-control::placeholder { 
 	 	color: lightgrey;
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
	
	#contact h2 {
	  font-weight: normal;
	  font-size: 24px;
	  line-height: 54px;
	  padding-top: 48px;
	  padding-bottom: 16px;
	  color: white;
	}

	.contact-form {

	  font-size: 24px;
	  line-height: 56px;
	  background-color: white;
	  border: 1px solid #e43f5a;
	  border-radius: 8px;
	  box-shadow: 2px 2px 8px rgba(0,0,0,0.2);
	  padding: 64px;
	  margin: 40px 0;
	}

	.contact-form .form-control{
	    border-radius:0.6rem;
	}

	.form-control:focus {
	  border-color: #e43f5a;
	  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(228,64,89, 0.4);
	}
	
	.form-group {
		margin-bottom: 16px;
	}
	
	.listing-field-header {
		color: #30475E;
		font-size: 16px;
	}
	
	label {
		color: #30475E;
		font-size: 16px;
		line-height: normal;
		letter-height: normal;
	}
	
	.radio_button_input {
		color: #30475E;
		font-size: 16px;
		line-height: normal;
		letter-height: normal;
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
                <a class="nav-link" href="">Become a Host</a>
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
      
      
      
     <div class="container">
        <div class="row">
          <div class="col-12">
            <h2 class="d-flex justify-content-center">Create a Listing</h2>
          </div>
        </div>
        <div class="row">
          <div class="col-12">
            <div class="contact-form">
              <form action="listingcreate" method="post">
                <div class="row">
                  <div class="col-12 col-md-12">
                    <div class="form-group">
                      <h3 class="listing-field-header">Title</h3>
                      <input
                        id="name"
                        type="text"
                        name="name"
                        class="form-control"
                        placeholder="Example: Modern Condominium in Downtown Seattle"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <h3 class="listing-field-header">Description</h3>
                      <input
                        id="description"
                        type="text"
                        name="description"
                        class="form-control"
                        placeholder="Example: This delightful, 2-bedroom modern condo is located two blocks north of Pike Place Market. Complete with a perfect view of Elliot Bay... "
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <h3 class="listing-field-header">Neighborhood Overview</h3>
                      <input
                        id="neighborhoodoverview"
                        type="text"
                        name="neighborhoodoverview"
                        class="form-control"
                        placeholder="Example: The heart of downtown Seattle. Lots of restaurants, bars, and shopping..."
                      />
                    </div>
                    <div class="form-group">
                      <h3 class="listing-field-header">Picture URL</h3>
                      <input
                        id="pictureurl"
                        type="url"
                        name="pictureurl"
                        class="form-control"
                        placeholder="Example: http://www.examplepicture.com"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <h3 class="listing-field-header">Host ID</h3>
                      <input
                        id="hostid"
                        type="text"
                        name="hostid"
                        class="form-control"
                        placeholder="Example: 2536"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <h3 class="listing-field-header">Neighborhood</h3>
                      <input
                        id="neigborhood"
                        type="text"
                        name="neighborhood"
                        class="form-control"
                        placeholder="Example: Downtown"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <h3 class="listing-field-header">Guests</h3>
                      <input
                        id="accommodates"
                        type="number"
                        name="accommodates"
                        class="form-control"
                        placeholder="Example: 3"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <h3 class="listing-field-header">Bathrooms</h3>
                      <input
                        id="bathroomstext"
                        type="text"
                        name="bathroomstext"
                        class="form-control"
                        placeholder="Example: 1 private bathroom"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <h3 class="listing-field-header">Bedrooms</h3>
                      <input
                        id="bedrooms"
                        type="number"
                        name="bedrooms"
                        class="form-control"
                        placeholder="Example: 2"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <h3 class="listing-field-header">Price Per Night (USD)</h3>
                      <input
                        id="price"
                        type="number"
                        name="price"
                        class="form-control"
                        placeholder="Example: $0.00"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <h3 class="listing-field-header">Has availability? (True/False)</h3>
                      <input
                        id="hasavailability"
                        type="text"
                        name="hasavailability"
                        class="form-control"
                        placeholder="Example: true"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <h3 class="listing-field-header">License</h3>
                      <input
                        id="license"
                        type="text"
                        name="license"
                        class="form-control"
                        placeholder="Example: LICE1234"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <h3 class="listing-field-header">Instantly bookable? (True/False)</h3>
                      <input
                        id="instantbookable"
                        type="text"
                        name="instantbookable"
                        class="form-control"
                        placeholder="Example: true"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <h3 class="listing-field-header">Latitude</h3>
                      <input
                        id="latitude"
                        type="text"
                        name="latitude"
                        class="form-control"
                        placeholder="Example: 47.6050° N"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <h3 class="listing-field-header">Longitude</h3>
                      <input
                        id="longitude"
                        type="text"
                        name="longitude"
                        class="form-control"
                        placeholder="Example: 122.3344° W"
                        value=""
                      />
                    </div>
                    <!-- <div class="form-group">
                    	<h3 class="listing-field-header">Room Type</h3>
  						<input class="radio-button-input" type="radio" id="entirehomeapt" name="roomtype" value="">
 						<label for="entirehomeapt">Entire home/apt</label><br>
  						<input class="radio-button-input" type="radio" id="privateroom" name="roomtype" value="">
  						<label for="privateroom">Private room</label><br>
  						<input class="radio-button-input" type="radio" id="sharedroom" name="roomtype" value="">
 						<label for="sharedroom">Shared room</label><br><br>
						<input class="radio-button-input" type="radio" id="notelroom" name="roomtype" value="">
 						<label for="hotelroom">Hotel room</label><br><br>
                    <!-- </div> -->
                     <div class="form-group">
                      <h3 class="listing-field-header">Room Type</h3>
                      <input
                        id="roomtype"
                        type="text"
                        name="roomtype"
                        class="form-control"
                        placeholder="Example: Entire home/apt"
                        value=""
                      />
                    </div>
                    <div class="form-group">
                      <h3 class="listing-field-header">Property Type</h3>
                      <input
                        id="propertytype"
                        type="text"
                        name="propertytype"
                        class="form-control"
                        placeholder="Example: Entire rental unit"
                        value=""
                      />
                    </div>
                    <div class="form-group">
	                      <div class="d-flex justify-content-start align-items-start"  >
	                        <input
	                          type="submit"
	                          name="btnSubmit"
	                          class="btn-main"
	                          value="Create Listing"
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
      
      
     

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>