<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    boolean isFail = "true".equals(request.getParameter("fail"));
    String failMsg = request.getParameter("msg");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Giriş Yap</title>
        <link rel="stylesheet" type="text/css" href="site.css"></link>
    </head>

    <body>
        <header>
            <div class="content-wrapper">
                <div class="float-left">
                    <p class="site-title">
                        <a href="">Course Center</a>
                    </p>
                </div>
                <div class="float-right">

                    <nav>
                        <ul id="menu">
                            <li><a href="index.jsp">Ana Sayfa</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </header>
        <div id="body">
            <center>
                <h1>Login Ekranina Hosgeldiniz</h1>

                <%            if (isFail) {
                %>
                <h2 style="color: red">Hata oluştu : <%=(failMsg)%></h2>
                <%
                    }
                %>
        <form method="post" action="ccService">
            <input type="hidden" name="action" value="logUser"/>
                <table>
                    <tr>
                        <td>Kullanici Adi:</td>
                        <td><input type="text" name="username"/></td>
                    </tr>
                    
                    <tr>
                        <td>Sifre:</td>
                        <td><input type="password" name="pass"/></td>
                    </tr>
                        
                    <tr>
                            <td>&nbsp;</td>
                            <td><input type="submit" value="Tamam"/></td>
                    </tr>
                </table>
                <br><br>

                    <a href="index.jsp">Ana Sayfaya Dön</a><br>
            </center>
        </form>


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
