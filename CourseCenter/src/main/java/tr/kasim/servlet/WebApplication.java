package tr.kasim.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tr.kasim.cc.model.UserSession;

public class WebApplication {

    final static String SESSION_NAME = "ccUser";
    static WebApplication app;

    public static WebApplication getApp() {
        if (app == null) {
            app = new WebApplication();

        }
        return app;
    }

    public void removeLoginSession(HttpSession session) {
        session.removeAttribute(SESSION_NAME);
    }

    public void setLoginSession(HttpSession session, UserSession userSession) {
        session.setAttribute(SESSION_NAME, userSession);
    }

    public UserSession getLoginSession(HttpSession session) {
        return (UserSession) session.getAttribute(SESSION_NAME);
    }

    public void gotoLoginPage(HttpServletResponse response, boolean isFail, String msg) throws IOException {
        response.sendRedirect("login.jsp?fail=" + isFail + "&msg=" + msg);
    }
}
