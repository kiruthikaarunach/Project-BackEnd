package com.bezkoder.spring.data.mongodb.controller;

import com.bezkoder.spring.data.mongodb.model.Comment;
import com.bezkoder.spring.data.mongodb.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class CommentController {

  @Autowired
  CommentRepository commentRepository;

  @GetMapping("/comments")
  public ResponseEntity<List<Comment>> getAllComments(@RequestParam(required = false) String name) {
    try {
      List<Comment> comments = new ArrayList<Comment>();

      if (name == null)
        commentRepository.findAll().forEach(comments::add);
      else
        commentRepository.findByNameContaining(name).forEach(comments::add);

      if (comments.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(comments, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/comments/{id}")
  public ResponseEntity<Comment> getCommentById(@PathVariable("id") String id) {
    Optional<Comment> commentData = commentRepository.findById(id);

    if (commentData.isPresent()) {
      return new ResponseEntity<>(commentData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/comments")
  public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
    try {
      Date date = new Date();
      System.out.println("Date is" + date.toString());
      comment.setTime(date.toString());
//      Comment _comment = commentRepository.save(new Comment(comment.getTitle(), comment.getDescription(), false));
      Comment _comment = commentRepository.save(new Comment(comment.getName(), comment.getText(),
               comment.getBlogpost(), comment.getTime()));

      return new ResponseEntity<>(_comment, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/comments/{name}")
  public ResponseEntity<Comment> updateComment(@PathVariable("name") String name, @RequestBody Comment comment) {
    Optional<Comment> commentData = commentRepository.findByName(name);


    Date date = new Date();
    System.out.println("Date is" + date.toString());
    comment.setTime(date.toString());


    if (commentData.isPresent()) {
      Comment _comment = commentData.get();
      _comment.setName(comment.getName());
      _comment.setText(comment.getText());
      _comment.setBlogpost(comment.getBlogpost());
      _comment.setTime(comment.getTime());
      return new ResponseEntity<>(commentRepository.save(_comment), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/comments/{name}")
  public ResponseEntity<HttpStatus> deleteComment(@PathVariable("name") String name) {
    try {
      commentRepository.deleteByName(name);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/comments")
  public ResponseEntity<HttpStatus> deleteAllComments() {
    try {
      commentRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
//
//  @GetMapping("/blogposts/published")
//  public ResponseEntity<List<Comment>> findByPublished() {
//    try {
//      List<Comment> comments = commentRepository.findByPublished(true);
//
//      if (comments.isEmpty()) {
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//      }
//      return new ResponseEntity<>(comments, HttpStatus.OK);
//    } catch (Exception e) {
//      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }

  @GetMapping("/findByName")
  public ResponseEntity<List<Comment>> findByName(@RequestParam("name") String name) {
    try {
      List<Comment> names = commentRepository.findByNameContaining(name);// Replace with your logic to retrieve blog posts by blogpost
      return new ResponseEntity<>(names, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
