package cn.fog.service.impl;

import cn.fog.entity.Category;
import cn.fog.mapper.CategoryMapper;
import cn.fog.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
