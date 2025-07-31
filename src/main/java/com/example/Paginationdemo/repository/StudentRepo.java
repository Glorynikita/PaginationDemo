package com.example.Paginationdemo.repository;

import com.example.Paginationdemo.model.StudentDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentDepartment,Integer>, JpaSpecificationExecutor<StudentDepartment> {
}
