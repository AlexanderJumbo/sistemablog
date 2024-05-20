package com.blogapp.sistemablog.service;

import com.blogapp.sistemablog.dto.Followers.FollowerRequest;
import com.blogapp.sistemablog.entity.Follower;
import com.blogapp.sistemablog.entity.security.User;
import com.blogapp.sistemablog.exception.ObjectNotFoundException;
import com.blogapp.sistemablog.repository.FollowerRepository;
import com.blogapp.sistemablog.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowerServiceImpl implements FollowerService{

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createFollowUpRequest(FollowerRequest followerRequest) {

        Follower findRequestBD = followerRepository.findFollowerRequestById(followerRequest.getIdUserFollower());
        System.out.println("solicitud " + findRequestBD);
        if(findRequestBD != null){
            followerRepository.delete(findRequestBD);
            return "Dejastes de seguir a esta usuario";
        }
        Follower newFollowUpRequest = mapDTO(followerRequest);

        followerRepository.save(newFollowUpRequest);
        return "Nuevo usuario seguido";
    }

    private Follower mapDTO(FollowerRequest followerRequest) {

        User userFollowing = userRepository.findById(followerRequest.getIdFollowedUser())
                .orElseThrow(() -> new ObjectNotFoundException("User not found. User Id: " + followerRequest.getIdFollowedUser()));
        User userFollower = userRepository.findById(followerRequest.getIdUserFollower())
                .orElseThrow(() -> new ObjectNotFoundException("User not found. User Id: " + followerRequest.getIdUserFollower()));

        Follower follower = new Follower();
        follower.setFollowing(userFollowing);
        follower.setFollower(userFollower);

        return follower;
    }
}
