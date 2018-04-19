<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="row">
	<div class="col-md-12">
		<div class="card">
			<div class="card-header" data-background-color="purple">
				<h4 class="title">Upload</h4>
				<p class="category">File must be excel format</p>
			</div>
			<div class="card-content">
				<form method="post" enctype="multipart/form-data"
					action="UploadServ">
					<div class="row">
						<div class="col-md-4">
							<div class="form-group label-floating">
								<a class="btn-floating purple-gradient mt-0 float-left"> <i
									class="material-icons">file_upload</i> <input type="file"
									id="uploadinpout">
								</a> <input type="text" class="form-control"
									placeholder="browse file.." id="filename" readonly="">
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-primary pull-right"
						id="btnuploadexcel" disabled>Upload File</button>
					<div class="clearfix"></div>
					<div id="my_file_output"></div>
				</form>
			</div>
		</div>
	</div>
</div>