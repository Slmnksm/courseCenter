<%-- 
    Document   : course
    Created on : 10.May.2017, 18:56:37
    Author     : SelmanKasim
--%>

<%@page import="tr.kasim.cc.model.CourseInstructor"%>
<%@page import="tr.kasim.cc.model.CourseType"%>
<%@page import="tr.kasim.cc.model.CourseTime"%>
<%@page import="tr.kasim.cc.model.CourseService"%>
<%@page import="tr.kasim.cc.model.CoursePrice"%>
<%@page import="java.util.List"%>
<%@page import="tr.kasim.cc.model.CoursePlace"%>
<%@page import="tr.kasim.cc.remote.MainService"%>
<%@page import="tr.kasim.servlet.WebApplication"%>
<%@page import="tr.kasim.cc.app.Application"%>
<%@page import="tr.kasim.cc.model.User"%>
<%@page import="tr.kasim.cc.model.UserSession"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UserSession userSession = WebApplication.getApp().getLoginSession(session);
    User user = userSession.getUser();
    MainService mainService = Application.getApp().getMainService();


%>

<div>
    <h2>Create New Course</h2>


    <form id="course" method="post" action="ccService" onsubmit="return validateForm()">
        <input type="hidden" name="action" value="cour"/>
        <table>
            <tr>
                <td>Kursun Adi:</td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td>Yer:</td>
                <td><select name="place">
                        <%
                            for (CoursePlace place : (List<CoursePlace>) Application.getApp().getMainService().getAll(CoursePlace.class)) {
                        %>
                        <option type="text" value="<%=(place.getId())%>"><%=(place.getName())%></option>
                        <%
                            }

                        %>
                    </select> </td>
            </tr>
            <tr>
                <td>Ucret:</td>
                <td><select name="price">
                        <%
                            for (CoursePrice price : (List<CoursePrice>) Application.getApp().getMainService().getAll(CoursePrice.class)) {
                        %>
                        <option type="text" value="<%=(price.getId())%>"><%=(price.getName())%></option>
                        <%
                            }

                        %>
                    </select></td>
            </tr>
            <tr>
                <td>Kurs Servis Sekli:</td>
                <td><select name="service">
                        <%
                            for (CourseService service : (List<CourseService>) Application.getApp().getMainService().getAll(CourseService.class)) {
                        %>
                        <option type="text" value="<%=(service.getId())%>"><%=(service.getName())%></option>
                        <%
                            }

                        %>
                    </select></td>
            </tr>
            <tr>
                <td>Zaman:</td>
                <td><select name="time">
                        <%
                            for (CourseTime time : (List<CourseTime>) Application.getApp().getMainService().getAll(CourseTime.class)) {
                        %>
                        <option type="text" value="<%=(time.getId())%>"><%=(time.getName())%></option>
                        <%
                            }

                        %>
                    </select></textarea></td>
            </tr>
            <tr>
                <td>Kurs Tipi:</td>
                <td><select name="type">
                        <%
                            for (CourseType type : (List<CourseType>) Application.getApp().getMainService().getAll(CourseType.class)) {
                        %>
                        <option type="text" value="<%=(type.getId())%>"><%=(type.getName())%></option>
                        <%
                            }

                        %>
                    </select></td>
            </tr>
            <tr>
                <td>Kurs Veren Tipi:</td>
                <td><select name="inst">
                        <%
                            for (CourseInstructor inst : (List<CourseInstructor>) Application.getApp().getMainService().getAll(CourseInstructor.class)) {
                        %>
                        <option type="text" value="<%=(inst.getId())%>"><%=(inst.getName())%></option>
                        <%
                            }

                        %>
                    </select></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" value="Düzenlemeyi Tamamla"/></td>
            </tr>
        </table>
    </form>

</div>
