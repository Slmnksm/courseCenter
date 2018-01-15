<%@page import="tr.kasim.cc.model.EducationStatus"%>
<%@page import="tr.kasim.cc.model.User"%>
<%@page import="tr.kasim.cc.app.Application"%>
<%@page import="tr.kasim.cc.remote.MainService"%>
<%@page import="tr.kasim.servlet.WebApplication"%>
<%@page import="tr.kasim.cc.model.UserSession"%>
<%
    UserSession userSession = WebApplication.getApp().getLoginSession(session);
    EducationStatus educationStatus = Application.getApp().getMainService().getEducationStatusByUserId(userSession.getUser().getId());


%>

<div>
    <h2>Education Status</h2>


    <form id="educationStatus" method="post" action="ccService" onsubmit="return validateForm()">
        <input type="hidden" name="action" value="eduStat"/>
        <table>
            <tr>
                <td>Okudugunuz Lisenin Adi:</td>
                <td><input type="text" name="highSchoolName" value="<%=(educationStatus != null ? educationStatus.getHighSchoolName(): "")%>"/></td>
            </tr>
            <tr>
                <td>Okudugunuz Universitenin Adi:</td>
                <td><input type="text" name="universityName" value="<%=(educationStatus != null ? educationStatus.getUniversityName(): "")%>"/></td>
            </tr>
            <tr>
                <td>Ekstra Bilgi:</td>
                <td><textarea name="extra" rows=5 cols=100><%=(educationStatus != null ? educationStatus.getExtra(): "")%></textarea></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" value="Kaydet"/></td>
            </tr>
        </table>
    </form>
</div>