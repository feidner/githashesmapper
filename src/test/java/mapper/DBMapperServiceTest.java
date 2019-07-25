package mapper;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {GitLabHashMappingApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DBMapperServiceTest {

    @Inject
    private DBMapperService service;

    @BeforeEach
    void setup() {
        service.deleteAll();
        service.gitData("{ \"after\": \"vader\" }");
        service.gitData("{ \"after\": \"hansolo\" }");
        service.gitData("{ \"after\": \"luke\" }");
    }

    @Test
    void sequentialNumber_InsertSomeDataSetsAndAskNotExistingHash_ThenResultIsMinus1() {
        assertEquals(-1, service.sequentialNumber("woodo"));
    }


    @Test
    void sequentialNumber_InsertSomeDataSetsAndSaveVaderWithNumber4000AndAddNewDataSet_ThenNewDataSetHasNumber4001() {
        Hash hash = service.findById("vader").orElseThrow();
        service.save(new Hash(hash.getHash(), 4000L));
        service.gitData("{ \"after\": \"jabba\" }");
        assertEquals(4001L, service.sequentialNumber("jabba"));
    }

    @Test
    void sequentialNumber_InsertMassiveDataSets_ThenSaveTimeDontExceed() {
        org.apache.logging.log4j.core.Logger logger = (Logger) LogManager.getLogger(MapperService.class.getSimpleName());
        logger.setLevel(Level.WARN);
        long smallest = -1;
        for (int i = 4; i < 1999; i++) {
            long millis = System.currentTimeMillis();
            service.gitData(String.format("{ \"after\": \"jabba_%s\" }", i));
            long diff = System.currentTimeMillis() - millis;
            if(i % 100 == 0) {
                LogManager.getLogger(getClass().getSimpleName()).info(String.format("%s: smallest: %s, diff: %s", i, smallest, diff));
            }
            long finalSmallest = smallest;
            int finalI = i;
            assertTrue(smallest == -1 || (diff - (3*smallest)) < 200, () -> String.format("%s: smallest: %s, diff: %s", finalI, finalSmallest, diff));
            if(smallest == -1 || diff < smallest) {
                smallest = diff;
            }
        }
    }

}