package com.project3.ecommerce.controllers;

import com.project3.ecommerce.Code.CodeClass;
import com.project3.ecommerce.enums.AccountStatus;
import com.project3.ecommerce.models.Administrator;
import com.project3.ecommerce.security.Jwt;
import com.project3.ecommerce.security.SessionSingleton;
import com.project3.ecommerce.services.implementations.AdministratorServiceImplementation;
import com.project3.ecommerce.services.implementations.CodeServiceImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/user")
public class AdministratorController  {

    AdministratorServiceImplementation adminService;
     CodeServiceImpl codeServiceImpl;


    public AdministratorController(AdministratorServiceImplementation adminService,CodeServiceImpl codeServiceImpl ) {
        super();
        this.adminService = adminService;
        this.codeServiceImpl=codeServiceImpl;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerAdministrator(@ModelAttribute Administrator administrator) {
        if (isAValidateCode(Jwt.createTokenCode(administrator.getCode()))) {
            if (isAValidateObject(administrator))
                return createAccount(administrator);
            return new ModelAndView("redirect:/register");
        }
        else
            return new ModelAndView("redirect:/register");

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginAdministrator(@ModelAttribute Administrator administrator) {

        return login(administrator);

    }
    @RequestMapping(value = "/logout")
    public ModelAndView logoutAdministrator() {

        return logout();

    }

    @GetMapping("/changestatus/{email}")
    public ModelAndView changeStatus(@PathVariable(value = "email") String email) {
        Administrator oModifyAdmin=adminService.getAdministrator(email);
        oModifyAdmin.setStatus(AccountStatus.ACTIVE.name());
        adminService.saveAdministrator(oModifyAdmin);
        return new ModelAndView("redirect:/show/users");
    }

    public boolean isThisUserRegistered(Administrator administrator) {
        return adminService.findMailAdministrator(administrator.getEmail());
    }

    public boolean isThisAdministratorActive(Administrator administrator) {
        return getStatus(administrator.getEmail()).equals(AccountStatus.ACTIVE.name());
    }

    public ModelAndView login(Administrator administrator) {
        if (isAValidateObject(administrator)){
            if (isThisUserRegistered(administrator)) {

                if (isThisAdministratorActive(administrator)) {
                    if (Jwt.isThisAValidateToken(Jwt.createToken(administrator),getOldToken(administrator.getEmail()))) {
                        SessionSingleton.createSession( SessionSingleton.findSessionAccount(adminService,administrator.getEmail()));
                        return new ModelAndView("redirect:/Dashboard");
                    } else {
                        return new ModelAndView("redirect:/login");
                    }
                } else {
                    return new ModelAndView("redirect:/login");
                }

            }else{
                return new ModelAndView("redirect:/login");
            }
        }else{
            return new ModelAndView("redirect:/login");
        }


    }

    public ModelAndView createAccount(Administrator administrator) {
        if (!isThisUserRegistered(administrator)) {
            administrator.setStatus(AccountStatus.INACTIVE.name());
            administrator.setJwt(Jwt.createToken(administrator));
            adminService.saveAdministrator(administrator);
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("redirect:/register");
    }

    public String getOldToken(String email){
        return adminService.getAdministrator(email).getJwt();
    }

    public String getStatus(String email){
        return  adminService.getAdministrator(email).getStatus();
    }

    public  boolean isAValidateCode(String code) {
        return CodeClass.getCodeValue(codeServiceImpl).equals(code);
    }

    public ModelAndView logout(){
     SessionSingleton.destroySession();
        return new ModelAndView("redirect:/login");
    }

    public boolean isAValidateObject(Administrator administrator){
        return !administrator.getEmail().isEmpty() && !administrator.getJwt().isEmpty();
    }



}
