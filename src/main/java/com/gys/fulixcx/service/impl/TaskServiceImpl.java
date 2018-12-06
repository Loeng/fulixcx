package com.gys.fulixcx.service.impl;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.dao.CallCompanyPhoneDao;
import com.gys.fulixcx.dao.CallStaffDao;
import com.gys.fulixcx.dao.CallTaskDao;
import com.gys.fulixcx.dao.CallTaskRepositoryImpl;
import com.gys.fulixcx.mode.CallTaskMode;
import com.gys.fulixcx.request.TaskRequest;
import com.gys.fulixcx.service.TaskService;
import com.gys.fulixcx.util.JsonReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private CallTaskRepositoryImpl callTaskRepositoryImpl;

    @Autowired
    private CallTaskDao callTaskDao;

    @Autowired
    private CallCompanyPhoneDao callCompanyPhoneDao;

    @Autowired
    private CallStaffDao callStaffDao;
    @Override
    public EasyUiVo findPage(TaskRequest taskRequest) {
        return callTaskRepositoryImpl.findList(taskRequest);
    }

    @Override
    public List<Map<String, Object>> findStaff(int commodityid) {
        return callStaffDao.findStaffList(commodityid);
    }

    @Override
    @Transactional
    public JsonReq distrTask(CallTaskMode callTaskMode, Integer distr_number,Integer categoryId) {
        int total = callCompanyPhoneDao.countAllByTaskIdAndCompanyIdAndCategoryId(0, callTaskMode.getCompanyId(),categoryId);
        if(total < distr_number){
            return new JsonReq(500,"分配失败，最大可分配"+total+"条数据");
        }
        CallTaskMode save = callTaskDao.save(callTaskMode);
        callCompanyPhoneDao.setTask(save.getId(), save.getCompanyId(), distr_number,categoryId);
        return new JsonReq(200,"分配成功");
    }

    @Override
    @Transactional
    public boolean releaseTask(Integer id) {
        try {
          callTaskDao.releaseTask(id);
          callTaskDao.deleteById(id);
          return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
