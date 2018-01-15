/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.db.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import tr.kasim.cc.db.Session;
import tr.kasim.cc.model.Message;
import tr.kasim.cc.model.User;

/**
 *
 * @author SelmanKasim
 */
public class UserDao extends CommonDao<User> {

    public User getByUserName(Session session, String userName) {
        EntityManager entityManager = session.getEntityManager();
        Query query = entityManager.createQuery("select a from User a where a.userName =:userName ");
        query.setParameter("userName", userName);
        return (User) getSingleResult(query.getResultList());
    }

    public List<Message> getMessages(Session session, Integer srcId, Integer destId) {
        EntityManager entityManager = session.getEntityManager();
        StringBuilder queryStr = new StringBuilder();

        boolean hasParameter = false;
        
        if (destId != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
             queryStr.append(" a.destUser.id = :destUser");
             hasParameter = true;
        }
         if (srcId != null) {
            if (hasParameter) {
                queryStr.append(" and ");
            }
            queryStr.append(" a.sourceUser.id = :srcUser");
            hasParameter = true;
        }
        Query query = entityManager.createQuery("select a from Message a " + (hasParameter ? "where " : "") + queryStr + " order by a.sendTime");

        if (destId != null) {
            query.setParameter("destUser", destId);
        }
        if (srcId != null) {
            query.setParameter("srcUser", srcId);
        }
        return query.getResultList();
    }

}
