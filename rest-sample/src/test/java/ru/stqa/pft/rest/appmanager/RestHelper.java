package ru.stqa.pft.rest.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

public class RestHelper {

    private final ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get(String.format("%s/issues.json", app.getProperty("restBugify.url")))).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");

        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post(String.format("%s/issues.json", app.getProperty("restBugify.url")))
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())
                )).returnContent().asString();
        JsonElement parced = new JsonParser().parse(json);
        return parced.getAsJsonObject().get("issue_id").getAsInt();
    }

    public Issue getIssueById(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(String.format("%s/issues/%s.json", app.getProperty("restBugify.url"), issueId))).returnContent().asString();
        JsonElement parced = new JsonParser().parse(json);
        JsonElement issues = parced.getAsJsonObject().get("issues");

        Set<Issue> issue = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());

        return issue.iterator().next();
    }
}
