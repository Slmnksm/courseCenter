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
import javax.persistence.Query;
import tr.kasim.cc.db.Session;
import tr.kasim.cc.filter.CourseFilter;
import tr.kasim.cc.model.Course;

/**
 *
 * @author SelmanKasim
 */
public class CourseDao extends CommonDao<Course> {

    public List<Course> getCoursesByUserId(Session session, Integer userId) {
        String userQuery = "";

        if (userId != null) {
            userQuery = " where c.user.id = :user ";
        }
        EntityManager entityManager = session.getEntityManager();
        Query query = entityManager.createQuery("SELECT c FROM Course c " + userQuery);
        if (userId != null) {
            query.setParameter("user", userId);
        }
        return query.getResultList();
    }

    public List<Course> getCoursesByFilter(Session session, CourseFilter courseFilter) {
        StringBuilder queryStr = new StringBuilder();
        Map<String, Object> parameters = new HashMap();

        boolean hasParameter = false;

        if (courseFilter.getName() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" lower(c.name) LIKE :pName");
            hasParameter = true;
            parameters.put("pName", "%" + courseFilter.getName().toLowerCase(Locale.ENGLISH) + "%");
        }
        if (courseFilter.getLocation() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" lower(c.user.city) LIKE :pLocation");
            hasParameter = true;
            parameters.put("pLocation", "%" + courseFilter.getLocation().toLowerCase(Locale.ENGLISH) + "%");
        }

        if (courseFilter.getPriceId() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" c.coursePrice.id = :pPriceId");
            hasParameter = true;
            parameters.put("pPriceId", courseFilter.getPriceId());
        }
        if (courseFilter.getPlaceId() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" c.coursePlace.id = :pPlaceId");
            hasParameter = true;
            parameters.put("pPlaceId", courseFilter.getPlaceId());
        }
        if (courseFilter.getServiceId() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" c.courseService.id = :pServiceId");
            hasParameter = true;
            parameters.put("pServiceId", courseFilter.getServiceId());
        }
        if (courseFilter.getTimeId() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" c.courseTime.id = :pTimeId");
            hasParameter = true;
            parameters.put("pTimeId", courseFilter.getTimeId());
        }
        if (courseFilter.getTypeId() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" c.courseType.id = :pTypeId");
            hasParameter = true;
            parameters.put("pTypeId", courseFilter.getTypeId());
        }
        if (courseFilter.getInstructorId() != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" c.courseInstructor.id = :pInstructorId");
            hasParameter = true;
            parameters.put("pInstructorId", courseFilter.getInstructorId());
        }

        EntityManager entityManager = session.getEntityManager();
        Query query = entityManager.createQuery("select c from Course c " + (hasParameter ? " where " : "") + queryStr);
        for (String param : parameters.keySet()) {
            query.setParameter(param, parameters.get(param));
        }

        return query.getResultList();
    }
}
