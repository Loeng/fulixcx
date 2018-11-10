package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.CommodityMode;
import com.gys.fulixcx.mode.ShiyongUserMode;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ShiyongUserDao extends CrudRepository<ShiyongUserMode, Integer> {
    ShiyongUserMode findByWcheToken(String s);
}
