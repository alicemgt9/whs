package com.jhb.whs.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jhb.whs.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 金涵博
 * @since 2023-06-11
 */
public interface UserService extends IService<User> {

    IPage pageC(Page<User> page);


    IPage pageCC(IPage<User> page, Wrapper wrapper);
}
