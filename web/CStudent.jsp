<%-- 
    Document   : CStudent
    Created on : Jun 10, 2015, 10:40:07 AM
    Author     : smoss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Student</title>
        <script src="js/jquery-2.1.4.min.js"></script>
        <link href="css/addStudentCss.css" rel="stylesheet">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <script type="text/javascript" src="js/formToWizard.js"></script>
        <script type="text/javascript">
        $(document).ready(function(){
            $("#SignupForm").formToWizard({ submitButton: 'SaveAccount' })
        });
        </script>
        
    </head>
    <body>
        <div id="main">
            <div id="header">
                <h3>Please complete the wizard below to add a new student.</h3>
            </div>
            
            <form id="SignupForm" action="">
                    <fieldset>
                        <legend>Basic Student Information</legend>
                        <label for="Name">Student Name</label>
                        <input id="Name" type="text">
                        <label for="Species"></label>
                        <select id="Species">
                          <option value="Select">Grade Level</option>
                          <option value="K">K</option>
                          <option value="1">1</option>
                          <option value="2">2</option>
                          <option value="3">3</option>
                          <option value="4">4</option>
                          <option value="5">5</option>
                          <option value="6">6</option>
                          <option value="7">7</option>
                          <option value="8">8</option>
                          <option value="9">9</option>
                          <option value="10">10</option>
                          <option value="11">11</option>
                          <option value="12">12</option>
                        </select>
                        <label for="Age">Student Age</label>
                        <input id="Age" type="text">
                    </fieldset>
                    <fieldset>
                        <legend>Student Address</legend>
                        <label for="Address1">Address1</label>
                        <input id="Address1" type="text">.
                        <label for="Address2">Address2</label>
                        <input id="Address2" type="text">
                        <label for="City">City</label>
                        <input id="City" type="text">
                        <label for="State">State</label>
                        <input id="State" type="text">
                        <label for="zip">Zipcode</label>
                        <input id="zip" type="text">
                    </fieldset>
                    <fieldset>
                        <legend>Contact Information</legend>
                        <label for="Phone">Primary contact</label>
                        <input id="Phone" type="text">
                        <label for="Phone2">Secondary contact</label>
                        <input id="Phone2" type="text">
                        <label for="Phone3">Emergency contact</label>
                        <input id="Phone2" type="text">
                    </fieldset>

            </form>    
        </div>
    </body>
</html>
