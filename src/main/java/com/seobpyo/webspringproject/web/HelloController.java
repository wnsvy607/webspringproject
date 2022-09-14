package com.seobpyo.webspringproject.web;

import com.seobpyo.webspringproject.service.posts.PostsService;
import com.seobpyo.webspringproject.web.dto.ErrorResponse;
import com.seobpyo.webspringproject.web.dto.HelloResponseDto;
import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class HelloController {

    private final PostsService postsService;

    public HelloController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }


    @GetMapping("/hello/request")
    public String HelloRequest() {
        return postsService.getImgUrl();
    }

    @PostMapping("/img")
    public String PostImgRequest(@NotNull @RequestParam MultipartFile file) {
        return postsService.postImg(file);
    }

}
