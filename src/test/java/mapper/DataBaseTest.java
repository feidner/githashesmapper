package mapper;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {GitLabHashMappingApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DataBaseTest {

    @Test
    void test() throws ManagedProcessException {

    }
}
