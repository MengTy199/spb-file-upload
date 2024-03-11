package com.mt.spring_file_upload.infrastructure.model.body;

public class PageResponse {

    private Long totalPage;
    private Long page;
    private Long totalCount;
    private Long pageSize;

    public Long getTotalPage() {
        return totalPage;
    }

    public Long getPage() {
        return page;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public Long getPageSize() {
        return pageSize;
    }
}
