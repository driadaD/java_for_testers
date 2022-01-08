package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHome();
        int before = app.getContactHelper().getContactCount();
        if(! app.getContactHelper().isThereContact()){
            app.getNavigationHelper().gotoAddNew();
            app.getContactHelper().createContact(new ContactData("Angry", "Birds", "preskot srit", "89990009900", "pochta@mail.ru", "test1"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().submitContactDelete();
        app.getNavigationHelper().gotoHome();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }

    @Test
    public void testAllContactDeletion() {
        app.getNavigationHelper().gotoHome();
        int before = app.getContactHelper().getContactCount();
        if(! app.getContactHelper().isThereContact()){
            app.getNavigationHelper().gotoAddNew();
            app.getContactHelper().createContact(new ContactData("Angry", "Birds", "preskot srit", "89990009900", "pochta@mail.ru", "test1"));
            app.getNavigationHelper().gotoAddNew();
            app.getContactHelper().createContact(new ContactData("Angry2", "Birds2", "preskot srit", "89990009900", "pochta@mail.ru", "test1"));
        }
        app.getContactHelper().selectAllContacts();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().submitContactDelete();
        app.getNavigationHelper().gotoHome();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before-before);
    }
}
