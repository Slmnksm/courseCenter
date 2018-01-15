/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tr.kasim.cc.db.Session;
import tr.kasim.cc.db.dao.UserDao;
import tr.kasim.cc.model.Course;
import tr.kasim.cc.model.CourseRequest;
import tr.kasim.cc.model.EducationStatus;
import tr.kasim.cc.model.InstructorInfo;
import tr.kasim.cc.model.Message;
import tr.kasim.cc.model.User;
import tr.kasim.cc.model.UserSession;

/**
 *
 * @author SelmanKasim
 */
public class UserService extends CommonService<User> {

    public UserService() {
        super(new UserDao());
    }

    public UserSession login(Session session, String userName, String password, String ipAddress) throws Exception {
        User user = getDao().getByUserName(session, userName);
        if (user == null) {
            throw new Exception("User not found . Username:" + userName);
        }

        if (user.getPassword() != null && user.getPassword().equals(password)) {
            UserSession userSession = new UserSession();
            userSession.setUser(user);
            userSession.setIp(ipAddress);
            userSession.setLoginTime(new Date());

            userSession = (UserSession) saveAndFlush(session, userSession, UserSession.class);
            user.setLastLoginTime(new Date());
            user.setIpAddress(ipAddress);

            saveAndFlush(session, user, User.class);
            return userSession;
        }
        throw new Exception("Login failed check username and password. Username:" + userName);
    }

    private User getByUserName(Session session, String userName) {
        return getDao().getByUserName(session, userName);
    }

    @Override
    public UserDao getDao() {
        return (UserDao) dao;
    }

    public User saveUser(Session session, User user) throws Exception {
        if (user.getId() == null) {
            User userCheck = getByUserName(session, user.getUserName());
            if (userCheck != null) {
                throw new Exception("User is already registered. Please select another username");
            }
        }
        user.setCreateTime(new Date());
        return (User) saveAndFlush(session, user, User.class);
    }

    public EducationStatus saveEducationStatus(Session session, EducationStatus educationStatus) {
        return (EducationStatus) saveAndFlush(session, educationStatus, EducationStatus.class);
    }

    public InstructorInfo saveInstructorInfo(Session session, InstructorInfo instructorInfo) {
        return (InstructorInfo) saveAndFlush(session, instructorInfo, InstructorInfo.class);
    }

    public Course saveCourse(Session session, Course course) {
        return (Course) saveAndFlush(session, course, Course.class);
    }

    public CourseRequest saveCourseRequest(Session session, CourseRequest courseRequest) {
        return (CourseRequest) saveAndFlush(session, courseRequest, CourseRequest.class);
    }

    public void sendMessage(Session session, Message message) {
        saveAndFlush(session, message, Message.class);
    }

    public Map<User, List<Message>> getMessages(Session session, Integer srcId, Integer destId) {
        List<Message> messages = getDao().getMessages(session, srcId, destId);
        Map<User, List<Message>> messagesMapByDestUser = new HashMap();
        for (Message message : messages) {
            User keyUser = srcId == null ? message.getSourceUser() : message.getDestUser();
            List<Message> messageList = messagesMapByDestUser.get(keyUser);
            if (messageList == null) {
                messageList = new ArrayList();
                messagesMapByDestUser.put(keyUser, messageList);
            }
            messageList.add(message);
        }
        return messagesMapByDestUser;
    }

}
