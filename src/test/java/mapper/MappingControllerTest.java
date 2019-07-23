package mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {GitLabHashMappingApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MappingControllerTest {

    @Inject
    private TestRestTemplate restTemplate;

    private MapperService mapperService = new MapperService();

    @Test
    void restController_SendGitLabJsonDataWithAfterHash_Then() throws IOException{
        mapperService.clearFile();
        String gitLabPushEvent = "{" +
                "  \"object_kind\": \"push\"," +
                "  \"event_name\": \"push\"," +
                "  \"before\": \"768b109afc290b10d7f7dc544b89cdf0ac0463c4\"," +
                "  \"after\": \"462ab7b6facbc241a2759897086eec320d74895f\"," +
                "  \"ref\": \"refs/heads/master\"," +
                "  \"checkout_sha\": \"462ab7b6facbc241a2759897086eec320d74895f\"," +
                "  \"message\": null," +
                "  \"user_id\": 2924," +
                "  \"user_name\": \"henrik\"" +
                "}";
        Entity<String> gitLabContent = Entity.json(gitLabPushEvent);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(gitLabPushEvent, headers);
        ResponseEntity<String> response = restTemplate.exchange("/gitlab", HttpMethod.POST, entity, String.class);

        ResponseEntity<String> result = restTemplate.getForEntity("/sequentialNumber/{hash}", String.class, "462ab7b6facbc241a2759897086eec320d74895f");
        String body = result.getBody();
        assertEquals("1", body);
    }
}