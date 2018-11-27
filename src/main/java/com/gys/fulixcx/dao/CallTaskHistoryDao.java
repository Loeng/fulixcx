package com.gys.fulixcx.dao;

import com.gys.fulixcx.mode.CallStaffCallHistoryMode;
import com.gys.fulixcx.mode.CallTaskCallHistoryMode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;

public interface CallTaskHistoryDao extends CrudRepository<CallTaskCallHistoryMode, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM call_task_call_history WHERE task_phone_id = ?1 order by dial_time desc limit 20")
    List<CallTaskCallHistoryMode> findByStaffCallIdList(int staffcallid);
    @Query(nativeQuery = true, value = "SELECT * FROM call_task_call_history WHERE task_phone_id = ?1 order by dial_time desc limit 1")
    CallTaskCallHistoryMode findByStaffCallId(int staffcallid);
    @Query(nativeQuery = true, value = "SELECT COUNT(id) total " +
            ",ifnull(sum(case when converse_time>0 then 1 ELSE 0 end),0) effective" +
            ",ifnull(sum(case when converse_time<1 then 1 ELSE 0 end),0) invalid" +
            ",ifnull(SUM(converse_time),0) totaltime" +
            ",ifnull(SUM(converse_time)/ ifnull(sum(case when converse_time>0 then 1 ELSE 0 end),1),0) averagetime" +
            " FROM (SELECT h.* FROM call_company_phone p,call_task_call_history h WHERE" +
            " p.id = h.task_phone_id and p.company_id = ?1 AND h.dial_time > ?2 and h.dial_time < ?3) a;")
    Map<String,String> findSum(int comId,String starttime, String endtime);
    @Query(nativeQuery = true,value = "SELECT days,total,effective,invalid,totaltime,averagetime FROM (SELECT FROM_UNIXTIME(dial_time/1000,'%Y-%m-%d') days,COUNT(id) total," +
            "ifnull(sum(case when converse_time>0 then 1 ELSE 0 end),0) effective," +
            "ifnull(sum(case when converse_time<1 then 1 ELSE 0 end),0) invalid ," +
            "ifnull(SUM(converse_time),0) totaltime," +
            "ifnull(SUM(converse_time)/ ifnull(sum(case when converse_time>0 then 1 ELSE 0 end),1),0) averagetime " +
            "FROM (SELECT h.* FROM call_company_phone p,call_task_call_history h WHERE" +
            " p.id = h.task_phone_id and p.company_id = 1) a GROUP BY days DESC LIMIT 7) b GROUP BY days")
    List<Map<String,String>> findSumList(int comId);
}
