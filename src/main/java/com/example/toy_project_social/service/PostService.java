package com.example.toy_project_social.service;

import com.example.toy_project_social.dto.PostCreateDto;
import com.example.toy_project_social.entity.Post;
import com.example.toy_project_social.entity.SiteUser;
import com.example.toy_project_social.repository.PostRepository;
import com.example.toy_project_social.repository.SiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    
    private final PostRepository postRepository;
    private final SiteUserRepository siteUserRepository; // UserRepository 주입

    // 게시글 생성 로직
    @Transactional
    public void createPost(PostCreateDto dto, SiteUser author) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAuthor(author);
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
    public void updatePost(Integer id, String title, String content) {
        // .get() 대신 orElseThrow()를 사용하여 안정성을 높입니다.
        Post post = this.postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("수정할 게시글이 없습니다. id=" + id));

        post.setTitle(title);
        post.setContent(content);
        this.postRepository.save(post);
    }

    // 게시글 삭제 로직
    @Transactional
    public void deletePost(Integer id) {
        this.postRepository.deleteById(id);
    }
}
