package com.sparta.market.service;

import com.sparta.market.dto.PostDeleteResponseDto;
import com.sparta.market.dto.PostRequestDto;
import com.sparta.market.dto.PostResponseDto;
import com.sparta.market.entity.Post;
import com.sparta.market.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        Post savePost = postRepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(savePost);
        return postResponseDto;
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAll().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPostById(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("데이터가 없습니다.")
        );
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = findPost(id);
        post.update(requestDto);
        return new PostResponseDto(post);
    }

    public PostDeleteResponseDto deletePost(Long id) {
        Post post = findPost(id);
        postRepository.delete(post);
        PostDeleteResponseDto deleteResponseDto = new PostDeleteResponseDto("성공적으로 삭제 되었습니다.");
        return deleteResponseDto;
    }
}
