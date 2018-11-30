package com.gys.fulixcx.controller;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.dao.*;
import com.gys.fulixcx.mode.*;
import com.gys.fulixcx.request.ResourceRequest;
import com.gys.fulixcx.service.CallCompanyPhoneService;
import com.gys.fulixcx.util.DateUtil;
import com.gys.fulixcx.util.GysAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping(value = "/customer")
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

    @Autowired
    private CallCompanyPhoneService callCompanyPhoneService;
    @RequestMapping(value = "/getComPhone",method = {RequestMethod.GET})
    public String getComPhone(HttpServletRequest request, ResourceRequest resourceRequest,  Model map){
        /*SessionMode comId = (SessionMode) request.getSession().getAttribute("sessionMode");
        resourceRequest.setCompanyId(comId.getCommodityid());
        Page<CallCompanyPhone> page = callCompanyPhoneService.findPage(resourceRequest);
        int start = (resourceRequest.getPageNo()-1)*resourceRequest.getPageSize();
//        List<Map<String, String>> listbycomId = callCompanyPhoneDao.findListbycomId(comId.getCommodityid(),start,resourceRequest.getPageSize());
        List<CallCompanyPhone> phoneList = page.getContent();
        for(CallCompanyPhone callCompanyPhone:phoneList){
            callCompanyPhone.setUpTime(DateUtil.timeStamp2Date(callCompanyPhone.getUpTime()));
        }
        map.addAttribute("phoneList",phoneList);
        map.addAttribute("totalNum",page.getTotalElements());
        map.addAttribute("resourceRequest",resourceRequest);*/
        return "home1";
    }
    @RequestMapping("/getComPhone1")
    @ResponseBody
    public EasyUiVo getComPhone1(HttpServletRequest request, ResourceRequest resourceRequest, Model map) {
        SessionMode comId = (SessionMode) request.getSession().getAttribute("sessionMode");
        resourceRequest.setCompanyId(comId.getCommodityid());
        Page<CallCompanyPhone> page = callCompanyPhoneService.findPage(resourceRequest);
        EasyUiVo easyUiVo = new EasyUiVo();
        easyUiVo.setTotal(page.getTotalElements());
        easyUiVo.setRows(page.getContent());
        return easyUiVo;
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

