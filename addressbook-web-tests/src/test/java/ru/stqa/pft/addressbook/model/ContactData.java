package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {

    @Id
    @Column(name = "id")
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;

    @Column(name = "firstname")
    @Expose
    private String firstname = "";

    @Column(name = "middlename")
    @Expose
    private String middlename = "";

    @Column(name = "lastname")
    @Expose
    private String lastname = "";

    @Column(name = "nickname")
    private String nickname = "";

    @Column(name = "company")
    private String company = "";

    @Column(name = "title")
    private String title = "";

    @Column(name = "address")
    @Type(type = "text")
    @Expose
    private String address = "";

    @Column(name = "home")
    @Type(type = "text")
    private String home_phone = "";

    @Column(name = "mobile")
    @Type(type = "text")
    @Expose
    private String mobile_phone = "";

    @Column(name = "work")
    @Type(type = "text")
    private String work_phone = "";

    @Column(name = "fax")
    @Type(type = "text")
    private String fax_phone = "";

    @Column(name = "email")
    @Type(type = "text")
    private String email = "";

    @Column(name = "email2")
    @Type(type = "text")
    private String email2 = "";

    @Column(name = "email3")
    @Type(type = "text")
    private String email3 = "";

    @Column(name = "homepage")
    @Type(type = "text")
    private String homePage = "";

    @Column(name = "address2")
    @Type(type = "text")
    private String address2 = "";

    @Column(name = "phone2")
    @Type(type = "text")
    private String phone2 = "";

    @Column(name = "notes")
    @Type(type = "text")
    private String notes = "";

    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @Transient
    private String all_phones;

    @Transient
    private String all_email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", home_phone='" + home_phone + '\'' +
                ", mobile_phone='" + mobile_phone + '\'' +
                ", work_phone='" + work_phone + '\'' +
                ", secondary_home_phone='" + phone2 + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", all_phones='" + all_phones + '\'' +
                ", all_email='" + all_email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(middlename, that.middlename) && Objects.equals(lastname, that.lastname) && Objects.equals(address, that.address) && Objects.equals(home_phone, that.home_phone) && Objects.equals(mobile_phone, that.mobile_phone) && Objects.equals(work_phone, that.work_phone) && Objects.equals(phone2, that.phone2) && Objects.equals(email, that.email) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3) && Objects.equals(all_phones, that.all_phones) && Objects.equals(all_email, that.all_email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, middlename, lastname, address, home_phone, mobile_phone, work_phone, phone2, email, email2, email3, all_phones, all_email);
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return home_phone;
    }

    public String getMobilePhone() {
        return mobile_phone;
    }

    public String getWorkPhone() {
        return work_phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getAllPhones() {
        return all_phones;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmail() {
        return all_email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public String getTitle() {
        return title;
    }

    public String getFaxPhone() {
        return fax_phone;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getAddress2() {
        return address2;
    }

    public String getNotes() {
        return notes;
    }

    public File getPhoto() {
        if (photo != null) {
            return new File(photo);
        } else {
            return null;
        }
    }


    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String home_phone) {
        this.home_phone = home_phone;
        return this;
    }

    public ContactData withMobilePhone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
        return this;
    }

    public ContactData withPhone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public ContactData withWorkPhone(String work_phone) {
        this.work_phone = work_phone;
        return this;
    }

    public ContactData withAllPhones(String all_phones) {
        this.all_phones = all_phones;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmail(String all_email) {
        this.all_email = all_email;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withFaxPhone(String fax_phone) {
        this.fax_phone = fax_phone;
        return this;
    }

    public ContactData withHomePage(String homePage) {
        this.homePage = homePage;
        return this;
    }

    public ContactData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public ContactData withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
