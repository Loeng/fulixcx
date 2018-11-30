package com.gys.fulixcx.service.impl;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.dao.CallCompanyPhoneRepository;
import com.gys.fulixcx.dao.CallCompanyPhoneRepositoryImpl;
import com.gys.fulixcx.mode.CallCompanyPhone;
import com.gys.fulixcx.request.CustomerRequest;
import com.gys.fulixcx.request.ResourceRequest;
import com.gys.fulixcx.service.CallCompanyPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CallCompanyPhoneServiceImpl implements CallCompanyPhoneService {
    @Autowired
    private CallCompanyPhoneRepository callCompanyPhoneRepository;

    @Autowired
    private CallCompanyPhoneRepositoryImpl callCompanyPhoneRepositoryImpl;
    @Override
    public Page<CallCompanyPhone> findPage(ResourceRequest resourceRequest) {
        Pageable pageable =  PageRequest.of(resourceRequest.getPage(), resourceRequest.getRows());
        Specification<CallCompanyPhone> specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(resourceRequest.getPhoneName())) {
                    predicates.add(cb.like(root.join("callPhoneMode").get("phoneName").as(String.class), "%" + resourceRequest.getPhoneName() + "%"));
                }
                if (!StringUtils.isEmpty(resourceRequest.getPhoneNumber())) {
                    predicates.add(cb.like(root.join("callPhoneMode").get("phoneNumber").as(String.class), "%" + resourceRequest.getPhoneNumber() + "%"));
                }
                if(!StringUtils.isEmpty(resourceRequest.getCompanyId())){
                    predicates.add(cb.equal(root.join("callCompanyMode").get("id").as(Integer.class),resourceRequest.getCompanyId()));
                }
                if(!StringUtils.isEmpty(resourceRequest.getCarrieroperator())){
                    predicates.add(cb.like(root.join("callPhoneMode").get("carrieroperator").as(String.class),"%" + resourceRequest.getCarrieroperator() + "%"));
                }
                if(!StringUtils.isEmpty(resourceRequest.getAssign())){
                   if("0".equals(resourceRequest.getAssign())){
                       predicates.add(cb.isNull(root.get("taskId")));
                   }else{
                       predicates.add(cb.isNotNull(root.get("taskId")));
                   }
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<CallCompanyPhone> monitorList = callCompanyPhoneRepository.findAll(specification, pageable);
//        Map<String, Object> result = new HashMap<>();
//        result.put("total", monitorList.getTotalElements());
//        result.put("rows", monitorList.getContent());
        return monitorList;
    }

    @Override
    public EasyUiVo findList(CustomerRequest customerRequest) {
        return callCompanyPhoneRepositoryImpl.findList(customerRequest);
    }
}
