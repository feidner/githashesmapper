package mocking;

import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.client.JerseyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class GitLabCommit {

    public static void main(String[] args) throws IOException {
        Client client = JerseyClientBuilder.createClient();
        WebTarget target = client.target("http://localhost:8080").path("gitlab");
        String json = String.join("\n", FileUtils.readLines(new File(GitLabCommit.class.getResource("/gitlab.data.json").getFile()), Charset.defaultCharset()));
        target.request().post(Entity.json(json));
    }
}
