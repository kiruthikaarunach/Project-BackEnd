package com.bezkoder.spring.data.mongodb.repository;

import com.bezkoder.spring.data.mongodb.model.BlogPost;
import com.bezkoder.spring.data.mongodb.model.Comment;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
  //List<Comment> findByPublished(boolean published);
  List<Comment> findByNameContaining(String name);
    Optional<Comment> findByName(String name);
  void deleteByName(String name);
 // List<Comment> findByComment(String comment);

}
