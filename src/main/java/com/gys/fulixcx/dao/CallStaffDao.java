package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.CallStaffMode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
public interface CallStaffDao extends CrudRepository<CallStaffMode, Integer> {
    @Query(nativeQuery = true, value = "SELECT C.company_name,S.* FROM call_company C,call_staff S WHERE S.staff_phone = ?1 AND S.pass_word = ?2 AND S.company_id = C.id")
    Map<String,String> login(String phone,String pass);
    List<CallStaffMode> findByCompanyId(int comId);
    CallStaffMode findByStaffPhone(String phone);
    @Query(nativeQuery = true, value = "select * from call_staff where " +
            "staff_phone = ?1 and pass_word = ?2 and staff_manage > 0")
    CallStaffMode findByLogin(String phone,String pass);

    CallStaffMode findById(int staffId);

    @Query(nativeQuery = true, value = "select id ,staff_name staffName from call_staff where company_id = ?1 and state <> 0")
    List<Map<String,Object>> findStaffList(int commodityid);
}
