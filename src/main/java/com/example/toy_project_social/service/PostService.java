package com.example.toy_project_social.service;

import com.example.toy_project_social.dto.PostCreateDto;
import com.example.toy_project_social.entity.Post;
import com.example.toy_project_social.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    
    private final PostRepository postRepository;

    // 게시글 생성 로직
    @Transactional
    public void createPost(PostCreateDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        // Java 컨벤션에 맞게 필드명을 camelCase로 변경하는 것을 추천합니다. (author_id -> authorId)
        post.setAuthor_id(dto.getAuthor_id());
        // @PrePersist 어노테이션을 사용하면 시간 자동 생성이 가능하여 이 코드를 생략할 수 있습니다.
        // post.setCreatedTime(LocalDateTime.now());
        // post.setUpdatedTime(LocalDateTime.now());
        this.postRepository.save(post);
    }
    
    // 게시글 조회 로직
    @Transactional(readOnly = true) // 읽기 전용 트랜잭션은 성능상 이점이 있습니다.
    public List<Post> listPosts() {
        return this.postRepository.findAll();
    }
    
    // 게시글 상세 조회 로직
    @Transactional(readOnly = true)
    public Post detailPost(Integer id) {
        // ID에 해당하는 게시글이 없으면 예외를 발생시켜 처리합니다.
        return this.postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    }

    // 게시글 수정 로직
    @Transactional
    public void updatePost(PostCreateDto dto) {
        // .get() 대신 orElseThrow()를 사용하여 안정성을 높입니다.
        Post post = this.postRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("수정할 게시글이 없습니다. id=" + dto.getId()));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        // @PreUpdate 어노테이션을 사용하면 시간 자동 업데이트가 가능하여 이 코드를 생략할 수 있습니다.
        // post.setUpdatedTime(LocalDateTime.now());
        this.postRepository.save(post);
    }

    // 게시글 삭제 로직
    @Transactional
    public void deletePost(Integer id) {
        this.postRepository.deleteById(id);
    }
}
