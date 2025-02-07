package com.emretest.model;

public class RequestPageModel {
    private int pageNumber;
    private int pageSize;
    private String columnName;
    private Boolean sortDirection;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Boolean getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Boolean sortDirection) {
        this.sortDirection = sortDirection;
    }
}