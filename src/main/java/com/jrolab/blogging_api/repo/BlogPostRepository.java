package com.jrolab.blogging_api.repo;

import com.jrolab.blogging_api.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {}