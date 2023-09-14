package com.sparta.market.controller;


import com.sparta.market.dto.PostDeleteResponseDto;
import com.sparta.market.dto.PostRequestDto;
import com.sparta.market.dto.PostResponseDto;
import com.sparta.market.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 판매 게시글 작성 API
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto)
    {
        return postService.createPost(requestDto);
    }

    // 판매 게시글 전체 리스트 조회
    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    // 판매 게시글 상세 조회
    @GetMapping("/post/{id}")
    public PostResponseDto getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    // 판매 게시글 수정
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.updatePost(id, requestDto);
    }

    // 판매 게시글 삭제
    @DeleteMapping("/post/{id}")
    public PostDeleteResponseDto deletePost(@PathVariable Long id){
        return postService.deletePost(id);
    }



}
