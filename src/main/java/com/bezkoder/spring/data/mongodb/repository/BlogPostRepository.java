package com.bezkoder.spring.data.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.data.mongodb.model.BlogPost;

public interface BlogPostRepository extends MongoRepository<BlogPost, String> {
  List<BlogPost> findByPublished(boolean published);
  List<BlogPost> findByTitleContaining(String title);
  Optional<BlogPost> findByTitle(String title);
  void deleteByTitle(String title);
}
