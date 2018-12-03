package com.gys.fulixcx.controller;


import com.aliyuncs.dyplsapi.model.v20170525.BindAxbResponse;
import com.aliyuncs.dyplsapi.model.v20170525.UnbindSubscriptionResponse;
import com.aliyuncs.exceptions.ClientException;
import com.gys.fulixcx.dao.*;
import com.gys.fulixcx.mode.*;
import com.gys.fulixcx.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("app")
public class AppController {
    @Autowired
    CallVisenionDao callVisenionDao;
    @GetMapping("/getVsion")
    public JsonReq getVsion(){
        CallVisenionMode byId = callVisenionDao.findById(1);
        return new JsonReq(byId);
    }
    @Autowired
    CallStaffDao callStaffDao;
    @GetMapping("/login")
    public JsonReq login(String userPhone,String pass){
        Map<String, String> login = callStaffDao.login(userPhone,MD5Util.StringToMd5(pass));
        if (null!=login.get("company_name")){
            String state = String.valueOf(login.get("state"));
            if (!state.equals("0")) {
                long endTime = Long.parseLong(login.get("end_time"));
                if (endTime<new Date().getTime()){
                    return new JsonReq(201,"账户已过期");
                }else {
                    long day = (endTime - new Date().getTime())/(24*60*60*1000);
                    Map<String,String> map = new HashMap<>(login);
                    map.put("surplus",day+"");
                    return new JsonReq(map);
                }

            }else {
                return new JsonReq(201,"账户已被冻结");
            }
        }else {
            return new JsonReq(201,"未注册用户");
        }
    }
    @Autowired
    CallTaskDao callTaskDao;
    @GetMapping("/getTask")
    public JsonReq getTask(int userId,int companyId){
        List<Map<String,String>> byStaffIdAndCompanyId = callTaskDao.findTask(userId, companyId);
        return new JsonReq(byStaffIdAndCompanyId);
    }
    @Autowired
    CallCompanyPhoneDao callCompanyPhoneDao;
    @GetMapping("/getTaskPhone")
    public JsonReq getTaskphone(int taskId,int index){
        List<Map<String,String>> byTaskId = callCompanyPhoneDao.findTaskPhone(taskId,index*20);
        return new JsonReq(byTaskId);
    }
    @Autowired
    CallTaskHistoryDao callTaskHistoryDao;
    @GetMapping("/setTaskPhone")
    public JsonReq setTaskphone(int id,int converse,String dialTime){
        CallCompanyPhoneMode byTaskId = callCompanyPhoneDao.findById(id);
        byTaskId.setConverseTime(converse);
        byTaskId.setDialTime(dialTime);
        byTaskId.setDialTime(""+new Date().getTime());
        if (converse>0){
            byTaskId.setDialType(1);
        }else {
            byTaskId.setDialType(2);
        }
        CallCompanyPhoneMode save = callCompanyPhoneDao.save(byTaskId);
        CallTaskCallHistoryMode historyMode = new CallTaskCallHistoryMode();
        historyMode.setTaskPhoneId(save.getId());
        historyMode.setConverseTime(converse);
        historyMode.setDialTime(dialTime);
        historyMode.setRemarks(save.getRemarks());
        historyMode.setSchedule(save.getSchedule());
        historyMode.setStar(save.getStar());
        callTaskHistoryDao.save(historyMode);
        return new JsonReq("上传成功");
    }
    @Autowired
    CallCompanyDao callCompanyDao;
    @GetMapping("/registerCom")
    public JsonReq registerCom(String ComName,String corporation,String manage,String phone){
        CallStaffMode byStaffPhone = callStaffDao.findByStaffPhone(phone);
        if (byStaffPhone != null){
            return new JsonReq(201,"账号已注册");
        }
        CallCompanyMode companyMode = new CallCompanyMode();
        companyMode.setCompanyName(ComName);
        companyMode.setCompanyCorporation(corporation);
        companyMode.setCompanyManage(manage);
        companyMode.setCreatTime(new Date().getTime()+"");
        companyMode.setEndTime((new Date().getTime()+3*24*60*60*1000)+"");
        companyMode.setServiceType("1");
        CallCompanyMode save = callCompanyDao.save(companyMode);
        byStaffPhone = new CallStaffMode();
        byStaffPhone.setCompanyId(save.getId());
        byStaffPhone.setStaffName(manage);
        byStaffPhone.setStaffPhone(phone);
        byStaffPhone.setPassWord(MD5Util.StringToMd5(phone.substring(phone.length()-4,phone.length())));
        byStaffPhone.setState(0);
        byStaffPhone.setStaffManage(2);
        byStaffPhone.setCreatTime(""+new Date().getTime());
        callStaffDao.save(byStaffPhone);
        return new JsonReq("注册成功");
    }
    @GetMapping("/setSign")
    public JsonReq setSign(int id,int star,int schedule,String phone_name,String customerLog,String remarks){
        CallCompanyPhoneMode cpmode = callCompanyPhoneDao.findById(id);
        if (cpmode !=null){
            cpmode.setStar(star);
            cpmode.setSchedule(schedule);
            if (phone_name!=null) {
                cpmode.setPhoneName(phone_name);
            }
            if (remarks!=null) {
                cpmode.setRemarks(remarks);
            }
            CallCompanyPhoneMode save = callCompanyPhoneDao.save(cpmode);
            CallTaskCallHistoryMode byStaffCallId = callTaskHistoryDao.findByStaffCallId(save.getId());
            if (byStaffCallId==null){
                byStaffCallId = new CallTaskCallHistoryMode();
                byStaffCallId.setTaskPhoneId(save.getId());
                byStaffCallId.setConverseTime(save.getConverseTime());
                byStaffCallId.setDialTime(save.getDialTime());
                if (customerLog!=null&&!customerLog.isEmpty()) {
                    byStaffCallId.setRemarks(customerLog);
                }
                byStaffCallId.setSchedule(schedule);
                byStaffCallId.setStar(star);
                callTaskHistoryDao.save(byStaffCallId);
            }else {
                byStaffCallId.setRemarks(customerLog);
                byStaffCallId.setSchedule(schedule);
                byStaffCallId.setStar(star);
                callTaskHistoryDao.save(byStaffCallId);
            }
        }else {
            return new JsonReq(201,"数据错误");
        }
        return new JsonReq("上传成功");
    }
    @GetMapping("/getTaskCallHistory")
    public JsonReq getTaskCallHistory(int taskphoneid){
        List<CallTaskCallHistoryMode> byStaffId = callTaskHistoryDao.findByStaffCallIdList(taskphoneid);
        if (byStaffId!=null) {
            return new JsonReq(byStaffId);
        }else {
            return new JsonReq(201,"获取数据失败");
        }
    }
    @GetMapping("/getRankingList")
    public JsonReq getRankingList(int staffId,int type){
        CallStaffMode byId = callStaffDao.findById(staffId);
        if (byId==null){
            return new JsonReq(201,"用户不存在");
        }else {
            String startTime = new Date().getTime()+"";
            String endTime = new Date().getTime()+"";
            switch (type){
                case 0:
                    startTime = DateUtil.getMidnight();
                    break;
                case 1:
                    startTime = DateUtil.getYesterday();
                    endTime = DateUtil.getMidnight();
                    break;
                case 2:
                    startTime = (Long.parseLong(DateUtil.getYesterday())-24*60*60*1000)+"";
                    endTime = DateUtil.getYesterday();
                    break;
                case 3:
                    startTime = DateUtil.getWeek();
                    break;
                case 4:
                    startTime = DateUtil.getMonth();
                    break;
            }
            List<Map<String, String>> tongjiByCom = callTaskHistoryDao.findTongjiByCom(byId.getCompanyId(), startTime, endTime);
            return new JsonReq(tongjiByCom);
        }
    }
    @GetMapping("/getCustomer")
    public JsonReq getCustomer(int staffid){
      List<Map<String, String>> taskPhone = callCompanyPhoneDao.findCustomer(staffid);
        return new JsonReq(taskPhone);
    }
    @GetMapping("/getCustomerScreen")
    public JsonReq getCustomerScreen(int staffid,int Schedule,int Star,String Text){
        System.out.println(staffid+"    "+Schedule+"    "+Star+"    "+Text);
        List<Map<String, String>> taskPhone;
        if (Schedule == 0&&Star == 0&&Text.isEmpty()){
            taskPhone = callCompanyPhoneDao.findCustomer(staffid);
        }else if (Schedule == 0&&Star == 0){
            taskPhone = callCompanyPhoneDao.findCustomerScreen(staffid,Text);
        }else if(Schedule == 0){
            taskPhone = callCompanyPhoneDao.findCustomerScreen(staffid,Star,Text);
        }else if(Star == 0){
            taskPhone = callCompanyPhoneDao.findCustomerScreen(staffid,Schedule+"",Text);
        }else {
            taskPhone = callCompanyPhoneDao.findCustomerScreen(staffid,Schedule+"",Star+"",Text);
        }

        return new JsonReq(taskPhone);
    }
    @Autowired
    CallStaffCallDao callStaffCallDao;
    @Autowired
    CallStaffHistoryDao callStaffHistoryDao;
    @GetMapping("/getStaffCall")
    public JsonReq getStaffCall(int staffid){
        List<CallStaffCallMode> byStaffId = callStaffCallDao.findByStaffId(staffid);
        if (byStaffId!=null) {
            return new JsonReq(byStaffId);
        }else {
            return new JsonReq(201,"获取数据失败");
        }
    }
    @GetMapping("/setStaffCall")
    public JsonReq setStaffCall(String id,String staffid,String phoneNumber,String dialTime,int converseTime){
        if (id!=null){
            CallStaffCallMode mode = callStaffCallDao.findById(Integer.parseInt(id));
            mode.setDialTime(dialTime);
            mode.setConverseTime(converseTime);
            CallStaffCallMode save = callStaffCallDao.save(mode);
            CallStaffCallHistoryMode byStaffCallId =new CallStaffCallHistoryMode();
            //= callStaffHistoryDao.findByStaffCallId(byStaffIdAndPhoneNumber.getId());
            byStaffCallId.setConverseTime(converseTime);
            byStaffCallId.setDialTime(dialTime);
            byStaffCallId.setRemarks(save.getRemarks());
            byStaffCallId.setSchedule(save.getSchedule());
            byStaffCallId.setStaffCallId(save.getId());
            byStaffCallId.setStar(save.getStar());
            callStaffHistoryDao.save(byStaffCallId);
            if (save != null) {
                return new JsonReq(save);
            } else {
                return new JsonReq(201, "上传失败");
            }
        }else {
            CallStaffCallMode byStaffIdAndPhoneNumber = callStaffCallDao.findByStaffIdAndPhoneNumber(Integer.parseInt(staffid), phoneNumber);
            if (byStaffIdAndPhoneNumber == null){
                CallStaffCallMode mode = new CallStaffCallMode();
                String str = UrlReqUtil.get("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=" + phoneNumber);
                System.out.println(str);
                String jsonStr = str.substring(str.indexOf("{") - 1, str.length());
                System.out.println(jsonStr);
                if (jsonStr.length() > 50) {
                    JSONObject object = null;
                    try {
                        object = new JSONObject(jsonStr);
                        mode.setAttribution(object.getString("carrier"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    mode.setAttribution("");
                }
                mode.setStaffId(Integer.parseInt(staffid));
                mode.setSchedule(0);
                mode.setStar(0);
                mode.setRemarks("");
                mode.setPhoneNumber(phoneNumber);
                mode.setConverseTime(converseTime);
                mode.setDialTime(dialTime);
                CallStaffCallMode save = callStaffCallDao.save(mode);
                CallStaffCallHistoryMode byStaffCallId =new CallStaffCallHistoryMode();
                //= callStaffHistoryDao.findByStaffCallId(byStaffIdAndPhoneNumber.getId());
                byStaffCallId.setConverseTime(converseTime);
                byStaffCallId.setDialTime(dialTime);
                byStaffCallId.setRemarks(save.getRemarks());
                byStaffCallId.setSchedule(save.getSchedule());
                byStaffCallId.setStaffCallId(save.getId());
                byStaffCallId.setStar(save.getStar());
                callStaffHistoryDao.save(byStaffCallId);
                List<CallStaffCallMode> byStaffId = callStaffCallDao.findByStaffId(Integer.parseInt(staffid));
                if (byStaffId != null) {
                    return new JsonReq(byStaffId);
                } else {
                    return new JsonReq(201, "上传失败");
                }
            }else {
                byStaffIdAndPhoneNumber.setConverseTime(converseTime);
                byStaffIdAndPhoneNumber.setDialTime(dialTime);
                callStaffCallDao.save(byStaffIdAndPhoneNumber);
                CallStaffCallHistoryMode byStaffCallId =new CallStaffCallHistoryMode();
                //= callStaffHistoryDao.findByStaffCallId(byStaffIdAndPhoneNumber.getId());
                byStaffCallId.setConverseTime(converseTime);
                byStaffCallId.setDialTime(dialTime);
                byStaffCallId.setRemarks(byStaffIdAndPhoneNumber.getRemarks());
                byStaffCallId.setSchedule(byStaffIdAndPhoneNumber.getSchedule());
                byStaffCallId.setStaffCallId(byStaffIdAndPhoneNumber.getId());
                byStaffCallId.setStar(byStaffIdAndPhoneNumber.getStar());
                callStaffHistoryDao.save(byStaffCallId);
                List<CallStaffCallMode> byStaffId = callStaffCallDao.findByStaffId(Integer.parseInt(staffid));
                if (byStaffId != null) {
                    return new JsonReq(byStaffId);
                } else {
                    return new JsonReq(201, "上传失败");
                }
            }
        }
    }
    @GetMapping("/updateStaffCall")
    public JsonReq updateStaffCall(int id,int star,int schedule,String customerLog,String name,String remarks){
        if (id>0){
            CallStaffCallMode mode = callStaffCallDao.findById(id);
            mode.setStar(star);
            mode.setSchedule(schedule);
            if (name!=null) {
                mode.setName(name);
            }
            if (remarks!=null) {
                mode.setRemarks(remarks);
            }
            CallStaffCallMode save = callStaffCallDao.save(mode);
            CallStaffCallHistoryMode byStaffCallId = callStaffHistoryDao.findByStaffCallId(save.getId());
            if (customerLog!=null&&!customerLog.isEmpty()) {
                byStaffCallId.setRemarks(customerLog);
            }
            byStaffCallId.setSchedule(save.getSchedule());
            byStaffCallId.setStar(save.getStar());
            callStaffHistoryDao.save(byStaffCallId);
            return new JsonReq(save);
        }else {
            return new JsonReq(201,"上传失败");
        }
    }
    @GetMapping("/getStaffCallHistory")
    public JsonReq getStaffCallHistory(int staffcallid){
        List<CallStaffCallHistoryMode> byStaffId = callStaffHistoryDao.findByStaffCallIdList(staffcallid);
        if (byStaffId!=null) {
            return new JsonReq(byStaffId);
        }else {
            return new JsonReq(201,"获取数据失败");
        }
    }
    @GetMapping("/addStaff")
    public JsonReq addStaff(int staffid,String staffName,String staffPhone,String staffPass,int manage){
        CallStaffMode staffMode = callStaffDao.findById(staffid);
        if (staffMode!=null&&staffMode.getStaffManage()==2) {
            CallStaffMode phone = callStaffDao.findByStaffPhone(staffPhone);
            if (phone!=null){
                return new JsonReq(201,"该员工信息已存在");
            }
            CallStaffMode mode = new CallStaffMode();
            mode.setCompanyId(staffMode.getCompanyId());
            mode.setCreatTime(new Date().getTime()+"");
            mode.setStaffPhone(staffPhone);
            mode.setPassWord(MD5Util.StringToMd5(staffPass));
            mode.setState(1);
            mode.setStaffName(staffName);
            mode.setStaffManage(manage);
            callStaffDao.save(mode);
            return new JsonReq("添加成功");
        }else if (staffMode.getStaffManage()!=2){
            return new JsonReq(201,"级别不足");
        }else {
            return new JsonReq(201,"获取数据失败");
        }
    }
    @GetMapping("/getStaff")
    public JsonReq getStaff(int staffid){
        CallStaffMode staffMode = callStaffDao.findById(staffid);
        if (staffMode!=null&&staffMode.getStaffManage()==2) {
            List<CallStaffMode> staffList = callStaffDao.findByCompanyId(staffMode.getCompanyId());
            return new JsonReq(staffList);
        }else if (staffMode.getStaffManage()!=2){
            return new JsonReq(201,"级别不足");
        }else {
            return new JsonReq(201,"获取数据失败");
        }
    }
    @GetMapping("/updateStaff")
    public JsonReq updateStaff(int staffid,int updateStaffId,int state,String pass){
        CallStaffMode staffMode = callStaffDao.findById(staffid);
        if (staffMode!=null&&staffMode.getStaffManage()==2) {
            CallStaffMode mode = callStaffDao.findById(updateStaffId);
            mode.setState(state);
            if (pass!=null&&!pass.isEmpty()) {
                mode.setPassWord(MD5Util.StringToMd5(pass));
            }
            CallStaffMode save = callStaffDao.save(mode);
            return new JsonReq(save);
        }else if (staffMode.getStaffManage()!=2){
            return new JsonReq(201,"级别不足");
        }else {
            return new JsonReq(201,"获取数据失败");
        }
    }
    //@Autowired
    //CallStaffDao callStaffDao;
    @GetMapping("/updatePass")
    public JsonReq updatePass(int staffid,String pass,String newPass){
        CallStaffMode byId = callStaffDao.findById(staffid);
        if (byId!=null&&byId.getPassWord().equals(MD5Util.StringToMd5(pass))){
            byId.setPassWord(MD5Util.StringToMd5(newPass));
            callStaffDao.save(byId);
            return new JsonReq("修改成功");
        }else {
            return new JsonReq(201,"原密码错误");
        }
    }
    @GetMapping("/getTongji")
    public JsonReq getTongji(int staffId){
        Map<String,String> map1 = callTaskHistoryDao.findTongji(staffId,DateUtil.getMidnight(),new Date().getTime()+"");
        Map<String,String> map2 = callTaskHistoryDao.findTongji(staffId,DateUtil.getYesterday(),DateUtil.getMidnight());
        Map<String,String> map3 = callTaskHistoryDao.findTongji(staffId,(Long.parseLong(DateUtil.getYesterday())-24*60*60*1000)+"",DateUtil.getYesterday());
        Map<String,String> map4 = callTaskHistoryDao.findTongji(staffId,DateUtil.getWeek(),new Date().getTime()+"");
        Map<String,String> map5 = callTaskHistoryDao.findTongji(staffId,(Long.parseLong(DateUtil.getWeek())-7*24*60*60*1000)+"",DateUtil.getWeek());
        Map<String,String> map6 = callTaskHistoryDao.findTongji(staffId,DateUtil.getMonth(),new Date().getTime()+"");
        Map<String,String> map7 = callTaskHistoryDao.findTongji(staffId,(Long.parseLong(DateUtil.getMonth())-30*24*60*60*1000)+"",DateUtil.getMonth());
        List<Map<String,String>> maps = new ArrayList<Map<String, String>>();
        maps.add(map1);
        maps.add(map2);
        maps.add(map3);
        maps.add(map4);
        maps.add(map5);
        maps.add(map6);
        maps.add(map7);
        return new JsonReq(maps);
    }
    @Autowired
    CallRemindDao callRemindDao;
    @GetMapping("/getRemind")
    public JsonReq getRemind(int staffId){
        List<CallRemindMode> reqs = new ArrayList<>();
        List<CallRemindMode> list = callRemindDao.findRemindNow(
                staffId,
                DateUtil.timeStamp2Date(DateUtil.getMidnight()),
                DateUtil.timeStamp2Date(Long.parseLong(DateUtil.getMidnight())+24*60*60*1000+""));
        List<CallRemindMode> list2 = callRemindDao.findRemind(
                staffId,
                DateUtil.timeStamp2Date(DateUtil.getYesterday()),
                DateUtil.timeStamp2Date(DateUtil.getMidnight()),
                DateUtil.dataTostr(new Date(Long.parseLong(DateUtil.getMidnight())+24*60*60*1000)));
        reqs.addAll(list);
        reqs.addAll(list2);
        return new JsonReq(reqs);
    }
    @GetMapping("/addRemind")
    public JsonReq addRemind(int staffId,String time,String data){
        CallRemindMode mode = new CallRemindMode();
        mode.setStaffId(staffId);
        mode.setRemindDate(time);
        mode.setBody(data);
        callRemindDao.save(mode);
        return new JsonReq("保存成功");
    }
    @GetMapping("/callTial")//电话通知
    public JsonReq phone(String phone1,String phone2) {
        BindAxbResponse bindAxbResponse = null;
        try {
            bindAxbResponse = SecretDemo.bindAxb("zanwu",phone1,phone2,"FC100000042948026","17088336965");
            if (bindAxbResponse.getCode().equals("OK")){
                return new JsonReq("17088336965");//"17088336965"
            }else{
                return new JsonReq(201,"电话绑定错误"+bindAxbResponse.getCode());
            }
        } catch (ClientException e) {
            return new JsonReq(201,"代码错误");
        }
    }
    @ResponseBody
    @RequestMapping(value="/MsnNotif",method=RequestMethod.POST)
    public Msn ajaxRequest(@RequestBody List<MsnMode> mode){//
        System.out.println("通话通知‘’‘’‘’‘：[" );
        for (MsnMode m : mode) {
            //System.out.println(m.toString());
            try {
                UnbindSubscriptionResponse unbind = SecretDemo.unbind(m.getSub_id() + "", "17088336965");
                System.out.println(unbind.getMessage());
            } catch (ClientException e) {
                e.printStackTrace();
            }
            //msnDao.save(m);
        }
        System.out.println("]’‘’‘’‘’" );
        return new Msn(0,"接收成功");
    }

}
