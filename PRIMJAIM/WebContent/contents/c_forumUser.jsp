<%@page import="facade.ControllerWrapper"%>
<%@page import="bean.Topic"%>
<%@page import="java.util.Vector"%>
<%@page import="bean.Teacher"%>
<%
	ControllerWrapper ctrlWrap = new ControllerWrapper();
	Teacher teacher = new Teacher();
	teacher = (Teacher) session.getAttribute("SASteacher");
	
	
	Vector<Topic> userTopics = new Vector<Topic>();
	userTopics = ctrlWrap.getAllTopicByUser(teacher);
	
	if (userTopics.isEmpty()) {
%>

<p align="center">Tiada rekod ADUAN anda ditemui.</p>

<%
	} else {
		int row = 1;
		for (Topic userTopic : userTopics) {

			if ((row % 2) == 0) {
%>
<div class="topicMain2">
	<div class="topicMainLeft">
		<a href="topicMain.jsp?col=<%=0%>&id=<%=userTopic.getTopicId()%>"><h3>
				<u><%=userTopic.getTitle()%></u>
			</h3></a>
		<p><%=userTopic.getText()%></p>
		<p>
			Daripada : <strong><%=userTopic.getTeacherName()%></strong>
		</p>
	</div>
	<div class="topicMainRight">
		<img src="${pageContext.request.contextPath}/images/message.png"
			height="30" width="30"> <strong><%=userTopic.getTotalDiscussion()%></strong>
		&nbsp
		<button class="blackbutton"
			onclick="window.location.href='topicMain.jsp?col=<%=0%>&id=<%=userTopic.getTopicId()%>'">Lihat</button>
		<br /> <br /><%=userTopic.getPostDate()%>
	</div>
</div>
<%
	}

			if ((row % 2) == 1) {
%>
<div class="topicMain">
	<div class="topicMain2Left">
		<a href="topicMain.jsp?col=<%=1%>&id=<%=userTopic.getTopicId()%>"><h3>
				<u><%=userTopic.getTitle()%></u>
			</h3></a>
		<p><%=userTopic.getText()%></p>
		<p>
			Daripada : <strong><%=userTopic.getTeacherName()%></strong>
		</p>
	</div>
	<div class="topicMain2Right">
		<img src="${pageContext.request.contextPath}/images/message.png"
			height="30" width="30"> <strong><%=userTopic.getTotalDiscussion()%></strong>
		&nbsp
		<button class="blackbutton"
			onclick="window.location.href='topicMain.jsp?col=<%=1%>&id=<%=userTopic.getTopicId()%>'">Lihat</button>
		<br /> <br /><%=userTopic.getPostDate()%>
	</div>
</div>
<%
	}
			row++;
		}
	}
%>