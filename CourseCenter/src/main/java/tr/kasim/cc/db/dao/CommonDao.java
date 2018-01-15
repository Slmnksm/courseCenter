/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.db.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import tr.kasim.cc.db.Session;

/**
 *
 * @author SelmanKasim
 */
public class CommonDao<T> {

    Class genericType;

    public CommonDao() {
        genericType = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T save(Session session, T object) {
        EntityManager entityManager = session.getEntityManager();
        object = entityManager.merge(object);
        return object;
    }

    public Object saveAndFlush(Session session, Object object) {
        EntityManager entityManager = session.getEntityManager();
        object = entityManager.merge(object);
        entityManager.flush();
        return object;
    }

    public List<T> getAll(Session session) {
        EntityManager entityManager = session.getEntityManager();
        Query query = entityManager.createQuery("select a from " + genericType.getSimpleName() + " a");
        return query.getResultList();
    }
  
    public List getAll(Session session,Class type) {
        EntityManager entityManager = session.getEntityManager();
        Query query = entityManager.createQuery("select a from " + type.getSimpleName() + " a");
        return query.getResultList();
    }

    public T getById(Session session, int id) {
        EntityManager entityManager = session.getEntityManager();
        Query query = entityManager.createQuery("select a from " + genericType.getSimpleName() + " a where a.id=:id");
        query.setParameter("id", id);
        return (T) getSingleResult(query.getResultList());
    }

    public T getSingleResult(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
