package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.CallStaffMode;
import com.gys.fulixcx.mode.CallTaskMode;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
public interface CallTaskDao extends CrudRepository<CallTaskMode, Integer> {
    @Query(nativeQuery = true, value = "select ct.*," +
            "(select count(*) from call_company_phone ctp where ctp.task_id = ct.id) AllNum," +
            "(select count(*) from call_company_phone ctp where ctp.task_id = ct.id and ctp.dial_type >0) calledNum," +
            "(select count(*) from call_company_phone ctp where ctp.task_id = ct.id and ctp.dial_type =2) callNot," +
            "(select s.staff_name from call_staff s where s.id = ct.Issuer_id) staffName"+
            " from call_task ct WHERE staff_id = ?1 AND company_id = ?2")
    List<Map<String,String>> findTask(int userId,int companyId);
    @Query(nativeQuery = true, value = "select ct.*,(select count(*) from call_company_phone ctp where ctp.task_id = ct.id) AllNum," +
            "(select count(*) from call_company_phone ctp where ctp.task_id = ct.id and ctp.dial_type >0) calledNum," +
            "(select count(*) from call_company_phone ctp where ctp.task_id = ct.id and ctp.dial_type =2) callNot," +
            "(select count(*) from call_company_phone ctp where ctp.task_id = ct.id and ctp.dial_type =3) callNull,"+
            "(select s.staff_name from call_staff s where s.id = ct.Issuer_id) issuerName," +
            "(select S.staff_name from call_staff S where S.id = ct.staff_id) staffName" +
            "  from call_task ct WHERE company_id = ?1")
    List<Map<String,String>> findserviceTask(int companyId);

    @Modifying
    @Query(nativeQuery = true, value = "update call_company_phone set task_id = 0 where task_id = ?1")
    void releaseTask(Integer id);
}
