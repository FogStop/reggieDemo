package cn.fog.service.impl;

import cn.fog.dto.DishDto;
import cn.fog.entity.Dish;
import cn.fog.entity.DishFlavor;
import cn.fog.mapper.DishMapper;
import cn.fog.service.DishFlavorService;
import cn.fog.service.DishService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
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

    /**
     * 根据id查询菜品信息和品味信息
     * @param id
     * @return
     */
    @Override
    public DishDto getByWithFlavor(Long id) {
        //查询菜品基本信息、
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish,dishDto);
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDto.setFlavors(flavors);

        return dishDto;
    }

    @Override
    public void updateWithFlavor(DishDto dishDto) {
//        更新dish基本信息
        this.updateById(dishDto);
//        清理当前菜品对应口味数据--dish_flavor的del操作
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());
        dishFlavorService.remove(queryWrapper);
//        添加当前提交的口味数据--dish_flavor的inter操作
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item)->{
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }

}
