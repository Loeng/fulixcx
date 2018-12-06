package com.gys.fulixcx.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;

public class JsonReq {
    public int code;
    public String msg;
    public String time;
    public Object data;
    public JsonReq(){}
    public JsonReq(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = new HashMap<>();
        this.time = DateUtil.dataTostr(new Date());
    }
    public JsonReq(int code, String msg, Object o) {
        this.code = code;
        this.msg = msg;
        this.data = o;
        this.time = DateUtil.dataTostr(new Date());
    }

    public JsonReq(Object data) {
        this.code = 200;
        this.msg = "请求成功";
        this.data = data;
        this.time = DateUtil.dataTostr(new Date());
    }
    public JsonReq(JSONArray data) {
        this.code = 200;
        this.msg = "请求成功";
        this.data = data;
        this.time = DateUtil.dataTostr(new Date());
    }
    public JsonReq(JSONObject data) {
        this.code = 200;
        this.msg = "请求成功";
        this.data = data;
        this.time = DateUtil.dataTostr(new Date());
    }
    public JsonReq(Object data, String msg) {
        this.code = 200;
        this.msg = msg;
        this.data = data;
        this.time = DateUtil.dataTostr(new Date());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
