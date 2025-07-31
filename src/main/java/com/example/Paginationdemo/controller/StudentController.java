package com.example.Paginationdemo.controller;

import com.example.Paginationdemo.dto.PageDto;
import com.example.Paginationdemo.model.StudentDepartment;
import com.example.Paginationdemo.repository.StudentRepo;
import com.example.Paginationdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
