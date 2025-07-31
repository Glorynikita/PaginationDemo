package com.example.Paginationdemo.dto;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

@Data
public class PageDto {
    private Integer pageNo = 0;
    private Integer pageSize = 10;

    public Pageable getPage(PageDto dto) {
        Integer pageNo = Objects.nonNull(dto.getPageNo()) ? dto.getPageNo() : this.pageNo;
        Integer pageSize = Objects.nonNull(dto.getPageSize()) ? dto.getPageSize() : this.pageSize;

        PageRequest request = PageRequest.of(pageNo, pageSize);
        return request;
    }
}
