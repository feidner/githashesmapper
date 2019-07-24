package mapper;

import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
public class MappingController {

    private final MapperService mapperService;

    public MappingController(MapperService mapperService) {
        this.mapperService = mapperService;
    }

    @RequestMapping(value = {"/sequentialNumber/{hash}"}, method = RequestMethod.GET)
    public Long sequentialNumber(@PathVariable(value = "hash") String hash) {
        LogManager.getLogger(getClass().getSimpleName()).info("sequentialNumber");
        return mapperService.sequentialNumber(hash);
    }

    @PostMapping(path= "/gitlab")
    public void gitData(HttpEntity<String> requestEntity) throws IOException {
        LogManager.getLogger(getClass().getSimpleName()).info("gitData");
        String gitLabJsonData = requestEntity.getBody();
        mapperService.gitData(gitLabJsonData);
    }

}