<%-- 
    Document   : WizardTemplate
    Created on : Jun 10, 2015, 9:38:41 AM
    Author     : smoss
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TEMPLATE</title>
        <script src="../../js/jquery-2.1.4.min.js"></script>
        <link href="../../css/addPetCss.css" rel="stylesheet">
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
                <h3>TEMPLATE</h3>
            </div>
            
            <form id="SignupForm" action="">
                <div>
                    <fieldset>
                        <legend>TEMPLATE</legend>
                        <label for="Name">TEMPLATE</label>
                        <input id="Name" type="text">
                        <label for="Species">TEMPLATE</label>
                        <select id="Species">
                          <option value="Select">Select</option>
                          <option value="Cat">TEMPLATE</option>
                          <option value="Dog">TEMPLATE</option>
                          <option value="Bird">TEMPLATE</option>
                        </select>
                        <label for="Age">TEMPLATE</label>
                        <input id="Age" type="text">
                        <a href="../index.jsp" class="cancel">Cancel</a>
                    </fieldset>
                    <fieldset>
                        <legend>TEMPLATE</legend>
                        <label for="Breed">TEMPLATE</label>
                        <input id="Breed" type="text">
                        <label for="Allergies">TEMPLATE</label>
                        <input id="Allergies" type="text">
                        <a href="../index.jsp" class="cancelMid">Cancel</a>
                    </fieldset>
                    <fieldset>
                        <legend>TEMPLATE</legend>
                        <label for="Phone">TEMPLATE</label>
                        <input id="Phone" type="text">
                        <label for="Email">TEMPLATE</label>
                        <input id="Email" type="text">
                        <a href="../index.jsp" class="cancelMid">Cancel</a>
                        <a href="../petBoard.jsp" class="submit">Submit</a>
                    </fieldset>
                </div>
            </form>    
        </div>
    </body>
</html>