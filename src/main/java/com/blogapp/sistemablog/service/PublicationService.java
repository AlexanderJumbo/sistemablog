package com.blogapp.sistemablog.service;

import com.blogapp.sistemablog.dto.Publications.PublicationRequest;
import com.blogapp.sistemablog.dto.Publications.PublicationResponse;
import com.blogapp.sistemablog.dto.Publications.PublicationsGenericResponse;
import com.blogapp.sistemablog.entity.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublicationService {

    Page<PublicationResponse> getAllPublications(Pageable pageable);

    PublicationResponse createPublication(PublicationRequest publicationRequest);

    PublicationResponse getPublicationById(Long id);

    PublicationResponse updatePublication(PublicationRequest publicationRequest, Long id);

    void deletePublication(Long id);

    Page<PublicationResponse> getAllPublicationsWithFilters(int pageNumber, int numberRecordsPerPage, String orderRecordsByField, String orderRecordsByDirection);

    PublicationsGenericResponse getPublicationByTitle(Pageable pageable, String publicationTitle);
}
