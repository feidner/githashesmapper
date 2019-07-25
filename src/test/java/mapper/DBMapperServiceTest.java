package mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Hash hash = service.findById("vader").get();
        service.save(new Hash(hash.getHash(), 4000L));
        service.gitData("{ \"after\": \"jabba\" }");
        assertEquals(4001L, service.sequentialNumber("jabba"));
    }

}