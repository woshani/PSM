<%@ page import="bean.Teacher"%>


<%

	Teacher teacher = new Teacher();

	teacher = (Teacher) session.getAttribute("SASteacher");
	
%>
 <script language="javascript" type="text/javascript">  
      var xmlHttp  
      var xmlHttp
      function showPenerima(str){
      if (typeof XMLHttpRequest != "undefined"){
      xmlHttp= new XMLHttpRequest();
      }
      else if (window.ActiveXObject){
      xmlHttp= new ActiveXObject("Microsoft.XMLHTTP");
      }
      if (xmlHttp==null){
      alert("Browser does not support XMLHTTP Request")
      return;
      } 
      var url="c_loadAnnouncementTarget.jsp";
      url +="?count=" +str;
      xmlHttp.onreadystatechange = stateChange;
      xmlHttp.open("GET", url, true);
      xmlHttp.send(null);
      }

      function stateChange(){   
      if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete"){   
      document.getElementById("target").innerHTML=xmlHttp.responseText   
      }   
       
      }
      </script>  
<div class="entry">
	<form id="AddAnnouncementForm" method="post" action="AddAnnouncement">
	<input type="hidden" name="teacherId" id="teacherId" value="<%=teacher.getTeacher_id()%>">
	<table width="600px" border="0" cellspacing="5" cellpadding="5">
	<% if(request.getAttribute("SASinvalidTarget") != null) { %>
		<tr>
			<td><span class="warning">* ID Pernerima tiada dalam sistem.</span></td>
		</tr>
	<% } %>
		<tr>
			<td>Subjek :<br />
				<input type="text" name="subject" style="width: 425px;"
					class="validate[required, maxSize[100]]" /></td>
		</tr>
		<tr>
			<td>Mesej :<br />
				<textarea type="text" name="text"
					class="validate[required, maxSize[160]]" rows="4" cols="50"
					style="width: 425px;"></textarea></td>
		</tr>
		<tr>
			<td>Jenis Pengumuman :<br />
				<select name="type" id="type" class="validate[required]" style="width: 160px" onchange="showPenerima(this.value)">
					<option value="">Sila Pilih Jenis</option>
					<option value="I">Institusi</option>
					<option value="C">Kelas</option>
					<option value="S">Pelajar</option>
				</select></td>
		</tr>
		<tr>
			<td>Pernerima :<br />
				<select  name="target" id="target" class="validate[required]"></select></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td align="right"><input type="submit" 
				name="addAnnouncement" value="Simpan" class="blackbutton" /></td>
		</tr>
	</table>
	</form>
</div>