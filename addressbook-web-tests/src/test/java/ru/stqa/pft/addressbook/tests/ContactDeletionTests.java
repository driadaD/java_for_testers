package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHome();
        if(! app.getContactHelper().isThereContact()){
            app.getNavigationHelper().gotoAddNew();
            app.getContactHelper().createContact(new ContactData("Angry", "Birds", "preskot srit", "89990009900", "pochta@mail.ru", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().submitContactDelete();
        app.getNavigationHelper().gotoHome();
    }

    @Test
    public void testAllContactDeletion() {
        app.getNavigationHelper().gotoHome();
        if(! app.getContactHelper().isThereContact()){
            app.getNavigationHelper().gotoAddNew();
            app.getContactHelper().createContact(new ContactData("Angry", "Birds", "preskot srit", "89990009900", "pochta@mail.ru", "test1"), true);
            app.getNavigationHelper().gotoAddNew();
            app.getContactHelper().createContact(new ContactData("Angry2", "Birds2", "preskot srit", "89990009900", "pochta@mail.ru", "test1"), true);
        }
        app.getContactHelper().selectAllContacts();
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().submitContactDelete();
        app.getNavigationHelper().gotoHome();
    }
}
