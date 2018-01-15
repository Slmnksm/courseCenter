/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.db.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import tr.kasim.cc.db.Session;
import tr.kasim.cc.model.InstructorInfo;
/**
 *
 * @author SelmanKasim
 */
public class InstructorInfoDao extends CommonDao<InstructorInfo> {

    public InstructorInfo getInstructorInfoByUserId(Session session, int userId) {
        EntityManager entityManager = session.getEntityManager();
        Query query = entityManager.createQuery("SELECT i FROM InstructorInfo i WHERE i.userId.id = :userId");
        query.setParameter("userId", userId);
        return (InstructorInfo) getSingleResult(query.getResultList());
    }
}
