package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactGroup;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactGroup contactGroup) {
        type(By.name("firstname"), contactGroup.getFirstname());
        type(By.name("lastname"), contactGroup.getLastname());
        type(By.name("address"), contactGroup.getAdress());
        type(By.name("mobile"), contactGroup.getMobilephone());
        type(By.name("email"), contactGroup.getEmail());
    }
}
