package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.CallRemindMode;
import com.gys.fulixcx.mode.MsnMode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CallRemindDao extends CrudRepository<CallRemindMode, Integer> {
    @Query(nativeQuery = true,value = "select * from call_remind where staff_id = ?1 and remind_date " +
            "> ?2 and remind_date < ?3")
    List<CallRemindMode> findRemindNow(int staffId,String start,String stop);
    @Query(nativeQuery = true,value = "select * from call_remind where staff_id = ?1 and ((remind_date " +
            "> ?2 and remind_date < ?3) or remind_date > ?4)")
    List<CallRemindMode> findRemind(int staffId,String start,String stop,String a);
}
