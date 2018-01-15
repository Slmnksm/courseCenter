<%-- 
    Document   : show_create_course
    Created on : 11.May.2017, 14:30:24
    Author     : SelmanKasim
--%>

<%@page import="tr.kasim.cc.model.CourseInstructor"%>
<%@page import="tr.kasim.cc.model.CourseType"%>
<%@page import="tr.kasim.cc.model.CourseTime"%>
<%@page import="tr.kasim.cc.model.CourseService"%>
<%@page import="tr.kasim.cc.model.CoursePrice"%>
<%@page import="tr.kasim.cc.model.CoursePlace"%>
<%@page import="tr.kasim.cc.util.GeneralUtil"%>
<%@page import="tr.kasim.cc.filter.CourseFilter"%>
<%@page import="tr.kasim.servlet.WebApplication"%>
<%@page import="tr.kasim.cc.model.UserSession"%>
<%@page import="tr.kasim.cc.app.Application"%>
<%@page import="java.util.List"%>
<%@page import="tr.kasim.cc.model.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UserSession userSession = WebApplication.getApp().getLoginSession(session);

    String mainPage = request.getParameter("mainPage");

    CourseFilter courseFilter = new CourseFilter();
    if (request.getParameter("fName") != null && !"".equals(request.getParameter("fName").trim())) {
        courseFilter.setName(request.getParameter("fName"));
    }
    if (request.getParameter("fTeacherName") != null && !"".equals(request.getParameter("fTeacherName").trim())) {
        courseFilter.setTeacherName(request.getParameter("fTeacherName"));
    }
    if (request.getParameter("fPlace") != null) {
        courseFilter.setPlaceId(GeneralUtil.toInt(request.getParameter("fPlace")));
    }
    if (request.getParameter("fPrice") != null) {
        courseFilter.setPriceId(GeneralUtil.toInt(request.getParameter("fPrice")));
    }
    if (request.getParameter("fService") != null) {
        courseFilter.setServiceId(GeneralUtil.toInt(request.getParameter("fService")));
    }
    if (request.getParameter("fTime") != null) {
        courseFilter.setTimeId(GeneralUtil.toInt(request.getParameter("fTime")));
    }
    if (request.getParameter("fType") != null) {
        courseFilter.setTypeId(GeneralUtil.toInt(request.getParameter("fType")));
    }
    if (request.getParameter("fLocation") != null && !"".equals(request.getParameter("fLocation").trim())) {
        courseFilter.setLocation(request.getParameter("fLocation"));
    }
    if (request.getParameter("fInstructor") != null) {
        courseFilter.setInstructorId(GeneralUtil.toInt(request.getParameter("fInstructor")));
    }

    List<Course> courses = Application.getApp().getMainService().getCourseByFilter(courseFilter);

%>

