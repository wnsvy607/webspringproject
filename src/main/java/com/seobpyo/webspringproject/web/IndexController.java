package com.seobpyo.webspringproject.web;

import com.seobpyo.webspringproject.config.auth.LoginUser;
import com.seobpyo.webspringproject.config.auth.dto.SessionUser;
import com.seobpyo.webspringproject.service.posts.PostsService;
import com.seobpyo.webspringproject.web.dto.PostsResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
//        앞으로는 src/main/resources/templates로, 뒤의 파일 확장자는 .mustache가 붙는다.
//        즉, src/maun/resourcex/templates/index.mustache로 전환되어 View Resolver가 처리하게 됩니다.
    }

    @GetMapping("posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
