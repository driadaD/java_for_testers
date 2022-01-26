package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.util.List;

public class UsersHelper  extends HelperBase{

    public UsersHelper(ApplicationManager app) {
        super(app);
    }

    public void editUser(UserData user) {
        click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + user.getId() + "']"));
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void confirmPassword(String confirmationLink, String newPassword) {
        wd.get(confirmationLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("input[value='Update User']"));
    }

    public String findConfirmationLink(List<MailMessage> mailMessages, String email, String type) {
        MailMessage mailMessage = mailMessages.get(0);

        if (type.equals("registration")) {
            mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).filter((m) -> m.text.contains("Thank you for registering")).findFirst().get();
        } else if (type.equals("password_changing")) {
            mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).filter((m) -> m.text.contains("requested a password change")).findFirst().get();
        }

        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
