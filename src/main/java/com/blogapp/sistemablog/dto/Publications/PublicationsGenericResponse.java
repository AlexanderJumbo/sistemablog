package com.blogapp.sistemablog.dto.Publications;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PublicationsGenericResponse implements Serializable {

    private List<PublicationResponse> publications;

    private int pageNumber;
    private int sizePage;
    private long totalRecords;
    private int totalPages;
    private boolean lastPage;

    public List<PublicationResponse> getPublications() {
        return publications;
    }

    public void setPublications(List<PublicationResponse> publications) {
        this.publications = publications;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getSizePage() {
        return sizePage;
    }

    public void setSizePage(int sizePage) {
        this.sizePage = sizePage;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }
}
