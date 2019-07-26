package mapper;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Resource(name = "DBMapperService")
public interface DBMapperService extends CrudRepository<Hash, String>, MapperInterface{
    List<Long> lst = new ArrayList<>();

    @Override
    default void gitData(String data) {
        String hash = MapperService.getHash(data);
        Long number = getNextSequentialNumber(hash);
        save(new Hash(hash, number));
    }

    @Override
    default Long sequentialNumber(String hash) {
        return findById(hash).map(Hash::getNumber).orElse(-1L);
    }

    default Long getNextSequentialNumber(String hash){
        return findById(hash).map(Hash::getNumber).orElseGet(() -> {
            Long hoechste = hoechsteNummer();
            return hoechste == null ? 1 : (hoechste +1);
        });
    }

    @Query(value = "select max(number) from hash", nativeQuery = true)
    Long hoechsteNummer();
}