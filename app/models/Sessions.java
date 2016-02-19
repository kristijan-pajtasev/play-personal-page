package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sesions")
public class Sessions extends Model {

  @Id
  @Constraints.Min(10)
  public Long id;

  public String uuid;

  public String username;

  public static Finder<Long, Sessions> find = new Finder<Long, Sessions>(
    Long.class, Sessions.class
  );

  public Sessions (String uuid, String username) {
    this.uuid = uuid;
    this.username = username;
  }

}