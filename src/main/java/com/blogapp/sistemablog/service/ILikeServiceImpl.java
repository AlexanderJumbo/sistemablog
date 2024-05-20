package com.blogapp.sistemablog.service;

import com.blogapp.sistemablog.dto.ILike.ILikeResponse;
import com.blogapp.sistemablog.entity.ILike;
import com.blogapp.sistemablog.entity.Publication;
import com.blogapp.sistemablog.entity.security.User;
import com.blogapp.sistemablog.exception.ObjectNotFoundException;
import com.blogapp.sistemablog.repository.ILikeRepository;
import com.blogapp.sistemablog.repository.PublicationRepository;
import com.blogapp.sistemablog.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ILikeServiceImpl implements ILikeService{

    @Autowired
    private ILikeRepository iLikeRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ILikeResponse giveLikeToPublication(Long publicationId, Long userId) {

        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ObjectNotFoundException("Publication not found. Publication Id: " + publicationId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found. User Id: " + userId));

        List<ILike> likes = iLikeRepository.findLikesListByUser(publication.getId(), user.getId());
        if(!likes.isEmpty()){
            System.out.println("No esta vacío" + likes.get(0));
            ILike like = new ILike();
            like.setId(likes.get(0).getId());
            like.setUser(likes.get(0).getUser());
            like.setPublication(likes.get(0).getPublication());
            like.setLikeDate(likes.get(0).getLikeDate());
            iLikeRepository.delete(like);
            throw new ObjectNotFoundException("User gave it a like this publication before");
        }

        ILike newLike = new ILike();
        newLike.setUser(user);
        newLike.setPublication(publication);
        newLike.setLikeDate(new Date());

        iLikeRepository.save(newLike);
        return mapDto(newLike);
    }

    private ILikeResponse mapDto(ILike newLike) {

        ILikeResponse iLikeResponse = new ILikeResponse();
        iLikeResponse.setId(newLike.getId());
        iLikeResponse.setUser(newLike.getUser().getUsername());
        iLikeResponse.setPublication(newLike.getPublication().getTitle());
        iLikeResponse.setLikeDate(newLike.getLikeDate());

        return iLikeResponse;
    }
}
