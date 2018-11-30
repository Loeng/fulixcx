package com.gys.fulixcx.dao;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.request.TaskRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CallTaskRepositoryImpl {
    @PersistenceContext
    private EntityManager em;

    public EasyUiVo findList(TaskRequest taskRequest) {

        String select = "select ";
        String columns = " ct.id,\n" +
                "\tct.task_name taskName,\n" +
                "\tct.lssuer_time issuerTime,\n" +
                "\tremarks,\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\tcount(*)\n" +
                "\t\tFROM\n" +
                "\t\t\tcall_company_phone ctp\n" +
                "\t\tWHERE\n" +
                "\t\t\tctp.task_id = ct.id\n" +
                "\t) allNum,\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\tcount(*)\n" +
                "\t\tFROM\n" +
                "\t\t\tcall_company_phone ctp\n" +
                "\t\tWHERE\n" +
                "\t\t\tctp.task_id = ct.id\n" +
                "\t\tAND ctp.dial_type > 0\n" +
                "\t) calledNum,\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\tcount(*)\n" +
                "\t\tFROM\n" +
                "\t\t\tcall_company_phone ctp\n" +
                "\t\tWHERE\n" +
                "\t\t\tctp.task_id = ct.id\n" +
                "\t\tAND ctp.dial_type = 2\n" +
                "\t) callNot,\n" +
                "\t(\n" +
                "\t\tSELECT\n" +
                "\t\t\tcount(*)\n" +
                "\t\tFROM\n" +
                "\t\t\tcall_company_phone ctp\n" +
                "\t\tWHERE\n" +
                "\t\t\tctp.task_id = ct.id\n" +
                "\t\tAND ctp.dial_type = 3\n" +
                "\t) invalidNum,\n" +
                "\tcs1.staff_name publishName,\n" +
                "\tcs2.staff_name executorName ";
        StringBuffer fromAndWhere = new StringBuffer(" FROM ");
        fromAndWhere.append(" call_task ct,");
        fromAndWhere.append(" call_staff cs1,");
        fromAndWhere.append(" call_staff cs2 ");
        fromAndWhere.append(" WHERE");
        fromAndWhere.append(" ct.Issuer_id = cs1.id");
        fromAndWhere.append(" AND ct.staff_id = cs2.id");
        if(!StringUtils.isEmpty(taskRequest.getCompanyId())){
            fromAndWhere.append(" and ct.company_id = "+taskRequest.getCompanyId());
        }
        if(!StringUtils.isEmpty(taskRequest.getTaskName())){
            fromAndWhere.append(" and ct.task_name like '%"+taskRequest.getTaskName()+"%'");
        }
        if(!StringUtils.isEmpty(taskRequest.getPublishName())){
            fromAndWhere.append(" and cs1.staff_name like '%"+taskRequest.getPublishName()+"%'");
        }
        if(!StringUtils.isEmpty(taskRequest.getExecutorName())){
            fromAndWhere.append(" and cs2.staff_name like '%"+taskRequest.getExecutorName()+"%'");
        }

        StringBuffer orderSb = new StringBuffer();
        int start = taskRequest.getPage() * taskRequest.getRows();
        String limit = " limit " + start + " , " + taskRequest.getRows();
        String sql = "";
        if(!StringUtils.isEmpty(taskRequest.getOrder()) && !StringUtils.isEmpty(taskRequest.getSort())){
            orderSb.append(" order by ").append(taskRequest.getSort()).append(" ").append(taskRequest.getOrder());
            sql = "select * from ("+select + columns + fromAndWhere.toString()+") tmp "+orderSb.toString()+limit;
        }else {
            sql = select + columns + fromAndWhere.toString()+limit;
        }
        Query listQuery = em.createNativeQuery(sql);
        List resultList = listQuery.getResultList();
        List<Map<String,Object>> rows = new ArrayList<>();
        for(Object obj : resultList){
            Object[] arr = (Object[])obj;
            Map<String, Object> map = new HashMap<>();
            map.put("id",arr[0]);
            map.put("taskName",arr[1]);
            map.put("issuerTime",arr[2]);
            map.put("remarks",arr[3]);
            map.put("allNum",arr[4]);
            map.put("calledNum",arr[5]);
            map.put("callNot",arr[6]);
            map.put("invalidNum",arr[7]);
            map.put("publishName",arr[8]);
            map.put("executorName",arr[9]);
            rows.add(map);
        }
        Query countQuery = em.createNativeQuery(select + " count(*) " + fromAndWhere.toString());
        Long total = ((BigInteger)countQuery.getSingleResult()).longValue();
        EasyUiVo easyUiVo = new EasyUiVo();
        easyUiVo.setRows(rows);
        easyUiVo.setTotal(total);
        return easyUiVo;
    }
}
