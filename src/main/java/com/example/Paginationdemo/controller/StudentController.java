package com.example.Paginationdemo.controller;

import com.example.Paginationdemo.dto.PageDto;
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
    public Page<StudentDepartment> getFilteredStudents(@RequestParam (required = false) String filter,
                                                       @RequestParam (defaultValue = "0") int pageNo,
                                                       @RequestParam (defaultValue =  "10") int pageSize) {

        Specification<StudentDepartment> sp = ((root, query, criteriaBuilder) -> null);

        if (filter != null && !filter.isEmpty()) {
            String[] filters = filter.split(",");
            for (String f: filters) {
                String[] parts = f.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    if(key.equalsIgnoreCase("name")){
                        sp = sp.and(StudentSpecification.hasName(value));
                    }
                    if(key.equalsIgnoreCase(("departmentName"))) {
                        sp = sp.and(StudentSpecification.hasName(value));
                    }
                }
            }
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return studentRepo.findAll(sp, pageable);
    }

}
