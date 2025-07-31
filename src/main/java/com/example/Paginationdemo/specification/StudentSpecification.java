package com.example.Paginationdemo.specification;

import com.example.Paginationdemo.model.StudentDepartment;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {
    public static Specification<StudentDepartment> hasName(String name){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"),name);
    }

    public static Specification<StudentDepartment> hasDepartmentName(String departmentName){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("departmentName"),departmentName);
    }

}
