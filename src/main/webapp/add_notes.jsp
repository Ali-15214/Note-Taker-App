<%@page import="javax.servlet.http.HttpSession"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Notes</title>
    <%@ include file="all_js_cs.jsp"%>
</head>
<body>
<!-- -fluid p-0 m-0-->
<div class ="container">
<%@include file="navbar.jsp"%>

<br>
<h1> Please fill your note detail </h1>
<br>
  <!-- this is form  -->
  <form action="SaveNoteServlet" method="post" onsubmit="disableButton()">
  <%
      HttpSession sessionUser = request.getSession(false); // Get the session, but don't create a new one if it doesn't exist
      if (sessionUser == null || sessionUser.getAttribute("password") == null || sessionUser.getAttribute("email")== null) {
          // If user is not logged in, redirect to the login page
          response.sendRedirect("Login.jsp");
          return; // Stop further execution
      }

      String password = (String) sessionUser.getAttribute("password");  // Fetch userId from session
      String email= (String) sessionUser.getAttribute("email");

  %>

  			<div class="form-group">
  				<label for="title">Note title</label>
  				<input
  				name="title"
  				required
  				type="text"
  				class="form-control"
  				id="title"
  				aria-describedby="emailHelp"
  				placeholder="Enter here" />

  			</div>


  			<div class="form-group">
  				<label for="content">Note Content</label>
  				<textarea
  				name="content"
  				required
  				id="content"
  				placeholder="Enter your content here"
  				class="form-control"
  				style="height: 300px;"
  					></textarea>


  			</div>

  			<div class="container text-center">

  				<button type="submit" class="btn btn-primary" id="submitBtn">Add</button>
  			</div>

  		</form>
</div>
<script>
              function disableButton() {
                  document.getElementById("submitBtn").disabled = true;
                  document.getElementById("submitBtn").innerText = "Submitting...";
              }
          </script>

</body>
</html>