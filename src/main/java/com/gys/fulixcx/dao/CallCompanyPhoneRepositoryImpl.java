package com.gys.fulixcx.dao;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.request.CustomerRequest;
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
public class CallCompanyPhoneRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public EasyUiVo findList(CustomerRequest customerRequest) {

        String select = "select ";
        String columns = "ccp.id,cp.phone_name phoneName,cp.phone_number phoneNumber,ccp.star,ccp.schedule,ccp.remarks ";
        StringBuffer fromAndWhere = new StringBuffer(" from call_company_phone ccp,call_phone cp where ccp.phone_id = cp.id and ccp.star > 0 ");
        StringBuffer orderSb = new StringBuffer();
        if (!StringUtils.isEmpty(customerRequest.getCompanyId())) {
            fromAndWhere.append(" and ccp.company_id = " + customerRequest.getCompanyId());
        }
        if (!StringUtils.isEmpty(customerRequest.getStar())) {
            fromAndWhere.append(" and ccp.star = " + customerRequest.getStar());
        }
        if (!StringUtils.isEmpty(customerRequest.getLevel())) {
            fromAndWhere.append(" and ccp.schedule = " + customerRequest.getLevel());
        }
        if (!StringUtils.isEmpty(customerRequest.getPhoneName())) {
            fromAndWhere.append(" and cp.phone_name like '%" + customerRequest.getPhoneName()+"%'");
        }
        if (!StringUtils.isEmpty(customerRequest.getPhoneNumber())) {
            fromAndWhere.append(" and cp.phone_number like '%" + customerRequest.getPhoneNumber()+"%'");
        }
        int start = customerRequest.getPage() * customerRequest.getRows();
        String limit = " limit " + start + " , " + customerRequest.getRows();
        String sql = "";
        if(!StringUtils.isEmpty(customerRequest.getOrder()) && !StringUtils.isEmpty(customerRequest.getSort())){
            orderSb.append(" order by ").append(customerRequest.getSort()).append(" ").append(customerRequest.getOrder());
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
            map.put("phoneName",arr[1]);
            map.put("phoneNumber",arr[2]);
            map.put("star",arr[3]);
            map.put("schedule",arr[4]);
            map.put("remarks",arr[5]);
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
