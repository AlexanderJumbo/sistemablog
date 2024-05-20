package com.blogapp.sistemablog.service;

import com.blogapp.sistemablog.dto.Category.CategoryResponse;
import com.blogapp.sistemablog.dto.Publications.PublicationsGenericResponse;
import com.blogapp.sistemablog.dto.Tag.PublicationTagResponse;
import com.blogapp.sistemablog.dto.Publications.PublicationRequest;
import com.blogapp.sistemablog.dto.Publications.PublicationResponse;
import com.blogapp.sistemablog.entity.Category;
import com.blogapp.sistemablog.entity.Publication;
import com.blogapp.sistemablog.entity.Publication_Tag;
import com.blogapp.sistemablog.entity.Tag;
import com.blogapp.sistemablog.entity.security.User;
import com.blogapp.sistemablog.exception.ObjectNotFoundException;
import com.blogapp.sistemablog.repository.PublicationRepository;
import com.blogapp.sistemablog.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService{

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PublicationTagService publicationTagService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public Page<PublicationResponse> getAllPublications(Pageable pageable) {
        return publicationRepository.findAll(pageable).map(this::mapDTO);
    }

    @Override
    public PublicationResponse createPublication(PublicationRequest publicationRequest) {

        User user = userService.findByUsername(publicationRequest.getAuthor())
                .orElseThrow(() -> new ObjectNotFoundException("User not found. Username: " + publicationRequest.getAuthor()));

       List<Tag> findTagsBD = tagService.findTagsByName(publicationRequest.getTags());

        Category category = categoryService.findByName(publicationRequest.getCategory());
        if(category == null)  category = categoryService.createCategory(publicationRequest.getCategory());
        System.out.println("Category " + category);

        Publication publication = new Publication();
        publication.setTitle(publicationRequest.getTitle());
        publication.setSutitle(publicationRequest.getSutitle());
        publication.setContent(publicationRequest.getContent());
        publication.setPublishDate(new Date());
        publication.setAuthor(user);
        publication.setCategory(category);

        Publication newPublication = publicationRepository.save(publication);
        List<Publication_Tag> publicationTag = publicationTagService.createPublicationTags(findTagsBD, newPublication);
        newPublication.setTagList(publicationTag);

        return mapDTO(newPublication);//publication
    }

    @Override
    public PublicationResponse getPublicationById(Long id) {
        Publication publication = publicationRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Publication not found. Publication Id: " + id));
        return mapDTO(publication);
    }

    @Override
    public PublicationResponse updatePublication(PublicationRequest publicationRequest, Long id) {

        Publication publication = publicationRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Publication not found. Publication Id: " + id));

        List<Tag> findTagsBD = tagService.findTagsByName(publicationRequest.getTags());

        Category category = categoryService.findByName(publicationRequest.getCategory());
        if(category == null)  category = categoryService.createCategory(publicationRequest.getCategory());
        System.out.println("Category " + category);

        publication.setTitle(publicationRequest.getTitle());
        publication.setSutitle(publicationRequest.getSutitle());
        publication.setContent(publicationRequest.getContent());
        publication.setPublishDate(new Date());
        publication.setCategory(category);

        Publication publicationUpdate =  publicationRepository.save(publication);

        List<Publication_Tag> publicationTag = publicationTagService.createPublicationTags(findTagsBD, publicationUpdate);

        publicationUpdate.setTagList(publicationTag);

        return mapDTO(publicationUpdate);//publication
    }

    @Override
    public void deletePublication(Long id) {

        Publication publication = publicationRepository.findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException("Publication not found. " + id));
        publicationRepository.delete(publication);
    }

    @Override
    //List<PublicationResponse>
    public Page<PublicationResponse> getAllPublicationsWithFilters(int pageNumber, int numberRecordsPerPage, String orderRecordsByField, String orderRecordsByDirection) {
        Sort sort = orderRecordsByDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderRecordsByField).ascending() : Sort.by(orderRecordsByField).descending();
        Pageable pageable = PageRequest.of(pageNumber, numberRecordsPerPage, sort);

        return publicationRepository.findAll(pageable).map(this::mapDTO);
    }

    @Override
    public PublicationsGenericResponse getPublicationByTitle(Pageable pageable, String publicationTitle) {

        Page<Publication> publicationsFound = publicationRepository.getPublicationByTitle(pageable, publicationTitle);

        List<Publication> getPublicationFound = publicationsFound.getContent();

        List<PublicationResponse> publications = getPublicationFound.stream().map(this::mapDTO).collect(Collectors.toList());

        return mapPublicationsGenericResponse(publications, publicationsFound);
    }

    private static PublicationsGenericResponse mapPublicationsGenericResponse(List<PublicationResponse> publications, Page<Publication> publicationsFound) {
        PublicationsGenericResponse publicationsGenericResponse = new PublicationsGenericResponse();
        publicationsGenericResponse.setPublications(publications);
        publicationsGenericResponse.setPageNumber(publicationsFound.getNumber());
        publicationsGenericResponse.setSizePage(publicationsFound.getSize());
        publicationsGenericResponse.setTotalRecords(publicationsFound.getTotalElements());
        publicationsGenericResponse.setTotalPages(publicationsFound.getTotalPages());
        publicationsGenericResponse.setLastPage(publicationsFound.isLast());
        return publicationsGenericResponse;
    }

    /*@Override
    public Page<PublicationResponse> getPublicationByTitle(Pageable pageable, String publicationTitle) {

        Page<Publication> publicationsFound = publicationRepository.getPublicationByTitle(pageable, publicationTitle);

        List<Publication> getPublicationFound = publicationsFound.getContent();

        List<PublicationResponse> publications = getPublicationFound.stream().map(this::mapDTO).collect(Collectors.toList());
        return new PageImpl<>(publications);
    }*/

    private PublicationResponse mapDTO(Publication publication) {
        System.out.println("publication " + publication.getCategory());
        PublicationResponse publicationResponse = new PublicationResponse();
        publicationResponse.setId(publication.getId());
        publicationResponse.setTitle(publication.getTitle());
        publicationResponse.setSutitle(publication.getSutitle());
        publicationResponse.setContent(publication.getContent());
        publicationResponse.setPublishDate(publication.getPublishDate());
        publicationResponse.setAuthor(publication.getAuthor().getUsername());
        publicationResponse.setComments(publication.getComments());
        publicationResponse.setLikesInfo(publication.getAllLikesToPublications());
        publicationResponse.setLikesCount(publication.getLikesInfo().size());
        publicationResponse.setTags(mapPublicationTagResponse(publication));
        publicationResponse.setCategory(mapCategory(publication));

        return publicationResponse;
    }

    private static PublicationTagResponse mapPublicationTagResponse(Publication publication) {
        PublicationTagResponse publicationTagResponse = new PublicationTagResponse();
        publicationTagResponse.setTags(publication.getTagList().stream().map(tag -> tag.getTag().getName()).collect(Collectors.toList()));
        return publicationTagResponse;
    }

    private static CategoryResponse mapCategory(Publication publication) {
        CategoryResponse categoryResp = new CategoryResponse();
        categoryResp.setId(publication.getCategory().getId());
        categoryResp.setName(publication.getCategory().getName());
        return categoryResp;
    }
}
