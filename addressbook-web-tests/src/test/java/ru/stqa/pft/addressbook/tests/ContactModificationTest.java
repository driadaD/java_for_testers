package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHome();
        if(! app.getContactHelper().isThereContact()){
            app.getNavigationHelper().gotoAddNew();
            app.getContactHelper().createContact(new ContactData("Angry", "Birds", "preskot srit", "89990009900", "pochta@mail.ru", "test1"), true);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Angry2", "Birds2", "preskot srit", "89990009900", "pochta@mail.ru", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHome();
    }
}
