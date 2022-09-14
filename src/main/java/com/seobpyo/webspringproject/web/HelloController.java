package com.seobpyo.webspringproject.web;

import com.seobpyo.webspringproject.service.posts.PostsService;
import com.seobpyo.webspringproject.web.dto.ErrorResponse;
import com.seobpyo.webspringproject.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.*;

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
    public void PostImgRequest() {

    }
    @ExceptionHandler(value = Exception.class)
    public ErrorResponse handle(Exception e){
        return new ErrorResponse("Error");
    }

}
