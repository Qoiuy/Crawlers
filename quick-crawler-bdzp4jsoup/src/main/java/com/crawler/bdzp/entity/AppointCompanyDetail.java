package com.crawler.bdzp.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "appoint_company_detail")
public class AppointCompanyDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private String companyNature;
  private String companyScale;
  private String companyCategory;
  private String companyAddress;
  private String companyContact;
  private java.sql.Timestamp addtime;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCompanyNature() {
    return companyNature;
  }

  public void setCompanyNature(String companyNature) {
    this.companyNature = companyNature;
  }

  public String getCompanyScale() {
    return companyScale;
  }

  public void setCompanyScale(String companyScale) {
    this.companyScale = companyScale;
  }

  public String getCompanyCategory() {
    return companyCategory;
  }

  public void setCompanyCategory(String companyCategory) {
    this.companyCategory = companyCategory;
  }

  public String getCompanyAddress() {
    return companyAddress;
  }

  public void setCompanyAddress(String companyAddress) {
    this.companyAddress = companyAddress;
  }

  public String getCompanyContact() {
    return companyContact;
  }

  public void setCompanyContact(String companyContact) {
    this.companyContact = companyContact;
  }

  public Timestamp getAddtime() {
    return addtime;
  }

  public void setAddtime(Timestamp addtime) {
    this.addtime = addtime;
  }

  @Override
  public String toString() {
    return "AppointCompanyDetail{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", companyNature='" + companyNature + '\'' +
            ", companyScale='" + companyScale + '\'' +
            ", companyCategory='" + companyCategory + '\'' +
            ", companyAddress='" + companyAddress + '\'' +
            ", companyContact='" + companyContact + '\'' +
            ", addtime=" + addtime +
            '}';
  }
}
