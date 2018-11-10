package com.gys.fulixcx.controller;


import com.aliyuncs.dyplsapi.model.v20170525.BindAxbResponse;
import com.aliyuncs.exceptions.ClientException;
import com.gys.fulixcx.dao.CallCompanyDao;
import com.gys.fulixcx.dao.CallCompanyPhoneDao;
import com.gys.fulixcx.dao.CallStaffDao;
import com.gys.fulixcx.dao.CallTaskDao;
import com.gys.fulixcx.mode.CallCompanyMode;
import com.gys.fulixcx.mode.CallCompanyPhoneMode;
import com.gys.fulixcx.mode.CallStaffMode;
import com.gys.fulixcx.util.JsonReq;
import com.gys.fulixcx.util.SecretDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("app")
public class AppController {
    @Autowired
    CallStaffDao callStaffDao;
    @GetMapping("/login")
    public JsonReq login(String userPhone){
        Map<String, String> login = callStaffDao.login(userPhone);
        if (null!=login.get("company_name")){
            return new JsonReq(login);
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
    public JsonReq getTaskphone(int taskId){
        List<Map<String,String>> byTaskId = callCompanyPhoneDao.findTaskPhone(taskId);
        return new JsonReq(byTaskId);
    }
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
        callCompanyPhoneDao.save(byTaskId);
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
        CallCompanyMode save = callCompanyDao.save(companyMode);
        byStaffPhone = new CallStaffMode();
        byStaffPhone.setCompanyId(save.getId());
        byStaffPhone.setStaffName(manage);
        byStaffPhone.setStaffPhone(phone);
        byStaffPhone.setState(0);
        byStaffPhone.setStaffManage(2);
        byStaffPhone.setCreatTime(""+new Date().getTime());
        callStaffDao.save(byStaffPhone);
        return new JsonReq("注册成功");
    }
    /*@GetMapping("/callTial")//电话通知
    public JsonReq phone(String phone1,String phone2) {
        BindAxbResponse bindAxbResponse = null;
        try {
            bindAxbResponse = SecretDemo.bindAxb("zanwu",phone1,phone2,"FC100000042948026","17088336965");
            if (bindAxbResponse.getCode().equals("OK")){
                return new JsonReq("17088336965");
            }else{
                return new JsonReq(201,"电话绑定错误"+bindAxbResponse.getCode());
            }
        } catch (ClientException e) {
            return new JsonReq(201,"代码错误");
        }
    }*/
}
