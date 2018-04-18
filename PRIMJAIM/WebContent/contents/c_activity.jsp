<%@page import="bean.Topic"%>
<%@page import="java.util.Vector"%>
<%@page import="facade.ControllerWrapper"%>
<%
	ControllerWrapper ctrlWrap = new ControllerWrapper();
	Vector<Topic> topics = ctrlWrap.getAllTopicMain();
%>
<h1 align="center">
	<font color="black">FORUM ADUAN</font>
</h1>
<hr />
<br />
<h1 align="center">
	<font color="black">ADUAN PARA PENGGUNA : </font>
</h1>
<br />
<p>
	<font color="red"> * </font><font color="black">Sila <a
		href="logIn.jsp"><strong>LOG MASUK</strong></a> untuk membuat aduan.
	</font>
</p>
<%
	if (topics.isEmpty()) {
%>

<p>Tiada rekod ADUAN ditemui.</p>

<%
	} else {
		int row = 1;
		for (Topic topic : topics) {

			if ((row % 2) == 0) {
%>
<div class="activityDiv1">
	<div class="activityDiv1Left">
		<img src="${pageContext.request.contextPath}/images/message.png"
			height="30" width="30"> <strong><%=topic.getTotalDiscussion()%></strong>
		&nbsp
		<button class="blackbutton"
			onclick="window.location.href='topicMain.jsp?col=<%=0%>&id=<%=topic.getTopicId()%>'">Lihat</button>
		<br /> <br /><%=topic.getPostDate()%>
	</div>
	<div class="activityDiv1Right">
		<a href="topicMain.jsp?col=<%=0%>&id=<%=topic.getTopicId()%>"><h3>
				<u><%=topic.getTitle()%></u>
			</h3></a>
		<p><%=topic.getText()%></p>
		<p>
			Daripada : <strong><%=topic.getTeacherName()%></strong>
		</p>
	</div>
</div>
<%
	}

			if ((row % 2) == 1) {
%>
<div class="topicMain2">
	<div class="topicMain2Left">
		<a href="topicMain.jsp?col=<%=1%>&id=<%=topic.getTopicId()%>"><h3>
				<u><%=topic.getTitle()%></u>
			</h3></a>
		<p><%=topic.getText()%></p>
		<p>
			Daripada : <strong><%=topic.getTeacherName()%></strong>
		</p>
	</div>
	<div class="topicMain2Right">
		<img src="${pageContext.request.contextPath}/images/message.png"
			height="30" width="30"> <strong><%=topic.getTotalDiscussion()%></strong>
		&nbsp
		<button class="blackbutton"
			onclick="window.location.href='topicMain.jsp?col=<%=1%>&id=<%=topic.getTopicId()%>'">Lihat</button>
		<br /> <br /><%=topic.getPostDate()%>
	</div>
</div>
<%
	}
			row++;
		}
	}
%>
<br />