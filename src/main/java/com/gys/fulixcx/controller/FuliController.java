package com.gys.fulixcx.controller;

import com.aliyuncs.dyplsapi.model.v20170525.BindAxbResponse;
import com.aliyuncs.exceptions.ClientException;
import com.gys.fulixcx.dao.*;
import com.gys.fulixcx.mode.*;
import com.gys.fulixcx.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@RestController
@RequestMapping(value = "xcx")
public class FuliController {
    @Autowired
    UserDao userDao;
    @Autowired
    RedDao redDao;
    @Autowired
    CommodityDao commodityDao;
    @Autowired
    ShiyongUserDao shiyongUserDao;
    @Autowired
    MsnDao msnDao;
    @GetMapping("/test")
    public JsonReq insert() throws JSONException {
        /*if (Context.ATOKEN.isEmpty()||new Date().getTime()-Context.atime>7100000) {
            String atstr = UrlReqUtil.get("https://api.weixin.qq.com/cgi-bin/token?appid=wxddca5849212241ab&secret=f7972b50bc68249bddd5a1f5935d3929&grant_type=client_credential");
            JSONObject object = new JSONObject(atstr);
            Context.atime = new Date().getTime();
            Context.ATOKEN = object.getString("access_token");
        }
        String str = SendTemplateUtil.SendTemplates("oN8oJ48msv9CcnPA7Fb5Dc-jPVGo","1539241070997",Context.ATOKEN);*/
        List<CommodityMode> byon = commodityDao.findByon("commodity_id = X002");
        return new JsonReq(byon);

    }
    @GetMapping("/payWiter")//提现
    public synchronized JsonReq payWiter(String openid){
        UserMode userMode = userDao.findByWechatToken(openid);
        if (userMode==null){
            return new JsonReq(201,"用户不存在");
        }
        try {

        Map<String, String> map = new HashMap<>();
        map.put("mch_appid", "wxddca5849212241ab");//应用ID
        map.put("mchid", "1510739681");//商户号
        map.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串
        map.put("desc", "yidao福利");//商品描述
        map.put("partner_trade_no", "fltx"+ new Date().getTime());//商户订单号
            if (userMode.getWaiteMoney()>9){
                return new JsonReq(201,"超额提现");
            }
            double m = 0.00;
            if (userMode.getMoney()+userMode.getWaiteMoney()>10){
                m = 10-userMode.getWaiteMoney();
                map.put("amount", (int)(m*100)+"");//总金额
            }else {
                m = userMode.getMoney();
                map.put("amount", (int) (userMode.getMoney() * 100) + "");//总金额
            }
        System.out.println("提现金额为："+map.get("amount"));
        map.put("spbill_create_ip", "117.176.201.62");//终端IP
        map.put("openid", userMode.getWechatToken());
        map.put("check_name","NO_CHECK");
        map.put("sign", WXPayUtil.generateSignature(map,"358ecc6e4d872a99aca5baf63c1302ba"));
            userMode.setMoney(userMode.getMoney()-m);
            userDao.save(userMode);
            String result = CommonUtil.httpsRequest2("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers", "POST", WXPayUtil.mapToXml(map));
            Map<String, String> str = WXPayUtil.xmlToMap(result);
            if (str.get("return_code").equals("SUCCESS")&&str.get("result_code").equals("SUCCESS")){
                userMode.setWaiteMoney(userMode.getWaiteMoney()+m);
                userDao.save(userMode);

                return new JsonReq("提现成功");
            }else {
                userMode.setMoney(userMode.getMoney()+m);
                userDao.save(userMode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonReq(202,"失败");
    }
    @GetMapping("/shiyonglogs")//登录
    public JsonReq logs(String logs,String SuperiorWxToken,String comm) {
        System.out.println(logs);
        String str = UrlReqUtil.get("https://api.weixin.qq.com/sns/jscode2session?appid=wx1631acf87bf9eeda&secret=c96cf87850692ba06639d862424447c1&js_code="+ logs +"&grant_type=authorization_code");
        System.out.println("进入试用#*****"+str+"*****#");
        try {
            JSONObject object = new JSONObject(str);
            Map<String,String> map = new HashMap<String, String>();
            map.put("openid",object.getString("openid"));
            map.put("session_key",object.getString("session_key"));
            System.out.println("福利id#*****"+SuperiorWxToken+"*****#");
            if (SuperiorWxToken!=null&&!SuperiorWxToken.equals("null")&&!"".equals(SuperiorWxToken)){
            UserMode mode = userDao.findByWechatToken(SuperiorWxToken);
            if (mode != null) {
                    mode.setShiyongToken(object.getString("openid"));
                    userDao.save(mode);
                CommodityMode commodityMode = commodityDao.findAllByCommodityId(mode.getCommodityId());
                if (commodityMode!=null) {
                    map.put("comm", commodityMode.getCommodityImg());
                }else {
                    map.put("comm","http://www.mchomes.cn/X001.jpg");
                }
            }else {
                map.put("comm","http://www.mchomes.cn/X001.jpg");
            }
            }else {
                UserMode usermode = userDao.findByShiyongToken(object.getString("openid"));
                if (usermode!=null) {
                    CommodityMode commodityMode = commodityDao.findAllByCommodityId(usermode.getCommodityId());
                    if (commodityMode != null) {
                        map.put("comm", commodityMode.getCommodityImg());
                    } else {
                        map.put("comm", "http://www.mchomes.cn/X001.jpg");
                    }
                }else {
                    ShiyongUserMode shiyong = shiyongUserDao.findByWcheToken(object.getString("openid"));
                    if (shiyong == null){
                        shiyong = new ShiyongUserMode();
                        shiyong.setWcheToken(object.getString("openid"));
                        shiyong.setCommodityId((comm!=null&&!comm.equals("null")&&!comm.isEmpty())?comm:"http://www.mchomes.cn/X001.jpg");
                        shiyong.setCreatTime(new Date().getTime()+"");
                        shiyongUserDao.save(shiyong);
                        map.put("comm", shiyong.getCommodityId());
                    }else {
                        map.put("comm", shiyong.getCommodityId());
                    }
                }
            }
            return new JsonReq(map);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JsonReq(201,"500");
        }
    }
    @GetMapping("/logs")//登录
    public JsonReq shiyonglogs(String logs,String SuperiorWxToken,String CommodityId) {
        String str = UrlReqUtil.get("https://api.weixin.qq.com/sns/jscode2session?appid=wxddca5849212241ab&secret=f7972b50bc68249bddd5a1f5935d3929&js_code="+ logs +"&grant_type=authorization_code");
        try {
            SuperiorWxToken=(SuperiorWxToken==null||"null".equals(SuperiorWxToken)||"undefined".equals(SuperiorWxToken))?"oN8oJ48msv9CcnPA7Fb5Dc-jPVGo":SuperiorWxToken;
            CommodityId=(CommodityId==null||"null".equals(CommodityId)||"undefined".equals(CommodityId))?"X002":CommodityId;
            JSONObject object = new JSONObject(str);
            Map<String,String> map = new HashMap<String, String>();
            map.put("openid",object.getString("openid"));
            System.out.println("#**"+object.getString("openid")+"进入福利");
            map.put("session_key",object.getString("session_key"));
            UserMode mode = userDao.findByWechatToken(object.getString("openid"));
            if (mode == null) {
                mode = new UserMode();
                mode.setWechatToken(object.getString("openid"));
                mode.setSuperiorWxToken(SuperiorWxToken);
                mode.setCommodityId(CommodityId);
                mode.setMoney(0.00);
                mode.setWaiteMoney(0.00);
                userDao.save(mode);
            }
            return new JsonReq(map);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JsonReq(201,"500");
        }
    }
    @GetMapping("/getMode")//获取用户信息
    public JsonReq getMode(String openid) {
        System.out.println("---------------微信openid="+openid);
            UserMode mode = userDao.findByWechatToken(openid);
            if (mode == null) {
                return new JsonReq(201,"未找到用户信息");
            }else {
                return new JsonReq(mode);
            }
    }
    @GetMapping("/getRed")//开启红包
    public JsonReq getRed(String suopenid,String openid,String fromid) {
        System.out.println("---------------微信openid="+openid);
        RedMode mode = redDao.findByUserWxToken(openid);
        if (mode == null) {
            mode = new RedMode();
            mode.setCreatTime(new Date().getTime()+"");
            mode.setUserWxToken(openid);
            mode.setSuperiorWxToken("undefined".equals(suopenid)?"123456":suopenid);
            mode.setMoney(0.3);
            mode.setSuperiorMoney(0.08);
            mode = redDao.save(mode);
            UserMode me = userDao.findByWechatToken(openid);
            me.setMoney(me.getMoney()+mode.getMoney());
            me.setRedPacket(""+mode.getId());
            userDao.save(me);
            UserMode su = userDao.findByWechatToken(suopenid);
            if (su != null) {
                su.setMoney(su.getMoney() + mode.getSuperiorMoney());
                userDao.save(su);
            }
            if (Context.ATOKEN.isEmpty()||new Date().getTime()-Context.atime>7100000) {
                String atstr = UrlReqUtil.get("https://api.weixin.qq.com/cgi-bin/token?appid=wxddca5849212241ab&secret=f7972b50bc68249bddd5a1f5935d3929&grant_type=client_credential");
                JSONObject object = null;
                try {
                    object = new JSONObject(atstr);
                    Context.atime = new Date().getTime();
                    Context.ATOKEN = object.getString("access_token");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            String sendstr = SendTemplateUtil.SendTemplates(openid,fromid,Context.ATOKEN);
            System.out.println("服务通知返回："+sendstr);
            return new JsonReq(200,"领取成功");
        }else {
            return new JsonReq(201,"重复领取");
        }
    }
    @GetMapping("/getRedList")
    public JsonReq getRedList(String suopenid) throws JSONException {
        List<Map<String,Object>> list = redDao.findVoteList(suopenid);
        if (list==null){
         list = new ArrayList<>();
        }
        JSONArray array = new JSONArray(list);
        for (int i = 0;i<array.length();i++){
            JSONObject object = array.getJSONObject(i);
            String t = DateUtil.timeStamp2Date(object.getString("creat_time"));
            array.getJSONObject(i).remove("creat_time");
            array.getJSONObject(i).put("creat_time",t);

        }

        return new JsonReq(array.toString());
    }
    @GetMapping("/setUsserInfo")//上传头像及名称
    public JsonReq setUsserInfo(String openid,String usericon,String nickname) {
        UserMode userMode = userDao.findByWechatToken(openid);
        if (userMode!=null){
            userMode.setWechatIcon(usericon);
            userMode.setWechatName(nickname);
            userDao.save(userMode);
            return new JsonReq("操作成功");
        }
        return new JsonReq(201,"操作失败");
    }
    @GetMapping("/Phoneto")//电话通知
    public JsonReq phone(String phone1,String phone2) {
        BindAxbResponse bindAxbResponse = null;
        try {
            bindAxbResponse = SecretDemo.bindAxb("zanwu",phone1,phone2,"FC100000042948026","17088336965");
            if (bindAxbResponse.getCode().equals("OK")){
                MsnMode msnMode = new MsnMode();
                //msnMode.setId(Integer.parseInt(bindAxbResponse.getRequestId()));
                msnMode.setPartner_key("FC100000042948026");
                msnMode.setPhone_no(phone1);
                msnMode.setSecret_no("17088336965");
                msnMode.setPeer_no(phone2);
                msnDao.save(msnMode);
                return new JsonReq("17088336965");
            }else{
                return new JsonReq(201,"电话绑定错误"+bindAxbResponse.getCode());
            }
        } catch (ClientException e) {
            return new JsonReq(201,"代码错误");
        }

    }
    @ResponseBody
    @RequestMapping(value="/MsnNotif",method=RequestMethod.POST)
    public Msn ajaxRequest(@RequestBody List<MsnMode> mode){
        System.out.println("通话通知‘’‘’‘’‘：[" );
        for (MsnMode m : mode) {
            System.out.println(m.toString());
            msnDao.save(m);
        }
        System.out.println("]’‘’‘’‘’" );
        return new Msn(0,"接收成功");
    }
}
