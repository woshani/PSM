<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Teacher"%>
<%@page import="bean.Classroom"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bean.Student"%>

<%
	ControllerWrapper controller = new ControllerWrapper();
	Teacher teacher = new Teacher();
	if (session.getAttribute("SASteacher") != null) {
		teacher = (Teacher) session.getAttribute("SASteacher");
	} else {
		teacher = controller.getTeacherById(teacher.getTeacher_id());
	}
	
	List<Student> students = controller.getStudentsByClassId(request.getParameter("id"));
	
	if(students.size() > 0){

%>

<script language="JavaScript">
<!--
	// PickList script- By Sean Geraty (http://www.freewebs.com/sean_geraty/)
	// Visit JavaScript Kit (http://www.javascriptkit.com) for this JavaScript and 100s more
	// Please keep this notice intact

	// Control flags for list selection and sort sequence
	// Sequence is on option value (first 2 chars - can be stripped off in form processing)
	// It is assumed that the select list is in sort sequence initially
	var singleSelect = true; // Allows an item to be selected once only
	var sortSelect = true; // Only effective if above flag set to true
	var sortPick = true; // Will order the picklist in sort sequence

	// Initialise - invoked on load
	function initIt() {
		var selectList = document.getElementById("SelectList");
		var selectOptions = selectList.options;
		var selectIndex = selectList.selectedIndex;
		var pickList = document.getElementById("PickList");
		var pickOptions = pickList.options;
		pickOptions[0] = null; // Remove initial entry from picklist (was only used to set default width)
		if (!(selectIndex > -1)) {
			selectOptions[0].selected = true; // Set first selected on load
			selectOptions[0].defaultSelected = true; // In case of reset/reload
		}
		selectList.focus(); // Set focus on the selectlist
	}

	// Adds a selected item into the picklist
	function addIt() {
		var selectList = document.getElementById("SelectList");
		var selectIndex = selectList.selectedIndex;
		var selectOptions = selectList.options;
		var pickList = document.getElementById("PickList");
		var pickOptions = pickList.options;
		var pickOLength = pickOptions.length;
		// An item must be selected
		while (selectIndex > -1) {
			pickOptions[pickOLength] = new Option(selectList[selectIndex].text);
			pickOptions[pickOLength].value = selectList[selectIndex].value;
			// If single selection, remove the item from the select list
			if (singleSelect) {
				selectOptions[selectIndex] = null;
			}
			if (sortPick) {
				var tempText;
				var tempValue;
				// Sort the pick list
				while (pickOLength > 0
						&& pickOptions[pickOLength].value < pickOptions[pickOLength - 1].value) {
					tempText = pickOptions[pickOLength - 1].text;
					tempValue = pickOptions[pickOLength - 1].value;
					pickOptions[pickOLength - 1].text = pickOptions[pickOLength].text;
					pickOptions[pickOLength - 1].value = pickOptions[pickOLength].value;
					pickOptions[pickOLength].text = tempText;
					pickOptions[pickOLength].value = tempValue;
					pickOLength = pickOLength - 1;
				}
			}
			selectIndex = selectList.selectedIndex;
			pickOLength = pickOptions.length;
		}
		selectOptions[0].selected = true;
	}

	// Deletes an item from the picklist
	function delIt() {
		var selectList = document.getElementById("SelectList");
		var selectOptions = selectList.options;
		var selectOLength = selectOptions.length;
		var pickList = document.getElementById("PickList");
		var pickIndex = pickList.selectedIndex;
		var pickOptions = pickList.options;
		while (pickIndex > -1) {
			// If single selection, replace the item in the select list
			if (singleSelect) {
				selectOptions[selectOLength] = new Option(
						pickList[pickIndex].text);
				selectOptions[selectOLength].value = pickList[pickIndex].value;
			}
			pickOptions[pickIndex] = null;
			if (singleSelect && sortSelect) {
				var tempText;
				var tempValue;
				// Re-sort the select list
				while (selectOLength > 0
						&& selectOptions[selectOLength].value < selectOptions[selectOLength - 1].value) {
					tempText = selectOptions[selectOLength - 1].text;
					tempValue = selectOptions[selectOLength - 1].value;
					selectOptions[selectOLength - 1].text = selectOptions[selectOLength].text;
					selectOptions[selectOLength - 1].value = selectOptions[selectOLength].value;
					selectOptions[selectOLength].text = tempText;
					selectOptions[selectOLength].value = tempValue;
					selectOLength = selectOLength - 1;
				}
			}
			pickIndex = pickList.selectedIndex;
			selectOLength = selectOptions.length;
		}
	}

	// Selection - invoked on submit
	function selIt(btn) {
		var pickList = document.getElementById("PickList");
		var pickOptions = pickList.options;
		var pickOLength = pickOptions.length;
		if (pickOLength < 1) {
			var r=confirm("Semua Pelajar Ke Kelas Yang Sama?");
			if (r == true){
				return true;
			}else{
				return false;
			}
		}
		
		for ( var i = 0; i < pickOLength; i++) {
			pickOptions[i].selected = true;
		}
		return true;
	}
//-->
</script>

<div class="entry">

<h2>Sila Pilih Nama Pelajar Yang Ke Kelas Lain</h2>
<form id="studentList" method="post" action="registerStudentOtherClass.jsp" onSubmit="return selIt();">
<table>
<tr><td>Pelajar Ke Kelas Yang Sama</td><td></td><td>Pelajar Ke Kelas Lain</td></tr>
	<tr>
		<td><select NAME="SelectList" ID="SelectList" SIZE="10"
			multiple="multiple" style="width: 400px">

				<%
					for(Student student : students) {
				%>
				<option value="<%=student.getStudent_id()%>"><%=student.getName()%></option>
				<%
					}
				%>

		</select></td>
		<td><input TYPE="BUTTON" VALUE="->" ONCLICK="addIt();"></input>
			<br /> <input TYPE="BUTTON" VALUE="<-" ONCLICK="delIt();"></input>
		</td>
		<td><select NAME="PickList" ID="PickList" SIZE="10"
			multiple="multiple" style="width: 400px">
			</select></td>
		</tr>
		<tr>
			<td ALIGN="left">&nbsp;</td>

			<td></td>
			<td ALIGN="left"><input class="blackbutton" name="StudentList" 
				TYPE="submit" VALUE="KEMASKINI">
			</td>
		</tr>
	</table>
</form>
</div>
<%}else{ %>
Tiada Maklumat Pelajar.
<%} %>
