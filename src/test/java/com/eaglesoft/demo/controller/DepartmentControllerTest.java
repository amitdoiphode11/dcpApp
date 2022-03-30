package com.eaglesoft.demo.controller;

import com.eaglesoft.demo.entity.Department;
import com.eaglesoft.demo.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;
    private Department inputDepartment;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentAddress("Satara")
                .departmentCode("IT-06")
                .departmentName("IT")
                .departmentId(1L)
                .build();

        inputDepartment = Department.builder()
                .departmentAddress("Satara")
                .departmentCode("IT-06")
                .departmentName("IT")
                .build();

    }

    @WithMockUser
    @Test
    void testSaveDepartment() throws Exception {
        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(post("/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"departmentName\":\"IT\",\n" +
                                "\t\"departmentAddress\":\"Satara\",\n" +
                                "\t\"departmentCode\":\"IT-06\"\n" +
                                "}"))
                .andExpect(status().isOk());

    }

    @WithMockUser
    @Test
    void testGetAllDepartment() throws Exception {
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(department);
        Mockito.when(departmentService.getDepartments())
                .thenReturn(departmentList);

        mockMvc.perform(get("/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[\n" +
                                "    {\n" +
                                "        \"departmentId\": 1,\n" +
                                "        \"departmentName\": \"Computer\",\n" +
                                "        \"departmentAddress\": \"Pune\",\n" +
                                "        \"departmentCode\": \"CS-02\"\n" +
                                "    }\n" +
                                "]"))
                .andExpect(status().isOk());

    }

    @WithMockUser
    @Test
    void testGetDepartmentByCode() throws Exception {
        Mockito.when(departmentService.getDepartmentByCode("CS-02"))
                .thenReturn(department);

        mockMvc.perform(get("/department/{departmentCode}", "CS-02")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"departmentName\":\"IT\",\n" +
                                "\t\"departmentAddress\":\"Satara\",\n" +
                                "\t\"departmentCode\":\"IT-06\"\n" +
                                "}"))
                .andExpect(status().isOk());

    }

    @WithMockUser
    @Test
    void testUpdateDepartment() throws Exception {
        Mockito.when(departmentService.updateDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(put("/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"departmentName\":\"IT\",\n" +
                                "\t\"departmentAddress\":\"Satara\",\n" +
                                "\t\"departmentCode\":\"IT-06\"\n" +
                                "}"))
                .andExpect(status().isOk());

    }

    @WithMockUser
    @Test
    void testDeleteDepartmentById() throws Exception {
        Mockito.when(departmentService.deleteDepartmentById(1L))
                .thenReturn(Optional.ofNullable(department));

        mockMvc.perform(delete("/department/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"departmentName\":\"IT\",\n" +
                                "\t\"departmentAddress\":\"Satara\",\n" +
                                "\t\"departmentCode\":\"IT-06\"\n" +
                                "}"))
                .andExpect(status().isOk());

    }
}