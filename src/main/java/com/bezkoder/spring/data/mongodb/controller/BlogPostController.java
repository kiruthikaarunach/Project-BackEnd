package com.bezkoder.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.data.mongodb.model.BlogPost;
import com.bezkoder.spring.data.mongodb.repository.BlogPostRepository;

//@CrossOrigin(origins = "http://localhost:8081")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class BlogPostController {

  @Autowired
  BlogPostRepository blogpostRepository;

  @GetMapping("/blogposts")
  public ResponseEntity<List<BlogPost>> getAllBlogPosts(@RequestParam(required = false) String title) {
    try {
      List<BlogPost> blogposts = new ArrayList<BlogPost>();

      if (title == null)
        blogpostRepository.findAll().forEach(blogposts::add);
      else
        blogpostRepository.findByTitleContaining(title).forEach(blogposts::add);

      if (blogposts.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(blogposts, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/blogposts/{id}")
  public ResponseEntity<BlogPost> getBlogPostById(@PathVariable("id") String id) {
    Optional<BlogPost> blogpostData = blogpostRepository.findById(id);

    if (blogpostData.isPresent()) {
      return new ResponseEntity<>(blogpostData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/blogposts")
  public ResponseEntity<BlogPost> createBlogPost(@RequestBody BlogPost blogpost) {
    try {
      Date date = new Date();
      System.out.println("Date is" + date + "tostring" + date.toString());
      blogpost.setTime(date.toString());
//      BlogPost _blogpost = blogpostRepository.save(new BlogPost(blogpost.getTitle(), blogpost.getDescription(), false));
      BlogPost _blogpost = blogpostRepository.save(new BlogPost(blogpost.getTitle(), blogpost.getDescription(),
              blogpost.getContent(), blogpost.getAuthor(), blogpost.getTime(), blogpost.isPublished()));

      return new ResponseEntity<>(_blogpost, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/blogposts/{title}")
  public ResponseEntity<BlogPost> updateBlogPost(@PathVariable("title") String title, @RequestBody BlogPost blogpost) {
    Optional<BlogPost> blogpostData = blogpostRepository.findByTitle(title);

    Date date = new Date();
    System.out.println("Date is" + date.toString());
    blogpost.setTime(date.toString());

    if (blogpostData.isPresent()) {
      BlogPost _blogpost = blogpostData.get();
      _blogpost.setTitle(blogpost.getTitle());
      _blogpost.setDescription(blogpost.getDescription());
      _blogpost.setContent(blogpost.getContent());
      _blogpost.setAuthor(blogpost.getAuthor());
      _blogpost.setTime(blogpost.getTime());
      _blogpost.setPublished(blogpost.isPublished());
      return new ResponseEntity<>(blogpostRepository.save(_blogpost), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/blogposts/{title}")
  public ResponseEntity<HttpStatus> deleteBlogPost(@PathVariable("title") String title) {
    try {
      blogpostRepository.deleteByTitle(title);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/blogposts")
  public ResponseEntity<HttpStatus> deleteAllBlogPosts() {
    try {
      blogpostRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/blogposts/published")
  public ResponseEntity<List<BlogPost>> findByPublished() {
    try {
      List<BlogPost> blogposts = blogpostRepository.findByPublished(true);

      if (blogposts.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(blogposts, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/findByTitle")
  public ResponseEntity<List<BlogPost>> findByTitle(@RequestParam("title") String title) {
    try {
      List<BlogPost> blogPosts = blogpostRepository.findByTitleContaining(title);// Replace with your logic to retrieve blog posts by title
      return new ResponseEntity<>(blogPosts, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
