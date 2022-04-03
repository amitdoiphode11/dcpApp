package com.eaglesoft.demo.repository;

import com.eaglesoft.demo.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository repository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSaveDepartment() {
        Department department = Department.builder()
                .departmentId(1L)
                .departmentCode("IT")
                .departmentName("IT")
                .departmentAddress("Satara")
                .build();

        Department department1 = repository.save(department);

        assertNotNull(department1);
        List<Department> list = repository.findAll();
        assertEquals(1, list.size());
        assertEquals("IT", list.get(0).getDepartmentCode());
    }

    @Test
    void WhenFindByCode_thenReturnDepartment() {
        Department result = repository.findByDepartmentCode("IT");
        assertEquals(result.getDepartmentName(), "IT");
    }

    @Test
    void testFindAll() {
        List<Department> departmentList = repository.findAll();
        assertNotNull(departmentList);
        assertEquals(1, departmentList.size());
    }

    @Test
    void testDelete() {
        Department department = Department.builder()
                .departmentId(1L)
                .departmentCode("IT")
                .departmentName("IT")
                .departmentAddress("Satara")
                .build();
        repository.delete(department);
        Department departmentCode = repository.findByDepartmentCode("IT");
        assertNull(departmentCode);
    }

}