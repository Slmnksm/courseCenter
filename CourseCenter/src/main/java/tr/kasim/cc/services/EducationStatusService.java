/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.services;

import tr.kasim.cc.db.Session;
import tr.kasim.cc.db.dao.CommonDao;
import tr.kasim.cc.db.dao.EducationStatusDao;
import tr.kasim.cc.model.EducationStatus;

/**
 *
 * @author SelmanKasim
 */
public class EducationStatusService extends CommonService<EducationStatus>{
    
     public EducationStatusService() {
        super(new EducationStatusDao());
    }
     
     public EducationStatus getEducationStatusByUserId(Session session, int userId) {
        return getDao().getEducationStatusByUserId(session, userId);
    }
    @Override
    public EducationStatusDao getDao() {
        return (EducationStatusDao) dao;
    }
    
}
