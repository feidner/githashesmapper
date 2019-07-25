package mapper;

import org.springframework.data.repository.CrudRepository;
import javax.annotation.Resource;

@Resource(name = "DBMapperService")
public interface DBMapperService extends CrudRepository<Hash, String>, MapperInterface{

    @Override
    default void gitData(String data) {
        String hash = MapperService.getHash(data);
        Long number = sequentialNumber(hash);
        save(new Hash(hash, number));
    }

    @Override
    default Long sequentialNumber(String hash) {
        return findById(hash).map(Hash::getNumber).orElse(0L);
    }
}