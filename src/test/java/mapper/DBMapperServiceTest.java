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
        service.saveGitLabData("{ \"after\": \"vader\" }");
        service.saveGitLabData("{ \"after\": \"hansolo\" }");
        service.saveGitLabData("{ \"after\": \"luke\" }");
    }

    @Test
    void sequentialNumber_InsertSomeDataSetsAndAskNotExistingHash_ThenResultIsMinus1() {
        assertEquals(-1, service.sequentialNumber("woodo"));
    }


    @Test
    void sequentialNumber_InsertSomeDataSetsAndSaveVaderWithNumber4000AndAddNewDataSet_ThenNewDataSetHasNumber4001() {
        HashToNumber hash = service.findById("vader").orElseThrow();
        service.save(new HashToNumber(hash.getHash(), 4000L));
        service.saveGitLabData("{ \"after\": \"jabba\" }");
        assertEquals(4001L, service.sequentialNumber("jabba"));
    }

    @Test
    void sequentialNumber_InsertMassiveDataSets_ThenSaveTimeDontExceed() {
        org.apache.logging.log4j.core.Logger logger = (Logger) LogManager.getLogger(FileMapperService.class.getSimpleName());
        logger.setLevel(Level.WARN);
        long smallest = -1, allTime = 0L;
        for (int i = 4; i < 1999; i++) {
            long saveTime = System.currentTimeMillis();
            service.saveGitLabData(String.format("{ \"after\": \"jabba_%s\" }", i));
            saveTime = System.currentTimeMillis() - saveTime;
            allTime += saveTime;
            long averageTime = allTime/(i-3);
            if(i % 100 == 0) {
                LogManager.getLogger(getClass().getSimpleName()).info(String.format("%s: smallest: %s, averageTime: %s, saveTime: %s",
                        i, smallest, averageTime, saveTime));
            }
            long finalSmallest = smallest;
            int finalI = i;
            assertTrue(smallest == -1 || (averageTime - smallest) < (3*smallest),
                    () -> String.format("Das Abspeichern dauert nun 3mal so lange .... %s: smallest: %s, averageTime: %s", finalI, finalSmallest, averageTime));
            if(smallest == -1 || saveTime < smallest) {
                smallest = saveTime;
            }
        }
    }

}