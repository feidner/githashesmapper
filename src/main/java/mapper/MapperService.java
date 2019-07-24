package mapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.inject.Named;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Named
public class MapperService {
    private Map<String, Long> gitMap = new HashMap<>();
    private long nr = 1;

    void gitData(String data) throws IOException {
        load();
        gitMap.put(getHash(data), nr);
        nr++;
        save();
    }

    Long sequentialNumber(String hash) {
        return gitMap.get(hash);
    }

    private void save() throws IOException {
        System.out.println("saving");
        StringBuilder erg = new StringBuilder();
        List<Hash> hashes = gitMap.keySet().stream().map(key -> new Hash(key, gitMap.get(key))).collect(Collectors.toList());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("map.json"))) {
            bw.write(objectToJson(new Hashes(hashes)));
        }
    }

    private void load() throws IOException {
        System.out.println("loading");
        StringBuilder ss = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("map.json"))) {

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                /*int leer = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (line.substring(i, i + 1).equals(" ")) leer = i;
                }
                gitMap.put(line.substring(0, leer), Long.parseLong(line.substring(leer + 1)));
                nr = Long.parseLong(line.substring(leer + 1)) + 1;*/
                ss.append(line);
            }
        }

        if (jsonToObject(ss.toString()) != null) {
            Hashes hashes = jsonToObject(ss.toString());
            hashes.getHashes().forEach(hash -> gitMap.put(hash.getHash(), Long.parseLong(hash.getNumber())));
        }
    }

    String getHash(String s) {
        System.out.println("creating hash");

        //Variante 1
        /*String erg = "";
        for (int i = 0; i < s.length() - 5; i++) {
            if (s.substring(i, i + 5).equals("after")) erg = s.substring(i + 9, i + 49);
        }
        return erg;*/

        //Variante 2
        GitLabData o = new Gson().fromJson(s, GitLabData.class);
        return o.getAfter();
    }

    void clearFile() throws IOException {
        System.out.println("clearing file");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("map.json"))) {
            bw.write("");
        }
    }

    private Hashes jsonToObject(String json){
        return new Gson().fromJson(json, Hashes.class);
    }
    private String objectToJson(Object o){
        Gson gson = new Gson();
        String s = gson.toJson(o);
        return s;
    }
}