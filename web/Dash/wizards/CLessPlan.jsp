<%-- 
    Document   : AddPet
    Created on : Jun 10, 2015, 10:40:07 AM
    Author     : smoss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Lesson Plan</title>
        <script src="../../js/jquery-2.1.4.min.js"></script>
        <link href="../../css/addStudentCss.css" rel="stylesheet">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
        <script type="text/javascript" src="../../js/formToWizard.js"></script>
        <script type="text/javascript">
        $(document).ready(function(){
            $("#SignupForm").formToWizard({ submitButton: 'SaveAccount' })
        });
        </script>
        
    </head>
    <body>
        <div id="main">
            <div id="header">
                <h3>Please complete the wizard below for your Lesson Plan.</h3>
            </div>
            
            <form id="SignupForm" action="">
                <div>
                    <fieldset>
                        <legend>Lesson plan</legend>
                        <label for="Name">Lesson Name</label>
                        <input id="Name" type="text">
                        <label for="Species">Subject</label>
                        <select id="Species">
                          <option value="Select">Select</option>
                          <option value="Math">Math</option>
                          <option value="english">English</option>
                          <option value="Science">Science</option>
                          <option value="History">History</option>
                        </select>
                        <a href="../index.jsp" class="cancel">Cancel</a>
                    </fieldset>
                    <fieldset>
                        <legend>Criteria Covered</legend>
                        <label for="Criteria">Criteria</label>
                        <input id="Criteria" type="text">
                        <a href="../index.jsp" class="cancel">Cancel</a>
                    </fieldset>
                    <fieldset>
                        <legend>Additional information</legend>
                        <label for="add">Additional</label>
                        <input id="add" type="add">
                        <a href="../index.jsp" class="cancel">Cancel</a>
                        <a href="../studentBoard.jsp" class="submit">Submit</a>
                    </fieldset>
                </div>
            </form>    
        </div>
    </body>
</html>
