/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.db.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.persistence.EntityManager;
import tr.kasim.cc.db.Session;
import tr.kasim.cc.model.CourseRequest;
import javax.persistence.Query;
import tr.kasim.cc.filter.CourseRequestFilter;

/**
 *
 * @author SelmanKasim
 */
public class CourseRequestDao extends CommonDao<CourseRequest>{
    
    public List<CourseRequest> getCourseRequestByUserId(Session session, int userId) {
        EntityManager entityManager = session.getEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM CourseRequest c WHERE c.user.id = :user");
        query.setParameter("user", userId);
        return query.getResultList();
    }
    
    public List<CourseRequest> getCourseRequestByFilter(Session session, CourseRequestFilter courseRequestFilter) {
        StringBuilder queryStr = new StringBuilder();
        Map<String, Object> parameters = new HashMap();

        boolean hasParameter = false;

        if (courseRequestFilter.getName() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" lower(c.name) LIKE :pName");
            hasParameter = true;
            parameters.put("pName", "%" + courseRequestFilter.getName().toLowerCase(Locale.ENGLISH) + "%");
        }
        
        if (courseRequestFilter.getLocation() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" lower(c.user.city) LIKE :pLocation");
            hasParameter = true;
            parameters.put("pLocation", "%" + courseRequestFilter.getLocation().toLowerCase(Locale.ENGLISH) + "%");
        }

        if (courseRequestFilter.getPriceId() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" c.coursePrice.id = :pPriceId");
            hasParameter = true;
            parameters.put("pPriceId", courseRequestFilter.getPriceId());
        }
        if (courseRequestFilter.getPlaceId() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" c.coursePlace.id = :pPlaceId");
            hasParameter = true;
            parameters.put("pPlaceId", courseRequestFilter.getPlaceId());
        }
        if (courseRequestFilter.getServiceId() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" c.courseService.id = :pServiceId");
            hasParameter = true;
            parameters.put("pServiceId", courseRequestFilter.getServiceId());
        }
        if (courseRequestFilter.getTimeId() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" c.courseTime.id = :pTimeId");
            hasParameter = true;
            parameters.put("pTimeId", courseRequestFilter.getTimeId());
        }
        if (courseRequestFilter.getTypeId() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" c.courseType.id = :pTypeId");
            hasParameter = true;
            parameters.put("pTypeId", courseRequestFilter.getTypeId());
        }
        if (courseRequestFilter.getInstructorId() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" c.courseInstructor.id = :pInstructorId");
            hasParameter = true;
            parameters.put("pInstructorId", courseRequestFilter.getInstructorId());
        }

        EntityManager entityManager = session.getEntityManager();
        Query query = entityManager.createQuery("select c from CourseRequest c  " + (hasParameter ? " where  " : "") + queryStr);
        for (String param : parameters.keySet()) {
            query.setParameter(param, parameters.get(param));
        }

        return query.getResultList();
    }
}
