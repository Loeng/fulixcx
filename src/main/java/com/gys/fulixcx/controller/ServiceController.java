package com.gys.fulixcx.controller;

import com.gys.fulixcx.dao.CallCompanyPhoneDao;
import com.gys.fulixcx.dao.CallStaffDao;
import com.gys.fulixcx.dao.CallTaskDao;
import com.gys.fulixcx.dao.UserDao;
import com.gys.fulixcx.mode.CallStaffMode;
import com.gys.fulixcx.mode.CallTaskMode;
import com.gys.fulixcx.mode.SessionMode;
import com.gys.fulixcx.mode.UserMode;
import com.gys.fulixcx.util.DateUtil;
import com.gys.fulixcx.util.GysAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@GysAnnotation
@Controller
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    UserDao userDao;
    @RequestMapping(value = "/getlist",method = {RequestMethod.GET})
    public String hello(String name,String hangye,int index,Model map){
        StringBuffer spl = new StringBuffer("SELECT * FROM t_user where ");
        if (null != hangye&&!hangye.isEmpty()){
            spl.append("commodity_id = ").append(hangye).append(" ");
        }
        if (null != name&&!name.isEmpty()){
            spl.append("wechat_name like %").append(name).append("% ");
        }
        spl.append("order by id desc limit ").append((index-1)*20).append(name).append(", 20");
        List<UserMode> list = userDao.findlist(hangye,(index-1)*20);
            map.addAttribute("userlist",list);
            System.out.println(""+list.size());

        map.addAttribute("userlist",list);
        System.out.println(""+list.size());
        return "main_list";
    }
    @RequestMapping(value = "/setn",method = {RequestMethod.GET})
    public String setN(){

        return "home_test";
    }
    @RequestMapping(value = "/home",method = {RequestMethod.GET})
    public String Home(HttpServletRequest request){
        return "adminhome";
    }
    @Autowired
    CallCompanyPhoneDao callCompanyPhoneDao;
    @RequestMapping(value = "/getComPhone",method = {RequestMethod.GET})
    public String getComPhone(HttpServletRequest request,int index,int num,Model map){
        SessionMode comId = (SessionMode) request.getSession().getAttribute("sessionMode");
        List<Map<String, String>> listbycomId = callCompanyPhoneDao.findListbycomId(comId.getCommodityid(),(index-1)*num,num);
        //System.out.println(listbycomId.get(0).keySet().toString());
        /*for (Map<String,String> map1:listbycomId){
            map1.put("up_time",DateUtil.timeStamp2Date(map1.get("up_time")));
        }*/
        map.addAttribute("ComPhone",listbycomId);
        map.addAttribute("AllNum",callCompanyPhoneDao.findNumbycomId(comId.getCommodityid()));
        map.addAttribute("index",index);
        return "home";
    }
    @RequestMapping(value = "/console",method = {RequestMethod.GET})
    public String console(HttpServletRequest request,Model map){
        SessionMode comId = (SessionMode) request.getSession().getAttribute("sessionMode");
        System.out.println(DateUtil.getYesterday()+"---"+DateUtil.getMidnight());
        Map<String, String> sum0 = callCompanyPhoneDao.findSum(DateUtil.getMidnight(), new Date().getTime() + "", comId.getCommodityid());
        Map<String, String> sum1 = callCompanyPhoneDao.findSum(DateUtil.getYesterday(), DateUtil.getMidnight(), comId.getCommodityid());
        Map<String, String> sum2 = callCompanyPhoneDao.findSum(DateUtil.getWeek(), new Date().getTime() + "", comId.getCommodityid());
        Map<String, String> sum3 = callCompanyPhoneDao.findSum(DateUtil.getMonth(), new Date().getTime() + "", comId.getCommodityid());
        map.addAttribute("sum0",sum0);
        map.addAttribute("sum1",sum1);
        map.addAttribute("sum2",sum2);
        map.addAttribute("sum3",sum3);
        return "console";
    }

    @Autowired
    CallTaskDao callTaskDao;
    @RequestMapping(value = "/getTask",method = {RequestMethod.GET})
    public String getTask(HttpServletRequest request,Model map){
        SessionMode comId = (SessionMode) request.getSession().getAttribute("sessionMode");
        List<Map<String, String>> maps = callTaskDao.findserviceTask(comId.getCommodityid());
        map.addAttribute("tasks",maps);
        return "task";
    }
    @Autowired
    CallStaffDao callStaffDao;
    @RequestMapping(value = "/disTask",method = {RequestMethod.GET})
    public String disTask(HttpServletRequest request,Model map){
        SessionMode comId = (SessionMode) request.getSession().getAttribute("sessionMode");
        int num = callCompanyPhoneDao.findDistriNum(comId.getCommodityid());
        map.addAttribute("num",num);
        List<CallStaffMode> staff = callStaffDao.findByCompanyId(comId.getCommodityid());
        map.addAttribute("staffs",staff);
        return "distribution_task";
    }
    @RequestMapping(value = "/staffSetting",method = {RequestMethod.GET})
    public String staffSetting(HttpServletRequest request){
        return "staffsettion";
    }
}

