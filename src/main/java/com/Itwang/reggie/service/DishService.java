package com.Itwang.reggie.service;


import com.Itwang.reggie.dto.DishDto;
import com.Itwang.reggie.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

public interface DishService extends IService<Dish> {

    //新增菜品，同时插入菜品口味数据
    public void saveWithFlavor(DishDto dishDto);
}
