package com.jrolab.blogging_api.service.impl;

import com.jrolab.blogging_api.model.BlogPost;
import com.jrolab.blogging_api.model.Comment;
import com.jrolab.blogging_api.repo.BlogPostRepository;
import com.jrolab.blogging_api.repo.CommentRepository;
import com.jrolab.blogging_api.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
class BlogPostServiceImpl implements BlogPostService {
    private final BlogPostRepository blogPostRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<BlogPost> findAll() {
        return blogPostRepository.findAll();
    }

    @Override
    public BlogPost save(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    @Override
    public BlogPost findById(Long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
    }

    @Override
    public BlogPost update(Long id, BlogPost blogPost) {
        BlogPost existingBlogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
        existingBlogPost.setTitle(blogPost.getTitle());
        existingBlogPost.setContent(blogPost.getContent());
        return blogPostRepository.save(existingBlogPost);
    }

    @Override
    public void delete(Long id) {
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
        blogPostRepository.delete(blogPost);
    }

    @Override
    public Comment addComment(Long id, Comment comment) {
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
        comment.setBlogPost(blogPost);
        return commentRepository.save(comment);
    }

}