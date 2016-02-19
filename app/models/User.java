package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="users")
public class User extends Model {

  @Id
  @Constraints.Min(10)
  public Long id;

  public String email;

  public String username;

  public String password;

  public static Finder<Long, User> find = new Finder<Long, User>(
    Long.class, User.class
  );

  public static boolean authenticate(String username, String password) {
    return true;
  }

}