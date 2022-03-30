package com.eaglesoft.demo.service;

import com.eaglesoft.demo.entity.Department;
import com.eaglesoft.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    @Override
    public Department saveDepartment(Department department) {
        return repository.save(department);
    }

    @Override
    public List<Department> getDepartments() {
        return repository.findAll();
    }

    @Override
    public Department getDepartmentByCode(String departmentCode) {
        return repository.findByDepartmentCode(departmentCode);
    }

    @Override
    public Department updateDepartment(Department department) {
        Department dep = repository.findById(department.getDepartmentId()).get();

        if (department.getDepartmentName() != null) {
            dep.setDepartmentName(department.getDepartmentName());
        }
        if (department.getDepartmentCode() != null) {
            dep.setDepartmentCode(department.getDepartmentCode());
        }
        if (department.getDepartmentAddress() != null) {
            dep.setDepartmentAddress(department.getDepartmentAddress());
        }

        return repository.save(dep);
    }

    @Override
    public Optional<Department> deleteDepartmentById(Long id) {
        repository.deleteById(id);
        return repository.findById(id);
    }
}
