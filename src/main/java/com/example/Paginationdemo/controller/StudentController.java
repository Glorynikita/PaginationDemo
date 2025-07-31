package com.example.Paginationdemo.controller;

import com.example.Paginationdemo.dto.PageDto;
import com.example.Paginationdemo.dto.PageResponseDto;
import com.example.Paginationdemo.model.StudentDepartment;
import com.example.Paginationdemo.repository.StudentRepo;
import com.example.Paginationdemo.service.StudentService;
import com.example.Paginationdemo.specification.StudentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepo studentRepo;

    @PostMapping("/add")
    public String add(@RequestBody StudentDepartment studentDepartment) {
        studentService.add(studentDepartment);
        return "success";
    }

    @PostMapping
    public Page<StudentDepartment> getStudents(@RequestBody PageDto dto){
        Pageable pageable = new PageDto().getPage(dto);
        Page<StudentDepartment> page = studentRepo.findAll(pageable);
        return page;

    }

    @GetMapping
    public PageResponseDto<StudentDepartment> getFilteredStudents(@RequestParam (required = false) String name,
                                                                  @RequestParam (required = false) String departmentName,
                                                                  @RequestParam (defaultValue = "0") int pageNo,
                                                                  @RequestParam (defaultValue =  "10") int pageSize,
                                                                  @RequestParam (required = false) String sortBy,
                                                                  @RequestParam (required = false) String sortDir) {

       return studentService.getFilteredStudents(name, departmentName , pageNo, pageSize, sortBy, sortDir);
    }



}
