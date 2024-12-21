package com.jrolab.blogging_api.service;
import com.jrolab.blogging_api.model.BlogPost;
import com.jrolab.blogging_api.model.Comment;

import java.util.List;

public interface BlogPostService {
    List<BlogPost> findAll();
    BlogPost save(BlogPost blogPost);
    BlogPost findById(Long id);
    BlogPost update(Long id, BlogPost blogPost);
    void delete(Long id);
    Comment addComment(Long id, Comment comment);
}