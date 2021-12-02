package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactGroup;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoAddNew();
        app.getContactHelper().fillContactForm(new ContactGroup("Angry", "Birds", "preskot srit", "89990009900", "pochta@mail.ru"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
    }
}
