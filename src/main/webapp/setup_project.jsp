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
		<div class="signinpanel">

			<div class="row">
				<div class="col-md-7">
					<div class="signin-info">
						<div class="logopanel">
							<h1>
								<span>[</span> buket list <span>]</span>
							</h1>
						</div>
						<div class="mb20"></div>
						<h5>
							<strong>Welcome to Buket List, you need the following
								to setup project!</strong>
						</h5>
						<ul>
							<li><i class="fa fa-arrow-circle-o-right mr5"></i>
								testng.xml configuration file path (eg.
								c:\my-project-name\testng.xml)</li>
							<li><i class="fa fa-arrow-circle-o-right mr5"></i> Xml should be at the root of project folder only</li>
								<li><i class="fa fa-arrow-circle-o-right mr5"></i> Maven
								nature project only</li>
						</ul>
					</div>
				</div>
				<div class="col-md-5">
					<form method="post"	action="CreateProjectDetailXmlControl">
						<h4 class="nomargin">Configure your project here!</h4>
						
						<input class="form-control" type="text" name="name" placeholder="Name your project !">
						<input class="form-control" type="text" name="rootPath" placeholder="Where is your project ?">
						<input class="form-control" name="xmlLocation" type="file">
						<button class="btn btn-success btn-block">Setup Project</button>
					</form>
				</div>
			</div>
		</div>
	</section>


	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/modernizr.min.js"></script>
	<script src="js/retina.min.js"></script>

	<script src="js/custom.js"></script>

</body>
</html>
