package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.CallDialRecordMode;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CallDialRecordDao extends CrudRepository<CallDialRecordMode, Integer> {
}
