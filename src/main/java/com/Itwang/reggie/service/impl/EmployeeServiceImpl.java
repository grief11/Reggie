package com.Itwang.reggie.service.impl;

import com.Itwang.reggie.entity.Employee;
import com.Itwang.reggie.mapper.EmployeeMapper;
import com.Itwang.reggie.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
