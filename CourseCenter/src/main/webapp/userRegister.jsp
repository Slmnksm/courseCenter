<%@page import="tr.kasim.cc.model.Gender"%>
<%@page import="tr.kasim.cc.model.Role"%>
<%@page import="tr.kasim.servlet.WebApplication"%>
<%@page import="tr.kasim.cc.type.GenderType"%>
<%@page import="java.util.List"%>
<%@page import="tr.kasim.cc.app.Application"%>
<%@page import="tr.kasim.cc.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%

    boolean isFail = "true".equals(request.getParameter("fail"));
    String failMsg = request.getParameter("msg");

%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Kaydol</title>
        <link rel="stylesheet" type="text/css" href="site.css"></link>
        <script src="js/jquery.js" ></script>
    </head>

    <body>
        <header>
            <div class="content-wrapper">
                <div class="float-left">
                    <p class="site-title">
                        <a href="index.jsp">Course Center</a>
                    </p>
                </div>
                <div class="float-right">

                    <nav>
                        <ul id="menu">
                            <li><a href="index.jsp">Ana Sayfa</a></li>
                            <li><a href="about.jsp">Hakkımızda</a></li>
                            <li><a href="contact.jsp">İletişim</a></li>
                            <li><a id="loginLink" href="login.jsp">Giriş</a></li>

                        </ul>
                    </nav>
                </div>
            </div>
        </header>
        <div id="body">
            <center>
                <h1>Yeni User Kayit</h1>
                <%        if (isFail) {%>
                <h2 style="color: red">Hata oluştu : <%=(failMsg)%></h2>
                <%}%>
                <form id="registerForm" method="post" action="ccService" onsubmit="return validateForm()">
                    <input type="hidden" name="action" value="addUser"/>
                    <table>
                        <tr>
                            <td>Kullanici Adi:</td>
                            <td><input type="text" name="username"/></td>
                        </tr>
                        <tr>
                            <td>Sifre:</td>
                            <td><input type="password" name="password"/></td>
                        </tr>
                        <tr>
                            <td>İsim:</td>
                            <td><input type="text" name="firstName"/></td>
                        </tr>
                        <tr>
                            <td>Soyisim:</td>
                            <td><input type="text" name="surname"/></td>
                        </tr>
                        <tr>
                            <td>eMail:</td>
                            <td><input type="text" name="email"/></td>
                        </tr>
                        <tr>
                            <td>Meslek:</td>
                            <td>
                                <select name="roleTypeId">
                                    <%
                                        for (Role role : (List<Role>) Application.getApp().getMainService().getAll(Role.class)) {
                                    %>
                                    <option type="text" value="<%=(role.getId())%>"><%=(role.getTypeRole())%></option>
                                    <%
                                        }

                                    %>
                                </select> 
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br>
                            </td>            
                        </tr>
                        <tr>
                            <td>Kullanici Cinsiyeti:</td>
                            <td><select name="genderId">
                                    <%                            for (Gender gender : (List<Gender>) Application.getApp().getMainService().getAll(Gender.class)) {
                                    %>
                                    <option type="text" value="<%=(gender.getId())%>"><%=(gender.getName())%></option>
                                    <%
                                        }

                                    %>
                                </select> 
                            </td>
                        </tr>
                        <tr>
                            <td>Kullanici Telefonu</td>
                            <td><input type="text" name="phone"/></td>
                        </tr>
                        <tr>
                            <td>Kullanici Sehri:</td>
                            <td><input type="text" name="city"/></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><input type="submit" value="Kayiti Tamamla"/></td>
                        </tr>
                    </table>
                    <p>
                        Kayit işlemi esnasinda "Meslek" sekmesinden öğrenci iseniz "Öğrenci", öğretmen iseniz "Öğretmen" seçiniz.
                    </p>


            </center>
            </form>
            <script>
                $(document).ready(function () {
                    $("#registerForm").submit(function () {
                        var inputs = $("#registerForm").find("input");
                        $(inputs).each(function () {
                            if (!$(this).val() || $(this).val().trim()) == "") {
                                $(this).after("<p color='red'>Boş olamaz</p>");
                            }
                        });
                    });

                    return false;
                });

            </script>



        </div>

        <footer>
            <div class="content-wrapper">
                <div class="float-left">
                    <p>Social Media Accounts</p> 
                    <a href="https://www.facebook.com" ><img src="icons/Facebook-icon.png" /></a>
                    <a href="https://www.twitter.com" ><img src="icons/Twitter-icon.png" /></a>
                    <a href="https://plus.google.com" ><img src="icons/GooglePlus-icon.png" /></a>
                    <a href="https://www.linkedin.com" ><img src="icons/LinkedIn-icon.png" /></a>
                </div>
                <div class="float-right">
                    <ul>
                        <li><a id="A1" runat="server" href="index.jsp">Home</a></li>
                        <li><a id="A2" runat="server" href="about.jsp">About</a></li>
                        <li><a id="A3" runat="server" href="contact.jsp">Contact</a></li>
                        <li><a id="A4" runat="server" href="userRegister.jsp">Register</a></li>
                        <li><a id="A5" runat="server" href="login.jsp">Log In</a></li>
                    </ul>
                </div>
            </div>
        </footer>

    </body>
</html>
