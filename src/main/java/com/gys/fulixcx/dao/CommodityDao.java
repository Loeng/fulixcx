package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.CommodityMode;
import com.gys.fulixcx.mode.RedMode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
public interface CommodityDao extends CrudRepository<CommodityMode, Integer> {
    CommodityMode findAllByCommodityId(String s);
    @Query(nativeQuery = true, value = "select * from t_commodity where ?1")
    List<CommodityMode> findByon(String no);
}
