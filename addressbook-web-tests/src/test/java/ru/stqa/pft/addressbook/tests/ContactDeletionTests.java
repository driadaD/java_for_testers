package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().home();
        if (app.contact().list().size() == 0) {
            app.goTo().addNew();
            app.contact().create(new ContactData().withFirstname("Angry").withLastname("Birds").withAddress("preskot srit").withMobilephone("89990009900").withEmail("pochta@mail.ru").withGroup("test1"));
        }
    }

    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        app.goTo().home();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

    @Test(enabled = false)
    public void testAllContactDeletion() {
        app.goTo().home();
        if (!app.contact().isThereContact()) {
            app.goTo().addNew();
            app.contact().create(new ContactData().withFirstname("Angry").withLastname("Birds").withAddress("preskot srit").withMobilephone("89990009900").withEmail("pochta@mail.ru").withGroup("test1"));
            app.goTo().addNew();
            app.contact().create(new ContactData().withFirstname("Angry2").withLastname("Birds2").withAddress("preskot srittt").withMobilephone("89990009900").withEmail("pochta@mail.ru").withGroup("test1"));
        }
        List<ContactData> before = app.contact().list();
        app.contact().selectAllContacts();
        app.contact().deleteSelectedContacts();
        app.contact().submitContactDelete();
        app.goTo().home();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - before.size());
    }
}
