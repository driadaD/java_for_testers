package ru.stqa.pft.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() throws MessagingException, IOException {
        app.mail().start();
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);

        if (app.db().getAllUsers().size() == 0) {
            app.registration().start(user, email);
            List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
            String confirmationLink = app.users().findConfirmationLink(mailMessages, email, "registration");
            app.registration().finish(confirmationLink, password);
            app.session().logout();
        }

    }

    @Test
    public void testChangePassword() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, MessagingException {
        String newPassword = "newPassword";

        app.session().login("administrator", "root");
        app.goTo().manageUsers();

        UserData user = app.db().getAllUsers().iterator().next();
        app.users().editUser(user);
        app.users().resetPassword();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.users().findConfirmationLink(mailMessages, user.getEmail(), "password_changing");

        app.users().confirmPassword(confirmationLink, newPassword);

        assertTrue(app.newSession().login(user.getUsername(), newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
