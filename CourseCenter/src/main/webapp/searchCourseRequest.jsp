<%-- 
    Document   : search_Course
    Created on : 13.May.2017, 00:39:27
    Author     : SelmanKasim
--%>

<%@page import="tr.kasim.servlet.WebApplication"%>
<%@page import="tr.kasim.cc.model.UserSession"%>
<%@page import="com.sun.xml.rpc.processor.modeler.j2ee.xml.roleNameType"%>
<%@page import="com.sun.xml.ws.config.metro.parser.jsr109.RoleNameType"%>
<%@page import="tr.kasim.cc.app.Application"%>
<%@page import="java.util.List"%>
<%@page import="tr.kasim.cc.model.CourseRequest"%>
<%@page import="tr.kasim.cc.model.Course"%>
<%@page import="tr.kasim.cc.type.RoleType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%
    UserSession userSession = WebApplication.getApp().getLoginSession(session);
%>

<div>
    <h1>Ders Alan Arama Ekranına Hoşgeldiniz ! </h1>
    <h2>Bu sayfada ders verebileceğin öğrencileri arayabilirsin!</h2>

    <form id="SearchCourseRequest" method="post" action="ccService" onsubmit="return validateForm()">
        <input type="hidden" name="action" value="searchCourReq"/>
        <table>
            <tr>
                <td>Aradığınız Kursun Adi: </td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" value="Dersleri Ara"/></td>
            </tr>
        </table>
    </form>

    <br><br><br><br><br>
    <br>
    <br>
    <a href="index.jsp">Ana Sayfaya Dön</a><br>
    
</div>











