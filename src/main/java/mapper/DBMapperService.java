package mapper;

import org.springframework.data.repository.CrudRepository;

public interface DBMapperService extends CrudRepository<Hash, String> {
}
