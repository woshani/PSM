<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="assets/css/login.css">
<!-- Bootstrap core CSS     -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="assets/css/material-dashboard.css?v=1.2.0" rel="stylesheet" />
<title>GPA NOTIFICATION</title>
</head>
<body>
	<div class="container">
		<div class="login-container">
			<div id="output"></div>
			<!-- <div class="avatar"></div> -->
			<div class="form-box">
			<h2>LOG IN</h2>
				<form action="LoginServ" method="post">
					<input name="user" type="text" placeholder="username"> <input
						name="password" type="password" placeholder="password">
					<button class="btn btn-info btn-block login" type="submit">Login</button>
				</form>
			</div>
		</div>
	</div>
	<!--   Core JS Files   -->
	<script src="assets/js/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="assets/js/bootstrap-notify.js" type="text/javascript"></script>
	<script src="assets/js/material.min.js" type="text/javascript"></script>
	<!--  PerfectScrollbar Library -->
	<script src="assets/js/perfect-scrollbar.jquery.min.js"></script>
	<!-- Material Dashboard javascript methods -->
	<script src="assets/js/material-dashboard.js?v=1.2.0"></script>

	<!--  Dynamic Elements plugin -->
	<script src="assets/js/arrive.min.js"></script>
	<!--  Login js
	<script src="../assets/js/login.js"></script> -->
	
	<script>
	function shownoti(messagee,typee){
		$.notify({
            icon: "",
            message: messagee

        }, {
            type: typee,
            timer: 4000,
            placement: {
                from: "top",
                align: "center"
            }
        });
	}
	
		var error = "<%=request.getParameter("x")%>";
		if (error === "1") {
			shownoti("Please do not leave the username OR password empty!","warning");

		} else if(error==="2") {
			shownoti("<b>USERNAME</b> OR <b>PASSWORD</b> is wrong,please check again.","danger");
		}
		 else if(error==="3") {
				shownoti("Please log in again","warning");
			}
		 else if(error==="4") {
				shownoti("Logout success!","info");
			}
	</script>
</body>
</html>