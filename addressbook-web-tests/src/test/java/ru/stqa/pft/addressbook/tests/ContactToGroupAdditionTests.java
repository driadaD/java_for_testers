package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactToGroupAdditionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().home();
            app.goTo().addNew();
            app.contact().create(new ContactData().withFirstname("Harry").withLastname("Potter").withAddress("Privet Drive")
                    .withHomePhone("555").withMobilePhone("666").withWorkPhone("777").withEmail("byklay@mail.ru"));
        }
        if (app.db().groups().size() == 0 || app.db().availableContactsToAddToGroup().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().home();
    }

    @Test
    public void contactToGroupAddition() {
        app.goTo().home();

        ContactData before = app.db().availableContactsToAddToGroup().iterator().next();
        GroupData group = app.db().availableGroupsForAddAContact(before).iterator().next();

        app.contact().addContactToGroup(before,group);

        ContactData after = app.db().getContactWithId(before.getId());

        assertThat(after.getGroups().size(), equalTo(before.getGroups().size() + 1));
        assertThat(after.getGroups(), hasItem(group));
    }
}
