package com.gys.fulixcx.service;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.mode.CallCompanyPhone;
import com.gys.fulixcx.request.CustomerRequest;
import com.gys.fulixcx.request.ResourceRequest;
import org.springframework.data.domain.Page;

public interface CallCompanyPhoneService {
    Page<CallCompanyPhone> findPage(ResourceRequest resourceRequest);
    EasyUiVo findList(CustomerRequest customerRequest);
}
