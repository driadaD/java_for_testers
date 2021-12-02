package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactGroup;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHome();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactGroup("Angry", "Birds", "preskot srit", "89990009900", "pochta@mail.ru"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHome();
    }
}
