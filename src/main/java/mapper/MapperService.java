package mapper;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import javax.inject.Named;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Named
public class MapperService {
    private Map<String, Long> gitMap = new HashMap<>();
    private long number;

    public MapperService() throws IOException {
        load();
    }

    void gitData(String data) throws IOException {
        number++;
        gitMap.put(getHash(data), number);
        save();
    }

    Long sequentialNumber(String hash) {
        return gitMap.get(hash);
    }

    private void save() throws IOException {
        LogManager.getLogger(getClass().getSimpleName()).info("saving");
        List<Hash> hashes = gitMap.keySet().stream().map(key -> new Hash(key, gitMap.get(key))).collect(Collectors.toList());
        FileUtils.writeStringToFile(FileUtils.getFile("map.json"), objectToJson(new Hashes(hashes)), (String)null);
    }

    private void load() throws IOException {
        LogManager.getLogger(getClass().getSimpleName()).info("loading");
        String ss = FileUtils.readFileToString(FileUtils.getFile("map.json"), (String)null);

        Hashes hashes = new Gson().fromJson(ss, Hashes.class);
        hashes = hashes == null ? new Hashes(new ArrayList<>()) : hashes;
        hashes.getHashes().forEach(hash -> gitMap.put(hash.getHash(), Long.parseLong(hash.getNumber())));
        number = gitMap.values().stream().max(Comparator.naturalOrder()).orElse(0L);
    }

    String getHash(String s) {
        LogManager.getLogger(getClass().getSimpleName()).info("creating hash");
        GitLabData o = new Gson().fromJson(s, GitLabData.class);
        return o.getAfter();
    }

    void clearFile() throws IOException {
        LogManager.getLogger(getClass().getSimpleName()).info("clearing file");
        FileUtils.writeStringToFile(FileUtils.getFile("map.json"), "", (String)null);
        number = 0;
    }

    private String objectToJson(Object o){
        Gson gson = new Gson();
        return gson.toJson(o);
    }
}