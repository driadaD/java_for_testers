package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().home();
        if (app.contact().all().size() == 0) {
            app.goTo().addNew();
            app.contact().create(new ContactData().withFirstname("Angry").withLastname("Birds").withAddress("preskot srit").withMobilephone("89990009900").withEmail("pochta@mail.ru").withGroup("test1"));
        }
    }

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Angry").withLastname("Birds").withAddress("preskot srit").withMobilephone("89990009900").withEmail("pochta@mail.ru");
        app.contact().modify(contact);
        app.goTo().home();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
