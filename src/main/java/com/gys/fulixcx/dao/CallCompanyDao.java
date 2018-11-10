package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.CallCompanyMode;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CallCompanyDao extends CrudRepository<CallCompanyMode, Integer> {
    CallCompanyMode findByid(int id);
}
