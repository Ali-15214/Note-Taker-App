 <link rel="stylesheet" type="text/css" href="Style.css">
<body>
    <div class="container">
        <h1>Register</h1>
        <form action="RegisterServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>
            <label for="email">Email:</label>
            <input type="text" id="username" name="email" required><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>
            <button type="submit" id="submitBtn">Register</button>
        </form>

        <p><a href="index.html">Back to Home</a></p>

 <%-- Display error message if email already exist --%>
        <% String error = request.getParameter("email");
           if (error != null && error.equals("exist")) { %>

            <p style="color: red;">Email/Password already registered. Please use a different email/password...</p>
        <% } %>



        <%-- Display error message if registration fails --%>
        <% String error1 = request.getParameter("error");
           if (error1 != null && error1.equals("1")) { %>
            <p style="color: white;">Registration failed. Please try again.</p>
        <% } %>
      </div>
       <script>
              function disableButton() {
                  document.getElementById("submitBtn").disabled = true;
                  document.getElementById("submitBtn").innerText = "Submitting...";
              }
          </script>

</body>
