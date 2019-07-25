package mapper;

import java.io.IOException;

public interface MapperInterface {
    void gitData(String data) throws IOException;
    Long sequentialNumber(String hash);
}