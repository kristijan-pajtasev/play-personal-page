package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="employment")
public class Employment extends Model {

  @Id
  private Long id;

  private String companyName;

  private String roleName;

  @Formats.DateTime(pattern="dd/MM/yyyy")
  private Date startDate;

  @Formats.DateTime(pattern="dd/MM/yyyy")
  private Date endDate;

  private String companyDescription;

  private String description;

  private String responsibilities;

  private String technologies;

  private String tags;

  public static Finder<Long, Employment> find = new Finder<Long, Employment>(
    Long.class, Employment.class
  );

  public String[] getTechnologiesAsArray() {
    return this.technologies.split("\n");
  }

  public String[] getResponsibilitiesAsArray() {
    return this.responsibilities.split("\n");
  }

  public Employment(String companyName, Date startDate, Date endDate, String companyDescription, String description, String responsibilities, String technologies, String tags, String roleName) {
    this.companyName = companyName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.companyDescription = companyDescription;
    this.description = description;
    this.responsibilities = responsibilities;
    this.technologies = technologies;
    this.tags = tags;
  }

  public Employment() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getCompanyDescription() {
    return companyDescription;
  }

  public void setCompanyDescription(String companyDescription) {
    this.companyDescription = companyDescription;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getResponsibilities() {
    return responsibilities;
  }

  public void setResponsibilities(String responsibilities) {
    this.responsibilities = responsibilities;
  }

  public String getTechnologies() {
    return technologies;
  }

  public void setTechnologies(String technologies) {
    this.technologies = technologies;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
}