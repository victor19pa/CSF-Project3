package com.project3.ecommerce.security;

import com.project3.ecommerce.models.Administrator;
import com.project3.ecommerce.services.implementations.AdministratorServiceImplementation;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;


public class SessionSingleton {

    private static SessionSingleton session;
    private static  Administrator administrator;

    private SessionSingleton(Administrator administrator) {
        SessionSingleton.administrator = administrator;
    }

    public static SessionSingleton createSession(Administrator administrator) {
        if (session == null) {
            session = new SessionSingleton(administrator);
            }
        return session;
    }


    public static void  destroySession(){
       if (isSessionActive())
           session = null;
    }

    public static boolean isSessionActive(){
        return session != null;
    }

    public static Administrator findSessionAccount(AdministratorServiceImplementation adminService,String email){
        Administrator administrator = adminService.getAdministrator(email);
        administrator.setJwt("null");
        return  administrator;
    }
    public static Administrator getSessionAccount(){
        return administrator;
    }
}
