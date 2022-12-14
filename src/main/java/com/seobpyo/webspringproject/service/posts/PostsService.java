package com.seobpyo.webspringproject.service.posts;

import com.seobpyo.webspringproject.domain.posts.Posts;
import com.seobpyo.webspringproject.domain.posts.PostsRepository;
import com.seobpyo.webspringproject.web.dto.PostsListResponseDto;
import com.seobpyo.webspringproject.web.dto.PostsResponseDto;
import com.seobpyo.webspringproject.web.dto.PostsSaveRequestDto;
import com.seobpyo.webspringproject.web.dto.PostsUpdateRequestDto;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final WebClient webClient;

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

    //    webclient 연습용 메서드
    public String getImgUrl(){
        String baseUrl = "localhost:9999/";
        String defaultValue = "default";
        Mono<String> result = webClient.get()
                .uri(baseUrl + "hello")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
        return result.blockOptional().orElse(defaultValue);
    }

    public String postImg(MultipartFile file) {
        if(file.isEmpty()){
            return "Error occur";
        }
        String baseUrl = "localhost:9999/file_upload";
        String defaultValue = "default";
        HttpHeaders headers = new HttpHeaders();

        Mono<String> result = webClient.post()
                .uri(baseUrl)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(Mono.just(file), MultipartFile.class)
                .retrieve()
                .bodyToMono(String.class);
        return result.blockOptional().orElse(defaultValue);
    }

}
