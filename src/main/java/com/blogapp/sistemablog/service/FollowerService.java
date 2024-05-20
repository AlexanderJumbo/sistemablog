package com.blogapp.sistemablog.service;

import com.blogapp.sistemablog.dto.Followers.FollowerRequest;

public interface FollowerService {
    String createFollowUpRequest(FollowerRequest followerRequest);
}
