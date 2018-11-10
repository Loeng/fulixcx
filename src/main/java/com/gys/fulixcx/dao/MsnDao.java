package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.MsnMode;
import com.gys.fulixcx.mode.RedMode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
public interface MsnDao extends CrudRepository<MsnMode, Integer> {
    MsnMode findById(int id);

}
