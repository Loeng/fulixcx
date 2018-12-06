package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.CallCategoryMode;
import com.gys.fulixcx.mode.CallPhoneMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CallCategoryDao extends JpaRepository<CallCategoryMode, Integer>,JpaSpecificationExecutor<CallCategoryMode> {
    List<CallCategoryMode> findByCompanyId(int companyId);
    CallCategoryMode findById(int id);

    List<CallCategoryMode> findAllByCompanyId(int commodityid);
}
