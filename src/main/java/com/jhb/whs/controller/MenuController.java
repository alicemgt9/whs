package com.jhb.whs.controller;


import com.jhb.whs.common.Result;
import com.jhb.whs.entity.Menu;
import com.jhb.whs.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 金涵博
 * @since 2023-07-06
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public Result list(@RequestParam String roleId){
        List list = menuService.lambdaQuery().like(Menu::getMenuRight,roleId).list();
        return Result.suc(list);
    }

}
