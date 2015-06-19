<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Moss LMS</title>

<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/styles.css" rel="stylesheet">
<link href="../css/w3full.css" rel="stylesheet" >
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" >

<body>
    <header class="navbar navbar-inverse navbar-fixed-top" role="navigation">

        <div class="container-fluid">
            <div class="navbar-header">

                <div class="col-md-2">
                <img class="img-responsive" id="logo" src="../img/LMSlogo.jpg">
                </div>
                    <div class="col-md-8">
                <img class="img-responsive" id="banner" src="../img/LMSbanner.jpg">
                </div>

              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
            <a class="navbar-brand" href="#"></a>
            </div>
            <div class="navbar-collapse collapse">
                <div class="col-md-2">
              <ul class="nav navbar-nav navbar-right">
                <li><a href="../LogoutServlet">Logout</a></li>
                <li>
                    <p>
                       <%=session.getAttribute("FirstName")%>
                       <%=session.getAttribute("LastName")%>
                       <%=session.getAttribute("Role")%>
                    </p>
                </li>
              </ul>
                </div>
            </div>
        </div>
    </header>
        <div class="container-fluid">
            <div class="row row-offcanvas row-offcanvas-left">
                <div class="col-sm-3 col-md-2 sidebar-offcanvas" id="sidebar" role="navigation">
                    <ul class="nav nav-sidebar">
                        <div><p> </p></div>
                        <div><p>|</p></div>
                        <li class="active"><a href="#">Overview</a></li>
                        <li><a href="wiz/CStudent.jsp">Create Student</a></li>
                        <li><a href="wiz/CLessPlan.jsp">Create Lesson Plan</a></li>
                        <li><a href="#">Create Lesson</a></li>
                    </ul>
                    <ul class="nav nav-sidebar">
                        <li><a href="">Move Student</a></li>
                        <li><a href="">Assess Student</a></li>
                        <li><a href="">Generate Report</a></li>
                    </ul>           
                </div><!--/span-->
                <div class="col-sm-9 col-md-10 main">
                <!--toggle sidebar button-->
                <p class="visible-xs">
                    <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas"><i class="glyphicon glyphicon-chevron-left"></i></button>
                </p>
            <div class="row">
                <div class="col-xs-12">
		</div>			
            </div>	
        <hr>
        <h2 class="sub-header">Info Box 1</h2>
        <div class="row">
            <div class="col-xs-6">
              <div>
				<h4>Name: <span class="text-muted">Test Name</span></h4>
			  </div>
			  <div>
				<h4>Name: <span class="text-muted">Test Name</span></h4>
			  </div>
			  <div>
				<h4>Name: <span class="text-muted">Test Name</span></h4>
			  </div>
			  <div>
				<h4>Name: <span class="text-muted">Test Name</span></h4>
			  </div>
            </div>
            <div class="col-xs-6">             
              <div>
				<h4>Name: <span class="text-muted">Test Name</span></h4>
			  </div>
			  <div>
				<h4>Name: <span class="text-muted">Test Name</span></h4>
			  </div>
			  <div>
				<h4>Name: <span class="text-muted">Test Name</span></h4>
			  </div>
			  <div>
				<h4>Name: <span class="text-muted">Test Name</span></h4>
			  </div>
            </div>
          </div>
		
			<hr>
                        <div>
                            <div>
                                <H4>Inbox:</H4>
                                <div class="col-xs-12">
                                    Message from Teacher in Room 121.
                                </div>
                            </div>
                            
                        </div>
                        <hr>
                        <hr>
		<ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#students">Students</a></li>
    <li><a data-toggle="tab" href="#lplans">Lesson Plans</a></li>
    <li><a data-toggle="tab" href="#lessons">Lessons</a></li>
  </ul>
 
  <div class="tab-content">
    <div id="students" class="tab-pane fade in active">
      <h3>Students</h3>
      <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Header</th>
                  <th>Header</th>
                  <th>Header</th>
                  <th>Header</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1,001</td>
                  <td>Lorem</td>
                  <td>ipsum</td>
                  <td>dolor</td>
                  <td>sit</td>
                </tr>
                <tr>
                  <td>1,002</td>
                  <td>amet</td>
                  <td>consectetur</td>
                  <td>adipiscing</td>
                  <td>elit</td>
                </tr>
                <tr>
                  <td>1,003</td>
                  <td>Integer</td>
                  <td>nec</td>
                  <td>odio</td>
                  <td>Praesent</td>
                </tr>
                <tr>
                  <td>1,003</td>
                  <td>libero</td>
                  <td>Sed</td>
                  <td>cursus</td>
                  <td>ante</td>
                </tr>
                <tr>
                  <td>1,004</td>
                  <td>dapibus</td>
                  <td>diam</td>
                  <td>Sed</td>
                  <td>nisi</td>
                </tr>                
              </tbody>
            </table>
          </div>
    </div>
    <div id="lplans" class="tab-pane fade">
      <h3>Lesson Plans</h3>
      <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Header</th>
                  <th>Header</th>
                  <th>Header</th>
                  <th>Header</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1,001</td>
                  <td>Lorem</td>
                  <td>ipsum</td>
                  <td>dolor</td>
                  <td>sit</td>
                </tr>
                <tr>
                  <td>1,002</td>
                  <td>amet</td>
                  <td>consectetur</td>
                  <td>adipiscing</td>
                  <td>elit</td>
                </tr>
                <tr>
                  <td>1,003</td>
                  <td>Integer</td>
                  <td>nec</td>
                  <td>odio</td>
                  <td>Praesent</td>
                </tr>
                <tr>
                  <td>1,003</td>
                  <td>libero</td>
                  <td>Sed</td>
                  <td>cursus</td>
                  <td>ante</td>
                </tr>
                <tr>
                  <td>1,004</td>
                  <td>dapibus</td>
                  <td>diam</td>
                  <td>Sed</td>
                  <td>nisi</td>
                </tr>                
              </tbody>
            </table>
          </div>
    </div>
    <div id="lessons" class="tab-pane fade">
      <h3>Lessons</h3>
      <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Header</th>
                  <th>Header</th>
                  <th>Header</th>
                  <th>Header</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1,001</td>
                  <td>Lorem</td>
                  <td>ipsum</td>
                  <td>dolor</td>
                  <td>sit</td>
                </tr>
                <tr>
                  <td>1,002</td>
                  <td>amet</td>
                  <td>consectetur</td>
                  <td>adipiscing</td>
                  <td>elit</td>
                </tr>
                <tr>
                  <td>1,003</td>
                  <td>Integer</td>
                  <td>nec</td>
                  <td>odio</td>
                  <td>Praesent</td>
                </tr>
                <tr>
                  <td>1,003</td>
                  <td>libero</td>
                  <td>Sed</td>
                  <td>cursus</td>
                  <td>ante</td>
                </tr>
                <tr>
                  <td>1,004</td>
                  <td>dapibus</td>
                  <td>diam</td>
                  <td>Sed</td>
                  <td>nisi</td>
                </tr>                
              </tbody>
            </table>
          </div>
    </div>
          
      </div><!--/row-->
	</div>
</div><!--/.container-->

<footer>
  <p class="pull-right">?2014 CIT LMS</p>
</footer>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
	</body>
</html>