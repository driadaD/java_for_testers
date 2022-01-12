package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactFieldsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().home();
        if (app.contact().all().size() == 0) {
            app.goTo().addNew();
            app.contact().create(new ContactData().withFirstname("Harry").withLastname("Potter").withAddress("Privet Drive").withHomePhone("555").withMobilePhone("666").withWorkPhone("777").withSecondary_home_phone("1122334455").withEmail("byklay@mail.ru").withEmail2("nimbys_2000@mail.ru").withGroup("test1"));
        }
    }

    @Test
    public void testContactFields() {
        app.goTo().home();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmail(), equalTo(mergeEmail(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(cleanedAddress(contactInfoFromEditForm.getAddress())));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getSecondary_home_phone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactFieldsTests::cleanedPhones)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmail(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactFieldsTests::cleanedEmail)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedPhones(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    public static String cleanedEmail(String email) {
        return email.replaceAll("^\\s*|\\s*$", "").replaceAll("\\s{2,}", " ");
    }

    public static String cleanedAddress(String address) {
        return address
                .replaceAll("\\s*$", "")
                .replaceAll("\\s+\\n","\n")
                .replaceAll("\\n\\s+","\n")
                .replaceAll("\\s{2,}"," ");
    }
}
