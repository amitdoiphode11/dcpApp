package com.eaglesoft.demo.controller;

import com.eaglesoft.demo.entity.Department;
import com.eaglesoft.demo.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "This api used to save the department.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save the department into db and return the department", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not available", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Missing or invalid request body"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
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
    @Operation(summary = "This api used to fetch all department.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched all department from db", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not available", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Missing or invalid request body"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
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
    @Operation(summary = "This api used to get the department using departmentCode.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch the department from db using departmentCode", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not available", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Missing or invalid request body"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
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
    @Operation(summary = "This api used to update the department.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update the department in db", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not available", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Missing or invalid request body"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @PutMapping("/department")
    public Department updateDepartment(@RequestBody Department department) {
        LOGGER.info("updateDepartment");
        return departmentService.updateDepartment(department);
    }

    @Operation(summary = "This api used to delete the department using department id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the department from db", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Not available", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Missing or invalid request body"),
            @ApiResponse(responseCode = "500", description = "Internal error")})
    @DeleteMapping("/department/{id}")
    public Department deleteDepartmentById(@PathVariable("id") Long id) {
        LOGGER.info("deleteDepartmentById");
        return departmentService.deleteDepartmentById(id).orElse(new Department());
    }
}
