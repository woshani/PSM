
<%@page import="facade.ControllerWrapper"%>
<%@page import="java.util.Vector"%>
<%@page import="controller.DiscussionController"%>
<%@page import="bean.Discussion"%>
<%@page import="bean.Topic"%>
<%
	String topicId = request.getParameter("id");
	String col = request.getParameter("col");

	Topic topic = new Topic();
	topic.setTopicId(Integer.parseInt(topicId));

	ControllerWrapper ctrlWrap = new ControllerWrapper();

	topic = ctrlWrap.getTopicById(topic);

	Vector<Discussion> discussions = ctrlWrap
			.getAllDiscussionMain(topic);
%><h1 align="center">
	<font color="black">PERBINCANGAN ADUAN :</font>
</h1>
<hr />
<br />
<%
	if (topic.getPostDate() != null) {

		if (col.contentEquals("0")) {
%>
<div class="topicSub">
	<h3>
		ADUAN : <u><%=topic.getTitle()%></u>
	</h3>
	<br />
	<p><%=topic.getText()%></p>
	<span class="left">Daripada : <strong><%=topic.getTeacherName()%></strong></span>
	<br /> <span class="right"><%=topic.getPostDate()%></span> <br />
</div>
<%
	} else {
%>
<div class="topicSub2">
	<h3>
		ADUAN : <u><%=topic.getTitle()%></u>
	</h3>
	<br />
	<p><%=topic.getText()%></p>
	<span class="left">Daripada : <strong><%=topic.getTeacherName()%></strong></span>
	<br /> <span class="right"><%=topic.getPostDate()%></span> <br />
</div>
<%
	}
%>
<br />
<br />
<div align="right">
	<button class="blackbutton"
		onclick="window.location.href='forumMain.jsp'">KEMBALI</button>
</div>
<br />
<h1 align="center">
	<font color="black">KOMEN / PENDAPAT / CADANGAN PARA PENGGUNA :
	</font>
</h1>

<br />

<%
		if (discussions.isEmpty()) {
%>
<p>Tiada REKOD ditemui</p>
<%
		} else {
			
			int row = 0;				
			
			for (Discussion discussion : discussions){
				if ((row % 2) == 0) {
%>
<div class="message2">
	<br />
	<h3><%=discussion.getTeacherName() %></h3>
	<br />
	<p>
		<%=discussion.getText() %>
	</p>
	<span><%=discussion.getPostDate() %></span> <br /> <br />

</div>
<%
				}else{
%>
<div class="message1">
	<br />
	<h3><%=discussion.getTeacherName() %></h3>
	<br />
	<p>
		<%=discussion.getText() %>
	</p>
	<span><%=discussion.getPostDate() %></span> <br /> <br />
</div>
<%			
				}
			row++;
			}
		}
	} else {
%>
<p>Tiada REKOD ADUAN ditemui.</p>
<%
	}
%>