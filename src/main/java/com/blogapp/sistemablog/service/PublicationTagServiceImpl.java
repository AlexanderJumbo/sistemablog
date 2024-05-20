package com.blogapp.sistemablog.service;

import com.blogapp.sistemablog.entity.Publication;
import com.blogapp.sistemablog.entity.Publication_Tag;
import com.blogapp.sistemablog.entity.Tag;
import com.blogapp.sistemablog.repository.PublicationTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationTagServiceImpl implements PublicationTagService{

    @Autowired
    private PublicationTagRepository publicationTagRepository;

    @Override
    public List<Publication_Tag> createPublicationTags(List<Tag> tagsFoundBD, Publication publication) {
        //Obtenemos las etiquetas de la publicación a actualizar, en caso de existir
        List<Publication_Tag> recordsToDelete = publicationTagRepository.findByPublicationId(publication.getId());
        //Borramos las etiquetas encontradas, para luego ingresar las etiquetas actualizadas
        publicationTagRepository.deleteAll(recordsToDelete);

        List<Publication_Tag> publicationTagList = new ArrayList<>();
        
        for (Tag tag : tagsFoundBD) {
            Publication_Tag publicationTag = new Publication_Tag();
            publicationTag.setTag(tag);
            publicationTag.setPublication(publication);
            publicationTagRepository.save(publicationTag);
            publicationTagList.add(publicationTag);
        }
        return publicationTagList;
    }
}
