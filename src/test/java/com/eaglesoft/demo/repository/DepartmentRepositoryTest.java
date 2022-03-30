package com.eaglesoft.demo.repository;

import com.eaglesoft.demo.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSaveDepartment() {
        Department department = Department.builder()
                .departmentCode("IT")
                .departmentName("IT")
                .departmentAddress("Satara")
                .build();

        repository.save(department);

        List<Department> list = repository.findAll();
        assertEquals(1, list.size());
        assertEquals("IT", list.get(0).getDepartmentCode());
    }

    @Test
    void WhenFindByCode_thenReturnDepartment() {
        Department department = Department.builder()
                .departmentCode("IT")
                .departmentName("IT")
                .departmentAddress("Satara")
                .departmentId(1L)
                .build();
        entityManager.persistAndFlush(department);

        Department result = repository.findByDepartmentCode("IT");
        assertEquals(result.getDepartmentName(), "IT");
    }
}