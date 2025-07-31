package com.example.Paginationdemo.service;

import com.example.Paginationdemo.dto.PageResponseDto;
import com.example.Paginationdemo.model.StudentDepartment;
import com.example.Paginationdemo.repository.StudentRepo;
import com.example.Paginationdemo.specification.StudentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public void add(StudentDepartment studentDepartment) {
        studentRepo.save(studentDepartment);
    }

    public PageResponseDto<StudentDepartment> getFilteredStudents(String name, String departmentName, int pageNo, int pageSize, String sortBy, String sortDir) {

        Specification<StudentDepartment> sp = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if (name != null && !name.isBlank()) {
            sp = sp.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }

        if (departmentName != null && !departmentName.isBlank()) {
            sp = sp.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("departmentName")), "%" + departmentName.toLowerCase() + "%"));
        }

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<StudentDepartment> page = studentRepo.findAll(sp, pageable);

        return new PageResponseDto<>(
                page.getContent(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize(),
                page.isLast()
        );

    }
}

