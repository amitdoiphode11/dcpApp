package com.eaglesoft.demo.repository;

import com.eaglesoft.demo.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentCode("IT")
                .departmentName("IT")
                .departmentAddress("Satara")
                .departmentId(1L)
                .build();
        entityManager.persist(department);
    }

    @Test
    void WhenFindByCode_thenReturnDepartment() {
        Department department = repository.findByDepartmentCode("IT");
        assertEquals(department.getDepartmentName(),"IT");
    }
}