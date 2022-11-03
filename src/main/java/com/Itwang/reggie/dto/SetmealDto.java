package com.Itwang.reggie.dto;


import com.Itwang.reggie.entity.Setmeal;
import com.Itwang.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
