<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="row">
	<div class="col-md-12">
		<div class="card">
			<div class="card-header" data-background-color="purple">
				<h4 class="title">Send Result Notification</h4>
				<p class="category">By Semester</p>
			</div>
			<div class="card-content">
				<form id="formNoti">
					<div class="row">
						<div class="col-md-6">
							<div class="col-md-4">
								<div class="form-group label-floating">
									<label class="control-label">Semester/year</label> <select
										class="form-control" id="selsesilist">
										<option selected disabled>Please select semester - year</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-primary pull-right"
						id="btnsentNoti">Send!</button>
					<div class="clearfix"></div>
				</form>
			</div>
		</div>
	</div>
</div>