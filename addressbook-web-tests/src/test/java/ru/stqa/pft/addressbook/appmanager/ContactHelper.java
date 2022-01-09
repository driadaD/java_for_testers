package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
    }

    public void selectAllContacts() {
        click(By.id("MassCB"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            try {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void submitContactDelete() {
        acceptAlert();
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void create(ContactData contact) {
        fillContactForm(contact, true);
        submitContactCreation();
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        submitContactDelete();
    }

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));

        for (WebElement element : elements) {
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath(".//td/input")).getAttribute("id"));

            contacts.add(new ContactData().withId(id).withFirstname(firstName).withLastname(lastName));
        }
        return contacts;
    }
}
