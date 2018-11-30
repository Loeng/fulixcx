package com.gys.fulixcx.service;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.mode.CallStaffMode;
import com.gys.fulixcx.request.StaffRequest;

public interface CallStaffService {

    EasyUiVo findPage(StaffRequest staffRequest);

    boolean stopOrStart(Integer id,Integer state);

    void save(CallStaffMode callStaffMode);
}
