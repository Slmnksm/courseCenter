<%-- 
    Document   : show_create_course
    Created on : 11.May.2017, 14:30:24
    Author     : SelmanKasim
--%>

<%@page import="tr.kasim.servlet.WebApplication"%>
<%@page import="tr.kasim.cc.model.UserSession"%>
<%@page import="tr.kasim.cc.app.Application"%>
<%@page import="java.util.List"%>
<%@page import="tr.kasim.cc.model.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UserSession userSession = WebApplication.getApp().getLoginSession(session);
    List<Course> courses = Application.getApp().getMainService().getCourseByUserId(userSession.getUser().getId());

%>

<div>
    <br>
    <br>

    <table id="courseList"></table>
</div>
<script>

    function pageInit() {

        var dataSet = [
    <%                int index = 1;
        for (Course course : courses) {
    %>
            [<%=(index++)%>,
                    "<%=(course.getName())%>",
                    "<%=(course.getCoursePlace().getName())%>",
                    "<%=(course.getCoursePrice().getName())%>",
                    "<%=(course.getCourseService().getName())%>",
                    "<%=(course.getCourseTime().getName())%>",
                    "<%=(course.getCourseType().getName())%>",
                    "<%=(course.getCourseInstructor().getName())%>",
                    "<%=(course.getUser().getCity())%>",
            ],
    <%
        }
    %>

        ];


        $('#courseList').DataTable({
            data: dataSet,
            columns: [
                {title: "#"},
                {title: "Adı"},
                {title: "Yer"},
                {title: "Ucret"},
                {title: "Servis Tipi"},
                {title: "Zaman"},
                {title: "Tipi"},
                {title: "Kurs Veren Tipi"},
                {title: "Sehir"},
            ]
        });






    }
</script>