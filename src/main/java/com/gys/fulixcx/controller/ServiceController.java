package com.gys.fulixcx.controller;

import com.gys.fulixcx.dao.*;
import com.gys.fulixcx.mode.*;
import com.gys.fulixcx.util.DateUtil;
import com.gys.fulixcx.util.GysAnnotation;
import com.gys.fulixcx.util.MD5Util;
import org.json.JSONObject;
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
    @RequestMapping(value = "/customer",method = {RequestMethod.GET})
    public String customer(){
        return "customer_list";
    }
    @RequestMapping(value = "/home",method = {RequestMethod.GET})
    public String Home(HttpServletRequest request){
        return "adminhome";
    }
    @Autowired
    CallTaskHistoryDao callTaskHistoryDao;
    @Autowired
    CallCompanyPhoneDao callCompanyPhoneDao;
    @RequestMapping(value = "/getComPhone",method = {RequestMethod.GET})
    public String getComPhone(HttpServletRequest request,int index,int num,Model map){
        SessionMode comId = (SessionMode) request.getSession().getAttribute("sessionMode");
        List<Map<String, String>> listbycomId = null;
        listbycomId = callCompanyPhoneDao.findListbycomId(comId.getCommodityid(),(index-1)*num,num);
        List<Map<String, String>> mapList = new ArrayList<>();
        for (Map<String,String> map1:listbycomId){
            //System.out.println(DateUtil.timeStamp2Date(map1.get("up_time")));
            Map<String,String> d = new HashMap<String, String>();
            for (String key:map1.keySet()){
                d.put(key,map1.get(key));
            }
            d.put("upTime",DateUtil.timeStamp2Date(map1.get("up_time")));
            mapList.add(d);
        }
        map.addAttribute("ComPhone",mapList);
        map.addAttribute("AllNum",callCompanyPhoneDao.findNumbycomId(comId.getCommodityid()));
        map.addAttribute("index",index);
        return "home";
    }
    @RequestMapping(value = "/console",method = {RequestMethod.GET})
    public String console(HttpServletRequest request,Model map){
        SessionMode comId = (SessionMode) request.getSession().getAttribute("sessionMode");
        Map<String, String> sum0 = callTaskHistoryDao.findSum(comId.getCommodityid(),DateUtil.getMidnight(), new Date().getTime() + "");
        Map<String, String> sum1 = callTaskHistoryDao.findSum(comId.getCommodityid(),DateUtil.getYesterday(), DateUtil.getMidnight());
        Map<String, String> sum2 = callTaskHistoryDao.findSum(comId.getCommodityid(),DateUtil.getWeek(), new Date().getTime() + "");
        Map<String, String> sum3 = callTaskHistoryDao.findSum(comId.getCommodityid(),DateUtil.getMonth(), new Date().getTime() + "");
        //List<Map<String, String>> sumList = callTaskHistoryDao.findSumList(comId.getCommodityid());
        map.addAttribute("sum0",sum0);
        map.addAttribute("sum1",sum1);
        map.addAttribute("sum2",sum2);
        map.addAttribute("sum3",sum3);
        return "console";
    }

    @Autowired
    CallTaskDao callTaskDao;
    @RequestMapping(value = "/getStaff",method = {RequestMethod.GET})
    public String getStaff(HttpServletRequest request,Model map){
        SessionMode comId = (SessionMode) request.getSession().getAttribute("sessionMode");
        List<CallStaffMode> byCompanyId = callStaffDao.findByCompanyId(comId.getCommodityid());
        for (CallStaffMode mode:byCompanyId){
            mode.setCreatTime(DateUtil.dataTostr(new Date(Long.valueOf(mode.getCreatTime()))));
        }
        map.addAttribute("staffs",byCompanyId);
        return "staff_setting";
    }
    @RequestMapping(value = "/getTask",method = {RequestMethod.GET})
    public String getTask(HttpServletRequest request,Model map){
        SessionMode comId = (SessionMode) request.getSession().getAttribute("sessionMode");
        List<Map<String, String>> maps = callTaskDao.findserviceTask(comId.getCommodityid());
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        for (Map<String,String> map1:maps) {
            Map<String, String> d = new HashMap<String, String>();
            for (String key : map1.keySet()) {
                d.put(key, map1.get(key));
            }
            d.put("upTime", DateUtil.timeStamp2Date(map1.get("lssuer_time")));
            mapList.add(d);
        }
        map.addAttribute("tasks",mapList);
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
    @Autowired
    CallCompanyDao callCompanyDao;
    @RequestMapping(value = "/addStaff",method = {RequestMethod.GET})
    public String addStaff(HttpServletRequest request,Model map){
        CallCompanyMode sessionMode = callCompanyDao.findByid(((SessionMode) request.getSession().getAttribute("sessionMode")).getCommodityid());
        map.addAttribute("comName",sessionMode.getCompanyName());
        /*CallStaffMode mode = new CallStaffMode();
        mode.setCompanyId(((SessionMode) request.getSession().getAttribute("sessionMode")).getCommodityid());
        mode.setCreatTime(""+new Date().getTime());
        mode.setStaffManage(0);
        mode.setState(1);
        mode.setStaffPhone("");
        mode.setStaffName("");
        mode.setPassWord(MD5Util.StringToMd5(""));*/
        return "add_staff";
    }
}

