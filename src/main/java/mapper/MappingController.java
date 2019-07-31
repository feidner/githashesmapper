package mapper;

import org.apache.commons.logging.LogFactory;
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
        LogFactory.getLog(getClass().getSimpleName()).info("sequentialNumber: " + hash);
        return mapperInterface.sequentialNumber(hash);
    }

    @PostMapping(path= "/gitlab")
    public void gitData(HttpEntity<String> requestEntity) throws IOException {
        String gitLabJsonData = requestEntity.getBody();
        LogFactory.getLog(getClass().getSimpleName()).info("gitlab");
        mapperInterface.saveGitLabData(gitLabJsonData);
    }
}