package com.blogapp.sistemablog.service;

import com.blogapp.sistemablog.dto.Category.CategoryResponse;
import com.blogapp.sistemablog.dto.PublicationsPerCategorieResponse;
import com.blogapp.sistemablog.dto.Tag.PublicationTagResponse;
import com.blogapp.sistemablog.dto.Publications.PublicationResponse;
import com.blogapp.sistemablog.entity.Category;
import com.blogapp.sistemablog.entity.Publication;
import com.blogapp.sistemablog.exception.ObjectNotFoundException;
import com.blogapp.sistemablog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findByName(String category) {
        return categoryRepository.findByName(category);
    }

    @Override
    public Category createCategory(String category) {
        Category newCategory = new Category();
        newCategory.setName(category);
        newCategory.setStatus(true);

        return categoryRepository.save(newCategory);
    }

    @Override
    public PublicationsPerCategorieResponse getPublicationsByCategory(int pageNumber, int numberRecordsPerPage, String orderRecordsByField, String orderRecordsByDirection, Long categoryId) {

        Sort sorteElements = orderRecordsByDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderRecordsByField).ascending() :  Sort.by(orderRecordsByField).descending();
        Pageable pageable = PageRequest.of(pageNumber, numberRecordsPerPage, sorteElements);

        Page<Publication> publicationswithoutFormat = categoryRepository.findPublicationsByCategoryId(pageable, categoryId, orderRecordsByField, orderRecordsByDirection);
        PublicationsPerCategorieResponse newPublicationsPerCategorie = new PublicationsPerCategorieResponse();

        List<PublicationResponse> publicationResponseList = publicationswithoutFormat.stream().map(publication -> {
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
            publicationResponse.setTags(getPublicationTagResponse(publication));
            publicationResponse.setCategory(getCategoryResponse(publication));

            return publicationResponse;
        }).collect(Collectors.toList());

        newPublicationsPerCategorie.setPublications(publicationResponseList);

        newPublicationsPerCategorie.setNumeroPagina(publicationswithoutFormat.getNumber());
        newPublicationsPerCategorie.setMedidaPagina(publicationswithoutFormat.getSize());
        newPublicationsPerCategorie.setTotalElementos(publicationswithoutFormat.getTotalElements());
        newPublicationsPerCategorie.setTotalPaginas(publicationswithoutFormat.getTotalPages());
        newPublicationsPerCategorie.setUltima(publicationswithoutFormat.isLast());

        return newPublicationsPerCategorie /*new PageImpl<>(array)*/;
    }

    @Override
    public CategoryResponse createCategoryFromController(String name) {
        Category category = new Category();
        category.setName(name);
        category.setStatus(true);
        Category categoryCreated = categoryRepository.save(category);
        return convertCategoryToCategoryResponse(categoryCreated);
    }

    @Override
    public CategoryResponse updateCategory(String name, Long categoryId) {
        Category categoryExistBD = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found. Category Id: " + categoryId));
        if(categoryExistBD != null) categoryExistBD.setName(name);
        return convertCategoryToCategoryResponse(categoryRepository.save(categoryExistBD));
    }

    @Override
    public CategoryResponse changeStatusCategory(Long categoryId) {
        Category categoryExistBD = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found. Category Id: " + categoryId));
        categoryExistBD.setStatus(!categoryExistBD.isStatus());
        return convertCategoryToCategoryResponse(categoryRepository.save(categoryExistBD));
    }

    private static CategoryResponse convertCategoryToCategoryResponse(Category categoryCreated) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(categoryCreated.getId());
        categoryResponse.setName(categoryCreated.getName());
        categoryResponse.setStatus(categoryCreated.isStatus());
        return categoryResponse;
    }

    private static CategoryResponse getCategoryResponse(Publication publication) {
        CategoryResponse categoryResponse = convertCategoryToCategoryResponse(publication.getCategory());
        return categoryResponse;
    }

    private static PublicationTagResponse getPublicationTagResponse(Publication publication) {
        PublicationTagResponse publicationTagResponse = new PublicationTagResponse();
        publicationTagResponse.setTags(publication.getTagList().stream().map(publicationTag -> publicationTag.getTag().getName()).collect(Collectors.toList()));
        return publicationTagResponse;
    }
}
