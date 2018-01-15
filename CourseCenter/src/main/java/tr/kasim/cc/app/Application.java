/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.kasim.cc.app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import tr.kasim.cc.remote.MainService;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author SelmanKasım
 */
public class Application {

    static Application app;
    EntityManagerFactory entityManagerFactory;
    AppConfig config;
    static ApplicationContext spring;
    Logger log;

    public static void main(String[] args) {
        app = new Application();
        app.init();
    }

    public static Application getApp() {
        if (app == null) {
            initSpring();
            app = (Application) spring.getBean("application");
            app.init();
        }
        return app;
    }

    private void init() {
        initSpring();
        initFileLog();
        initDb();
    }

    private void initDb() {
        entityManagerFactory = Persistence.createEntityManagerFactory("c.c.db");
        if (log.isDebugEnabled()) {
            log.debug("[Application][Db] initialized");
        }
    }

    public AppConfig getConfig() {
        return config;
    }

    public void setConfig(AppConfig config) {
        this.config = config;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    private void initFileLog() {
/*        PropertyConfigurator.configure(ClassLoader.getSystemResource("./log4j/logger.properties"));
*/
        log = Logger.getLogger(Application.class);
        if (log.isDebugEnabled()) {
            log.debug("[Application][Logger] initialized");
        }
    }

    public MainService getMainService() {
        return (MainService) spring.getBean("mainService");
    }

    public void destroy() {
        entityManagerFactory.close();
    }

    private static void initSpring() {
        spring = new ClassPathXmlApplicationContext("/spring/application.xml");
    }

    public ApplicationContext getSpring() {
        return spring;
    }

}
