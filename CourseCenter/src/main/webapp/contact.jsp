<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>İletişim</title>
        <link href="site.css" rel="stylesheet" type="text/css" />
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
                            <li><a id="registerLink" href="userRegister.jsp">Kaydol</a></li>
                            <li><a id="loginLink" href="login.jsp">Giriş</a></li>

                        </ul>
                    </nav>
                </div>
            </div>
        </header>
        <div id="body">

            <h2>Help</h2>
            <section class="contact">
                <header>
                    <h3>Phone:</h3>
                </header>
                <p>
                    <span class="label">Main:</span><br />
                    <span>0(545)384 62 95</span><br />
                    <span>0(543) 741 47 53</span><br />
                    <span>0(536) 692 45 45</span>
                </p>
                <p>
                    <span class="label">After Hours:</span>
                    <span>09.00 - 17.30</span>
                </p>
            </section>

            <section class="contact">
                <header>
                    <h3>Email:</h3>
                </header>
                <p>
                    <span class="label">Support:</span>
                    <span><a href="mailto:Support@example.com">Support@coursecenter.com</a></span>
                </p>
                <p>
                    <span class="label">Marketing:</span>
                    <span><a href="mailto:Marketing@example.com">Marketing@coursecenter.com</a></span>
                </p>
                <p>
                    <span class="label">General:</span>
                    <span><a href="mailto:General@example.com">General@coursecenter.com</a></span>
                </p>
            </section>

            <section class="contact">
                <header>
                    <h3>Address:</h3>
                </header>
                <p>
                    Çukurova Üniversitesi , Eğitim Fakültesi R Derslikleri , R2-304<br />
                    Sarıçam/ADANA
                </p>
            </section>

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
