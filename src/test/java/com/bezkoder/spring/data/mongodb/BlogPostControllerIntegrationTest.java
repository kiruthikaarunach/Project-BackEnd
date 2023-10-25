//package com.bezkoder.spring.data.mongodb;
//
//import com.bezkoder.spring.data.mongodb.model.BlogPost;
//import com.bezkoder.spring.data.mongodb.repository.BlogPostRepository;
//import com.bezkoder.spring.data.mongodb.controller.BlogPostController;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Date;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@WebMvcTest(BlogPostController.class)
//public class BlogPostControllerIntegrationTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    private BlogPostController blogPostController;
//
//    @Autowired
//    private BlogPostRepository blogPostRepository;
//
//    @BeforeEach
//    public void setUp() {
//        blogPostRepository.deleteAll();
//    }
//
//    @Test
//    public void testCRUDOperations() {
//        // Create
//
//        BlogPost blogPost = new BlogPost("Sample Post 1", "Description 1", "Content 1", "Author 1", new Date().toString(), true);
//        ResponseEntity<BlogPost> createResponse = blogPostController.createBlogPost(blogPost);
//        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
//        String postId = createResponse.getBody().getId();
//
//        // Read
//        ResponseEntity<BlogPost> readResponse = blogPostController.getBlogPostById(postId);
//        assertEquals(HttpStatus.OK, readResponse.getStatusCode());
//
//        // Update
//        blogPost = readResponse.getBody();
//        blogPost.setTitle("Updated Title");
//        ResponseEntity<BlogPost> updateResponse = blogPostController.updateBlogPost(blogPost.getTitle(), blogPost);
//        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
//
//        // Delete
//        ResponseEntity<HttpStatus> deleteResponse = blogPostController.deleteBlogPost(blogPost.getTitle());
//        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());
//
//        // Verify the post is deleted
//        ResponseEntity<BlogPost> deletedPost = blogPostController.getBlogPostById(postId);
//        assertEquals(HttpStatus.NOT_FOUND, deletedPost.getStatusCode());
//    }
//}
//
