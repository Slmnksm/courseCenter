/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tr.kasim.cc.app.Application;
import tr.kasim.cc.model.Course;
import tr.kasim.cc.model.CourseInstructor;
import tr.kasim.cc.model.CoursePlace;
import tr.kasim.cc.model.CoursePrice;
import tr.kasim.cc.model.CourseRequest;
import tr.kasim.cc.model.CourseService;
import tr.kasim.cc.model.CourseTime;
import tr.kasim.cc.model.CourseType;
import tr.kasim.cc.model.EducationStatus;
import tr.kasim.cc.model.Gender;
import tr.kasim.cc.model.Role;
import tr.kasim.cc.model.User;
import tr.kasim.cc.model.InstructorInfo;
import tr.kasim.cc.model.Message;
import tr.kasim.cc.model.UserSession;
import tr.kasim.cc.remote.MainService;
import tr.kasim.cc.util.GeneralUtil;

/**
 *
 * @author SelmanKasim
 */
@WebServlet(urlPatterns = {"/ccService"})
public class CCServlet extends HttpServlet {

    org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = new PrintWriter(resp.getOutputStream());

            String action = req.getParameter("action");

            if ("logUser".equals(action)) {
                login(req, resp);
            } else if ("addUser".equals(action)) {
                addUser(req, resp);
            } else if ("logout".equals(action)) {
                logout(req, resp);
            } else if ("eduStat".equals(action)) {
                eduStat(req, resp);
            } else if ("insInfo".equals(action)) {
                setInstructorInfo(req, resp);
            } else if ("cour".equals(action)) {
                setCourse(req, resp);
            } else if ("courReq".equals(action)) {
                setCourseRequest(req, resp);
            } else if ("sendMessage".equals(action)) {
                sendMessage(req, resp);
            }

        } catch (Exception ex) {
            log.error(ex, ex);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String userName = req.getParameter("username");
        String password = req.getParameter("pass");
        String ip = req.getHeader("x-forwarded-for");
        if (ip == null) {
            ip = req.getRemoteAddr();
        }
        try {
            UserSession userSession = Application.getApp().getMainService().login(userName, password, ip);

            WebApplication.getApp().setLoginSession(req.getSession(true), userSession);
            resp.sendRedirect("main.jsp");
        } catch (Exception ex) {
            log.error(ex, ex);
            WebApplication.getApp().gotoLoginPage(resp, true, ex.getMessage());
        }
    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, Exception {

        User user = new User();
        user.setUserName(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setFirstName(req.getParameter("firstName"));
        user.setSurname(req.getParameter("surname"));
        user.setEmail(req.getParameter("email"));
        user.setCity(req.getParameter("city"));
        user.setTelephone(req.getParameter("phone"));
        user.setGender(new Gender(GeneralUtil.toInt(req.getParameter("genderId"))));
        user.setRole(new Role(GeneralUtil.toInt(req.getParameter("roleTypeId"))));
        user.setIpAddress(req.getRemoteAddr());

        try {
            Application.getApp().getMainService().saveUser(user);
            UserSession userSession = Application.getApp().getMainService().login(user.getUserName(), user.getPassword(), user.getIpAddress());
            WebApplication.getApp().setLoginSession(req.getSession(), userSession);
            resp.sendRedirect("main.jsp");
        } catch (Exception ex) {
            log.error(ex, ex);
            resp.sendRedirect("userRegister.jsp?fail=true&msg=" + ex.getMessage());
        }

    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        WebApplication.getApp().removeLoginSession(req.getSession());
        WebApplication.getApp().gotoLoginPage(resp, false, "");
    }

    private void eduStat(HttpServletRequest req, HttpServletResponse resp) throws IOException, Exception {

        UserSession userSession = WebApplication.getApp().getLoginSession(req.getSession());
        EducationStatus educationStatus = Application.getApp().getMainService().getEducationStatusByUserId(userSession.getUser().getId());
        if (educationStatus == null) {
            educationStatus = new EducationStatus();
            educationStatus.setUserId(userSession.getUser());
        }
        educationStatus.setHighSchoolName(req.getParameter("highSchoolName"));
        educationStatus.setUniversityName(req.getParameter("universityName"));
        educationStatus.setExtra(req.getParameter("extra"));

        try {
            Application.getApp().getMainService().saveEducationStatus(educationStatus);

            resp.sendRedirect("main.jsp?page=teacher/education_status.jsp&type=success&msg=Basari ile kaydedildi");
        } catch (Exception ex) {
            log.error(ex, ex);
            resp.sendRedirect("main.jsp?page=teacher/education_status.jsp&type=fail=true&msg=" + ex.getMessage());
        }

    }

    private void setInstructorInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException, Exception {

        UserSession userSession = WebApplication.getApp().getLoginSession(req.getSession());
        InstructorInfo instructorInfo = Application.getApp().getMainService().getInstructorInfoByUserId(userSession.getUser().getId());
        if (instructorInfo == null) {
            instructorInfo = new InstructorInfo();
            instructorInfo.setUserId(userSession.getUser());
        }
        instructorInfo.setTitle(req.getParameter("title"));
        instructorInfo.setExperience(req.getParameter("exp"));
        instructorInfo.setInfo(req.getParameter("info"));
        instructorInfo.setReference(req.getParameter("reference"));

        try {
            Application.getApp().getMainService().saveInstructorInfo(instructorInfo);
            resp.sendRedirect("main.jsp?page=teacher/instructor_info.jsp&type=success&msg=Basari ile kaydedildi");
        } catch (Exception ex) {
            log.error(ex, ex);
            resp.sendRedirect("main.jsp?page=teacher/instructor_info.jsp&type=fail=true&msg=" + ex.getMessage());
        }

    }

    private void setCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Course course = new Course();
        course.setName(req.getParameter("name"));
        course.setCoursePlace(new CoursePlace(GeneralUtil.toInt(req.getParameter("place"))));
        course.setCoursePrice(new CoursePrice(GeneralUtil.toInt(req.getParameter("price"))));
        course.setCourseService(new CourseService(GeneralUtil.toInt(req.getParameter("service"))));
        course.setCourseTime(new CourseTime(GeneralUtil.toInt(req.getParameter("time"))));
        course.setCourseType(new CourseType(GeneralUtil.toInt(req.getParameter("type"))));
        course.setCourseInstructor(new CourseInstructor(GeneralUtil.toInt(req.getParameter("inst"))));

        UserSession userSession = WebApplication.getApp().getLoginSession(req.getSession());
        course.setUser(userSession.getUser());

        try {
            Application.getApp().getMainService().saveCourse(course);

            resp.sendRedirect("main.jsp?page=teacher/list_course.jsp");
        } catch (Exception ex) {
            log.error(ex, ex);
            resp.sendRedirect("course.jsp?fail=true&msg=" + ex.getMessage());
        }

    }

    private void setCourseRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        CourseRequest courseReq = new CourseRequest();
        courseReq.setName(req.getParameter("name"));
        courseReq.setMaxPrice(req.getParameter("maxPrice"));
        courseReq.setExtra(req.getParameter("extra"));
        courseReq.setCoursePlace(new CoursePlace(GeneralUtil.toInt(req.getParameter("place"))));
        courseReq.setCoursePrice(new CoursePrice(GeneralUtil.toInt(req.getParameter("price"))));
        courseReq.setCourseService(new CourseService(GeneralUtil.toInt(req.getParameter("service"))));
        courseReq.setCourseTime(new CourseTime(GeneralUtil.toInt(req.getParameter("time"))));
        courseReq.setCourseType(new CourseType(GeneralUtil.toInt(req.getParameter("type"))));
        courseReq.setCourseInstructor(new CourseInstructor(GeneralUtil.toInt(req.getParameter("inst"))));

        UserSession userSession = WebApplication.getApp().getLoginSession(req.getSession());
        courseReq.setUser(userSession.getUser());

        try {
            Application.getApp().getMainService().saveCourseRequest(courseReq);

            resp.sendRedirect("main.jsp?page=student/list_course_requests.jsp");
        } catch (Exception ex) {
            log.error(ex, ex);
            resp.sendRedirect("create_course_request.jsp?fail=true&msg=" + ex.getMessage());
        }
    }


    private void sendMessage(HttpServletRequest req, HttpServletResponse resp) {
        Message message = new Message();
        message.setMessageText(req.getParameter("msg"));
        message.setSourceUser(new User(GeneralUtil.toInt(req.getParameter("srcId"))));
        message.setDestUser(new User(GeneralUtil.toInt(req.getParameter("destId"))));
        message.setIsView(false);
        message.setSendTime(new Date());
        message.setViewTime(null);

        try {
            Application.getApp().getMainService().sendMessage(message);
            JsonObject result = Json.createObjectBuilder()
                    .add("status", true)
                    .add("time",new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(message.getSendTime()))
                    .add("msg", "Basari ile eklendi").build();
            PrintStream ps = new PrintStream(resp.getOutputStream());
            ps.print(result);
        } catch (Exception ex) {
            log.error(ex, ex);
        }

    }

}
