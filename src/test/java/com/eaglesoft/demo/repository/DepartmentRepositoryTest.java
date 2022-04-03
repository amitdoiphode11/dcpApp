package com.eaglesoft.demo.repository;

import com.eaglesoft.demo.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository repository;

    private Department department;

    private Department department1;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentId(1L)
                .departmentCode("IT")
                .departmentName("IT")
                .departmentAddress("Satara")
                .build();

        department1 = Department.builder()
                .departmentId(2L)
                .departmentCode("COMP")
                .departmentName("COMP")
                .departmentAddress("Pune")
                .build();

        List<Department> departmentList = new ArrayList<>();
        departmentList.add(department);
        departmentList.add(department1);
        repository.saveAll(departmentList);
    }

    @Test
    void testSaveDepartment() {
        assertNotNull(department1);
        List<Department> list = repository.findAll();
        assertEquals(2, list.size());
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
        assertEquals(2, departmentList.size());
    }

    @Test
    void testDelete() {
        repository.delete(repository.findByDepartmentCode("IT"));
        Department departmentCode = repository.findByDepartmentCode("IT");
        assertNull(departmentCode);
        List<Department> departmentList1 = repository.findAll();
        assertEquals(1, departmentList1.size());
    }

}