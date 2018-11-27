package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.CallVisenionMode;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CallVisenionDao extends CrudRepository<CallVisenionMode, Integer> {
    CallVisenionMode findById(int id);
}
