package mapper;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
public class MappingController {

    private final MapperService mapperService;
    private DBMapperService dbMapperService;

    public MappingController(MapperService mapperService, DBMapperService dbMapperService) {
        this.mapperService = mapperService;
        this.dbMapperService = dbMapperService;
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

    @GetMapping(path= "/db")
    public void insert(HttpEntity<String> requestEntity) {
        GitLabData o = new Gson().fromJson(requestEntity.getBody(), GitLabData.class);
        String hash = o.getAfter();
        Long number = mapperService.sequentialNumber(hash);
        dbMapperService.save(new Hash(hash, number));
    }
    @GetMapping(path= "/db/{hash}/{number}")
    public void insert(@PathVariable(value = "hash") String hash, @PathVariable(value = "number") Long number) {
        dbMapperService.save(new Hash(hash, number));
    }
}