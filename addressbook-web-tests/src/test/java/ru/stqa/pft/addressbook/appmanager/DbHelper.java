package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }

    public Contacts availableContactsToAddToGroup() {
        Contacts contacts = contacts();
        Groups groups = groups();

        Contacts availableContacts = new Contacts();

        for (ContactData contact : contacts) {
            if (!contact.getGroups().equals(groups)) {
                availableContacts.add(contact);
            }
        }
        return availableContacts;
    }

    public Groups availableGroupsForAddAContact(ContactData contact) {
        Groups groups = groups();
        Groups availableGroups = new Groups();

        for(GroupData group: groups){
            if (!contact.getGroups().contains(group)) {
                availableGroups.add(group);
            }
        }
        return availableGroups;
    }

    public Contacts availableContactsToDeleteFromGroup(){
        Contacts contacts = contacts();

        Contacts availableContacts = new Contacts();

        for (ContactData contact: contacts){
            if(contact.getGroups().size() != 0){
                availableContacts.add(contact);
            }
        }
        return availableContacts;
    }

    public ContactData getContactWithId(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ContactData result = (ContactData) session.createQuery("from ContactData where deprecated = '0000-00-00' and id="+id).uniqueResult();
        session.getTransaction().commit();
        session.close();

        return result;
    }
}
