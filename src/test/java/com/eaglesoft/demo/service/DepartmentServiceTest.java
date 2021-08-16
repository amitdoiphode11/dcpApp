package com.eaglesoft.demo.service;

import com.eaglesoft.demo.entity.Department;
import com.eaglesoft.demo.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentCode("CS-01")
                .departmentId(1L)
                .departmentName("COMP")
                .departmentAddress("Satara")
                .build();

        Mockito.when(departmentRepository.findByDepartmentCode("CS-01")).thenReturn(department);

    }

    @Test
    public void whenValidDepartmentCode_thenDepartmentShouldFound() {
        String departCode = "CS-01";
        Department find = departmentService.getDepartmentByCode(departCode);
        assertEquals(departCode, find.getDepartmentCode());
    }
}