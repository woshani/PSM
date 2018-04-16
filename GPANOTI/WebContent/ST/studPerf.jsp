<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="card">
	<div class="card-content">
			<div class="card-content table-responsive">
		<form id="formoverview">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group label-floating">
						<label class="control-label">Sessions</label>
						<select class="form-control" id="selsesilist">
							<option selected disabled>Please select Session</option>
						</select>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group label-floating" id="divcourse">
						<label class="control-label">Course</label> <select class="form-control" id="selcourselist">
							<option selected disabled>Please select course</option>
						</select>
					</div>
				</div>
				<div class="col-md-2" hidden>
					<div class="form-group label-floating" id="divyear">
						<label class="control-label">Year</label><select class="form-control" id="selyearlist">
							<option selected disabled>Please select year</option>
						</select>
					</div>
				</div>
			</div>
			<button type="button" class="btn btn-primary pull-right" id="btnSearchOverview">Search</button>
			<div class="clearfix"></div>
		</form>
	</div>
	</div>
</div>