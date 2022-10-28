package com.Itwang.reggie.service.impl;

import com.Itwang.reggie.common.CustomException;
import com.Itwang.reggie.entity.Category;
import com.Itwang.reggie.entity.Dish;
import com.Itwang.reggie.entity.Setmeal;
import com.Itwang.reggie.mapper.CategoryMapper;
import com.Itwang.reggie.service.CategoryService;
import com.Itwang.reggie.service.DishService;
import com.Itwang.reggie.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {

    @Autowired
    DishService dishService;
    @Autowired
    SetmealService setmealService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishQueryWrapper = new LambdaQueryWrapper<>();
        dishQueryWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(dishQueryWrapper);
        if (count>0){
            throw new CustomException("当前分类下关联的菜品，不能删除");
        }
        LambdaQueryWrapper<Setmeal> setmealQueryWrapper = new LambdaQueryWrapper<>();
        setmealQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count(setmealQueryWrapper);
        System.out.println("count2=" +count2);
        if (count2>0){
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }

        categoryMapper.deleteById(id);
    }
}
