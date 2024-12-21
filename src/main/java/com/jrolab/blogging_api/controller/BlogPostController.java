package com.jrolab.blogging_api.controller;

import com.jrolab.blogging_api.model.BlogPost;
import com.jrolab.blogging_api.model.Comment;
import com.jrolab.blogging_api.service.BlogPostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")

public class BlogPostController {

    private final BlogPostService blogPostService;
    @GetMapping

    public ResponseEntity<List<BlogPost>> getAllPosts() {
        return ResponseEntity.ok(blogPostService.findAll());
    }

    @PostMapping

    public ResponseEntity<BlogPost> createPost(@Valid @RequestBody BlogPost blogPost) {
        return ResponseEntity.ok(blogPostService.save(blogPost));
    }

    @GetMapping("/{id}")
  
    public ResponseEntity<BlogPost> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(blogPostService.findById(id));
    }

    @PutMapping("/{id}")
   
    public ResponseEntity<BlogPost> updatePost(@PathVariable Long id, @Valid @RequestBody BlogPost blogPost) {
        return ResponseEntity.ok(blogPostService.update(id, blogPost));
    }

    @DeleteMapping("/{id}")
   
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        blogPostService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comments")
 
    public ResponseEntity<Comment> addComment(@PathVariable Long id, @Valid @RequestBody Comment comment) {
        return ResponseEntity.ok(blogPostService.addComment(id, comment));
    }
}
