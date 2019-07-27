package mapper;

import java.io.IOException;

public interface MapperService {
    void saveGitLabData(String gitLabData) throws IOException;
    Long sequentialNumber(String hash);
}