<div>
    <br>
    <br>
    <table>
        <tr>
            <td style="width: 20%">
                <form action="<%=(mainPage)%>">
                    <input type="hidden" name="page" value="student/active_course_list.jsp"/>
                    <h2>Filtre:</h2>
                    <table>
                        <tr>
                            <td>Kursun Adi:</td>
                            <td><input type="text" name="fName" value="<%=(request.getParameter("fName"))%>"/></td>
                        </tr>
                        <tr>
                            <td>Yer:</td>
                            <td><select name="fPlace">
                                    <option  value="" >Seçiniz...</option>
                                    <%
                                        Integer fPlaceId = GeneralUtil.toInt(request.getParameter("fPlace"));
                                        for (CoursePlace place : (List<CoursePlace>) Application.getApp().getMainService().getAll(CoursePlace.class)) {
                                            boolean selected = false;
                                            if (place.getId().equals(fPlaceId)) {
                                                selected = true;
                                            }
                                    %>
                                    <option type="text" <%=(selected ? "selected" : "")%> value="<%=(place.getId())%>"><%=(place.getName())%></option>
                                    <%
                                        }

                                    %>
                                </select> </td>
                        </tr>
                        <tr>
                            <td>Ucret:</td>
                            <td><select name="fPrice">
                                    <option type="text" value="">Seçiniz...</option>
                                    <%                            for (CoursePrice price : (List<CoursePrice>) Application.getApp().getMainService().getAll(CoursePrice.class)) {
                                    %>
                                    <option type="text" value="<%=(price.getId())%>"><%=(price.getName())%></option>
                                    <%
                                        }

                                    %>
                                </select></td>
                        </tr>
                        <tr>
                            <td>Kurs Servis Sekli:</td>
                            <td><select name="fService">
                                    <option type="text" value="">Seçiniz...</option>
                                    <%                            for (CourseService service : (List<CourseService>) Application.getApp().getMainService().getAll(CourseService.class)) {
                                    %>
                                    <option type="text" value="<%=(service.getId())%>"><%=(service.getName())%></option>
                                    <%
                                        }

                                    %>
                                </select></td>
                        </tr>
                        <tr>
                            <td>Zaman:</td>
                            <td><select name="fTime">
                                    <option type="text" value="">Seçiniz...</option>
                                    <%                            for (CourseTime time : (List<CourseTime>) Application.getApp().getMainService().getAll(CourseTime.class)) {
                                    %>
                                    <option type="text" value="<%=(time.getId())%>"><%=(time.getName())%></option>
                                    <%
                                        }

                                    %>
                                </select></textarea></td>
                        </tr>
                        <tr>
                            <td>Kurs Tipi:</td>
                            <td><select name="fType">
                                    <option type="text" value="">Seçiniz...</option>
                                    <%                            for (CourseType type : (List<CourseType>) Application.getApp().getMainService().getAll(CourseType.class)) {
                                    %>
                                    <option type="text" value="<%=(type.getId())%>"><%=(type.getName())%></option>
                                    <%
                                        }

                                    %>
                                </select></td>
                        </tr>
                        <tr>
                            <td>Kurs Veren Tipi:</td>
                            <td><select name="fInstructor">
                                    <option type="text" value="">Seçiniz...</option>
                                    <%                            for (CourseInstructor inst : (List<CourseInstructor>) Application.getApp().getMainService().getAll(CourseInstructor.class)) {
                                    %>
                                    <option type="text" value="<%=(inst.getId())%>"><%=(inst.getName())%></option>
                                    <%
                                        }

                                    %>
                                </select></td>
                        </tr>
                        <tr>
                            <td>Şehir:</td>
                            <td><input type="text" name="fLocation"/></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                        <input type="submit" value="Filtrele"/>
                        <input type="reset" value="Temizle"/>
                        </td>
                        </tr>
                    </table>
                </form>
            </td>
            <td style="width: 80%"><table id="courseList"></table></td>
        </tr>
    </table>

</div>
<script>

    function pageInit() {

        var dataSet = [
    <%                int index = 1;
        for (Course course : courses) {
    %>
        [<%=(index++)%>,
                "<%=(course.getName())%>",
                "<%=(course.getUser().getFirstName() + " " + course.getUser().getSurname())%>",
                "<%=(course.getCoursePlace().getName())%>",
                "<%=(course.getCoursePrice().getName())%>",
                "<%=(course.getCourseService().getName())%>",
                "<%=(course.getCourseTime().getName())%>",
                "<%=(course.getCourseType().getName())%>",
                "<%=(course.getCourseInstructor().getName())%>",
                "<%=(course.getUser().getCity())%>",
    <%if (userSession == null) {
    %>
        "<%=("<a href='login.jsp'>Giriş yap</a>")%>",<%
         }else {
    %>
        "<%=("<a href='"+mainPage+"?page=message/send_message.jsp&srcId="+userSession.getUser().getId()+"&destId="+course.getUser().getId()+"'>Mesaj Gönder</a>")%>",
    <%
}%>

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
                {title: "Öğretmen"},
                {title: "Yer"},
                {title: "Ucret"},
                {title: "Servis Tipi"},
                {title: "Zaman"},
                {title: "Tipi"},
                {title: "Kurs Veren Tipi"},
                {title: "Sehir"},
                {title: "&nbsp;"},
            ]
        });
    }
</script>