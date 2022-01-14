package ru.stqa.pft.addressbook.model;

import java.io.File;
import java.util.Objects;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String lastname;
    private String address;
    private String home_phone;
    private String mobile_phone;
    private String work_phone;
    private String secondary_home_phone;
    private String all_phones;
    private String email;
    private String email2;
    private String email3;
    private String all_email;
    private String group;
    private File photo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public String getFirstname() {
        return firstname;
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

    public String getSecondary_home_phone() {
        return secondary_home_phone;
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

    public File getPhoto() {
        return photo;
    }

    public String getGroup() {
        return group;
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

    public ContactData withSecondary_home_phone(String secondary_home_phone) {
        this.secondary_home_phone = secondary_home_phone;
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

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
}
