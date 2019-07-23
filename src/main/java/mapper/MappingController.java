package mapper;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;

@RestController
public class MappingController {

    @Inject
    private MapperService mapperService;

    @RequestMapping(value = {"/sequentialNumber/{hash}"}, method = RequestMethod.GET)
    public Long sequentialNumber(@PathVariable(value = "hash") String hash) {
        System.out.println("sequentialNumber");
        return mapperService.sequentialNumber(hash);
    }

    @PostMapping(path= "/gitlab")
    public void gitData(HttpEntity<String> requestEntity) throws IOException {
        System.out.println("gitData");
        String gitLabJsonData = requestEntity.getBody();
        mapperService.gitData(gitLabJsonData);
    }
}