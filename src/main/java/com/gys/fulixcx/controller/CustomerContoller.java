package com.gys.fulixcx.controller;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.mode.SessionMode;
import com.gys.fulixcx.request.CustomerRequest;
import com.gys.fulixcx.service.CallCompanyPhoneService;
import com.gys.fulixcx.util.GysAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/customer")
@GysAnnotation
public class CustomerContoller {
    @Autowired
    private CallCompanyPhoneService callCompanyPhoneService;

    @RequestMapping("/index")
    public String index(){
        return "customer_list1";
    }

    @ResponseBody
    @RequestMapping("/page")
    public EasyUiVo page(HttpServletRequest request, CustomerRequest customerRequest){
        SessionMode comId = (SessionMode) request.getSession().getAttribute("sessionMode");
        customerRequest.setCompanyId(comId.getCommodityid());
        return callCompanyPhoneService.findList(customerRequest);
    }
}
