package mapper;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.GsonTester;

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
        assertEquals("57fbc7941adb5241a2459699086efc321074795c", mapperService.getHash(s));
        System.out.println("Test 1 passed\n");
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
            "  \"checkout_sha\": \"37fbc7941adb5241a2459699086efc321074795c\"," +
            "  \"after\": \"37fbc7941adb5241a2459699086efc321074795c\"," +
            "  \"ref\": \"refs/heads/master\"," +
            "  \"message\": null," +
            "  \"user_id\": 2924," +
            "  \"user_name\": \"henrik\"" +
            "}";
        mapperService.gitData(data1);
        mapperService.gitData(data2);
        mapperService.gitData(data3);

        assertEquals(1, mapperService.sequentialNumber("17fbc7941adb5241a2459699086efc321074795c"));
        assertEquals(2, mapperService.sequentialNumber("27fbc7941adb5241a2459699086efc321074795c"));
        assertEquals(3, mapperService.sequentialNumber("37fbc7941adb5241a2459699086efc321074795c"));
        System.out.println("Test 2 passed\n");
    }
}