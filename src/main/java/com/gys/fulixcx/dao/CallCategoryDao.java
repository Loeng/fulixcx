package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.CallCategoryMode;
import com.gys.fulixcx.mode.CallPhoneMode;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CallCategoryDao extends CrudRepository<CallCategoryMode, Integer> {
    List<CallCategoryMode> findByCompanyId(int companyId);
    CallCategoryMode findById(int id);

}
