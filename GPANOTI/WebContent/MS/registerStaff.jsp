<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="card">
	<div class="card-header" data-background-color="purple">
		<h4 class="title">Register</h4>
		<p class="category">register new staff into the databases</p>
	</div>
	<div class="card-content table-responsive">
		<form>
			<div class="row">
				<div class="col-md-2">
					<div class="form-group label-floating">
						<label class="control-label">Staff ID</label> <input
							type="text" class="form-control" id="staffid">
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group label-floating">
						<label class="control-label">Full Name</label> <input type="text"
							class="form-control" id="stafffullname">
					</div>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-3">
					<div class="form-group label-floating">
						<label class="control-label">Email address</label> <input
							type="email" class="form-control" id="staffemail">
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group label-floating">
						<label class="control-label">Phone Number</label> <input type="text"
							class="form-control" id="staffphone">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-8">
					<div class="form-group label-floating">
						<label class="control-label">Adress</label> <input type="text"
							class="form-control" id="staffaddress">
					</div>
				</div>
			</div>
			<button type="button" class="btn btn-primary pull-right" id="btnregister">Register</button>
			<div class="clearfix"></div>
		</form>
	</div>
</div>