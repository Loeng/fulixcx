package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.CallStaffCallMode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CallStaffCallDao extends CrudRepository<CallStaffCallMode, Integer> {
    @Query(nativeQuery = true,value = "select * from call_staff_call where staff_id = ?1 order by dial_time desc limit 20")
    List<CallStaffCallMode> findByStaffId(int staffid);
    CallStaffCallMode findById(int id);
    CallStaffCallMode findByStaffIdAndPhoneNumber(int staffid,String phone);
}
