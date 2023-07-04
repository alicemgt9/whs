package com.jhb.whs.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jhb.whs.common.QueryPageParam;
import com.jhb.whs.common.Result;
import com.jhb.whs.entity.User;
import com.jhb.whs.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 金涵博
 * @since 2023-06-11
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> listAll(){
        return userService.list();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        return userService.save(user) ? Result.suc():Result.fail();
    }

    //新增
    @PostMapping("/mod")
    public Result mod(@RequestBody User user){
        return userService.updateById(user)?Result.suc(): Result.fail();
    }

    //修改或新增
    @PostMapping("/saveorMod")
    public Result saveorMod(@RequestBody User user){
        return userService.saveOrUpdate(user)?Result.suc():Result.fail();
    }

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        List list = userService.lambdaQuery()
                .eq(User::getAcc,user.getAcc())
                .eq(User::getPassword,user.getPassword()).list();
        return list.size()>0?Result.suc(list.get(0)):Result.fail();
    }

    //删除
    @GetMapping("/delete")
    public Result delete(Integer id){
        return userService.removeById(id)?Result.suc():Result.fail();
    }

    //查询（模糊、匹配）
    @PostMapping("/listP")
    public Result listP(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(user.getName())){
            lambdaQueryWrapper.like(User::getName,user.getName());
        }
        return Result.suc(userService.list(lambdaQueryWrapper));
    }

    //分页查询
    @PostMapping("/listPage")
    public List<User> listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        System.out.println("name====" + (String)param.get("name"));

        String name = (String)param.get("name");

        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName,name);

        IPage result = userService.page(page,lambdaQueryWrapper);

        System.out.println("总记录数" + result.getTotal());

        return result.getRecords();
    }

    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        System.out.println("name====" + (String)param.get("name"));

        String name = (String)param.get("name");

        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

       LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
       if(StringUtils.isNotBlank(name) && !"null".equals(name)){
           lambdaQueryWrapper.like(User::getName,name);
       }


        //IPage result = userService.pageC(page);
        IPage result = userService.pageCC(page,lambdaQueryWrapper);

        System.out.println("总记录数" + result.getTotal());


        return Result.suc(result.getRecords());
    }

    @PostMapping("/listPageCC")
    public Result listPageCC(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();


        String name = (String)param.get("name");
        String gender = (String)param.get("gender");

        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(User::getName,name);
        }
        if(StringUtils.isNotBlank(gender)){
            lambdaQueryWrapper.eq(User::getGender,gender);
        }

        //IPage result = userService.pageC(page);
        IPage result = userService.pageCC(page,lambdaQueryWrapper);

        System.out.println("总记录数" + result.getTotal());

        return Result.suc(result.getRecords(), result.getTotal());
    }
}
