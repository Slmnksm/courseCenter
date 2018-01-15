/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.db.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import tr.kasim.cc.db.Session;
import tr.kasim.cc.model.EducationStatus;

/**
 *
 * @author SelmanKasim
 */
public class EducationStatusDao extends CommonDao<EducationStatus>{

    public EducationStatus getEducationStatusByUserId(Session session, int userId) {
        EntityManager entityManager = session.getEntityManager();
        Query query = entityManager.createQuery("SELECT e FROM EducationStatus e WHERE e.userId.id = :userId");
        query.setParameter("userId", userId);
        return (EducationStatus) getSingleResult(query.getResultList());
    }

   
    
}
