/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.services;

import java.util.List;
import tr.kasim.cc.db.Session;
import tr.kasim.cc.db.dao.CourseRequestDao;
import tr.kasim.cc.filter.CourseRequestFilter;
import tr.kasim.cc.model.CourseRequest;

/**
 *
 * @author SelmanKasim
 */
public class CourseRequestService extends CommonService<CourseRequest> {

    public CourseRequestService() {
        super(new CourseRequestDao());
    }

    public List<CourseRequest> getCourseRequestByUserId(Session session, int userId) {
        return getDao().getCourseRequestByUserId(session, userId);
    }

    public List<CourseRequest> getCourseRequestByFilter(Session session, CourseRequestFilter courseRequestFilter) {
        return getDao().getCourseRequestByFilter(session, courseRequestFilter);
    }

    @Override
    public CourseRequestDao getDao() {
        return (CourseRequestDao) dao;
    }

}
