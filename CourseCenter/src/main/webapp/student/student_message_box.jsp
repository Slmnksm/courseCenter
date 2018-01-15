<%-- 
    Document   : message
    Created on : 20.May.2017, 10:13:45
    Author     : SelmanKasim
--%>
<%@page import="tr.kasim.cc.model.CourseRequest"%>
<%@page import="tr.kasim.servlet.WebApplication"%>
<%@page import="tr.kasim.cc.model.UserSession"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="tr.kasim.cc.model.Message"%>
<%@page import="tr.kasim.cc.util.GeneralUtil"%>
<%@page import="tr.kasim.cc.model.User"%>
<%@page import="tr.kasim.cc.app.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String mainPage = request.getParameter("mainPage");

    UserSession userSession = WebApplication.getApp().getLoginSession(session);
    User srcUser = userSession.getUser();

    Map<User, List<Message>> srcUserMsgs = Application.getApp().getMainService().getMessages(null, srcUser.getId());

%>
<div id="messages" style="height: 400px;overflow: scroll;margin-left: 20px;margin-top: 50px">
    <p>Kişiler</p>
    <%        for (User msgDestUser : srcUserMsgs.keySet()) {
            List<Message> messages = srcUserMsgs.get(msgDestUser);
            Message lastMsg = null;
            String time = "";
            if(!messages.isEmpty()){
                lastMsg = messages.get(messages.size()-1);
                time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lastMsg.getSendTime());
            }
            
    %>
    <div  style = "width: 100%;height: 30px;background-color: #0085c8;color: white;" >
        <a href='<%=(mainPage)%>?page=message/send_message.jsp&srcId=<%=(userSession.getUser().getId())%>&destId=<%=(msgDestUser.getId())%>'><%=(msgDestUser.getFirstName() + " " + msgDestUser.getSurname())%></a> : <%=(time)%> 
    </div > <hr>
    <%
        }
    %> 

</div>


