package mapper;

import java.util.List;

public class Hashes {
    private List<Hash> hashes;

    public Hashes(List<Hash> hashes) {
        this.hashes = hashes;
    }

    public List<Hash> getHashes() {
        return hashes;
    }

    public void setHashes(List<Hash> hashes) {
        this.hashes = hashes;
    }
}
