package com.bezkoder.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "comments")
public class Comment {
  @Id
  private String id;

  private String name;
  private String text;

  private String blogpost;

  private LocalDateTime localDateTime;
  private String time;
  public Comment(String time) {
    this.time = time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getTime() {
    return time;
  }

  public Comment() {

  }

  public Comment(String name, String text, String blogpost, String time ) {
    this.name = name;
    this.text = text;

    this.blogpost = blogpost;
    this.time = time;

  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getBlogpost() {
    return blogpost;
  }

  public void setBlogpost(String blogpost) {
    this.blogpost = blogpost;
  }

  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  public void setLocalDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }


  @Override
  public String toString() {
    return "Comment [id=" + id + ", title=" + name + ", desc=" + text + "]";
  }
}
