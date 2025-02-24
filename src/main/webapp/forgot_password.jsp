
<link rel="stylesheet" type="text/css" href="Style.css">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
</head>
<body>
<div class="container">
    <h2>Reset Your Password</h2>
    <form action="ForgotPasswordServlet" method="post">
        <label for="email">Email:</label>
        <input type="email" name="email" required>
        <br>
        <label for="newPassword">New Password:</label>
        <input type="password" name="newPassword" required>
        <br>
        <button type="submit">Reset Password</button>
          <p><a href="Login.jsp">Back to Login</a></p>
    </form>
    <%-- Display error message if login fails --%>
            <% String error = request.getParameter("error");
                if (error != null && error.equals("1")) { %>
                    <p style="color: white;">Invalid email. Please try again.</p>
            <% } %>
 </div>

</body>
</html>