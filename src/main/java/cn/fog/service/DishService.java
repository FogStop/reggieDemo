package cn.fog.service;

import cn.fog.dto.DishDto;
import cn.fog.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

public interface DishService extends IService<Dish> {
//    新增菜品，同时插入菜品对应空位数据，操作两张表dish,dish_flavor
    void saveWithFlavor(DishDto dishDto);
}
