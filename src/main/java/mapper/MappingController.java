package mapper;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import javax.inject.Named;
import java.io.IOException;

@RestController
public class MappingController {

    @Named
    private MapperService mapperInterface;

    public MappingController(@Qualifier("DBMapperService") MapperService mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @RequestMapping(value = {"/sequentialNumber/{hash}"}, method = RequestMethod.GET)
    public Long sequentialNumber(@PathVariable(value = "hash") String hash) {
        LogManager.getLogger(getClass().getSimpleName()).info("sequentialNumber");
        return mapperInterface.sequentialNumber(hash);
    }

    @PostMapping(path= "/gitlab")
    public void gitData(HttpEntity<String> requestEntity) throws IOException {
        LogManager.getLogger(getClass().getSimpleName()).info("gitData");
        String gitLabJsonData = requestEntity.getBody();
        mapperInterface.saveGitLabData(gitLabJsonData);
    }
}