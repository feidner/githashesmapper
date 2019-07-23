package mapper;

import org.junit.jupiter.api.Test;
import javax.inject.Inject;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MapperServiceTest {

    private MapperService mapperService = new MapperService();

    @Test
    void test1() {
        String s = "{" +
            "  \"object_kind\": \"push\"," +
            "  \"event_name\": \"push\"," +
            "  \"before\": \"768b109afc290b10d7f7dc544b89cdf0ac0463c4\"," +
            "  \"after\": \"57fbc7941adb5241a2459699086efc321074795c\"," +
            "  \"ref\": \"refs/heads/master\"," +
            "  \"checkout_sha\": \"57fbc7941adb5241a2459699086efc321074795c\"," +
            "  \"message\": null," +
            "  \"user_id\": 2924," +
            "  \"user_name\": \"henrik\"" +
            "}";
        assertEquals("57fbc7941adb5241a2459699086efc321074795c", mapperService.getHash(s), "successfully created hash");
    }

    @Test
    void test2() throws IOException {
        mapperService.clearFile();
        String data1 = "{" +
            "  \"object_kind\": \"push\"," +
            "  \"event_name\": \"push\"," +
            "  \"before\": \"768b109afc290b10d7f7dc544b89cdf0ac0463c4\"," +
            "  \"after\": \"17fbc7941adb5241a2459699086efc321074795c\"," +
            "  \"ref\": \"refs/heads/master\"," +
            "  \"checkout_sha\": \"17fbc7941adb5241a2459699086efc321074795c\"," +
            "  \"message\": null," +
            "  \"user_id\": 2924," +
            "  \"user_name\": \"henrik\"" +
            "}";
        String data2 = "{" +
            "  \"object_kind\": \"push\"," +
            "  \"event_name\": \"push\"," +
            "  \"before\": \"768b109afc290b10d7f7dc544b89cdf0ac0463c4\"," +
            "  \"after\": \"27fbc7941adb5241a2459699086efc321074795c\"," +
            "  \"ref\": \"refs/heads/master\"," +
            "  \"checkout_sha\": \"27fbc7941adb5241a2459699086efc321074795c\"," +
            "  \"message\": null," +
            "  \"user_id\": 2924," +
            "  \"user_name\": \"henrik\"" +
            "}";
        String data3 = "{" +
            "  \"object_kind\": \"push\"," +
            "  \"event_name\": \"push\"," +
            "  \"before\": \"768b109afc290b10d7f7dc544b89cdf0ac0463c4\"," +
            "  \"after\": \"37fbc7941adb5241a2459699086efc321074795c\"," +
            "  \"ref\": \"refs/heads/master\"," +
            "  \"checkout_sha\": \"37fbc7941adb5241a2459699086efc321074795c\"," +
            "  \"message\": null," +
            "  \"user_id\": 2924," +
            "  \"user_name\": \"henrik\"" +
            "}";
        mapperService.gitData(data1);
        mapperService.gitData(data2);
        mapperService.gitData(data3);
    }
}