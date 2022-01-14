package ru.stqa.pft.addressbook.tests;

import com.sun.deploy.panel.ITreeNode;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContact(){
        List<Object[]> list = new ArrayList<Object[]>();
        File photo = new File("src/test/resources/stru.png");
        list.add(new Object[] {new ContactData().withFirstname("Harry").withLastname("Potter").withAddress("Privet Drive")
                .withHomePhone("555").withMobilePhone("666").withWorkPhone("777").
                withEmail("byklay@mail.ru").withGroup("test1").withPhoto(photo)});
        list.add(new Object[] {new ContactData().withFirstname("Hermione").withLastname("Granger").withAddress("London")
                .withHomePhone("000").withMobilePhone("999").withWorkPhone("888").
                withEmail("hermi@mail.ru").withGroup("test1")});
        list.add(new Object[] {new ContactData().withFirstname("Fred").withLastname("Weasley").withAddress("Nora")
                .withHomePhone("111").withMobilePhone("222").withWorkPhone("333").
                withEmail("fred@mail.ru").withGroup("test1")});
        return list.iterator();
    }

    @Test (dataProvider = "validContact")
    public void testContactCreation(ContactData contact) throws Exception {
        app.goTo().home();
        Contacts before = app.contact().all();
        app.goTo().addNew();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
