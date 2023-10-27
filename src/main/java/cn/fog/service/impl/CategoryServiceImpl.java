package cn.fog.service.impl;

import cn.fog.conf.CustomException;
import cn.fog.entity.Category;
import cn.fog.entity.Dish;
import cn.fog.entity.Setmeal;
import cn.fog.mapper.CategoryMapper;
import cn.fog.service.CategoryService;
import cn.fog.service.DishService;
import cn.fog.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;
    //根据id删除分类
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = (int) dishService.count(dishLambdaQueryWrapper);
        //查询当前分类是否关联了菜品，如果已经关联，抛出一个业务异常
        if (count1>0){
//            已经关联菜品
            throw new CustomException("已经关联菜品,不能删除");
        }
        //查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = (int) setmealService.count(setmealLambdaQueryWrapper);
            if (count2>0){
//                已关联套餐
                throw new CustomException("已经关联套餐,不能删除");
            }
//        正常删除
        super.removeById(id);
    }
}
