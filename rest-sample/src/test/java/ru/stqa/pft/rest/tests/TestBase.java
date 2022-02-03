package ru.stqa.pft.rest.tests;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.rest.appmanager.ApplicationManager;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;

public class TestBase {
    protected static final ApplicationManager app
            = new ApplicationManager();

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
    }

    boolean isIssueOpen(int issueId) throws IOException {
        Issue issue = app.rest().getIssueById(issueId);

        if (issue.getState_name().equals("Closed")||issue.getState_name().equals("Resolved")){
            return false;
        }
        else{
            return true;
        }
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
