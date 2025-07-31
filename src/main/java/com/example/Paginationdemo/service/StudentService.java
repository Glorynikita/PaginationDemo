package com.example.Paginationdemo.service;

import com.example.Paginationdemo.model.StudentDepartment;
import com.example.Paginationdemo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    public void add(StudentDepartment studentDepartment) {
        studentRepo.save(studentDepartment);
    }
}
