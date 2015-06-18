<%-- 
    Document   : index
    Created on : Jun 11, 2015, 9:27:43 AM
    Author     : smoss
--%>
<!DOCTYPE html>
<html>
    <head>
        <title>Moss LMS Login</title>
        <!--<link rel="stylesheet" type="text/css" href="css/LoginSass.css">-->
        <link href="css/LoginAlt.css" rel="stylesheet">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
<body>
    <div>
        <img src="img/LMSlogo.jpg" height="150" width="150">
        <img src="img/LMSbanner.jpg" height="150" width="900">
    </div>
    <div class="container">
        <div id="login-form">
                <form action="LoginServlet" method="post" data-init-psswrd>
                    <h3>Please enter your Username and Password</h3>
                    <label>
                    Username:
                    <input type="text" required name="userID">
                    </label>
                    <label>
                    Password:
                    <input type="password" data-psswrd-toggle name="password" required>
                    </label>
                    <button type="submit" value="Login">
                        Submit
                    </button>
                </form>
                    <% 
                        Object message = request.getAttribute("message");
                        if(message != null)
                        {%>
                            <%=message.toString()%>
                        <%}
                    %>
        </div> <!-- end login-form -->
    <footer>
        
    </footer>    
    </div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.js"></script>
    <script src="js/psswrd.js"></script>
</body>
</html>