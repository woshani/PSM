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
<title>Statistics</title>
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
						href="#overview" id="lioverview"> <i class="material-icons">bubble_chart</i>
							<p>Overviews</p>
					</a></li>
					<li class=""><a role="tab" data-toggle="tab"
						href="#stud_perf"> <i class="material-icons">multiline_chart</i>
							<p>Student Performance</p>
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
								<div role="tabpanel" id="overview" class="tab-pane active">
									<h3 style="margin: 0px; padding: 0px;">Overviews</h3>
									<hr />
									<jsp:include page="overviews.jsp" />
								</div>
								<div role="tabpanel" id="stud_perf" class="tab-pane">
									<h3 style="margin: 0px; padding: 0px;">Student Performance</h3>
									<hr />
									<jsp:include page="studPerf.jsp" />
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

function getsesiajax(){
	$.ajax({
		type:"post",
		url:"../ListSesiServ",
		success:function(databack){
			databack = $.parseJSON(databack);
			var i;
			for (i = 0; i < databack.length; i++) {
				 var option = new Option(databack[i], databack[i]);
				 $('#formoverview #selsesilist').append($(option));
			}
		},
		contentType : "application/json"
	})
}

function graphControll(labesData,seriesData,location,height){
	var dataGraph = {
	        labels: labesData,
	        series: seriesData
	    };
    var optionbarchart = {
            axisX: {
                showGrid: false
            },
            low: 0,
            high: height,
            chartPadding: {
                top: 0,
                right: 5,
                bottom: 0,
                left: 0
            }
        };
	showbarchart(dataGraph,location,optionbarchart);
}

//function when document load 
$(document).ready(function() {
	
	getsesiajax();
	$('#formoverview #divcourse').hide();
	$('#formoverview #divyear').hide();
	$('#divForGraphOverview').hide();
	
});

$('#formoverview #selsesilist').on('change',function(){
	var sesi = $(this).val();
	$('#formoverview #selcourselist').find('option').remove();
	$('#formoverview #divcourse').hide();
	$('#formoverview #divyear').hide();
	$.ajax({
		type:"post",
		data:{sesi : sesi},
		url:"../ListCourseServ",
		success:function(databack){
			console.log(databack);
			databack = $.parseJSON(databack);
			$('#formoverview #divcourse').show();
			$('#formoverview #selcourselist').append('<option selected disabled>Please select course</option>');
			var i;
			for (i = 0; i < databack.length; i++) {
				 var option = new Option(databack[i], databack[i]);
				 $('#formoverview #selcourselist').append($(option));
			}
		}
	})
});

$('#formoverview #selcourselist').on('change',function(){
	var sesi = $('#formoverview #selsesilist').val();
	var course = $(this).val();
	$('#formoverview #divyear').hide();
	$('#formoverview #selyearlist').find('option').remove();
	$.ajax({
		type:"post",
		data:{sesi : sesi,course : course},
		url:"../ListYearServ",
		success:function(databack){
			console.log(databack);
			databack = $.parseJSON(databack);
			//$('#formoverview #divyear').show();
			$('#formoverview #selyearlist').append('<option selected disabled>Please select years</option>');
			var i;
			for (i = 0; i < databack.length; i++) {
				 var option = new Option(databack[i], databack[i]);
				 $('#formoverview #selyearlist').append($(option));
			}
		}
	})
});

$('#formoverview #btnSearchOverview').on('click',function(){
	var sesi = $('#formoverview #selsesilist').val();
	var course =  $('#formoverview #selcourselist').val();
	var year =  $('#formoverview #selyearlist').val();
	
	$.ajax({
		type:'post',
		url:'../getBySession',
		data:{sesi : sesi, kos : course ,year : year},
		success:function(databack){
			$('#divForGraphOverview').show();
			$('#divForGraphOverview #titleOverview').html('GPA with 3.0 and above');
			$('#divForGraphOverview #subtitleOverview').html('For Course: '+course+' Semester: '+sesi);
			databack = $.parseJSON(databack);
			//alert(databack);
			console.log(databack);
			
			var labelData = [];
			var seriesData = [];
			var weirdData = [];
			var totalHeight = 0;
			
			for (i = 0; i < databack.length; i++) {
				labelData.push('Year '+databack[i][1]);
				seriesData.push(parseInt(databack[i][0]));
				totalHeight += parseInt(databack[i][0]);
			}
			
			weirdData.push(seriesData);
			graphControll(labelData,weirdData,'#overviewChart',totalHeight);
		}
	})
});
</script>
</html>