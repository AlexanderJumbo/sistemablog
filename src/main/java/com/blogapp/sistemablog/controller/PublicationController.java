package com.blogapp.sistemablog.controller;

import com.blogapp.sistemablog.dto.Publications.PublicationRequest;
import com.blogapp.sistemablog.dto.Publications.PublicationResponse;
import com.blogapp.sistemablog.dto.Publications.PublicationsGenericResponse;
import com.blogapp.sistemablog.entity.Publication;
import com.blogapp.sistemablog.repository.PublicationRepository;
import com.blogapp.sistemablog.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    //@PreAuthorize("hasAuthority('READ_ALL_PUBLICATIONS')")
    @GetMapping
    public Page<PublicationResponse> getAllPublicationsWithFilters(
            @RequestParam(value = "p", defaultValue =  "0" , required = false) int pageNumber,
            @RequestParam(value = "n", defaultValue = "20", required = false) int numberRecordsPerPage,
            @RequestParam(value = "f", defaultValue = "id", required = false) String orderRecordsByField,
            @RequestParam(value = "d", defaultValue = "asc", required = false) String orderRecordsByDirection
    ){

        return publicationService.getAllPublicationsWithFilters(pageNumber, numberRecordsPerPage, orderRecordsByField, orderRecordsByDirection);
    }
    @GetMapping("/filter")
    public PublicationsGenericResponse getPublicationByTitle(Pageable pageable, @RequestParam(value = "title") String publicationTitle){
        return publicationService.getPublicationByTitle(pageable, publicationTitle);
    }

    /*@GetMapping
    public ResponseEntity<Page<PublicationResponse>> getAllPublications(Pageable pageable){

        Page<PublicationResponse> publications = publicationService.getAllPublications(pageable);

        if(publications.hasContent()){
            return ResponseEntity.ok(publications);
        }

        return ResponseEntity.notFound().build();
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<PublicationResponse> getPublicationById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(publicationService.getPublicationById(id));
    }

    @PostMapping
    public ResponseEntity<PublicationResponse> createPublication(@RequestBody PublicationRequest publicationRequest){
        PublicationResponse publication = publicationService.createPublication(publicationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(publication);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationResponse> updatePublication(@RequestBody PublicationRequest publicationRequest, @PathVariable Long id){
        return ResponseEntity.ok(publicationService.updatePublication(publicationRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublication(@PathVariable Long id){
        publicationService.deletePublication(id);
        return ResponseEntity.status(HttpStatus.OK).body("Publicación eliminada con éxito");
    }

}
