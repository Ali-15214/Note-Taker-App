 <link rel="stylesheet" type="text/css" href="Style.css">
<body>
    <div class="container">
        <h1>Login</h1>
        <form action="LoginServlet" method="post"> <!-- Change method to "post" -->
            <label for="email">email:</label>
            <input type="email" id="email" name="email" placeholder="email" required><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="password" required><br>



            <button type="submit">Login</button>
        </form>

        <p><a href="index.html">Back to Home</a></p>

<!-- Link to the forgot password page -->
        <p><a href="forgot_password.jsp">Forgot Password?</a></p>


        <%-- Display error message if login fails --%>
        <% String error = request.getParameter("error");
            if (error != null && error.equals("1")) { %>
                <p style="color: red;">Invalid username or password. Please try again.</p>
        <% } %>

        <%-- Display  message if Register Successful --%>
        <% String rs = request.getParameter("registration");
            if (rs != null && rs.equals("success")) { %>
                <p style="color: white;">Your Registration is Successful. Please Login.</p>
        <% } %>

         <%-- Display  message if password update Successful --%>
                <% String update = request.getParameter("update");
                    if (update!= null && update.equals("1")) { %>
                        <p style="color: white;">Your Password Successfully Update. Please Login.</p>
                <% } %>
    </div>
</body>