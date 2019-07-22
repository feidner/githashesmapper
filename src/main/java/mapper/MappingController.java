package mapper;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    @PostMapping(path= "/gitlab")
    public void gitData(HttpEntity<String> requestEntity) {
        System.out.println("gitData");
        String gitLabJsonData = requestEntity.getBody();
    }

    @RequestMapping(value = {"/sequentialNumber/{hash}"}, method = RequestMethod.GET)
    public String squentialNumber(@PathVariable(value = "hash") String hash) {
        System.out.println("squentialNumber");
        return "murks";
    }
}
