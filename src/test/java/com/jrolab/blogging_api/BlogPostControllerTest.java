package com.jrolab.blogging_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrolab.blogging_api.model.BlogPost;
import com.jrolab.blogging_api.model.Comment;
import com.jrolab.blogging_api.repo.BlogPostRepository;

@SpringBootTest
@AutoConfigureMockMvc
class BlogPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
@Autowired
private BlogPostRepository blogPostRepository;

    @Test
    void testCreateBlogPost() throws Exception {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle("Test Post");
        blogPost.setContent("This is a test content.");

        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(blogPost)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Post"))
                .andExpect(jsonPath("$.content").value("This is a test content."));
    }

    @Test
    void testGetBlogPostById() throws Exception {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle("Sample Post");
        blogPost.setContent("Sample Content");
        BlogPost savedPost = blogPostRepository.save(blogPost);

        mockMvc.perform(get("/api/posts/" + savedPost.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sample Post"))
                .andExpect(jsonPath("$.content").value("Sample Content"));
    }

    @Test
    void testAddComment() throws Exception {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle("Post with Comment");
        blogPost.setContent("Content");
        BlogPost savedPost = blogPostRepository.save(blogPost);

        Comment comment = new Comment();
        comment.setText("This is a comment.");

        mockMvc.perform(post("/api/posts/" + savedPost.getId() + "/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("This is a comment."));
    }

    @Test
    void testDeleteBlogPost() throws Exception {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle("To Delete");
        blogPost.setContent("Content");
        BlogPost savedPost = blogPostRepository.save(blogPost);

        mockMvc.perform(delete("/api/posts/" + savedPost.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testValidationError() throws Exception {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle("");
        blogPost.setContent("");

        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(blogPost)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0]").value("title: Title must not be empty"));
    }
}