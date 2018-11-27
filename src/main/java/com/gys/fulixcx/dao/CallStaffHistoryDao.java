package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.CallStaffCallHistoryMode;
import com.gys.fulixcx.mode.CallStaffCallMode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CallStaffHistoryDao extends CrudRepository<CallStaffCallHistoryMode, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM call_staff_call_history WHERE staff_call_id = ?1 order by dial_time desc limit 20")
    List<CallStaffCallHistoryMode> findByStaffCallIdList(int staffcallid);
    @Query(nativeQuery = true, value = "SELECT * FROM call_staff_call_history WHERE staff_call_id = ?1 order by dial_time desc limit 1")
    CallStaffCallHistoryMode findByStaffCallId(int staffcallid);
}
