package mapper;

import org.apache.commons.logging.LogFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource(name = "DBMapperService")
public interface DBMapperService extends CrudRepository<HashToNumber, String>, MapperService {
    @Override
    default void saveGitLabData(String gitLabData) {
        String hash = FileMapperService.getHash(gitLabData);
        Long number = getNextSequentialNumber(hash);
        LogFactory.getLog(getClass().getSimpleName()).info(String.format("save to DB: %s/%s", hash, number));
        save(new HashToNumber(hash, number));
    }

    @Override
    default Long sequentialNumber(String hash) {
        return findById(hash).map(HashToNumber::getNumber).orElse(-1L);
    }

    default Long getNextSequentialNumber(String hash){
        return findById(hash).map(HashToNumber::getNumber).orElseGet(() -> {
            Long maxNumber = maxNumber();
            return maxNumber == null ? 1 : (maxNumber +1);
        });
    }

    @Query(value = "select max(number) from hash_to_number", nativeQuery = true)
    Long maxNumber();
}