<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	session = request.getSession(false);
	if (session.getAttribute("userid") == null) {
		response.sendRedirect("../index.jsp?x=3");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<jsp:include page="../include/head.jsp" />
<title>Dashboard</title>
</head>
<body>
	<div class="wrapper">
		<div class="sidebar" data-color="purple"
			data-image="../assets/img/sidebar-1.jpg">
			<div class="logo">
				<a href="" class="simple-text"> CGPA NOTIFICATION </a> Welcome <b><%=session.getAttribute("fullname")%>
			</div>
			<div class="sidebar-wrapper">
				<ul class="nav">
					<li class="active"><a href="index.jsp"> <i
							class="material-icons">dashboard</i>
							<p>Dashboard</p>
					</a></li>
				</ul>
			</div>
		</div>
		<div class="main-panel">
			<nav class="navbar navbar-transparent navbar-absolute">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href=""> Dashboard </a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <i class="material-icons">person</i>
								<p class="hidden-lg hidden-md">Profile</p>
						</a>
							<ul class="dropdown-menu">
								<li> <a href="../logout.jsp"><i class="material-icons">exit_to_app</i>
										Log Out</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
			</nav>
			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-lg-3 col-md-6 col-sm-6">
							<a href="../UP/index.jsp"><div class="card card-stats">
									<div class="card-header" data-background-color="orange">
										<i class="material-icons">file_upload</i>
									</div>
									<div class="card-content">
										<p class="category">Menu</p>
										<h3 class="title">Upload Excel File</h3>
									</div>
								</div></a>

						</div>
						<div class="col-lg-3 col-md-6 col-sm-6">
							<a href=""><div class="card card-stats">
									<div class="card-header" data-background-color="blue">
										<i class="material-icons">show_chart</i>
									</div>
									<div class="card-content">
										<p class="category">Menu</p>
										<h3 class="title">Statistics</h3>
									</div>
								</div></a>
						</div>
						<div class="col-lg-3 col-md-6 col-sm-6">
							<a href="../MS/index.jsp"><div class="card card-stats">
									<div class="card-header" data-background-color="green">
										<i class="material-icons">people</i>
									</div>
									<div class="card-content">
										<p class="category">Menu</p>
										<h3 class="title">Staff Management</h3>
									</div>
								</div></a>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="../include/footer.jsp" />
		</div>
	</div>
</body>
<jsp:include page="../include/jsload.jsp" />
</html>