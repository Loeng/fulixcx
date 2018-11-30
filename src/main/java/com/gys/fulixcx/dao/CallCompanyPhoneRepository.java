package com.gys.fulixcx.dao;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.mode.CallCompanyPhone;
import com.gys.fulixcx.request.CustomerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CallCompanyPhoneRepository extends JpaRepository<CallCompanyPhone,Integer>,JpaSpecificationExecutor<CallCompanyPhone> {
    EasyUiVo findList(CustomerRequest customerRequest);
}
