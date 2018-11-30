package com.gys.fulixcx.controller;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.mode.CallTaskMode;
import com.gys.fulixcx.mode.SessionMode;
import com.gys.fulixcx.request.TaskRequest;
import com.gys.fulixcx.service.TaskService;
import com.gys.fulixcx.util.GysAnnotation;
import com.gys.fulixcx.util.JsonReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/task")
@GysAnnotation
public class TaskController {

    @Autowired
    private TaskService taskService;
    @RequestMapping("/index")
    public String index(){
        return "task_main";
    }

    @RequestMapping("/page")
    @ResponseBody
    public EasyUiVo findPage(TaskRequest taskRequest){
        taskRequest.setCompanyId(1);
        return taskService.findPage(taskRequest);
    }
    @RequestMapping("/getStaff")
    @ResponseBody
    public List<Map<String,Object>> getStaff(HttpServletRequest request){
        SessionMode sessionMode = (SessionMode) request.getSession().getAttribute("sessionMode");
        return taskService.findStaff(sessionMode.getCommodityid());
    }

    @PostMapping("/add")
    @ResponseBody
    public JsonReq addTask(CallTaskMode callTaskMode,HttpServletRequest request){
        SessionMode sessionMode = (SessionMode) request.getSession().getAttribute("sessionMode");
        callTaskMode.setCompanyId(sessionMode.getCommodityid());
        callTaskMode.setIssuerId(sessionMode.getStaffid());
        callTaskMode.setLssuerTime(String.valueOf(new Date().getTime()));
        String asignNum = request.getParameter("asignNum");
        return taskService.distrTask(callTaskMode,Integer.valueOf(asignNum));
    }

    @PostMapping("/release/{id}")
    @ResponseBody
    public JsonReq releasTask(@PathVariable Integer id){
       if(taskService.releaseTask(id)){
           return new JsonReq(200,"操作成功");
       }
        return new JsonReq(500,"操作失败");
    }
}
