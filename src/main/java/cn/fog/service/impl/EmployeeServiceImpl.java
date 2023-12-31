package cn.fog.service.impl;

import cn.fog.entity.Employee;
import cn.fog.mapper.EmployeeMapper;
import cn.fog.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>implements EmployeeService {

}
