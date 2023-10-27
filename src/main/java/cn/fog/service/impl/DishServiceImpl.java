package cn.fog.service.impl;

import cn.fog.entity.Dish;
import cn.fog.mapper.DishMapper;
import cn.fog.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
