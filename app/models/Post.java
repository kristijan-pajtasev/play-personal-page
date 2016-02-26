package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

@Entity
@Table(name="posts")
public class Post extends Model {

  @Id
  @Constraints.Min(10)
  public Long id;

  public String title;

  @Column(unique=true)
  public String urlTitle;

  @Column(columnDefinition = "TEXT")
  public String post;

  public String tags;

  public String description;

  @Formats.DateTime(pattern="dd/MM/yyyy")
  public Date created;

  @Formats.DateTime(pattern="dd/MM/yyyy")
  public Date published;

//  public String getPost() {
//    return "<pre>" + escapeHtml4(this.post)+ "</pre>";
//  }
  public String getPost() {
    return this.post
            .replaceAll("<", "&lt;")
            .replaceAll(">", "&gt;")
            .replaceAll("\\[", "<")
            .replaceAll("\\]", ">");
  }

//  @Constraints.Required
//  public String name;
//
//  public boolean done;
  
//  @Formats.DateTime(pattern="dd/MM/yyyy")
//  public Date dueDate = new Date();

  public Post(String title, String urlTitle, String post) {
    this.title = title;
    this.urlTitle = urlTitle;
    this.post = post;
    this.tags = "";
    this.description = "";
    this.created = new Date();
    this.published = new Date();
  }

  public Post(String title, String urlTitle, String post, String tags, String description) {
    this.title = title;
    this.urlTitle = urlTitle;
    this.post = post;
    this.tags = tags;
    this.description = description;
    this.created = new Date();
    this.published = new Date();
  }
  
  public static Finder<Long,Post> find = new Finder<Long,Post>(
    Long.class, Post.class
  );


  public void setId(long id) {
    this.id = id;
  }
}