package com.gys.fulixcx.controller;

import com.gys.fulixcx.dao.CallCompanyDao;
import com.gys.fulixcx.dao.CallStaffDao;
import com.gys.fulixcx.mode.CallCompanyMode;
import com.gys.fulixcx.mode.CallStaffMode;
import com.gys.fulixcx.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/admin",method = {RequestMethod.GET})
    public String admin(HttpServletRequest request,String username,String password){
        return "login";
    }
}
