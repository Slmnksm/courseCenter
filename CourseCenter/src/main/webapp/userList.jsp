<%-- 
    Document   : userList
    Created on : 24.Mar.2017, 17:50:45
    Author     : SelmanKasim
--%>

<%@page import="tr.kasim.cc.model.User"%>
<%@page import="tr.kasim.cc.app.Application"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/index.css"></link>
    <title>User Listesi</title>
</head>

<body><center>
    <p class="title">User Listesi</p>

    <table style="border: black 1px solid">
        <tr>
            <td>Username</td>
            <td>Password</td>
            <td>Name</td>
            <td>Surname</td>
            <td>e-Mail</td>
            <%--<td>Job</td>--%>
            <td>IP ADDRESS</td>
            <%--<td>Gender</td>--%>
            <td>userTelephone</td>
            <td>userCity</td>
        </tr>
        <%
            List<User> users = Application.getApp().getMainService().getUsers();
            for (User cc_user : users) {
        %>
        <tr>
            <td><%=(cc_user.getUserName())%></td>
            <td><%=(cc_user.getPassword())%></td>
            <td><%=(cc_user.getFirstName())%></td>
            <td><%=(cc_user.getSurname())%></td>
            <td><%=(cc_user.getEmail())%></td>
            <%--<td><%=(cc_user.getRole())%></td>--%>
            <td><%=(cc_user.getIpAddress())%></td>
            <%--<td><%=(cc_user.getGender())%></td>--%>
            <td><%=(cc_user.getTelephone())%></td>
            <td><%=(cc_user.getCity())%></td>
        </tr>
        <%}
        %>
    </table>
    <br/><br><br>
    <a href="index.jsp">Ana Sayfaya Dön</a><br>
</center>
</body>
</html>




