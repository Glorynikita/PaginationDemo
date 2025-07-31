package com.example.Paginationdemo.dto;

import com.example.Paginationdemo.model.StudentDepartment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDto<T> {
    private List<StudentDepartment> content;
    private long totalElements;
    private int totalPages;
    private int pageNo;
    private int pageSize;
    private boolean last;
}
