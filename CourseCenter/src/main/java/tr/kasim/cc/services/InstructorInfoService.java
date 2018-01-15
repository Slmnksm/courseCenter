/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.services;

import tr.kasim.cc.db.Session;
import tr.kasim.cc.db.dao.InstructorInfoDao;
import tr.kasim.cc.model.InstructorInfo;



/**
 *
 * @author SelmanKasim
 */
public class InstructorInfoService extends CommonService<InstructorInfo>{
    
    public InstructorInfoService() {
        super(new InstructorInfoDao());
    }
    
    public InstructorInfo getInstructorInfoByUserId(Session session, int userId) {
        return getDao().getInstructorInfoByUserId(session, userId);
    }
    @Override
    public InstructorInfoDao getDao() {
        return (InstructorInfoDao) dao;
    }
    
}
