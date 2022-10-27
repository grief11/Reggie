package com.Itwang.reggie.service.impl;

import com.Itwang.reggie.entity.Dish;
import com.Itwang.reggie.mapper.DishMapper;
import com.Itwang.reggie.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
