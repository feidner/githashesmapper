package mapper;

import javax.inject.Named;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("map.txt"))) {
            StringBuilder erg = new StringBuilder();
            for (String key : gitMap.keySet()) {
                erg
                        .append(key)
                        .append(" ")
                        .append(gitMap.get(key))
                        .append("\n");
            }
            bw.write(erg.toString());
        }
    }

    private void load() throws IOException {
        System.out.println("loading");
        try (BufferedReader br = new BufferedReader(new FileReader("map.txt"))) {

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                int leer = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (line.substring(i, i + 1).equals(" ")) leer = i;
                }
                gitMap.put(line.substring(0, leer), Long.parseLong(line.substring(leer + 1)));
                nr = Long.parseLong(line.substring(leer + 1)) + 1;
            }
        }
    }

    String getHash(String s) {
        System.out.println("creating hash");
        String erg = "";
        for (int i = 0; i < s.length() - 5; i++) {
            if (s.substring(i, i + 5).equals("after")) erg = s.substring(i + 9, i + 49);
        }
        return erg;
    }

    void clearFile() throws IOException {
        System.out.println("clearing file");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("map.txt"))) {
            bw.write("");
        }
    }
}