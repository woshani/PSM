<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	session = request.getSession(false);
	if (session.getAttribute("staffid") == null) {
		response.sendRedirect("../index.jsp?x=3");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<jsp:include page="../include/head.jsp" />
<title>Staff Management</title>
</head>
<body>
	<div class="wrapper">
		<div class="sidebar" data-color="purple"
			data-image="../assets/img/sidebar-1.jpg">
			<div class="logo">
				<a href="" class="simple-text"> GPA NOTIFICATION </a> Welcome <b><%=session.getAttribute("fullname")%></b>
			</div>
			<div class="sidebar-wrapper">
				<ul class="nav">
					<li class=""><a href="../dashboard/index.jsp"> <i
							class="material-icons">dashboard</i>
							<p>Home</p>
					</a></li>
					<li class="active"><a role="tab" data-toggle="tab"
						href="#list_staff" id="liliststaff"> <i class="material-icons">people</i>
							<p>List Staff</p>
					</a></li>
					<li class=""><a role="tab" data-toggle="tab"
						href="#register_staff"> <i class="material-icons">person_add</i>
							<p>Register Staff</p>
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
					<a class="navbar-brand" href=""> Staff Management </a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <i class="material-icons">person</i>
								<p class="hidden-lg hidden-md">Profile</p>
						</a>
							<ul class="dropdown-menu">
								<li><a href="../logout.jsp"><i class="material-icons">exit_to_app</i>
										Log Out</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
			</nav>
			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="tab-content">
								<div role="tabpanel" id="list_staff" class="tab-pane active">
									<h3 style="margin: 0px; padding: 0px;">List Staff</h3>
									<hr />
									<jsp:include page="staffList.jsp" />
								</div>
								<div role="tabpanel" id="register_staff" class="tab-pane">
									<h3 style="margin: 0px; padding: 0px;">Register Staff</h3>
									<hr />
									<jsp:include page="registerStaff.jsp" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="../include/footer.jsp" />
		</div>
	</div>
</body>
<jsp:include page="../include/jsload.jsp" />

<script>
	//function get list of all staff
	function getStaff() {
		$.ajax({
			type : "post",
			url : "../GetStaffListServ",
			success : function(databack) {
				databack = $.parseJSON(databack);
				//console.log(databack);
				$.each(databack, function(i, item) {
					var app = "<tr><td>" + item.staffID + "</td><td>"
							+ item.fullName + "</td><td>" + item.email
							+ "</td><td>" + item.phoneNum + "</td></tr>"
					var $tr = $('#tblliststaff tbody').append(app);
				});
			},
			contentType : "application/json"
		});
	}

	//function when document load 
	$(document).ready(function() {
		getStaff();
	});

	//function when menu list staff is clicked
	$('#liliststaff').on('click', function() {
		$('#tblliststaff tbody tr').remove();
		getStaff();
	});

	//function when button register is clicked
	$('#btnregister').on(
					'click',
					function(e) {
						e.preventDefault();
						var staffid = $('#staffid').val();
						var fullname = $('#stafffullname').val();
						var email = $('#staffemail').val();
						var phone = $('#staffphone').val();
						var address = $('#staffaddress').val();
						var datas = {staffid:staffid,fullname:fullname,email:email,phone:phone,address:address};
						
						console.log(datas);
						
						if (staffid === "" || fullname === "") {
							shownoti(
									"Please make sure Staff ID and Staff Name is inserted!",
									"danger");
						} else {
							$.ajax({
								type : "post",
								url : "../RegisterStaff",
								data:{staffid:staffid,fullname:fullname,email:email,phone:phone,address:address},
								success : function(databack) {
									//databack = $.parseJSON(databack);
									console.log(databack);
									if(databack ==="SUCCESS"){
										shownoti(
												"Staff successfully registered!",
												"success");
										$('#staffid').val("");
										$('#stafffullname').val("");
										$('#staffemail').val("");
										$('#staffphone').val("");
										$('#staffaddress').val("");
									}else if(databack ==="ALREADY"){
										shownoti(
												"Staff is already registered!,try another staff",
												"danger");
									}
								}
							});
						}

					});
</script>
</html>