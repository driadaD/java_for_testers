package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void manageUsers() {
        if(!isElementPresent(By.xpath("/html/body/div[2]/p/span[1]/a"))){
            click(By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[7]"));
        }
        click(By.xpath("/html/body/div[2]/p/span[1]/a"));
    }
}