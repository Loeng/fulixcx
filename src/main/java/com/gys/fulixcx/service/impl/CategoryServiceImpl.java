package com.gys.fulixcx.service.impl;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.dao.CallCategoryDao;
import com.gys.fulixcx.mode.CallCategoryMode;
import com.gys.fulixcx.mode.CallStaffMode;
import com.gys.fulixcx.request.CatelogyRequest;
import com.gys.fulixcx.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CallCategoryDao callCategoryDao;
    @Override
    public EasyUiVo findPage(CatelogyRequest catelogyRequest) {
        Pageable pageRequest =PageRequest.of(catelogyRequest.getPage(),catelogyRequest.getRows());
        Specification<CallCategoryMode> specification = new Specification<CallCategoryMode>() {
            @Override
            public Predicate toPredicate(Root<CallCategoryMode> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(!StringUtils.isEmpty(catelogyRequest.getCompanyId())){
                    predicates.add(cb.equal(root.get("companyId").as(Integer.class),catelogyRequest.getCompanyId()));
                }
                if(!StringUtils.isEmpty(catelogyRequest.getCategoryName())){
                    predicates.add(cb.like(root.get("categoryName").as(String.class),"%"+catelogyRequest.getCategoryName()+"%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<CallCategoryMode> page = callCategoryDao.findAll(specification,pageRequest);
        EasyUiVo easyUiVo = new EasyUiVo();
        easyUiVo.setTotal(page.getTotalElements());
        easyUiVo.setRows(page.getContent());
        return easyUiVo;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        callCategoryDao.deleteById(id);
    }

    @Override
    @Transactional
    public void add(CallCategoryMode callCategoryMode) {
        callCategoryDao.saveAndFlush(callCategoryMode);
    }
}
