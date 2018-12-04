package com.gys.fulixcx.controller;

import com.gys.fulixcx.EasyUiVo;
import com.gys.fulixcx.mode.CallCategoryMode;
import com.gys.fulixcx.mode.SessionMode;
import com.gys.fulixcx.request.CatelogyRequest;
import com.gys.fulixcx.service.CategoryService;
import com.gys.fulixcx.util.GysAnnotation;
import com.gys.fulixcx.util.JsonReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.Oneway;
import javax.servlet.http.HttpServletRequest;

@Controller
@GysAnnotation
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/index")
    public String index(){
        return "category_main";
    }

    @RequestMapping("/page")
    @ResponseBody
    public EasyUiVo findPage(HttpServletRequest request, CatelogyRequest catelogyRequest){
        SessionMode sessionMode = (SessionMode) request.getSession().getAttribute("sessionMode");
        catelogyRequest.setCompanyId(sessionMode.getCommodityid());
        return categoryService.findPage(catelogyRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public JsonReq deleteCate(@PathVariable Integer id){
        try {
            categoryService.deleteById(id);
            return new JsonReq(200,"操作成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new JsonReq(500,"操作失败");
    }

    @PostMapping("/add")
    @ResponseBody
    public JsonReq addCate(HttpServletRequest request, CallCategoryMode callCategoryMode){
        try {
            SessionMode sessionMode = (SessionMode) request.getSession().getAttribute("sessionMode");
            callCategoryMode.setCompanyId(sessionMode.getCommodityid());
            if(callCategoryMode.getId() < 0){
                callCategoryMode.setId(null);
            }
            categoryService.add(callCategoryMode);
            return new JsonReq(200,"操作成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new JsonReq(500,"操作失败");
    }
}
