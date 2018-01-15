/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import tr.kasim.cc.model.UserSession;

/**
 *
 * @author SelmanKasim
 */
public class Session {

    UserSession userSession;
    EntityManager entityManager;
    EntityTransaction transaction;

    public Session(EntityManager entityManager, UserSession userSession) {
        this.entityManager = entityManager;
        transaction = entityManager.getTransaction();
        transaction.begin();
        this.userSession = userSession;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void commit() {
        transaction.commit();
        if (!transaction.isActive()) {
            transaction.begin();
        }
    }

    public void rollback() {
        if (transaction.isActive()) {
            transaction.rollback();
        }
        if (!transaction.isActive()) {
            transaction.begin();
        }
    }

    public void close() {
        if (transaction.isActive()) {
            transaction.commit();
        }
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

}
