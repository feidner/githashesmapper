package mapper;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Named
@Resource(name = "FileMapperService")
public class FileMapperService implements MapperService {
    private static final String FILE_NAME = "map.json";
    private Map<String, Long> gitMap = new HashMap<>();
    private long maximumNumber;

    public FileMapperService() throws IOException {
        load();
    }

    @Override
    public void saveGitLabData(String gitLabData) throws IOException {
        maximumNumber++;
        gitMap.put(getHash(gitLabData), maximumNumber);
        save();
    }

    @Override
    public Long sequentialNumber(String hash) {
        return gitMap.getOrDefault(hash, -1L);
    }

    private void save() throws IOException {
        LogFactory.getLog(getClass().getSimpleName()).info("saving");
        List<HashToNumber> hashes = gitMap.keySet().stream().map(key -> new HashToNumber(key, gitMap.get(key))).collect(Collectors.toList());
        FileUtils.writeStringToFile(FileUtils.getFile(FILE_NAME), new Gson().toJson(new HashToNumbers(hashes)), (String)null);
    }

    private void load() throws IOException {
        LogFactory.getLog(getClass().getSimpleName()).info("loading");
        File contentFile = FileUtils.getFile(FILE_NAME);
        if(contentFile.exists()) {
            String fileContent = FileUtils.readFileToString(contentFile, (String) null);
            HashToNumbers hashes = new Gson().fromJson(fileContent, HashToNumbers.class);
            hashes = hashes == null ? new HashToNumbers(new ArrayList<>()) : hashes;
            hashes.getHashToNumberList().forEach(hash -> gitMap.put(hash.getHash(), hash.getNumber()));
        }
        maximumNumber = gitMap.values().stream().max(Comparator.naturalOrder()).orElse(0L);
    }

    static String getHash(String s) {
        return new Gson().fromJson(s, GitLabData.class).getAfter();
    }

    void clearFile() {
        LogFactory.getLog(getClass().getSimpleName()).info("clearing file");
        FileUtils.deleteQuietly(FileUtils.getFile(FILE_NAME));
        maximumNumber = 0;
    }

}