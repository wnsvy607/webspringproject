package com.seobpyo.webspringproject.web.domain.posts;


import com.seobpyo.webspringproject.web.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//entity class 에서는 절대 setter를 만들지 않는다.
//필요하면 따로 메소드를 명확하게 정의해서 사용한다.
//값 변경이 필요한 경우  해당 이벤트에 맞는 public 메소드를 호출해 변경하는것이 전제
@Getter
@NoArgsConstructor
@Entity     //주요 어노테이션(필수 어노테이션은 클래스에 가깝게 둔다)
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
