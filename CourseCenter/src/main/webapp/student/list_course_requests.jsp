<%@page import="java.util.List"%>
<%@page import="tr.kasim.cc.model.CourseRequest"%>
<%@page import="tr.kasim.cc.model.User"%>
<%@page import="tr.kasim.cc.app.Application"%>
<%@page import="tr.kasim.cc.remote.MainService"%>
<%@page import="tr.kasim.servlet.WebApplication"%>
<%@page import="tr.kasim.cc.model.UserSession"%>
<%
    UserSession userSession = WebApplication.getApp().getLoginSession(session);
    List<CourseRequest> courseReqs = Application.getApp().getMainService().getCourseRequestByUserId(userSession.getUser().getId());

%>

<div>
    <br>
    <br>

    <table id="courseRequestList"></table>
</div>
<script>

    function pageInit() {

        var dataSet = [
    <%                int index = 1;
        for (CourseRequest courseRequest : courseReqs) {
    %>
            [<%=(index++)%>,
                    "<%=(courseRequest.getName())%>",
                    "<%=(courseRequest.getMaxPrice())%>",
                    "<%=(courseRequest.getExtra())%>",
                    "<%=(courseRequest.getCoursePlace().getName())%>",
                    "<%=(courseRequest.getCoursePrice().getName())%>",
                    "<%=(courseRequest.getCourseService().getName())%>",
                    "<%=(courseRequest.getCourseTime().getName())%>",
                    "<%=(courseRequest.getCourseType().getName())%>",
                    "<%=(courseRequest.getCourseInstructor().getName())%>",
                    "<%=(courseRequest.getUser().getCity())%>",
            ],
    <%
        }
    %>

        ];


        $('#courseRequestList').DataTable({
            data: dataSet,
            columns: [
                {title: "#"},
                {title: "Adi"},
                {title: "Ucret(Max)"},
                {title: "Ekstra"},
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