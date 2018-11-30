package com.gys.fulixcx.service.impl;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.dao.CallStaffRepository;
import com.gys.fulixcx.mode.CallStaffMode;
import com.gys.fulixcx.request.StaffRequest;
import com.gys.fulixcx.service.CallStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CallStaffServiceImpl implements CallStaffService {
    @Autowired
    private CallStaffRepository callStaffRepository;
    @Override
    public EasyUiVo findPage(StaffRequest staffRequest) {
        Sort sort = null;
        if(!StringUtils.isEmpty(staffRequest.getOrder()) && !StringUtils.isEmpty(staffRequest.getSort())){
            if("desc".equalsIgnoreCase(staffRequest.getOrder())){
                sort = Sort.by(staffRequest.getSort()).descending();
            }else {
                sort = Sort.by(staffRequest.getSort()).ascending();
            }
        }
        Pageable pageRequest;
        if(sort == null){
            pageRequest = PageRequest.of(staffRequest.getPage(),staffRequest.getRows());
        }else {
            pageRequest = PageRequest.of(staffRequest.getPage(),staffRequest.getRows(),sort);
        }
        Specification<CallStaffMode> specification = new Specification<CallStaffMode>() {
            @Override
            public Predicate toPredicate(Root<CallStaffMode> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(!StringUtils.isEmpty(staffRequest.getCompanyId())){
                    predicates.add(cb.equal(root.get("companyId").as(Integer.class),staffRequest.getCompanyId()));
                }
                if(!StringUtils.isEmpty(staffRequest.getStaffName())){
                    predicates.add(cb.like(root.get("staffName").as(String.class),"%"+staffRequest.getStaffName()+"%"));
                }
                if(!StringUtils.isEmpty(staffRequest.getStaffPhone())){
                    predicates.add(cb.like(root.get("staffPhone").as(String.class),"%"+staffRequest.getStaffPhone()+"%"));
                }
                if(!StringUtils.isEmpty(staffRequest.getState())){
                    predicates.add(cb.equal(root.get("state").as(Integer.class),staffRequest.getState()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<CallStaffMode> page = callStaffRepository.findAll(specification,pageRequest);
        EasyUiVo easyUiVo = new EasyUiVo();
        easyUiVo.setTotal(page.getTotalElements());
        easyUiVo.setRows(page.getContent());
        return easyUiVo;
    }

    @Override
    @Transactional
    public boolean stopOrStart(Integer id, Integer state) {
        try {
            callStaffRepository.updateStatus(id,state);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public void save(CallStaffMode callStaffMode) {
        callStaffRepository.saveAndFlush(callStaffMode);
    }
}
