package com.seobpyo.webspringproject.web;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {

        return "index";
//        앞으로는 src/main/resources/templates로, 뒤의 파일 확장자는 .mustache가 붙는다.
//        즉, src/maun/resourcex/templates/index.mustache로 전환되어 View Resolver가 처리하게 됩니다.
    }
}
