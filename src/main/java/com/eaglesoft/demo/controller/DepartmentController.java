package com.eaglesoft.demo.controller;

import com.eaglesoft.demo.entity.Department;
import com.eaglesoft.demo.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    /**
     * Save the department
     *
     * @param department
     * @return Department
     */
    @PostMapping("/department")
    public Department saveDepartment(@Validated @RequestBody Department department) {
        LOGGER.info("saveDepartment" + department.toString());
        return departmentService.saveDepartment(department);
    }

    /**
     * Get All department
     *
     * @return List<Department>
     */
    @GetMapping("/department")
    public List<Department> getAllDepartment() {
        LOGGER.info("getAllDepartment");
        return departmentService.getDepartments();
    }

    /**
     * Get Department by code
     *
     * @param departmentCode
     * @return Department
     */
    @GetMapping("/department/{departmentCode}")
    public Department getDepartmentByCode(@PathVariable String departmentCode) {
        LOGGER.info("getDepartmentByCode");
        return departmentService.getDepartmentByCode(departmentCode);
    }

    /**
     * Update department
     * @param department
     * @return Department
     */
    @PutMapping("/department")
    public Department updateDepartment(@RequestBody Department department) {
        LOGGER.info("updateDepartment");
        return departmentService.updateDepartment(department);
    }

    @DeleteMapping("/department/{id}")
    public Department deleteDepartmentById(@PathVariable("id") Long id){
        LOGGER.info("deleteDepartmentById");
        return departmentService.deleteDepartmentById(id).orElse(new Department());
    }
}
