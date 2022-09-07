package com.seobpyo.webspringproject.service.posts;

import com.seobpyo.webspringproject.domain.posts.Posts;
import com.seobpyo.webspringproject.domain.posts.PostsRepository;
import com.seobpyo.webspringproject.web.dto.PostsListResponseDto;
import com.seobpyo.webspringproject.web.dto.PostsResponseDto;
import com.seobpyo.webspringproject.web.dto.PostsSaveRequestDto;
import com.seobpyo.webspringproject.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        entity.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                //.map(posts -> PostsListResponseDtd(posts)) 하고 같다.
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }

    //webclient 연습용 메서드
//    public String getImgUrl(){
//        String baseUrl = "";
//        WebClient webClient = WebClient.create();
//        return "hello";
//    }
}
