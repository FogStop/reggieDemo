package cn.fog.service.impl;

import cn.fog.dto.DishDto;
import cn.fog.entity.Dish;
import cn.fog.entity.DishFlavor;
import cn.fog.mapper.DishMapper;
import cn.fog.service.DishFlavorService;
import cn.fog.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;
    /**
     * 新增菜品，同时保持口味数据
     * @param dishDto
     */
//    事务控制类： @Transactional
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
//      保存菜品基本信息
        this.save(dishDto);
        Long dishId = dishDto.getId();//菜品id
//        菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.stream().map((item)->{
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
//        保存菜品口味数据保存到dish_flavor表
        dishFlavorService.saveBatch(flavors);

    }
}
