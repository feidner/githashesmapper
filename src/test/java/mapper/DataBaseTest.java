package mapper;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import org.junit.jupiter.api.Test;

class DataBaseTest {

    @Test
    void test() throws ManagedProcessException {
        DB db = DB.newEmbeddedDB(0);
        db.start();
        db.createDB("hash");

    }
}
