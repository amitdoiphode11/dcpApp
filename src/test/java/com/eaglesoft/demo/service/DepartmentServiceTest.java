package com.eaglesoft.demo.service;

import com.eaglesoft.demo.entity.Department;
import com.eaglesoft.demo.repository.DepartmentRepository;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenSaveDepartment_thenReturnDepartment() {
        //mock
        Department department = Department.builder()
                .departmentCode("CS-01")
                .departmentName("COMP")
                .departmentAddress("Satara")
                .build();
        Mockito.when(departmentRepository.save(department)).thenReturn(department);

        Department departmentResult = departmentService.saveDepartment(department);
        assertNotNull(departmentResult);
        assertEquals(department.getDepartmentCode(), departmentResult.getDepartmentCode());
    }

    @Test
    public void whenRequestForDepartments_thenReturnListOfDepartment() {
        //mock
        Department department = Department.builder()
                .departmentCode("CS-01")
                .departmentName("COMP")
                .departmentAddress("Satara")
                .build();
        List<Department> list = new ArrayList<>();
        list.add(department);
        Mockito.when(departmentRepository.findAll()).thenReturn(list);

        List<Department> result = departmentService.getDepartments();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void whenValidDepartmentCode_thenDepartmentShouldFound() {
        //Mock
        Department department = Department.builder()
                .departmentCode("CS-01")
                .departmentId(1L)
                .departmentName("COMP")
                .departmentAddress("Satara")
                .build();
        Mockito.when(departmentRepository.findByDepartmentCode("CS-01")).thenReturn(department);

        String departCode = "CS-01";
        Department find = departmentService.getDepartmentByCode(departCode);
        assertEquals(departCode, find.getDepartmentCode());
    }

    @Test
    public void whenUpdateDepartment_thenReturnDepartment() {
        //Mock
        Department department = Department.builder()
                .departmentCode("CS-01")
                .departmentId(1L)
                .departmentName("COMP")
                .departmentAddress("Satara")
                .build();
        Optional<Department> departmentOptional = Optional.ofNullable(department);
        Mockito.when(departmentRepository.findById(1L)).thenReturn(departmentOptional);
        Mockito.when(departmentRepository.save(department)).thenReturn(department);

        Department result = departmentService.updateDepartment(department);
        assertEquals("CS-01", result.getDepartmentCode());
    }


    /*@Test
    public void whenDepartmentDeleteById_thenDepartmentShouldNull() {
        //Mock
        Department department = Department.builder()
                .departmentCode("CS-01")
                .departmentId(1L)
                .departmentName("COMP")
                .departmentAddress("Satara")
                .build();
        Mockito.when(departmentRepository.save(department)).thenReturn(department);
        Mockito.verify(departmentRepository, Mockito.times(1)).delete(department);
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Optional<Department> result = departmentService.deleteDepartmentById(department.getDepartmentId());

        assertEquals("CS-01", result.get().getDepartmentCode());
    }*/
}