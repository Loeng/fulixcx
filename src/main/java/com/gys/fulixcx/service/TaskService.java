package com.gys.fulixcx.service;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.mode.CallTaskMode;
import com.gys.fulixcx.request.TaskRequest;
import com.gys.fulixcx.util.JsonReq;

import java.util.List;
import java.util.Map;

public interface TaskService {

    EasyUiVo findPage(TaskRequest taskRequest);

    List<Map<String,Object>> findStaff(int commodityid);

    JsonReq distrTask(CallTaskMode callTaskMode, Integer integer,Integer categoryId);

    boolean releaseTask(Integer id);
}
