package com.seobpyo.webspringproject.service.posts;

import com.seobpyo.webspringproject.web.domain.posts.PostsRepository;
import com.seobpyo.webspringproject.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class PostsService {
    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
