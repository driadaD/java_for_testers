package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactFromGroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().home();
            app.goTo().addNew();
            app.contact().create(new ContactData().withFirstname("Harry").withLastname("Potter").withAddress("Privet Drive")
                    .withHomePhone("555").withMobilePhone("666").withWorkPhone("777").withEmail("byklay@mail.ru"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (app.db().availableContactsToDeleteFromGroup().size() == 0){
            ContactData contact = app.db().availableContactsToAddToGroup().iterator().next();
            GroupData group = app.db().availableGroupsForAddAContact(contact).iterator().next();

            app.contact().addContactToGroup(contact,group);
        }
        app.goTo().home();
    }

    @Test
    public void contactFromGroupDeletion(){
        app.goTo().home();

        ContactData before = app.db().availableContactsToDeleteFromGroup().iterator().next();
        GroupData group = before.getGroups().iterator().next();

        app.contact().deleteContactFromGroup(before, group);

        ContactData after = app.db().getContactWithId(before.getId());
        assertThat(after.getGroups().size(), equalTo(before.getGroups().size() - 1));
        assertThat(after.getGroups(), not(hasItem(group)));
    }
}
