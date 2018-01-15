/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.services;

import java.util.List;
import tr.kasim.cc.db.Session;
import tr.kasim.cc.db.dao.CourseDao;
import tr.kasim.cc.filter.CourseFilter;
import tr.kasim.cc.model.Course;


/**
 *
 * @author SelmanKasim
 */
public class CourseService extends CommonService<Course>{
    
    public CourseService() {
        super(new CourseDao());
    }
    
    public List<Course> getCoursesByUserId(Session session, Integer userId) {
        return getDao().getCoursesByUserId(session, userId);
    }
    
    @Override
    public CourseDao getDao() {
        return (CourseDao) dao;
    }

    public List<Course> getCoursesByFilter(Session session, CourseFilter courseFilter) {
        return getDao().getCoursesByFilter(session, courseFilter);
    }
    
}
