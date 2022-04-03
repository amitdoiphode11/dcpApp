package com.eaglesoft.demo.service;

import com.eaglesoft.demo.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService  {

    Department saveDepartment(Department department);

    List<Department> getDepartments();

    Department getDepartmentByCode(String code);

    Department updateDepartment(Department department);

    Optional<Department> deleteDepartmentById(Long id);
}
