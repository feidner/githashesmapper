package mapper;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MapperServiceTest {

    private MapperService mapperService;

    @BeforeEach
    void setup() throws IOException {
        mapperService = new MapperService();
    }

    @Test
    void listCompare() {
        List<Integer> values = new ArrayList<>(List.of(3,2,8,56,1));
        values.sort(Comparator.comparingInt(o -> o));
        assertEquals(List.of(1,2,3,8,56), values);
    }

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
        LogManager.getLogger(getClass().getSimpleName()).info("Test 1 passed\n");
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
        LogManager.getLogger(getClass().getSimpleName()).info("Test 2 passed\n");
    }
}