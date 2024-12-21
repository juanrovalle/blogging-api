package com.jrolab.blogging_api.repo;

import com.jrolab.blogging_api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
