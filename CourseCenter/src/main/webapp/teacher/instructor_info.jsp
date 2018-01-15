<%@page import="tr.kasim.cc.model.InstructorInfo"%>
<%@page import="tr.kasim.cc.model.User"%>
<%@page import="tr.kasim.cc.app.Application"%>
<%@page import="tr.kasim.cc.remote.MainService"%>
<%@page import="tr.kasim.servlet.WebApplication"%>
<%@page import="tr.kasim.cc.model.UserSession"%>
<%

    UserSession userSession = WebApplication.getApp().getLoginSession(session);
    InstructorInfo instructorInfo = Application.getApp().getMainService().getInstructorInfoByUserId(userSession.getUser().getId());

%>

<div>
    <h2>Instructor Info</h2>


    <form id="instructorInfo" method="post" action="ccService" onsubmit="return validateForm()">
        <input type="hidden" name="action" value="insInfo"/>
        <table>
            <tr>
                <td>Baslik:</td>
                <td><input type="text" name="title" value="<%=(instructorInfo != null ? instructorInfo.getTitle() : "")%>"/></td>
            </tr>
            <tr>
                <td>Deneyim:</td>
                <td><textarea name="exp" rows=5 cols=80><%=(instructorInfo != null ? instructorInfo.getExperience() : "")%></textarea></td>
            </tr>
            <tr>
                <td>Hakkimda:</td>
                <td><textarea name="info" rows=5 cols=80><%=(instructorInfo != null ? instructorInfo.getInfo() : "")%></textarea></td>
            </tr>
            <tr>
                <td>Referans:</td>
                <td><textarea name="reference" rows=5 cols=80><%=(instructorInfo != null ? instructorInfo.getReference() : "")%></textarea></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" value="Kaydet"/></td>
            </tr>
        </table>
    </form>

</div>