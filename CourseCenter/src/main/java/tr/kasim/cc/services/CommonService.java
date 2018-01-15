/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.services;

import java.util.List;
import tr.kasim.cc.db.Session;
import tr.kasim.cc.db.dao.CommonDao;

/**
 *
 * @author SelmanKasim
 */
public class CommonService<T> {

    CommonDao<T> dao;

    public CommonService(CommonDao dao) {
        this.dao = dao;
    }

    public T save(Session session, T object) {
        return (T) dao.save(session, object);
    }

    public Object saveAndFlush(Session session, Object object, Class type) {
        return dao.saveAndFlush(session, object);
    }

    public List<T> getAll(Session session) {
        return dao.getAll(session);
    }

    public List getAll(Session session, Class type) {
        return dao.getAll(session, type);
    }

    public T getById(Session session, int id) {
        return (T) dao.getById(session, id);
    }

    public CommonDao<T> getDao() {
        return dao;
    }

}
