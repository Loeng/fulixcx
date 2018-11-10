package com.gys.fulixcx.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.gys.fulixcx.dao.CallCompanyDao;
import com.gys.fulixcx.dao.CallCompanyPhoneDao;
import com.gys.fulixcx.dao.CallStaffDao;
import com.gys.fulixcx.dao.CallTaskDao;
import com.gys.fulixcx.mode.*;
import com.gys.fulixcx.util.GysAnnotation;
import com.gys.fulixcx.util.JsonReq;
import com.gys.fulixcx.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@GysAnnotation
@Controller
@RequestMapping("/admin/*")
public class AjaxController {
    @Autowired
    CallTaskDao callTaskDao;
    @Autowired
    CallCompanyPhoneDao callCompanyPhoneDao;
    @ResponseBody
    @RequestMapping(value = "/disTask",method={RequestMethod.GET,RequestMethod.POST})
    public JsonReq setTaskphone(HttpServletRequest request,int staff,int distr_number,String remarks,String name){
        SessionMode comId = (SessionMode) request.getSession().getAttribute("sessionMode");
        CallTaskMode mode = new CallTaskMode();
        mode.setCompanyId(comId.getCommodityid());
        mode.setIssuerId(comId.getStaffid());
        mode.setLssuerTime(""+new Date().getTime());
        mode.setRemarks(remarks);
        mode.setStaffId(staff);
        mode.setTaskName(name);
        CallTaskMode save = callTaskDao.save(mode);
        int i = callCompanyPhoneDao.setTask(save.getId(), comId.getCommodityid(), distr_number);
        System.out.println("修改条数："+i);
        return new JsonReq("分配任务成功"+i);
    }
    @Autowired
    CallCompanyDao callCompanyDao;
    @Autowired
    CallStaffDao callStaffDao;
    @ResponseBody
    @RequestMapping(value = "/login",method={RequestMethod.GET,RequestMethod.POST})
    public JsonReq login(HttpServletRequest request,String username,String password){
        System.out.println("======="+MD5Util.StringToMd5(password));
        //SendSmsRequest req = new SendSmsRequest();
        //req.setPhoneNumbers();
        CallStaffMode callStaffMode = callStaffDao.findByLogin(username, MD5Util.StringToMd5(password));
        if (callStaffMode==null){
            return new JsonReq(201,"用户名或密码错误");
        }
        CallCompanyMode byId = callCompanyDao.findByid(callStaffMode.getCompanyId());
        if (byId == null){
            return new JsonReq(201,"用户名或密码错误");
        }
        SessionMode mode = new SessionMode();
        mode.setCommodityid(byId.getId());
        mode.setStaffid(callStaffMode.getId());
        request.getSession().setAttribute("sessionMode",mode);
        return new JsonReq("登录成功");
    }
}
