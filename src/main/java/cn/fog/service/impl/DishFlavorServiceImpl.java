package cn.fog.service.impl;

import cn.fog.entity.DishFlavor;
import cn.fog.mapper.DishFlavorMapper;
import cn.fog.service.DishFlavorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>implements DishFlavorService {
}
