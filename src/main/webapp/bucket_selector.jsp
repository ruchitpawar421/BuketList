<%@page import="java.io.File"  %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="images/favicon.png" type="image/png">

<title>Buket List - TestNg Manager</title>

<link href="css/style.default.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="js/html5shiv.js"></script>
  <script src="js/respond.min.js"></script>
  <![endif]-->
</head>

<body class="notfound">

	<!-- Preloader -->
	<div id="preloader">
		<div id="status">
			<i class="fa fa-spinner fa-spin"></i>
		</div>
	</div>

	<section>
		<div class="lockedpanel">
			
			<div class="logged">
        <h4><span>Welcome to [</span> buket list <span>]</span></h4>
    
			</div>
			<form class="form-inline" method="post" action="setup_project.jsp">
				<button class="btn btn-success btn-block">New Project Setup</button>
				<%File f=new File("dataFiles\\"); 
				File[] contains=f.listFiles();
				if(contains.length!=0){
				%>
				<div class="btn-group">
					<button class="btn btn-warning btn-block dropdown-toggle"	data-toggle="dropdown" type="button">Existing Project					
						<span class="caret"></span> 
						</button>
					<ul class="dropdown-menu" role="menu">
					<% 
						for(File each:contains){
					%>
						<li><a href="index.jsp?projectName=<%=each.getName().split("\\.")[0] %>"><%=each.getName().split("\\.")[0] %></a></li>
						
						<%} %>
					</ul>
				</div>
				<%} %>
			</form>
		</div>
		<!-- lockedpanel -->
	</section>


	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/modernizr.min.js"></script>
	<script src="js/retina.min.js"></script>

	<script src="js/custom.js"></script>

</body>
</html>
