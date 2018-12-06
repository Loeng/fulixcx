package com.gys.fulixcx.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.gys.fulixcx.dao.*;
import com.gys.fulixcx.mode.*;
import com.gys.fulixcx.util.GysAnnotation;
import com.gys.fulixcx.util.JsonReq;
import com.gys.fulixcx.util.MD5Util;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
        //int i = callCompanyPhoneDao.setTask(save.getId(), comId.getCommodityid(), distr_number);
        System.out.println("修改条数：");
        return new JsonReq("分配任务成功");
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
    @Autowired
    CallTaskHistoryDao callTaskHistoryDao;
    @ResponseBody
    @RequestMapping(value = "/getChart",method={RequestMethod.GET,RequestMethod.POST})
    public JsonReq getChart(HttpServletRequest request){
        try {
        SessionMode comId = (SessionMode) request.getSession().getAttribute("sessionMode");
        List<Map<String, String>> sumList = callTaskHistoryDao.findSumList(comId.getCommodityid());
        JSONArray array = new JSONArray(sumList);
        JSONObject retjson = new JSONObject();
        JSONArray days = new JSONArray();
        JSONArray total = new JSONArray();
        JSONArray effective = new JSONArray();
        JSONArray invalid = new JSONArray();
        for (int i = 0;i<array.length();i++){
            JSONObject object = array.getJSONObject(i);
            days.put(object.getString("days"));
            total.put(object.getInt("total"));
            effective.put(object.getInt("effective"));
            invalid.put(object.getInt("invalid"));
        }
            retjson.put("labels",days);
        JSONObject object1 = new JSONObject();
            object1.put("fillColor","rgba(220,220,220,0)");
            object1.put("strokeColor","rgba(0,0,205,1)");
            object1.put("pointColor","rgba(0,0,205,1)");
            object1.put("pointStrokeColor","#fff");
            object1.put("data",total);
            object1.put("label","通话总次数");
            JSONObject object2 = new JSONObject();
            object2.put("fillColor","rgba(220,220,220,0)");
            object2.put("strokeColor","rgba(0,191,255,1)");
            object2.put("pointColor","rgba(0,191,255,1)");
            object2.put("pointStrokeColor","#fff");
            object2.put("data",effective);
            object2.put("label","有效通话次数");
            JSONObject object3 = new JSONObject();
            object3.put("fillColor","rgba(220,220,220,0)");
            object3.put("strokeColor","rgba(105,105,105,1)");
            object3.put("pointColor","rgba(105,105,105,1)");
            object3.put("pointStrokeColor","#fff");
            object3.put("data",invalid);
            object3.put("label","无效通话次数");
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(object1);
            jsonArray.put(object2);
            jsonArray.put(object3);
            retjson.put("datasets",jsonArray);
            return new JsonReq(retjson.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return new JsonReq(0);
        }
    }
    @ResponseBody
    @RequestMapping(value = "/getCustomer",method={RequestMethod.GET,RequestMethod.POST})
    public JsonReq getCustomer(HttpServletRequest request,int index){
        SessionMode comId = (SessionMode) request.getSession().getAttribute("sessionMode");
        List<Map<String,String>> customerList = callCompanyPhoneDao.findCustomerList(comId.getCommodityid(), index*20);
        return new JsonReq(customerList);
    }

    @ResponseBody
    @RequestMapping("/logOut")
    public JsonReq logOut(HttpServletRequest request){
        request.getSession().invalidate();
        return new JsonReq(500,"success");
    }
}
