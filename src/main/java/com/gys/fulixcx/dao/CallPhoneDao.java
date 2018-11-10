package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.CallPhoneMode;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CallPhoneDao extends CrudRepository<CallPhoneMode, Integer> {
    CallPhoneMode findByPhoneNumber(String number);
}
