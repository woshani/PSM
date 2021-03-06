<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<title>Upload Excel</title>
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
					<li class="active"><a role="tab" data-toggle="tab" href="#uploadTab"> <i
							class="material-icons">file_upload</i>
							<p>Upload Excel File</p>
					</a></li>
					<li class=""><a role="tab" data-toggle="tab" href="#notiTab"> <i class="material-icons">announcement</i>
							<p>Notification</p>
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
					<a class="navbar-brand" href=""> Upload Results </a>
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
					<div class="col-md-12">
						<div class="tab-content">
							<div role="tabpanel" id="uploadTab" class="tab-pane active">
								<h3 style="margin: 0px; padding: 0px;">Upload</h3>
								<hr />
								<jsp:include page="Upload.jsp" />
							</div>
							<div role="tabpanel" id="notiTab" class="tab-pane">
								<h3 style="margin: 0px; padding: 0px;">Notification</h3>
								<hr />
								<jsp:include page="notification.jsp" />
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
	var oFileIn;
	var oJS;
	var newOJS;
	var arr = new Array();
	var createdby = '<%out.print(session.getAttribute("staffid").toString());%>';

	/*
		button upload file when click run function to extract data and parse to servlet
	 */
	$('#uploadTab #btnuploadexcel').on('click',function(e) {
						e.preventDefault();
						var oFile = document.getElementById('uploadinpout').files[0];
						// Create A File Reader HTML5
						var reader = new FileReader();
						arr = [];
						// Ready The Event For When A File Gets Selected
						reader.onload = function(e) {
							var data = e.target.result;
							var cfb = XLS.CFB.read(data, {
								type : 'binary'
							});
							var wb = XLS.parse_xlscfb(cfb);
							// Loop Over Each Sheet
							wb.SheetNames.forEach(function(sheetName) {
								/* 
									Obtain The Current Row As JSON
								 */
								var worksheet = wb.Sheets[sheetName];
								oJS = XLS.utils.sheet_to_json(worksheet);
								newOJS = XLS.utils.sheet_to_json(worksheet);
								var sts = JSON.stringify(newOJS[1]);
								var stringW = sts.split("SESI PENGAJIAN :")
										.pop();
								var sesi = stringW.substring(0, 12);
								//console.log(stringW);
								//console.log(sesi);

								oJS.splice(0, 3);
								var xxxx = oJS;
								//xxxx["sesi"] = sesi;
								$.each(xxxx, function(poss, obj) {
									obj.sessions = sesi.trim();
									obj.create = createdby;
								});
								//console.log(xxxx);

								var newarr = arr.concat(xxxx);
								arr = newarr;

							});

							var keys = [ "bil", "name", "matricNumber",
									"course", "cohort", "muet", "yearsem",
									"gpa", "academicAdvisor", "status",
									"phoneNumber", "sesi", "created_by" ];
							$.each(arr, function(pos, obj) {
								var counter = 0;
								$.each(obj, function(key, value) {
									arr[pos][keys[counter]] = value;
									delete arr[pos][key];
									counter++;
								});
							});
							//console.log(arr);
							//convert array into string
							var st = JSON.stringify(arr);
							//console.log(st);

							/*
								ajax to send data to the servlet
							 */
							$
									.ajax({
										type : 'post',
										url : '../UploadDataServ',
										data : st,
										success : function(databackk) {
											console.log(databackk);
											if (databackk === 1) {
												shownoti(
														"Data in excell file has been uploaded!",
														"success");
											} else if (databackk === 0) {
												shownoti(
														"Data in excell file failed to upload!",
														"danger");
											}
										},
										dataType : 'json',
										contentType : 'application/json',
										mimeType : 'application/json'
									});

						};
						/* 
							Tell JS To Start Reading The File.. You could delay this if desired 
						 */
						reader.readAsBinaryString(oFile);
					});

	/*
		function when select file trigger event to check extension file
	 */
	$(function() {
		oFileIn = document.getElementById('uploadinpout');
		if (oFileIn.addEventListener) {
			oFileIn.addEventListener('change', filePicked, false);
		}
	});
	function filePicked(oEvent) {
		/* 
			Get The File From The Input
		 */
		var oFile = oEvent.target.files[0];
		var sFilename = oFile.name;
		$("#filename").val(sFilename);
		var fileExtension = sFilename.substr(sFilename.length - 4);

		console.log(fileExtension);
		if (fileExtension != ".xls") {
			shownoti("Please make sure the file format is EXCEL (.xls) ONLY!",
					"danger");
			$('#btnuploadexcel').prop("disabled", true);
		} else {
			$('#btnuploadexcel').prop("disabled", false);
		}

	}
	
	function getsesiajax(){
		$.ajax({
			type:"post",
			url:"../ListSesiServ",
			success:function(databack){
				databack = $.parseJSON(databack);
				var i;
				for (i = 0; i < databack.length; i++) {
					 var option = new Option(databack[i], databack[i]);
					 $('#notiTab #formNoti #selsesilist').append($(option));
				}
			},
			contentType : "application/json"
		})
	}
	
	$(document).ready(function() {	
		getsesiajax();
		
	});
	
	$('#notiTab #formNoti #btnsentNoti').on('click',function(e){
		e.preventDefault();
		var sesi =  $('#notiTab #formNoti #selsesilist').val();
		console.log(sesi);
		$.ajax({
			type:"post",
			data:{sessions : sesi,sender:createdby},
			url:"../SendPushNoti",
			success:function(pulangdata){
				console.log(pulangdata);
				
				if(pulangdata==="OKAY"){
					shownoti("Notification Sent!","success");
				}else{
					shownoti("Something was wrong during sending the notification","danger");
				}
			}
		})
		
	})
</script>
</html>