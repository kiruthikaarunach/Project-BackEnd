package com.bezkoder.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "blogposts")
public class BlogPost {
  @Id
  private String id;

  private String title;
  private String description;

  private String content;
  private String author;

  private LocalDateTime localDateTime;
  private String time;
  public BlogPost(String time) {
    this.time = time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getTime() {
    return time;
  }

  private boolean published;

  public BlogPost() {

  }

  public BlogPost(String title, String description, String content, String author, String time,  boolean published) {
    this.title = title;
    this.description = description;
    this.published = published;
    this.content = content;
    this.author = author;
    this.time = time;
    this.published = published;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean isPublished) {
    this.published = isPublished;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  public void setLocalDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }


  @Override
  public String toString() {
    return "BlogPost [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
  }
}
