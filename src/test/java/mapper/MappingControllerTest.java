package mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import javax.inject.Inject;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {GitLabHashMappingApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MappingControllerTest {

    @Inject
    private TestRestTemplate restTemplate;

    @Inject
    private MapperService mapperService;

    @Inject
    private DBMapperService dbMapperService;

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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(gitLabPushEvent, headers);
        restTemplate.exchange("/gitlab", HttpMethod.POST, entity, String.class);

        ResponseEntity<String> result = restTemplate.getForEntity("/sequentialNumber/{hash}", String.class, "462ab7b6facbc241a2759897086eec320d74895f");
        String body = result.getBody();
        assertEquals("1", body);
    }

    @Test
    void dataBaseInsertTest(){
        dbMapperService.deleteAll();
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(gitLabPushEvent, headers);
        ResponseEntity<String> result = restTemplate.exchange("/gitlab", HttpMethod.POST, entity, String.class);
        assertSame(result.getStatusCode(), HttpStatus.OK);
        assertTrue(dbMapperService.existsById("462ab7b6facbc241a2759897086eec320d74895f"));
    }
}