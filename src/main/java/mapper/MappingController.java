package mapper;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {

    public MappingController() {
        super();
    }

    //@RequestMapping(value = {"/gitlab"}, method = RequestMethod.POST)
    @PostMapping(path= "/gitlab")
    public void gitData(HttpEntity<String> requestEntity) {
        String gitLabJsonData = requestEntity.getBody();
    }

    @RequestMapping({"/sequentialNumber/{hash}"})
    public String squentialNumber(@PathVariable(value = "hash") String hash) {
        return "murks";
    }
}
