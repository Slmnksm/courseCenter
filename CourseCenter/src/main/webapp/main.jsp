
<%@page import="tr.kasim.cc.app.Application"%>
<%@page import="java.util.List"%>
<%@page import="tr.kasim.cc.model.EducationStatus"%>
<%@page import="tr.kasim.cc.model.User"%>
<%@page import="tr.kasim.cc.type.RoleType"%>
<%@page import="tr.kasim.cc.model.UserSession"%>
<%@page import="tr.kasim.servlet.WebApplication"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UserSession userSession = WebApplication.getApp().getLoginSession(session);

    if (userSession == null) {
        WebApplication.getApp().gotoLoginPage(response, true, "You are not logged");
        return;
    }

    User user = userSession.getUser();
    RoleType roleType = RoleType.getByValue(user.getRole().getId());
    String containerPage = request.getParameter("page");

    boolean isFail = "fail".equals(request.getParameter("type"));
    String actionMsg = request.getParameter("msg");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Profilim</title>
    <link rel="stylesheet" href="css/main.css"/>
    <script src="js/jquery.js"></script>
    <script src="js/metadata.js"></script>
    <link rel="stylesheet" type="text/css" href="js/datatables.min.css"/>

    <script type="text/javascript" src="js/datatables.min.js"></script>


</head>
<body>
    <table class="main" cellspacing="0px" cellpadding="0px">
        <tr class="header">
            <td  class="header_text">
                <h1><%=(userSession.getUser().getRole().getTypeRole())%> Profile Sayfası</h1>
            </td>
            <td  class="header_info">
                <br>
                <a href="main.jsp"><%=(userSession.getUser().getFirstName() + " " + userSession.getUser().getSurname())%></a> <br>
                <a href="ccService?action=logout"> Sistemden çıkış</a>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="">
                <table class="main" cellspacing="0px" cellpadding="0px">
                    <tr>
                        <td class="menu">
                            <ul>
                                <%
                                    switch (roleType) {
                                        case Student: {
                                %>
                                <li><a id="cre" class="{url:'student/create_course_request.jsp'}" href="#">Ders Talebi Oluşturma</a></li>
                                <li><a id="list" class="{url:'student/list_course_requests.jsp'}" href="#">Ders Taleplerim</a></li>
                                <li><a id="act" class="{url:'student/active_course_list.jsp'}" href="#">Aktif Dersler</a></li>
                                <li><a id="msg" class="{url:'student/student_message_box.jsp'}" href="#">Mesaj Kutusu</a></li>


                                <%
                                        break;
                                    }
                                    case Teacher: {
                                %>
                                <li><a id="edu" class="{url:'teacher/education_status.jsp'}" href="#">Öğrenim Durumu</a></li>
                                <li><a id="info" class="{url:'teacher/instructor_info.jsp'}" href="#">Hakkımda</a></li>
                                <li><a id="cre"  class="{url:'teacher/create_course.jsp'}"href="#">Vereceğim Ders Bilgileri</a></li>
                                <li><a id="list"  class="{url:'teacher/list_course.jsp'}"href="#">Olusturdugum Ders Bilgileri</a></li>
                                <li><a id="act"  class="{url:'teacher/active_course_request_list.jsp'}"href="#">Aktif Ders İstekleri</a></li>
                                <li><a id="msg" class="{url:'teacher/teacher_message_box.jsp'}" href="#">Mesaj Kutusu</a></li>

                                <%
                                            break;
                                        }
                                    }

                                %>

                            </ul>
                        </td> 
                        <td class="container">


                            <%            if (actionMsg != null) {
                            %>
                            <h2 style="color: <%=(isFail ? "red" : "green")%>"><%=(isFail ? "Hata oluştu :" : "")%> <%=(actionMsg)%></h2>
                            <%
                                }
                            %>
                            <%if (containerPage == null) {
                            %> <h1>Hosgeldin
                                <%=(userSession.getUser().getFirstName() + " " + userSession.getUser().getSurname())%>!</h1> <br>

                            <h1>Bu senin profil sayfan.</h1> <br><br><br>

                            <h1>Soldaki menüleri kullanarak profil bilgilerini guncelleyebilirsin !</h1>

                            <%
                            } else {
                            %>
                            <jsp:include page="<%=(containerPage)%>">
                                <jsp:param name="mainPage" value="main.jsp"></jsp:param>
                            </jsp:include>    
                            <%
                                }
                            %>

                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>

<script>
    $(document).ready(function () {
        $(".menu a").click(function () {
            var data = $(this).metadata();
            var url = data.url;

            document.location = "main.jsp?page=" + url;
        });

        try {
            pageInit();
        } catch (err) {
            //alert(err);
        }
    });
</script>
</body>
</html>
