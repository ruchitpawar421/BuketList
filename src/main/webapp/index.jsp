<%@page import="controllers.XmlParser"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>

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
<link href="css/jquery.datatables.css" rel="stylesheet">


<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="js/html5shiv.js"></script>
  <script src="js/respond.min.js"></script>
  <![endif]-->
</head>

<body>

	<!-- Preloader -->
	<div id="preloader">
		<div id="status">
			<i class="fa fa-spinner fa-spin"></i>
		</div>
	</div>

	<section>

		<div class="leftpanel">

			<div class="logopanel">
				<h1>
					<span>[</span> buket list <span>]</span>
				</h1>
			</div>
			<!-- logopanel -->

			<div class="leftpanelinner">

				<h5 class="sidebartitle">List of Suites</h5>
				<ul class="nav nav-pills nav-stacked nav-bracket">
					<% String suite_name=null;
		 String reportUrl1=null;
		 String reportUrl=null;
									XmlParser read = new XmlParser();
									String fileName=request.getParameter("projectName");
									String root=read.returnTargetRootPath(fileName);
									String root1=root.replace("\\\\", "\\");
									reportUrl1=root1+"\\report\\";
									reportUrl=reportUrl1.replace("\\", "/");
									String xmlFileName=read.returnTargetXmlLocation(fileName);
									HashMap<String, String> listSuite = read.getSuite(xmlFileName);
									HashMap<String, ArrayList<String>> listTest = read.getTestCases();
									int count = 0;
									for (int i = 0; i < listSuite.size(); i++) {
										suite_name = listSuite.get("Suite" + i);
										count = listTest.get(suite_name).size();
										String url = "suitePage.jsp?suite=" + suite_name+"&projectName="+fileName;
								%>
					<li class="nav-parent"><a href=""><i class="fa fa-tags"></i>
							<span><%=suite_name %></span></a>
						<ul class="children">
							<li><a href="<%=url %>"><i class="fa fa-road"></i> Run</a></li><%-- file:///<%=reportUrl+suite_name%>.html --%>
							<li><a href="file:///C|/<%=suite_name%>.html"><i
									class="fa fa-bookmark-o"></i> Report</a></li>
						</ul></li>
					<%
									}
								%>
				</ul>
			</div>
			<!-- leftpanelinner -->
		</div>
		<!-- leftpanel -->

		<div class="mainpanel">

			<div class="headerbar">

				<a class="menutoggle"><i class="fa fa-bars"></i></a>

				<div class="header-right">
					<ul class="headermenu">

						<li>
							<div class="btn-group">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown">
									<%=request.getParameter("projectName") %>
									<span class="caret"></span>
								</button>
								<%int value = 0;
				for (int i = 0; i < listSuite.size(); i++) {
					suite_name = listSuite.get("Suite" + i);
					value = value+listTest.get(suite_name).size(); }%>
								<ul class="dropdown-menu dropdown-menu-usermenu pull-right">
									<li><a href="#"><i class="fa fa-bitbucket"></i>
											Suites(<%=listSuite.size() %>), Tests(<%=value %>)</a></li>
									<li><a href="setup_project.jsp"><i
											class="glyphicon glyphicon-plus"></i> Add New Project</a></li>
								</ul>
							</div>
						</li>
					</ul>
				</div>
				<!-- header-right -->

			</div>
			<!-- headerbar -->

			<div class="pageheader">
				<h2>
					<i class="fa fa-home"></i> Dashboard <span> summary of each
						suite....</span>
				</h2>

			</div>

			<div class="contentpanel">

				<div class="row">

					<!-- <div class="col-sm-6 col-md-3">
          <div class="panel panel-success panel-stat">
            <div class="panel-heading">
              
              <div class="stat">
                <div class="row">
                  <div class="col-xs-4">
                    <img src="images/is-user.png" alt="" />
                  </div>
                  <div class="col-xs-8">
                    <small class="stat-label">Visits Today</small>
                    <h1>900k+</h1>
                  </div>
                </div>row
                
                <div class="mb15"></div>
                
                <div class="row">
                  <div class="col-xs-6">
                    <small class="stat-label">Pages / Visit</small>
                    <h4>7.80</h4>
                  </div>
                  
                  <div class="col-xs-6">
                    <small class="stat-label">% New Visits</small>
                    <h4>76.43%</h4>
                  </div>
                </div>row
              </div>stat
              
            </div>panel-heading
          </div>panel
        </div>col-sm-6 -->

				</div>
				<!-- row -->
			</div>
			<!-- contentpanel -->
		</div>
		<!-- mainpanel -->


	</section>


	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/jquery-ui-1.10.3.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/modernizr.min.js"></script>
	<script src="js/jquery.sparkline.min.js"></script>
	<script src="js/toggles.min.js"></script>
	<script src="js/retina.min.js"></script>
	<script src="js/jquery.cookies.js"></script>

	<script src="js/flot/flot.min.js"></script>
	<script src="js/flot/flot.resize.min.js"></script>
	<script src="js/morris.min.js"></script>
	<script src="js/raphael-2.1.0.min.js"></script>

	<script src="js/jquery.datatables.min.js"></script>
	<script src="js/chosen.jquery.min.js"></script>

	<script src="js/custom.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>