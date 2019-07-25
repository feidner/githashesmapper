package mapper;

import org.springframework.data.repository.CrudRepository;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Resource(name = "DBMapperService")
public interface DBMapperService extends CrudRepository<Hash, String>, MapperInterface{
    List<Long> lst = new ArrayList<>();

    @Override
    default void gitData(String data) {
        String hash = MapperService.getHash(data);
        Long number = sequentialNumber(hash);
        save(new Hash(hash, number));
    }

    @Override
    default Long sequentialNumber(String hash) {
        return findById(hash).map(Hash::getNumber).orElse(hoechsteNummer());
    }

    default Long hoechsteNummer(){
        findAll().forEach(value -> lst.add(value.getNumber()));
        if (lst.size() > 0) {
            lst.sort(Comparator.naturalOrder());
            return lst.get(lst.size() - 1);
        } else {
            return 1L;
        }
    }
}