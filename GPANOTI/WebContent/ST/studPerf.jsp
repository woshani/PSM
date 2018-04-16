<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="row">
	<div class="col-md-7">
		<div class="card">
			<div class="card-content">
				<div class="card-content table-responsive">
					<form id="formovperf">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group label-floating">
									<label class="control-label">Matric Number</label> <input
										type="text" class="form-control" id="studperfmatric">
								</div>
							</div>
						</div>
						<button type="button" class="btn btn-primary pull-right"
							id="btnSearchPerf">Search</button>
						<div class="clearfix"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-6">
		<div class="card hidden" id="idDetails">
			<div class="card-content">
				<div class="card-content table-responsive">
					<table id="studDetail">
						<tbody>
							<tr>
								<td id="matrik"></td>
								<td id="name"></td>
							</tr>
							<tr>
								<td id="phone"></td>
								<td id="status"></td>
							</tr>
							<tr>
								<td id="course"></td>
								<td id="cohort"></td>
							</tr>
							<tr>
								<td id="PA"></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="card hidden"  id='divForGraphStud'>
			<div class="card-header card-chart" data-background-color="green">
				<div class="ct-chart" id="StudChart"></div>
			</div>
			<div class="card-content">
				<h4 class="title" id="titleStud">...</h4>
				<p class="category" id="subtitleStud">..</p>
			</div>
		</div>
	</div>
</div>

