package com.cashback.usecase.common;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class Page implements Serializable {

    private List<?> content;
    private Integer totalElements;
    private Integer totalPages;
    private Integer pageNumber;
    private Boolean last;
    private Boolean first;

    public Page(List<?> content, Integer totalElements, Integer totalPages, Integer pageNumber, Boolean last, Boolean first) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.pageNumber = pageNumber;
        this.last = last;
        this.first = first;
    }

    public List<?> getContent() {
        return content;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Boolean getLast() {
        return last;
    }

    public Boolean getFirst() {
        return first;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(content, page.content) &&
                Objects.equals(totalElements, page.totalElements) &&
                Objects.equals(totalPages, page.totalPages) &&
                Objects.equals(pageNumber, page.pageNumber) &&
                Objects.equals(last, page.last) &&
                Objects.equals(first, page.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, totalElements, totalPages, pageNumber, last, first);
    }

    @Override
    public String toString() {
        return "Page{" +
                "content=" + content +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", pageNumber=" + pageNumber +
                ", last=" + last +
                ", first=" + first +
                '}';
    }
}
