package com.gys.fulixcx.controller;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.mode.CallStaffMode;
import com.gys.fulixcx.mode.SessionMode;
import com.gys.fulixcx.request.StaffRequest;
import com.gys.fulixcx.service.CallStaffService;
import com.gys.fulixcx.util.GysAnnotation;
import com.gys.fulixcx.util.JsonReq;
import com.gys.fulixcx.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@GysAnnotation
@RequestMapping("/staff")
public class StaffContoller {
    @Autowired
    private CallStaffService callStaffService;


    @RequestMapping("/index")
    public String index(){
        return "staff_main";
    }

    @RequestMapping("/page")
    @ResponseBody
    public EasyUiVo findPage(HttpServletRequest request,StaffRequest staffRequest){
        SessionMode sessionMode = (SessionMode) request.getSession().getAttribute("sessionMode");
        staffRequest.setCompanyId(sessionMode.getCommodityid());
        return callStaffService.findPage(staffRequest);
    }
    @PostMapping("/stopOrStart/{id}/{state}")
    @ResponseBody
    public JsonReq stopOrStart(@PathVariable Integer id, @PathVariable Integer state){
        if(callStaffService.stopOrStart(id,state)){
            return new JsonReq(200,"操作成功");
        }else {
            return new JsonReq(500,"操作失败");
        }
    }
    @PostMapping("/add")
    @ResponseBody
    public JsonReq addStaff(CallStaffMode callStaffMode, HttpServletRequest request){
        SessionMode sessionMode = (SessionMode) request.getSession().getAttribute("sessionMode");
        callStaffMode.setCompanyId(sessionMode.getCommodityid());
        callStaffMode.setCreatTime(String.valueOf(new Date().getTime()));
        callStaffMode.setState(1);
        callStaffMode.setPassWord(MD5Util.StringToMd5(callStaffMode.getPassWord()));
        callStaffService.save(callStaffMode);
        return new JsonReq(200,"操作成功");
    }
}
