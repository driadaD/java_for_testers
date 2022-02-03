package ru.stqa.pft.rest.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(300);
        Set<Issue> oldIssues = app.rest().getIssues();
        Issue newIssue = new Issue().withSubject("Test Issue 03022022").withDescription("Test description 03022022");
        int issueId = app.rest().createIssue(newIssue);
        Issue issue = app.rest().getIssueById(issueId);
        Set<Issue> newIssues = app.rest().getIssues();
        oldIssues.add(newIssue.withId(issueId).withStatus(issue.getState_name()));
        assertEquals(newIssues, oldIssues);
    }

    @Test
    public void testIssueStatus() throws IOException {
        Issue issue = app.rest().getIssueById(701);

        System.out.println(isIssueOpen(701));
        System.out.println(issue);
    }
}


