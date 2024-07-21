package org.test.sekolah.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
public class ResponseCustomPaging<T> {

    private List<T> content;
    private long totalElements;
    private boolean first;
    private boolean last;
    private int totalPages;

    public ResponseCustomPaging(Page<T> all) {
        this.content = all.getContent();
        this.totalElements = all.getTotalElements();
        this.first = all.isFirst();
        this.last = all.isLast();
        this.totalPages = all.getTotalPages();
    }

    public ResponseCustomPaging(List<T> all) {
        this.content = all;
        this.totalElements = all.size();
        this.first = false;
        this.last = false;
        this.totalPages = all.size();
    }

    public ResponseCustomPaging(List<T> all, int currentPage, int lastPage, int totalElements, int perPage) {
        this.content = all;
        this.totalElements = totalElements;
        this.first = currentPage == 1;
        this.last = currentPage == lastPage;
        if (perPage > 0) {
            int total = totalElements / perPage;
            this.totalPages = total + 1;
        }
    }

    public ResponseCustomPaging(List<T> all, ResponseCustomPaging customPaging) {
        this.content = all;
        this.totalElements = customPaging.getTotalElements();
        this.first = customPaging.isFirst();
        this.last = customPaging.isLast();
        this.totalPages = customPaging.getTotalPages();
    }

    public ResponseCustomPaging(List<T> all, Page page) {
        this.content = all;
        this.totalElements = page.getTotalElements();
        this.first = page.isFirst();
        this.last = page.isLast();
        this.totalPages = page.getTotalPages();
    }
}
