package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void manageUsers() {
        if(!isElementPresent(By.cssSelector("a[href='/mantisbt-1.2.19/manage_user_page.php']"))){
            click(By.cssSelector("/mantisbt-1.2.19/manage_overview_page.php"));
        }
        click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_user_page.php']"));
    }
}
