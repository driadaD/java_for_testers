package ru.stqa.pft.addressbook;

public class ContactGroup {
    private final String firstname;
    private final String lastname;
    private final String adress;
    private final String mobilephone;
    private final String email;

    public ContactGroup(String firstname, String lastname, String adress, String mobilephone, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.adress = adress;
        this.mobilephone = mobilephone;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAdress() {
        return adress;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public String getEmail() {
        return email;
    }
}
