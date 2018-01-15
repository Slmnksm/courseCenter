/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.remote;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import tr.kasim.cc.app.Application;
import tr.kasim.cc.db.Session;
import tr.kasim.cc.filter.CourseFilter;
import tr.kasim.cc.model.Course;
import tr.kasim.cc.model.CourseRequest;
import tr.kasim.cc.filter.CourseRequestFilter;
import tr.kasim.cc.model.EducationStatus;
import tr.kasim.cc.model.InstructorInfo;
import tr.kasim.cc.model.Message;
import tr.kasim.cc.model.User;
import tr.kasim.cc.model.UserSession;
import tr.kasim.cc.services.CourseRequestService;
import tr.kasim.cc.services.EducationStatusService;
import tr.kasim.cc.services.InstructorInfoService;
import tr.kasim.cc.services.*;

/**
 *
 * @author SelmanKasim
 */
public class MainService {

    Logger log = Logger.getLogger(MainService.class);

    public List getAll(Class type) {
        String tag = "[MainService][getAll]";
        Session session = newSession();
        List result = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Start]");
            }
            result = getUserService().getAll(session, type);
            session.commit();
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Complete] " + result.size());
            }
            return result;
        } catch (Exception e) {
            log.error(tag + "[Error] msg:" + e.getMessage(), e);
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public User saveUser(User user) throws Exception {
        String tag = "[MainService][addUser]";
        Session session = newSession();
        try {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Start] " + user);
            }
            User savedUser = getUserService().saveUser(session, user);
            session.commit();
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Complete] " + savedUser);
            }
            return savedUser;
        } catch (Exception e) {
            log.error(tag + "[Error] " + user + ", msg:" + e.getMessage(), e);
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public EducationStatus saveEducationStatus(EducationStatus educationStatus) throws Exception {
        String tag = "[MainService][eduStat]";
        Session session = newSession();
        try {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Start] " + educationStatus);
            }
            EducationStatus savedEducationStatus = getUserService().saveEducationStatus(session, educationStatus);
            session.commit();
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Complete] " + savedEducationStatus);
            }
            return savedEducationStatus;
        } catch (Exception e) {
            log.error(tag + "[Error] " + educationStatus + ", msg:" + e.getMessage(), e);
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public InstructorInfo saveInstructorInfo(InstructorInfo instructorInfo) throws Exception {
        String tag = "[MainService][insInfo]";
        Session session = newSession();
        try {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Start] " + instructorInfo);
            }
            InstructorInfo savedInstructorInfo = getUserService().saveInstructorInfo(session, instructorInfo);
            session.commit();
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Complete] " + savedInstructorInfo);
            }
            return savedInstructorInfo;
        } catch (Exception e) {
            log.error(tag + "[Error] " + instructorInfo + ", msg:" + e.getMessage(), e);
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Course saveCourse(Course course) throws Exception {
        String tag = "[MainService][cour]";
        Session session = newSession();
        try {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Start] " + course);
            }
            Course savedCourse = getUserService().saveCourse(session, course);
            session.commit();
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Complete] " + savedCourse);
            }
            return savedCourse;
        } catch (Exception e) {
            log.error(tag + "[Error] " + course + ", msg:" + e.getMessage(), e);
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public CourseRequest saveCourseRequest(CourseRequest courseRequest) throws Exception {
        String tag = "[MainService][courReq]";
        Session session = newSession();
        try {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Start] " + courseRequest);
            }
            CourseRequest savedCourseRequest = getUserService().saveCourseRequest(session, courseRequest);
            session.commit();
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Complete] " + savedCourseRequest);
            }
            return savedCourseRequest;
        } catch (Exception e) {
            log.error(tag + "[Error] " + courseRequest + ", msg:" + e.getMessage(), e);
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public UserSession login(String userName, String password, String ip)
            throws Exception {
        String tag = "[MainService][login]";
        Session session = newSession();
        UserSession result = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Start] userName:" + userName + ", password:" + password);
            }
            result = getUserService().login(session, userName, password, ip);
            session.commit();
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Complete] userName:" + userName + ", password:" + password + ",result:" + result);
            }
            return result;
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Error] userName:" + userName + ", password:" + password + ", msg:" + e.getMessage(), e);
            }
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<User> getUsers()
            throws Exception {
        List<User> result;
        Session session = newSession();

        try {
            if (log.isDebugEnabled()) {
                log.debug("[MainService][getUsers][Start] ");
            }

            result = ((UserService) getService("userService")).getAll(session);

            if (log.isDebugEnabled()) {
                log.debug("[MainService][getUsers][Complete] size:" + result.size());
            }
            return result;
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("[MainService][getUsers][Error] msg:" + e.getMessage(), e);
            }
            session.rollback();
            throw e;
        } finally {
            session.close();
        }

    }

    public EducationStatus getEducationStatusByUserId(int userId) throws Exception {

        String tag = "[MainService][login]";
        Session session = newSession();
        EducationStatus result = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Start] userId:" + userId);
            }
            result = getEducationStatusService().getEducationStatusByUserId(session, userId);
            session.commit();
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Complete] userId:" + userId + ",result:" + result);
            }
            return result;
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Error] userId:" + userId + ",result:" + result + ", msg:" + e.getMessage(), e);
            }
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public InstructorInfo getInstructorInfoByUserId(int userId) throws Exception {

        String tag = "[MainService][login]";
        Session session = newSession();
        InstructorInfo result = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Start] userId:" + userId);
            }
            result = getInstructorInfoService().getInstructorInfoByUserId(session, userId);
            session.commit();
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Complete] userId:" + userId + ",result:" + result);
            }
            return result;
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Error] userId:" + userId + ",result:" + result + ", msg:" + e.getMessage(), e);
            }
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Course> getCourseByUserId(Integer userId) throws Exception {

        String tag = "[MainService][login]";
        Session session = newSession();
        List<Course> result = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Start] userId:" + userId);
            }
            result = getCourseService().getCoursesByUserId(session, userId);
            session.commit();
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Complete] userId:" + userId + ",result:" + result);
            }
            return result;
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Error] userId:" + userId + ",result:" + result + ", msg:" + e.getMessage(), e);
            }
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Course> getCourseByFilter(CourseFilter courseFilter) throws Exception {

        String tag = "[MainService][getCourseByFilter]";
        Session session = newSession();
        List<Course> result = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Start] " + courseFilter);
            }
            result = getCourseService().getCoursesByFilter(session, courseFilter);
            session.commit();
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Complete] " + courseFilter + ",result:" + result);
            }
            return result;
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Error]  " + courseFilter + ",result:" + result + ", msg:" + e.getMessage(), e);
            }
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    public List<CourseRequest> getCourseRequestByFilter(CourseRequestFilter courseRequestFilter) throws Exception {

        String tag = "[MainService][getCourseByFilter]";
        Session session = newSession();
        List<CourseRequest> result = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Start] " + courseRequestFilter);
            }
            result = getCourseRequestService().getCourseRequestByFilter(session, courseRequestFilter);
            session.commit();
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Complete] " + courseRequestFilter + ",result:" + result);
            }
            return result;
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Error]  " + courseRequestFilter + ",result:" + result + ", msg:" + e.getMessage(), e);
            }
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<CourseRequest> getCourseRequestByUserId(int userId) throws Exception {

        String tag = "[MainService][login]";
        Session session = newSession();
        List<CourseRequest> result = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Start] userId:" + userId);
            }
            result = getCourseRequestService().getCourseRequestByUserId(session, userId);
            session.commit();
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Complete] userId:" + userId + ",result:" + result);
            }
            return result;
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Error] userId:" + userId + ",result:" + result + ", msg:" + e.getMessage(), e);
            }
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Map<User,  List<Message>> getMessages(Integer srcId, Integer destId)
            throws Exception {
        Map<User,  List<Message>> result = null;
        Session session = newSession();

        try {
            if (log.isDebugEnabled()) {
                log.debug("[MainService][getById][Start] srcId:" + srcId);
            }
            result = getUserService().getMessages(session, srcId, destId);
            if (log.isDebugEnabled()) {
                log.debug("[MainService][getById][Complete] srcId:" + srcId + " ,result:" + result);
            }
            return result;
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("[MainService][getById][Error] srcId: " + srcId + " msg:" + e.getMessage(), e);
            }
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
    
    public Object getById(Integer id, Class type)
            throws Exception {
        Object result = null;
        Session session = newSession();

        try {
            if (log.isDebugEnabled()) {
                log.debug("[MainService][getById][Start] " + id);
            }
            if (type == User.class) {
                result = ((UserService) getUserService()).getById(session, id);

            } else if (type == User.class) {
                result = ((UserService) getUserService()).getById(session, id);
            }

            if (log.isDebugEnabled()) {
                log.debug("[MainService][getById][Complete] " + id + " ,result:" + result);
            }
            return result;
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("[MainService][getById][Error]  " + id + " msg:" + e.getMessage(), e);
            }
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private Session newSession() {
        return newSession(null);
    }

    private Session newSession(UserSession userSession) {
        Session session = new Session(Application.getApp().getEntityManagerFactory().createEntityManager(), userSession);
        return session;
    }

    public Object getService(String serviceName) {
        return Application.getApp().getSpring().getBean(serviceName);
    }

    private UserService getUserService() {
        return (UserService) getService("userService");
    }

    private InstructorInfoService getInstructorInfoService() {
        return (InstructorInfoService) getService("instructorInfoService");
    }

    private EducationStatusService getEducationStatusService() {
        return (EducationStatusService) getService("educationStatusService");
    }

    private CourseRequestService getCourseRequestService() {
        return (CourseRequestService) getService("courseRequestService");
    }

    private CourseService getCourseService() {
        return (CourseService) getService("courseService");
    }

    public void sendMessage(Message message) {
        String tag = "[MainService][sendMessage]";
        Session session = newSession();

        try {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Start] " + message);
            }
            getUserService().sendMessage(session, message);
            session.commit();
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Complete]  " + message);
            }

        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug(tag + "[Error]  " + message + ", msg:" + e.getMessage(), e);
            }
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

}
