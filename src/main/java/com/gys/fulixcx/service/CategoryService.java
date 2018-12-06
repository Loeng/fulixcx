package com.gys.fulixcx.service;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.mode.CallCategoryMode;
import com.gys.fulixcx.request.CatelogyRequest;

import java.util.List;

public interface CategoryService {
    EasyUiVo findPage(CatelogyRequest catelogyRequest);

    void deleteById(Integer id);

    void add(CallCategoryMode callCategoryMode);

    List<CallCategoryMode> findAllByCompanyId(int commodityid);
}
