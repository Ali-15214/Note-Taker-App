<link rel="stylesheet" type="text/css" href="Style.css">
<body>
    <%
        // Retrieve the session object
        HttpSession session1 = request.getSession(false);

        // Check if the session is not null and the username attribute is set
        if (session1 != null && session1.getAttribute("userId") != null) {
            // Get the username from the session
            String username = (String) session1.getAttribute("username");
    %>

      <div class="container">
        <h1>Welcome, <%= username %>!</h1>
        <p>We're delighted to have you on our platform. 🌟</p>
        <h3>Explore, learn, and connect with  our vibrant community! 🚀</h3>
        <p>Feel free to stay as long as you like, and when you're ready,</p>
       <%  response.sendRedirect("index.jsp"); %>
        you can <a href="LogOut">LOGOUT</a> securely.
    </div>

    <%
        } else {
            // Redirect to the login page if the session is not valid
            response.sendRedirect("Login.jsp");
        }
    %>


</body>
