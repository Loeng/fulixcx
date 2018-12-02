package com.gys.fulixcx.controller;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gys.fulixcx.dao.CallCompanyDao;
import com.gys.fulixcx.dao.CallCompanyPhoneDao;
import com.gys.fulixcx.dao.CallPhoneDao;
import com.gys.fulixcx.mode.*;
import com.gys.fulixcx.util.GysAnnotation;
import com.gys.fulixcx.util.ImportExcelUtil;
import com.gys.fulixcx.util.UrlReqUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@GysAnnotation
@Controller
@RequestMapping("/uploadExcel/*")
public class UploadExcelControl {
    @Autowired
    CallPhoneDao callPhoneDao;
    @Autowired
    CallCompanyDao callCompanyDao;
    @Autowired
    CallCompanyPhoneDao callCompanyPhoneDao;
    /**
     * 描述：通过传统方式form表单提交方式导入excel文件
     * @param request
     * @throws Exception
     */
    @RequestMapping(value="upload.do",method={RequestMethod.GET,RequestMethod.POST})
    public  String  uploadExcel(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("通过传统方式form表单提交方式导入excel文件！");

        InputStream in =null;
        List<List<Object>> listob = null;
        MultipartFile file = multipartRequest.getFile("upfile");
        if(file.isEmpty()){
            throw new Exception("文件不存在！");
        }
        in = file.getInputStream();
        listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename());
        in.close();

        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            InfoVo vo = new InfoVo();
            vo.setCode(String.valueOf(lo.get(0)));
            vo.setName(String.valueOf(lo.get(1)));
            vo.setDate(String.valueOf(lo.get(2)));
            vo.setMoney(String.valueOf(lo.get(3)));

            System.out.println("打印信息-->机构:"+vo.getCode()+"  名称："+vo.getName()+"   时间："+vo.getDate()+"   资产："+vo.getMoney());
        }
        return "result";
    }

    /**
     * 描述：通过 jquery.form.js 插件提供的ajax方式上传文件
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value="ajaxUpload.do",method={RequestMethod.GET,RequestMethod.POST})
    public  void  ajaxUploadExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        System.out.println("通过 jquery.form.js 提供的ajax方式上传文件！");

        InputStream in =null;
        List<List<Object>> listob = null;
        MultipartFile file = multipartRequest.getFile("upfile");
        SessionMode comId = (SessionMode) request.getSession().getAttribute("sessionMode");
        if(file.isEmpty()){
            throw new Exception("文件不存在！");
        }
        in = file.getInputStream();
        listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename());

        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        List<CallPhoneMode> list = new ArrayList<CallPhoneMode>();
        List<CallCompanyPhoneMode> list1 = new ArrayList<CallCompanyPhoneMode>();
        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            CallPhoneMode vo = new CallPhoneMode();
            vo.setPhoneNumber(String.valueOf(lo.get(0)));
            vo.setPhoneName(String.valueOf(lo.get(1)));
            vo.setRemarks(String.valueOf(lo.get(2)));
            CallPhoneMode byPhoneNumber = callPhoneDao.findByPhoneNumber(vo.getPhoneNumber());
            if (byPhoneNumber == null){
                String str = UrlReqUtil.get("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel="+vo.getPhoneNumber());
                String jsonStr = str.substring(str.indexOf("{")-1,str.length());
                System.out.println(jsonStr);
                if (jsonStr.length()>50) {
                    JSONObject object = null;
                    object = new JSONObject(jsonStr);
                    vo.setAttribution(object.getString("carrier"));
                    vo.setCarrieroperator(object.getString("province"));
                }else {
                    continue;
                }
                vo.setUpDate(""+new Date().getTime());
                list.add(vo);
            }else {
                list.add(byPhoneNumber);
            }
        }

        Iterable<CallPhoneMode> callPhoneModes = callPhoneDao.saveAll(list);
        for (CallPhoneMode mode: callPhoneModes){
            CallCompanyPhoneMode phoneMode = callCompanyPhoneDao.findByCompanyIdAndPhoneId(comId.getCommodityid(),mode.getId());
            if (phoneMode == null) {
                phoneMode = new CallCompanyPhoneMode();
                phoneMode.setCompanyId(comId.getCommodityid());
                phoneMode.setPhoneId(mode.getId());
                phoneMode.setRemarks(mode.getRemarks());
                phoneMode.setPhoneName(mode.getPhoneName());
                phoneMode.setUpTime(""+new Date().getTime());
                phoneMode.setStaffId(comId.getStaffid());
                phoneMode.setDialType(0);
                phoneMode.setStar(0);
                phoneMode.setSchedule(0);
                phoneMode.setConverseTime(0);
                phoneMode.setTaskId(0);
                list1.add(phoneMode);
                //callCompanyPhoneDao.save(phoneMode);
            }else {
                //list1.add(phoneMode);
            }
        }
        callCompanyPhoneDao.saveAll(list1);
        PrintWriter out = null;
        response.setCharacterEncoding("utf-8");  //防止ajax接受到的中文信息乱码
        out = response.getWriter();
        out.print("文件导入成功！");
        out.flush();
        out.close();
    }
    private void n(List<List<Object>> listob){
        List<CallPhoneMode> list = new ArrayList<CallPhoneMode>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < listob.size(); i++) {
                        List<Object> lo = listob.get(i);
                        CallPhoneMode vo = new CallPhoneMode();
                        vo.setPhoneNumber(String.valueOf(lo.get(0)));
                        vo.setPhoneName(String.valueOf(lo.get(1)));
                        vo.setRemarks(String.valueOf(lo.get(2)));
                        CallPhoneMode byPhoneNumber = callPhoneDao.findByPhoneNumber(vo.getPhoneNumber());
                        if (byPhoneNumber == null){
                            String str = UrlReqUtil.get("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel="+vo.getPhoneNumber());
                            String jsonStr = str.substring(str.indexOf("{")-1,str.length());
                            System.out.println(jsonStr);
                            if (jsonStr.length()>50) {
                                JSONObject object = null;
                                object = new JSONObject(jsonStr);
                                vo.setAttribution(object.getString("carrier"));
                                vo.setCarrieroperator(object.getString("province"));
                            }else {
                                continue;
                            }
                            vo.setUpDate(""+new Date().getTime());
                            list.add(vo);
                        }else {
                            list.add(byPhoneNumber);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                runBack1.Back(list);
            }
        }).start();
    }
    private runBack runBack1;
    interface runBack{
        void Back(List<CallPhoneMode> list);
    }
}

